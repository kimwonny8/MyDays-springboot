package com.mydays.backend.service;

import com.mydays.backend.domain.User;
import com.mydays.backend.dto.UserForm;
import com.mydays.backend.exception.DuplicateEmailException;
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
    public void signUpUser(UserForm userForm) throws Exception {
        if(userForm.getEmail() != null){
            validateDuplicateEmail(userForm.getEmail());
            userRepository.save(User.createUser(userForm));
        }
        else {
            throw new NullPointerException();
        }
    }

    private void validateDuplicateEmail(String email) throws Exception {
        if(userRepository.existsByEmail(email)){
            throw new DuplicateEmailException();
        }
    }
}
