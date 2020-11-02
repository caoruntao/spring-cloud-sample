package com.crt.jpapostgresql.service;

import com.crt.jpapostgresql.pojo.entity.Student;
import com.crt.jpapostgresql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Caort
 * @date 2020/11/2 20:13
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student find(Long id){
        Optional<Student> findedStudent = studentRepository.findById(id);
        return findedStudent.isPresent() ? findedStudent.get() : null;
    }

    @Transactional
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Student student){
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent1(Student student){
        Long id = student.getId();
        Optional<Student> finded = studentRepository.findById(id);
        Student entity = finded.get();
        entity.setName(student.getName());
        entity.setAge(student.getAge());
        entity.setInfo(student.getInfo());
        return studentRepository.save(entity);
    }

    @Transactional
    public Student updateStudent2(Student student){
        Student finded = studentRepository.findByName(student.getName());
        finded.setName(student.getName());
        finded.setAge(student.getAge());
        finded.setInfo(student.getInfo());
        return studentRepository.save(finded);
    }

    @Transactional
    public Student updateStudent3(Student student){
        Student finded = studentRepository.findByName(student.getName());
        student.setId(finded.getId());
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent4(Student student){
        Student finded = studentRepository.findByName(student.getName());
        finded.setName(student.getName());
        finded.setAge(student.getAge());
        finded.setInfo(student.getInfo());
        return student;
    }
}
