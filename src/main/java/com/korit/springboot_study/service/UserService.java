package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddUserDto;
import com.korit.springboot_study.dto.request.ReqModifyUserDto;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.entity.UserRole;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.repository.UserRoleRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional(rollbackFor = Exception.class)
    public User addUser(ReqAddUserDto reqAddUserDto) {
        User saveUser = userRepository.save(reqAddUserDto.toUser())
                .orElseThrow(
                        () -> new CustomDuplicateKeyException("", Map.of("username", "이미 사용중인 사용자입니다."))
                );
        userRoleRepository.save(UserRole.builder()
                .userId(saveUser.getUserId())
                .roleId(1) // RoleId(1) == ROLE_USER
                .build()).orElseThrow(() -> new RuntimeException("SQL Error"));
        return saveUser;
    }

    public Boolean duplicateUsername(String username){
        return userRepository.findByUsername(username).isPresent(); //.isPresent() 값이 있으면 true 없으면 false
    }

    public User getUserById(int userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 사용자 ID는 존재하지 않습니다."));
    }

    public List<User> getAllUsers() throws NotFoundException {
        return userRepository.findAll()
                .orElseThrow(() -> new NotFoundException("사용자 정보가 존재하지 않습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyUser(int userId, ReqModifyUserDto reqModifyUserDto) throws NotFoundException {
        return userRepository.updateUserById(reqModifyUserDto.toUser(userId))
                .orElseThrow(() -> new NotFoundException("해당 사용자 ID는 존재하지 않습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUser(int userId) throws NotFoundException {
        return userRepository.deleteUserById(userId)
                .orElseThrow(() -> new NotFoundException("해당 사용자 ID는 존재하지 않습니다."));
    }
}
