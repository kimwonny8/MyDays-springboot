package com.mydays.backend.service;

import com.mydays.backend.domain.User;
import com.mydays.backend.dto.UserForm;
import com.mydays.backend.exception.DuplicateException;
import com.mydays.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        int id = user.getId();
        String token = jwtService.getToken("id", id);

        return token;
    }

    @Transactional
    public void signUpUser(UserForm userForm) throws Exception {
        String encodedPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .email(userForm.getEmail())
                .password(encodedPassword)
                .name(userForm.getName())
                .birth(userForm.getBirth())
                .build();

        if(user.getEmail() != null){
            validateDuplicateEmail(user.getEmail());
            userRepository.save(user);
        } else {
            throw new NullPointerException();
        }
    }

    private void validateDuplicateEmail(String email) throws Exception {
        if(userRepository.existsByEmail(email)){
            throw new DuplicateException();
        }
    }
}
