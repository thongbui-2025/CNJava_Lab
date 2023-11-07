package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity
public class Student {
        @Id
        private  Long id;

        private  String name;

        private Integer age;

        private String email;

        private Double ieltsScore;

    public Student(Long id, String name, Integer age, String email, Double ieltsScore) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.ieltsScore = ieltsScore;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getIeltsScore() {
        return ieltsScore;
    }

    public void setIeltsScore(Double ieltsScore) {
        this.ieltsScore = ieltsScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", ieltsScore=" + ieltsScore +
                '}';
    }
}
