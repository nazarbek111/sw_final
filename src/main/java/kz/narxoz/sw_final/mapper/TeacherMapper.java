package kz.narxoz.sw_final.mapper;

import kz.narxoz.sw_final.dto.CourseDto;
import kz.narxoz.sw_final.dto.TeacherDto;
import kz.narxoz.sw_final.entity.Course;
import kz.narxoz.sw_final.entity.Teacher;

import java.util.Collections;
import java.util.List;

public class TeacherMapper {

    public static TeacherDto toDto(Teacher t) {
        List<CourseDto> courses = (t.getCourses() == null)
                ? Collections.emptyList()
                : t.getCourses().stream()
                .map(c -> new CourseDto(c.getId(), c.getTitle(), c.getCode()))
                .toList();

        return new TeacherDto(
                t.getId(),
                t.getFirstName(),
                t.getLastName(),
                t.getEmail(),
                t.getCourses()
                        .stream()
                        .map(CourseMapper::toDto)
                        .toList()
        );
    }

    public static Teacher toEntity(TeacherDto d) {
        Teacher t = new Teacher();
        t.setId(d.getId());
        t.setFirstName(d.getFirstName());
        t.setLastName(d.getLastName());
        t.setEmail(d.getEmail());
        return t;
    }
}