package com.example.book.repository;

import com.example.book.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TODO
 * zhangyd
 * 2020/4/7 10:30
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findAllByName(String name);

    List<User> withNameAndPasswordQuery(String name, String password);

    @Override
    User save(User user);

    @Override
    void deleteById(Long aLong);
}
