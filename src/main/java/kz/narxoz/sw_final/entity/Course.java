package kz.narxoz.sw_final.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;


@Entity
@Table(name = "courses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}