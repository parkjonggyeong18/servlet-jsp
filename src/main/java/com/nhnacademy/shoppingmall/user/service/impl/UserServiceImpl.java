package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId) {
        //todo#4-1 회원조회

        return userRepository.findById(userId).orElse(null);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if (userRepository.countByUserId(user.getUserId()) == 0) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("이미 등록된 아이디입니다.");
        }
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if (userRepository.countByUserId(user.getUserId()) == 1) {
            userRepository.update(user);
        } else {
            throw new UserNotFoundException("등록되지 않은 아이디입니다.");
        }
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if (userRepository.countByUserId(userId) == 1){
            userRepository.deleteByUserId(userId);
        }else{
            throw new UserNotFoundException("등록되지 않은 아이디입니다.");
        }
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        //todo#4-5 로그인 구현, userId, userPassword로 일치하는 회원 조회
        User user = userRepository.findByUserIdAndUserPassword(userId,userPassword).orElse(null);
        if(user != null){
            userRepository.updateLatestLoginAtByUserId(userId, LocalDateTime.now());
        }else{
            throw new UserNotFoundException("아이디, 비빌번호가 일치하지 않습니다.");
        }

        return user;
    }

}
