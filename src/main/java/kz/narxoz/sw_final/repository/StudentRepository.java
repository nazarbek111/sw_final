package kz.narxoz.sw_final.repository;

import kz.narxoz.sw_final.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeGreaterThanEqual(Integer age);
}