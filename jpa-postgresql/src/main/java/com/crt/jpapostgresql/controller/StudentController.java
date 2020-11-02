package com.crt.jpapostgresql.controller;

import com.crt.jpapostgresql.pojo.entity.Student;
import com.crt.jpapostgresql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Caort
 * @date 2020/11/2 20:18
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/all")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @PutMapping("/student1")
    public Student updateStudent1(@RequestBody Student student){
        return studentService.updateStudent1(student);
    }

    @PutMapping("/student2")
    public Student updateStudent2(@RequestBody Student student){
        return studentService.updateStudent2(student);
    }

    @PutMapping("/student3")
    public Student updateStudent3(@RequestBody Student student){
        return studentService.updateStudent3(student);
    }

    @PutMapping("/student4")
    public Student updateStudent4(@RequestBody Student student){
        return studentService.updateStudent4(student);
    }
}
