package com.servlet.lab04.servlet;

import com.google.gson.Gson;
import com.servlet.lab04.model.Product;
import com.servlet.lab04.model.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private Gson gson = new Gson();
    private List<Product> products;

    public void init() {
        gson = new Gson();
        products = new ArrayList<>();
        products.add(new Product(0, "Iphone", 120.0));
        products.add(new Product(1, "Samsung", 200.0));
        products.add(new Product(2, "Ipad", 300.0));
        products.add(new Product(3, "Android", 500.0));
        products.add(new Product(4, "Laptop", 700.0));
        products.add(new Product(5, "PC", 800.0));
    }

    private void sendAsJson(HttpServletResponse resp, Integer statusCode, String message, Object data) throws IOException {
        resp.setContentType("application/json");
        ResponseData responseData = new ResponseData(statusCode, message, data);
        String json = gson.toJson(responseData);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        ResponseData responseData;
        if(id != null){
            Optional<Product> optionalProduct = products.stream()
                    .filter(p -> p.getId() == Integer.parseInt(id)).findFirst();
            if(optionalProduct.isPresent()) {
                sendAsJson(resp, 200, "Found product", optionalProduct.get());
            } else {
                sendAsJson(resp, 404, "The product not found", null);
            }
        } else {
            sendAsJson(resp, 200, "All the products", this.products);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        if(id == null || name == null || price == null) {
            sendAsJson(resp, 202, "Missing parameter", "");
            return;
        }

        Pattern intCheck = Pattern.compile("^[-+]?\\d+$");
        if(!intCheck.matcher(id).matches()) {
            sendAsJson(resp, 400, "Id must be an integer", "");
            return;
        }

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            sendAsJson(resp, 400, "Price must be a number", "");
            return;
        }

        if(name.isEmpty()) {
            sendAsJson(resp, 400, "Name is empty", "");
            return;
        }

        double priceParam = Double.parseDouble(price);
        int idParam = Integer.parseInt(id);

        Optional<Product> findProduct = products.stream()
                .filter(p -> p.getId() == idParam)
                .findFirst();

        if(findProduct.isPresent()) {
            sendAsJson(resp, 202, "Failed, id has existed", "");
            return;
        }

        products.add(new Product(idParam, name, priceParam));

        sendAsJson(resp, 200, "Successfully to added", products);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        if(id == null || name == null || price == null) {
            sendAsJson(resp, 202, "Missing parameter", "");
            return;
        }

        Pattern intCheck = Pattern.compile("^[-+]?\\d+$");
        if(!intCheck.matcher(id).matches()) {
            sendAsJson(resp, 400, "Id must be an integer", "");
            return;
        }

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            sendAsJson(resp, 400, "Price must be a number", "");
            return;
        }

        if(name.isEmpty()) {
            sendAsJson(resp, 400, "Name is empty", "");
            return;
        }

        double priceParam = Double.parseDouble(price);
        int idParam = Integer.parseInt(id);

        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getId() == idParam)
                .findFirst();

        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setPrice(priceParam);
            sendAsJson(resp, 200, "Successfully to updated", products);
        } else {
            sendAsJson(resp, 202, "Failed, id doesn't existed", "");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            Optional<Product> optionalProduct = products.stream()
                    .filter(p -> p.getId() == Integer.parseInt(id))
                    .findFirst();
            if(optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                products.remove(product);
                sendAsJson(resp, 200, "Successfully to deleted", products);
            } else {
                sendAsJson(resp, 404, "Not found product", "");
            }
        } else {
            sendAsJson(resp, 202, "Missing parameter", "");
        }
    }
}
