package kz.narxoz.sw_final.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CourseDto {
    private Long id;
    private String title;
    private String code;
}