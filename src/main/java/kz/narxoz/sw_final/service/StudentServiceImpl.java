package kz.narxoz.sw_final.service;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import kz.narxoz.sw_final.dto.StudentDto;
import kz.narxoz.sw_final.entity.Student;
import kz.narxoz.sw_final.mapper.StudentMapper;
import kz.narxoz.sw_final.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    @PersistenceContext
    private EntityManager em;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentDto> findAll() {
        return repo.findAll().stream().map(StudentMapper::toDto).toList();
    }

    @Override
    public StudentDto findById(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return StudentMapper.toDto(s);
    }

    @Override
    public StudentDto create(StudentDto dto) {
        Student saved = repo.save(StudentMapper.toEntity(dto));
        return StudentMapper.toDto(saved);
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student s = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setAge(dto.getAge());
        return StudentMapper.toDto(repo.save(s));
    }

    @Override
    public void delete(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        repo.delete(s);
    }

    @Override
    public List<StudentDto> search(Integer minAge) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        List<Predicate> preds = new ArrayList<>();
        if (minAge != null) preds.add(cb.greaterThanOrEqualTo(root.get("age"), minAge));

        cq.select(root).where(preds.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList().stream().map(StudentMapper::toDto).toList();
    }
}