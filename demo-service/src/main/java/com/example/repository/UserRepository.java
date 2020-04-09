package com.example.repository;

import com.example.common.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * user持久层
 * zhangyd
 * 2020/4/7 10:30
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    List<UserEntity> findAll();

    UserEntity findAllByName(String name);

    List<UserEntity> withNameAndPasswordQuery(String name, String password);

    @Override
    UserEntity save(UserEntity user);

    @Override
    void deleteById(Long id);
}
