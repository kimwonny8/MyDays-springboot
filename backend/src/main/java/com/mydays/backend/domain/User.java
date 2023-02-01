package com.mydays.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    String email;

    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = true, length = 50)
    String name;

    @Column(nullable = true, length = 50)
    String birth;
}