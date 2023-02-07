package com.mydays.backend.service;

import com.mydays.backend.domain.User;
import com.mydays.backend.dto.UserForm;
import com.mydays.backend.exception.DuplicateException;
import com.mydays.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;
    @Transactional
    public void signUpUser(User user) throws Exception {
        if(user.getEmail() != null){
            validateDuplicateEmail(user.getEmail());
            userRepository.save(user);
        }
        else {
            throw new NullPointerException();
        }
    }

    private void validateDuplicateEmail(String email) throws Exception {
        if(userRepository.existsByEmail(email)){
            throw new DuplicateException();
        }
    }
}
