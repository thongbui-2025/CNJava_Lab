package edu.tdtu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import edu.tdtu.beans.Product;
import edu.tdtu.daos.ProductDAO;

@WebServlet("/home")
public class ProductManagement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("login");
        } else {
            String action = req.getParameter("action");
            String id = req.getParameter("id");
            if(action != null && action.equals("del")) {
                int idParam = Integer.parseInt(id);
                ProductDAO.delete(idParam);

            }
            List<Product> lst = ProductDAO.getAll();
            req.setAttribute("list", lst);

            req.getRequestDispatcher("product.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        if(name != null && price != null) {

            if(name.equals("") || price.equals("")) {
                req.setAttribute("error", "Bạn chưa nhập đủ thông tin");
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            } else {
                int priceParam = Integer.parseInt(price);
                Product product = new Product(name, priceParam);
                ProductDAO.add(product);

                List<Product> lst = ProductDAO.getAll();
                req.setAttribute("list", lst);
                req.getRequestDispatcher("product.jsp").forward(req, resp);
            }



        } else {
            req.setAttribute("error", "Thông tin gửi đi không đủ");
            req.getRequestDispatcher("product.jsp").forward(req, resp);
        }
    }
}
