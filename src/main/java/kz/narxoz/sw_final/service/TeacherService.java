package kz.narxoz.sw_final.service;

import kz.narxoz.sw_final.dto.TeacherDto;
import kz.narxoz.sw_final.entity.Teacher;
import kz.narxoz.sw_final.mapper.TeacherMapper;
import kz.narxoz.sw_final.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherDto> getAll() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::toDto)
                .toList();
    }

    public TeacherDto getById(Long id) {
        Teacher teacher = teacherRepository.findWithCoursesById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return TeacherMapper.toDto(teacher);
    }

    public TeacherDto create(Teacher teacher) {
        Teacher saved = teacherRepository.save(teacher);
        return TeacherMapper.toDto(saved);
    }

    public TeacherDto update(Long id, Teacher teacher) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        existing.setFirstName(teacher.getFirstName());
        existing.setLastName(teacher.getLastName());
        existing.setEmail(teacher.getEmail());

        Teacher saved = teacherRepository.save(existing);
        return TeacherMapper.toDto(saved);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}