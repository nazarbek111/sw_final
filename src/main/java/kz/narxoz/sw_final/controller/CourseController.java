package kz.narxoz.sw_final.controller;

import kz.narxoz.sw_final.dto.CourseDto;
import kz.narxoz.sw_final.mapper.CourseMapper;
import kz.narxoz.sw_final.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDto> getAll() {
        return courseService.getAll()
                .stream()
                .map(CourseMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable Long id) {
        return CourseMapper.toDto(courseService.getById(id));
    }

    @PostMapping
    public CourseDto create(@RequestBody CourseDto dto) {
        return CourseMapper.toDto(courseService.create(CourseMapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable Long id, @RequestBody CourseDto dto) {
        return CourseMapper.toDto(courseService.update(id, CourseMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}