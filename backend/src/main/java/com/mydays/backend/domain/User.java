package com.mydays.backend.domain;

import com.mydays.backend.dto.UserForm;
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
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = true, length = 50)
    private String name;

    @Column(nullable = true, length = 50)
    private String birth;

    public User(){}

    public User(UserForm userForm) {
        this.id= userForm.getId();
        this.email = userForm.getEmail();
        this.password = userForm.getPassword();
        this.name = userForm.getName();
        this.birth = userForm.getBirth();
    }

    public static User createUser(UserForm userForm){
        return new User(userForm);
    }


}