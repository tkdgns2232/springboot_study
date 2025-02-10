package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public Optional<User> save(User user) {
        try{
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMapper.selectByUsername(username));
    }

    public Optional<User> findById(int id) {
        return Optional.ofNullable(userMapper.selectById(id));
    }

    public Optional<List<User>> findAll() {
        List<User> foundUsers = userMapper.selectAll();
        return foundUsers.isEmpty() ? Optional.empty() : Optional.of(foundUsers);
    }

    public Optional<Boolean> updateUserById(User user) {
        return userMapper.updateUserByID(user) < 1 ? Optional.empty() : Optional.of(true); // 1보다 적으면 empty 아니면 true
    }

    public Optional<Boolean> deleteUserById(int id) {
        return userMapper.deleteById(id) < 1 ? Optional.empty() : Optional.of(true); // 1보다 적으면 empty 아니면 true
    }
}
