package kz.narxoz.sw_final.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TeacherDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseDto> courses;
}