package com.crt.jpapostgresql.repository;

import com.crt.jpapostgresql.pojo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Caort
 * @date 2020/11/2 20:11
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
}
