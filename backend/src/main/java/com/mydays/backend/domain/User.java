package com.mydays.backend.domain;

import com.mydays.backend.dto.UserForm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = true, length = 50)
    private String name;

    @Column(nullable = true, length = 50)
    private String birth;

    @Builder
    public User(int id, String email, String password, String name, String birth) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
    }
}