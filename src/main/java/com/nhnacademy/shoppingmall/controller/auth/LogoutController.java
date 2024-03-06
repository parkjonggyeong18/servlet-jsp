package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/logout.do")
public class LogoutController implements BaseController {
    //todo#13-3 로그아웃 구현
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("loginID");
        return "redirect:/index.do";
    }
}
