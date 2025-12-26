package kz.narxoz.sw_final.mapper;

import kz.narxoz.sw_final.dto.CourseDto;
import kz.narxoz.sw_final.entity.Course;

public class CourseMapper {
    public static CourseDto toDto(Course c) {
        return new CourseDto(c.getId(), c.getTitle(), c.getCode());
    }

    public static Course toEntity(CourseDto d) {
        Course c = new Course();
        c.setId(d.getId());
        c.setTitle(d.getTitle());
        c.setCode(d.getCode());
        return c;
    }
}
