package com.servlet.lab04.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ImageServlet2", urlPatterns = {"/image2"})
public class ImageServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"image2.png\"");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(getServletContext().getResourceAsStream("/WEB-INF/images/image2.png").readAllBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
