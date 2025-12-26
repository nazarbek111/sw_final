package kz.narxoz.sw_final.repository;

import kz.narxoz.sw_final.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {}