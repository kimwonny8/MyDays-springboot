package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@DynamicInsert
public class User {

    @Id
    @Column(nullable = false, length = 30)
    String id;

    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false, length = 50)
    String name;

    String birth;
}