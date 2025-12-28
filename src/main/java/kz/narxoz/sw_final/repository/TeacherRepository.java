package kz.narxoz.sw_final.repository;

import kz.narxoz.sw_final.entity.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Override
    @EntityGraph(attributePaths = "courses")
    List<Teacher> findAll();

    @EntityGraph(attributePaths = "courses")
    java.util.Optional<Teacher> findWithCoursesById(Long id);
}