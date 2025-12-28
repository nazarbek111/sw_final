package kz.narxoz.sw_final.service;

import kz.narxoz.sw_final.dto.TeacherDto;
import kz.narxoz.sw_final.entity.Teacher;
import kz.narxoz.sw_final.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    void getAll_shouldReturnTeacherDtos() {
        Teacher teacher = new Teacher(1L, "Ali", "Rakhym", "ali@uni.kz", List.of());
        when(teacherRepository.findAll()).thenReturn(List.of(teacher));

        List<TeacherDto> result = teacherService.getAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Ali", result.get(0).getFirstName());
        assertEquals("Rakhym", result.get(0).getLastName());
        assertEquals("ali@uni.kz", result.get(0).getEmail());
    }

    @Test
    void getById_shouldReturnTeacherDto_withCourses() {
        Teacher teacher = new Teacher(1L, "Alibek", "Balabek", "alibek@uni.kz", List.of());
        when(teacherRepository.findWithCoursesById(1L)).thenReturn(Optional.of(teacher));

        TeacherDto result = teacherService.getById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Alibek", result.getFirstName());
        assertEquals("Balabek", result.getLastName());
        assertEquals("alibek@uni.kz", result.getEmail());
        assertNotNull(result.getCourses());
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {
        when(teacherRepository.findWithCoursesById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> teacherService.getById(1L));
    }

    @Test
    void create_shouldSaveTeacher_andReturnDto() {
        Teacher input = new Teacher(null, "Madi", "Kaliev", "madi@uni.kz", List.of());
        Teacher saved = new Teacher(10L, "Madi", "Kaliev", "madi@uni.kz", List.of());

        when(teacherRepository.save(any(Teacher.class))).thenReturn(saved);

        TeacherDto result = teacherService.create(input);

        assertEquals(10L, result.getId());
        assertEquals("Madi", result.getFirstName());
        assertEquals("Kaliev", result.getLastName());
        assertEquals("madi@uni.kz", result.getEmail());

        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    void update_shouldUpdateTeacher_andReturnDto() {
        Teacher existing = new Teacher(1L, "Old", "Name", "old@uni.kz", List.of());
        Teacher update = new Teacher(null, "New", "Name", "new@uni.kz", List.of());

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(teacherRepository.save(existing)).thenReturn(existing);

        TeacherDto result = teacherService.update(1L, update);

        assertEquals(1L, result.getId());
        assertEquals("New", result.getFirstName());
        assertEquals("Name", result.getLastName());
        assertEquals("new@uni.kz", result.getEmail());

        verify(teacherRepository).findById(1L);
        verify(teacherRepository).save(existing);
    }

    @Test
    void update_shouldThrowException_whenNotFound() {
        Teacher update = new Teacher(null, "New", "Name", "new@uni.kz", List.of());
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> teacherService.update(1L, update));
    }

    @Test
    void delete_shouldCallRepository() {
        doNothing().when(teacherRepository).deleteById(1L);

        teacherService.delete(1L);

        verify(teacherRepository).deleteById(1L);
    }
}