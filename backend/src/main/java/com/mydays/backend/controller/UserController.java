package com.mydays.backend.controller;

import com.mydays.backend.domain.User;
import com.mydays.backend.dto.UserForm;
import com.mydays.backend.repository.UserRepository;
import com.mydays.backend.service.JwtService;
import com.mydays.backend.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                HttpServletResponse res) {
        String token = userService.login(params.get("email"), params.get("password"));

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        res.addCookie(cookie);

        User user = userRepository.findByEmail(params.get("email"));
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("name", user.getName());

        return ResponseEntity.ok().body(map);
    }
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid UserForm userForm) throws Exception{
        userService.signUpUser(userForm);
    }
}
