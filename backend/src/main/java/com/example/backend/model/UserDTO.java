package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    String id;
    String password;
    String name;
    String birth;

    public UserDTO() {}

    public UserDTO(String id, String password, String name, String birth) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
    }

    public UserDTO(String name) {
        super();
        this.name = name;
    }
}
