package com.example.book.cache;

import com.example.book.domain.User;

/**
 * cache
 * zhangyd
 * 2020/4/8 11:07
 */
public interface CacheUserService {

    public User save(User user);

    public void remove(Long id);

    public User findOne(String name);

}
