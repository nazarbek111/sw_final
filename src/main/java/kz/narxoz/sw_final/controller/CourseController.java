package kz.narxoz.sw_final.controller;

import kz.narxoz.sw_final.entity.Course;
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
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public Course update(
            @PathVariable Long id,
            @RequestBody Course course
    ) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}