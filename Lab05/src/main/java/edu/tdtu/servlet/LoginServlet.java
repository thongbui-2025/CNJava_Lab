package edu.tdtu.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import edu.tdtu.beans.Account;
import edu.tdtu.daos.AccountDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");


        if(username != null && password != null) {
            Account account = AccountDAO.get(username);

            if(account != null) {
                if(AccountDAO.isMatchPassword(username, password)) {
                    if(remember != null) {
                        Cookie cookieUser = new Cookie("user", username);
                        Cookie cookiePass = new Cookie("password", password);
                        cookieUser.setMaxAge(3600 * 30);
                        cookiePass.setMaxAge(3600 * 30);


                    }

                    HttpSession session = req.getSession();
                    session.setAttribute("username", username);
                    session.setMaxInactiveInterval(30 * 60);

                    resp.sendRedirect("/Lab05/home");
                } else {
                    req.setAttribute("errorMessage", "Mật khẩu không đúng");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                }
            } else {
                req.setAttribute("errorMessage", "Tài khoản không tồn tại");

                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }
        } else {

            req.setAttribute("errorMessage", "Bạn chưa nhập đủ thông tin");

            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") != null) {
            resp.sendRedirect("home");
        } else
            req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
