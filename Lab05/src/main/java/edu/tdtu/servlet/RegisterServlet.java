package edu.tdtu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import edu.tdtu.beans.Account;
import edu.tdtu.daos.AccountDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") != null) {
            resp.sendRedirect("home");
        } else
            req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String confirmPassword = req.getParameter("confirmPassword");

        if(username != null && password != null && email != null && confirmPassword != null) {
            if(username.equals("") || password.equals("") || email.equals("") || confirmPassword.equals("")) {
                req.setAttribute("error", "Bạn chưa nhập đủ thông tin");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                Account acc = AccountDAO.get(username);

                if(acc != null) {
                    req.setAttribute("error", "Username đã tồn tại");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                } else {
                    if(password.equals(confirmPassword)) {
                        Account account = new Account(username, password, email);
                        AccountDAO.add(account);

                        resp.sendRedirect("login");
                    } else {
                        req.setAttribute("error", "Mật khẩu không khớp");
                        req.getRequestDispatcher("register.jsp").forward(req, resp);
                    }
                }
            }
        } else {
            req.setAttribute("error", "Không đủ thông tin");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
