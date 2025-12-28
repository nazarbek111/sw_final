package kz.narxoz.sw_final.controller;

import kz.narxoz.sw_final.dto.TeacherDto;
import kz.narxoz.sw_final.entity.Teacher;
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
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public TeacherDto create(@RequestBody Teacher teacher) {
        return teacherService.create(teacher);
    }

    @PutMapping("/{id}")
    public TeacherDto update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.update(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}