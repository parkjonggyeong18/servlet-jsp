package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/delete.do")
public class UserDeleteController implements BaseController {
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String userId = user.getUserId();
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        String userBirth = user.getUserBirth();
        User.Auth userAuth = user.getUserAuth();
        int userPoint = user.getUserPoint();
        LocalDateTime userCreatedAt = user.getCreatedAt();
        LocalDateTime userLastLoginAt = user.getLatestLoginAt();

        user = new User(userId, userName, userPassword, userBirth, userAuth, userPoint, userCreatedAt, userLastLoginAt);
        req.getSession().removeAttribute("user");
        userService.deleteUser(userId);
        log.info(user.getUserName() + "을 delete 완료 ");
        return "redirect:/index.do";
    }
}
