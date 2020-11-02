package com.crt.jpapostgresql.pojo.entity;


import com.crt.jpapostgresql.plus.JsonbType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author Caort
 * @date 2020/11/2 20:01
 */
@Entity
@Table(name = "tb_student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "info", columnDefinition = "jsonb")
    @Type(type = "com.crt.jpapostgresql.plus.JsonbType",
            parameters = {
                    @org.hibernate.annotations.Parameter (name = JsonbType.CLASS,
                            value = "com.crt.jpapostgresql.pojo.entity.Info")
            })
    private Info info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
