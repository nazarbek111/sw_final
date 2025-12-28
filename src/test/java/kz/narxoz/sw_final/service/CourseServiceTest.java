package kz.narxoz.sw_final.service;

import kz.narxoz.sw_final.entity.Course;
import kz.narxoz.sw_final.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void getAll_shouldReturnCourses() {
        Course course = new Course(1L, "Java", "JAVA-101", null, Set.of());
        when(courseRepository.findAll()).thenReturn(List.of(course));

        List<Course> result = courseService.getAll();

        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());
    }

    @Test
    void getById_shouldReturnCourse() {
        Course course = new Course(1L, "Spring", "SPR-101", null, Set.of());
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseService.getById(1L);

        assertEquals("Spring", result.getTitle());
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> courseService.getById(1L));
    }

    @Test
    void create_shouldSaveCourse() {
        Course course = new Course(null, "Docker", "DOC-101", null, Set.of());
        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseService.create(course);

        assertEquals("Docker", result.getTitle());
        verify(courseRepository).save(course);
    }

    @Test
    void update_shouldUpdateCourse() {
        Course existing = new Course(1L, "Old", "OLD-1", null, Set.of());
        Course updated = new Course(null, "New", "NEW-1", null, Set.of());

        when(courseRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(courseRepository.save(existing)).thenReturn(existing);

        Course result = courseService.update(1L, updated);

        assertEquals("New", result.getTitle());
        assertEquals("NEW-1", result.getCode());
    }

    @Test
    void delete_shouldCallRepository() {
        doNothing().when(courseRepository).deleteById(1L);

        courseService.delete(1L);

        verify(courseRepository).deleteById(1L);
    }
}