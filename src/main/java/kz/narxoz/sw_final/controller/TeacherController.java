package kz.narxoz.sw_final.controller;

import kz.narxoz.sw_final.dto.TeacherDto;
import kz.narxoz.sw_final.mapper.TeacherMapper;
import kz.narxoz.sw_final.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherDto> getAll() {
        return teacherService.getAll()
                .stream()
                .map(TeacherMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TeacherDto getById(@PathVariable Long id) {
        return TeacherMapper.toDto(teacherService.getById(id));
    }

    @PostMapping
    public TeacherDto create(@RequestBody TeacherDto dto) {
        return TeacherMapper.toDto(teacherService.create(TeacherMapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public TeacherDto update(@PathVariable Long id, @RequestBody TeacherDto dto) {
        return TeacherMapper.toDto(teacherService.update(id, TeacherMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}