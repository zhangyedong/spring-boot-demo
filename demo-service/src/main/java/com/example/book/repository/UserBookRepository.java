package com.example.book.repository;

import com.example.book.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 * zhangyd
 * 2020/4/7 10:30
 */
public interface UserBookRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findAllByName(String name);

    List<User> withBookNameAndPasswordQuery(String name, String password);

    @Override
    User save(User user);

    void deleteById(Long id);

//    @Query("select u from user u where u.name = ?1")
//    Page<User> getByUserAndName(String name, Pageable pageable);

//    @Query("select u from user u where u.phone_num = :phoneNum")
//    List<User> getByPhoneNum(@Param("phoneNum") String phoneNum);

//    @Transactional
//    @Modifying
//    @Query("delete from user where name = ?1")
//    void deleteByName(String name);

}
