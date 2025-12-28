package kz.narxoz.sw_final.mapper;

import kz.narxoz.sw_final.dto.StudentDto;
import kz.narxoz.sw_final.entity.Student;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDto toDto(Student s) {
        return new StudentDto(
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getAge(),
                s.getCourses() == null ? null :
                        s.getCourses()
                                .stream()
                                .map(CourseMapper::toDto)
                                .collect(Collectors.toSet())
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