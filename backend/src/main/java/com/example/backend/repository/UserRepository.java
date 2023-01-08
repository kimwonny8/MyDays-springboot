package com.example.backend.repository;

import com.example.backend.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // 작성
    public void save(User user) {
        em.persist(user);
    }

    // 단건 조회
    public User findOne(String id) {
        return em.find(User.class, id);
    }

    // 전체 조회
    public List<User> findAll() {
        List<User> userList = null;

        userList = em.createQuery("select u from User u", User.class).getResultList();
        return userList;
    }
}
