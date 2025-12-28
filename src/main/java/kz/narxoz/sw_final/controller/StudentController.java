package kz.narxoz.sw_final.controller;

import kz.narxoz.sw_final.dto.StudentDto;
import kz.narxoz.sw_final.mapper.StudentMapper;
import kz.narxoz.sw_final.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.getAll()
                .stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return StudentMapper.toDto(studentService.getById(id));
    }

    @PostMapping
    public StudentDto create(@RequestBody StudentDto dto) {
        return StudentMapper.toDto(studentService.create(StudentMapper.toEntity(dto)));
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto dto) {
        return StudentMapper.toDto(studentService.update(id, StudentMapper.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}