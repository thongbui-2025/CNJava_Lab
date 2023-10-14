package com.servlet.lab04.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

@WebServlet(name = "DownloadServlet", urlPatterns = { "/download"})
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("file");
        if (fileName == null || fileName.isEmpty()) {
            throw new ServletException("Parameter not found");
        }

        if (!Path.of(getServletContext().getRealPath("/WEB-INF/images/" + fileName)).toFile().exists()) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1> File not found </h1>");
            out.flush();
            out.close();
            return;
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ServletOutputStream outputStream = resp.getOutputStream();
        getServletContext().getResourceAsStream("/WEB-INF/images/" + fileName).transferTo(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
