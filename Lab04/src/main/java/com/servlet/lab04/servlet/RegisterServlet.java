package com.servlet.lab04.servlet;

import com.servlet.lab04.model.Info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("birthday");
        String birthday = req.getParameter("birthday");
        String birthtime = req.getParameter("birthtime");
        String country = req.getParameter("country");
        String toeic = req.getParameter("toeic");
        String message = req.getParameter("message");
        String gender = req.getParameter("gender");
        String[] favoriteIdes = req.getParameterValues("favorite_ides");

        if(name != null && !name.isEmpty() && email != null && !email.isEmpty() && birthtime != null && !birthtime.isEmpty()
                && birthday != null && !birthday.isEmpty() && country != null && !country.isEmpty()
                && toeic != null && !toeic.isEmpty() && gender != null && !gender.isEmpty()
                && message != null && !message.isEmpty() && favoriteIdes != null && favoriteIdes.length > 0
        ) {
            Double toeicParam = Double.parseDouble(toeic);
            Info inf = new Info(name, email, birthday, birthtime, gender, country, favoriteIdes, toeicParam, message);
            req.setAttribute("info", inf);
            req.getRequestDispatcher("/output.jsp").forward(req, resp);

        } else {
            resp.getWriter().println("Error");
        }
    }
}
