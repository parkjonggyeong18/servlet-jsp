package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter("/admin/*")
public class AdminCheckFilter extends HttpFilter {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        DbConnectionThreadLocal.initialize();
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        HttpSession session = req.getSession(false);
        if(session == null){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String id = (String) session.getAttribute("loginID");

        if(id == null){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        User user = userService.getUser(id);
        DbConnectionThreadLocal.reset();

        if(user == null){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if(user.getUserAuth() == User.Auth.ROLE_ADMIN){
            chain.doFilter(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

    }
}
