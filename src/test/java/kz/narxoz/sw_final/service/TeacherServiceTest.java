package kz.narxoz.sw_final.service;

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
    void getAll_shouldReturnTeachers() {
        Teacher teacher = new Teacher(1L, "Ali", "Rakhym", "ali.rakhym@uni.kz", List.of());
        when(teacherRepository.findAll()).thenReturn(List.of(teacher));

        List<Teacher> result = teacherService.getAll();

        assertEquals(1, result.size());
        assertEquals("Ali", result.get(0).getFirstName());
    }

    @Test
    void getById_shouldReturnTeacher() {
        Teacher teacher = new Teacher(1L, "Alibek", "Balabek", "alibek.balabek@uni.kz", List.of());
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher result = teacherService.getById(1L);

        assertEquals("Alibek", result.getFirstName());
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> teacherService.getById(1L));
    }

    @Test
    void create_shouldSaveTeacher() {
        Teacher teacher = new Teacher(null, "Madi", "Kaliev", "madi.kaliev@uni.kz", List.of());
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher result = teacherService.create(teacher);

        assertEquals("Madi", result.getFirstName());
        verify(teacherRepository).save(teacher);
    }

    @Test
    void update_shouldUpdateTeacher() {
        Teacher existing = new Teacher(1L, "Old", "Name", "old@uni.kz", List.of());
        Teacher updated = new Teacher(null, "New", "Name", "new@uni.kz", List.of());

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(teacherRepository.save(existing)).thenReturn(existing);

        Teacher result = teacherService.update(1L, updated);

        assertEquals("New", result.getFirstName());
        assertEquals("new@uni.kz", result.getEmail());
    }

    @Test
    void delete_shouldCallRepository() {
        doNothing().when(teacherRepository).deleteById(1L);

        teacherService.delete(1L);

        verify(teacherRepository).deleteById(1L);
    }
}