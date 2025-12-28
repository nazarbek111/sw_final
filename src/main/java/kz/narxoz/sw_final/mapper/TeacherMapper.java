package kz.narxoz.sw_final.mapper;

import kz.narxoz.sw_final.dto.TeacherDto;
import kz.narxoz.sw_final.entity.Teacher;

public class TeacherMapper {

    public static TeacherDto toDto(Teacher t) {
        return new TeacherDto(t.getId(), t.getFirstName(), t.getLastName(), t.getEmail());
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