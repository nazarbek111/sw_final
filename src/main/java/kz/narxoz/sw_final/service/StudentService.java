package kz.narxoz.sw_final.service;

import kz.narxoz.sw_final.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    StudentDto findById(Long id);
    StudentDto create(StudentDto dto);
    StudentDto update(Long id, StudentDto dto);
    void delete(Long id);

    List<StudentDto> search(Integer minAge);
}