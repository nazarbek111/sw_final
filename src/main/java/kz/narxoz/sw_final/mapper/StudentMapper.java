package kz.narxoz.sw_final.mapper;

import kz.narxoz.sw_final.dto.StudentDto;
import kz.narxoz.sw_final.entity.Course;
import kz.narxoz.sw_final.entity.Student;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDto toDto(Student s) {
        var ids = s.getCourses() == null ? null :
                s.getCourses().stream().map(Course::getId).collect(java.util.stream.Collectors.toSet());

        return new StudentDto(
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getAge(),
                ids
        );
    }

    public static Student toEntity(StudentDto d) {
        Student s = new Student();
        s.setId(d.getId());
        s.setFirstName(d.getFirstName());
        s.setLastName(d.getLastName());
        s.setEmail(d.getEmail());
        s.setAge(d.getAge());
        return s;
    }
}