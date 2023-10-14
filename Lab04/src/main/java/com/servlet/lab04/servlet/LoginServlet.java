package com.servlet.lab04.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> users = new HashMap<>();

    @Override
    public void init() throws ServletException {
        users.put("admin", "123");
        users.put("user", "123");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        System.out.println(username);

        if(users.containsKey(username) && users.get(username).equals(password)) {
            printWriter.println("Login success");
        } else {
            printWriter.println("Login failed");
        }

        printWriter.flush();
        printWriter.close();
    }
}