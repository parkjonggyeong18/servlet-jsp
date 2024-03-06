package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbConnectionThreadLocal.initialize();
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        User admin = new User("admin", "admin1", "1234", "19990112", User.Auth.ROLE_ADMIN, 0, LocalDateTime.now(), null);
        User user = new User("user", "user1", "1234", "19990112", User.Auth.ROLE_USER, 0, LocalDateTime.now(), null);
        try {
            userService.saveUser(admin);
            userService.saveUser(user);
        } catch(UserAlreadyExistsException e){

        }
        DbConnectionThreadLocal.reset();
    }
}