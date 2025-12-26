package kz.narxoz.sw_final.repository;

import kz.narxoz.sw_final.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}