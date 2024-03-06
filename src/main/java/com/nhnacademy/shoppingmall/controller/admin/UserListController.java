package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RequestMapping(method = RequestMapping.Method.GET ,value = "/admin/userlist.do")
public class UserListController implements BaseController {
    private final UserService userService;

    public UserListController() {
        UserRepository userRepository = new UserRepositoryImpl(); // 실제 구현체 생성
        this.userService = new UserServiceImpl(userRepository);
    }

    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> userList = userService.getAllUsers();
        Page<User> page = new Page<>(userList);
        req.setAttribute("totalPages", page.getTotalPage());
        int currentPage = Objects.nonNull(req.getParameter("page")) ? Integer.parseInt(req.getParameter("page")) : 1;
        List<User> pagingList = page.pagingList(currentPage);
        req.setAttribute("userList", pagingList);
        return "shop/admin/userList";
    }
}
