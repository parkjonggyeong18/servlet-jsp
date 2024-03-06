package com.nhnacademy.shoppingmall.common.mvc.servlet;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;
import com.nhnacademy.shoppingmall.common.util.CookieUtil;

import java.sql.Connection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@WebServlet(name = "frontServlet",urlPatterns = {"*.do"})
public class FrontServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        controllerFactory = (ControllerFactory) getServletContext().getAttribute(ControllerFactory.CONTEXT_CONTROLLER_FACTORY_NAME);

        viewResolver = new ViewResolver();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        try{
            DbConnectionThreadLocal.initialize();

            List<String> recentlyViewedProducts = CookieUtil.getRecentlyViewedProducts(req);
            req.setAttribute("recentlyViewedProducts", recentlyViewedProducts);

            BaseController baseController = (BaseController) controllerFactory.getController(req);
            String viewName = baseController.execute(req,resp);

            if(viewResolver.isRedirect(viewName)){
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                log.debug("redirectUrl:{}",redirectUrl);
                resp.sendRedirect(redirectUrl);
            }else {
                String layout = viewResolver.getLayOut(viewName);
                log.debug("viewName:{}", viewResolver.getPath(viewName));
                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewName));
                RequestDispatcher rd = req.getRequestDispatcher(layout);
                rd.include(req, resp);
            }
        }catch (Exception e){
            log.error("error:{}",e);
            DbConnectionThreadLocal.setSqlError(true);
            e.printStackTrace();
        }finally {
            DbConnectionThreadLocal.reset();
        }
    }

}