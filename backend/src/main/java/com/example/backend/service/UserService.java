package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public String save(User user){
        userRepository.save(user);
        return user.getId();
    }

    // user 전체 조회
    public List<User> findAll(){
        return userRepository.findAll();
    }

    // user 단건 조회
    public User findOne(String userId){
        return userRepository.findOne(userId);
    }
}
