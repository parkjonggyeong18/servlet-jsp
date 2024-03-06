package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value ="/signupAction.do")
public class SignUpPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");
        User.Auth userAuth = User.Auth.valueOf(req.getParameter("user_auth"));
        int point = 10000000;
        LocalDateTime timestamp = java.time.LocalDateTime.now();
        LocalDateTime lastestLoginAt = null;

        User user = new User(userId, userName, userPassword, userBirth, userAuth, point, timestamp, lastestLoginAt);
        if (Objects.isNull(user)) {
            throw new RuntimeException("[signup] user 객체가 생성되지 않음!");
        }
        userService.saveUser(user);
        return  "shop/main/index";
    }
}
