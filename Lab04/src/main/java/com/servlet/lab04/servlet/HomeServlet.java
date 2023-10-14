package com.servlet.lab04.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("page");
        if(query != null) {
            req.getRequestDispatcher(query + ".jsp").forward(req, resp);
        } else {
            resp.getWriter().println("<h1>Welcome to our website</h1>");
        }
    }
}
