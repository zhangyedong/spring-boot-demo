package com.example.book.cache;

import com.example.book.domain.User;
import com.example.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * cache服务
 * zhangyd
 * 2020/4/8 11:14
 */
@Service
public class CacheUserServiceImpl implements CacheUserService {

    @Autowired
    UserRepository userRepository;

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "user")
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Cacheable(value = "user", key = "#name")
    @Override
    public User findOne(String name) {
        return userRepository.findAllByName(name);
    }

}
