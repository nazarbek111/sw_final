package kz.narxoz.sw_final.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
