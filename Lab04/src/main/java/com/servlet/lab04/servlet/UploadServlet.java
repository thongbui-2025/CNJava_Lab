package com.servlet.lab04.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final List<String> EXTENSIONS = Arrays.asList("txt", "docx", "jpg", "png", "pdf");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String saveFileName = req.getParameter("name");

        if (saveFileName == null || saveFileName.isEmpty()) {
            out.println("<h3>Error: Please provide a file name.</h3>");
            return;
        }

        Part filePart = req.getPart("file");
        String fileExtension = getFileExtension(filePart);

        if (!EXTENSIONS.contains(fileExtension)) {
            out.println("<h3>Error: Unsupported file extension.</h3>");
            return;
        }

        String uploadPath = getServletContext().getRealPath("/WEB-INF") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File existingFile = new File(uploadPath + File.separator + saveFileName);
        String overrideIfExists = req.getParameter("override");

        if (existingFile.exists() && overrideIfExists == null) {
            out.println("<h3>Error: File already exists.</h3>");
            return;
        }

        InputStream input = filePart.getInputStream();
        Files.copy(input, new File(uploadPath, saveFileName).toPath(), StandardCopyOption.REPLACE_EXISTING);

        if (existingFile.exists() && overrideIfExists != null) {
            out.println("<h3>File has been overridden.</h3>");
        } else {
            out.println("<h3>File has been uploaded.</h3>");
        }

        String downloadUrl = req.getContextPath() + File.separator + UPLOAD_DIRECTORY + File.separator + saveFileName;

        out.println("<a href='" + downloadUrl + "'>Click here to visit file</a>");
    }

    private String getFileExtension(Part part) {
        String fileName = part.getSubmittedFileName();
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }
}
