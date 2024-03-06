package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/mypage/updateAction.do")
public class UserUpdatePostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        log.debug(user.toString() + "을 업데이트 합니다.");
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");
        User.Auth userAuth = User.Auth.valueOf(req.getParameter("user_auth"));
        /*
            기존 정보
         */
        int userPoint = user.getUserPoint();
        String userId = user.getUserId();
        LocalDateTime userCreatedAt = user.getCreatedAt();
        LocalDateTime userLastLoginAt = user.getLatestLoginAt();

        user = new User(userId, userName, userPassword, userBirth, userAuth, userPoint, userCreatedAt, userLastLoginAt);
        userService.updateUser(user);
        req.getSession().removeAttribute("user");
        log.info(user.getUserName() + "을 update 완료 ");
        return "shop/login/login_form";
    }
}
