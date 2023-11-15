package tdtu.edu.vn.Lab08.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse reponse;
    @GetMapping("/")
    public String homepage() {
        return "index";
    }
    @GetMapping("/contact")
    public String contactpage() {
        return "contact";
    }
    @PostMapping("/contact")
    public String contactpagePOST(Model model) {
        String fullname = request.getParameter("fullname");
        String age = request.getParameter("age");

        model.addAttribute("fullname", fullname);
        model.addAttribute("age", age);
        return "result";
    }
    @GetMapping("/about")
    @ResponseBody
    public String about() {
        return "About this site" ;
    }
}
