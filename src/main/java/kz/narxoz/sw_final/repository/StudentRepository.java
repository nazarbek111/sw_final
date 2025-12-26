package kz.narxoz.sw_final.repository;

import kz.narxoz.sw_final.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}