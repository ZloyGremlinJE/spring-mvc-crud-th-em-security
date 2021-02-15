package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/showFormUser")
    public String showFormUser(Model theModel, HttpSession session) {
        String name = (String) session.getAttribute("userName");
        theModel.addAttribute("user", userService.getUserByName(name));
        return "user-no-editable-form";
    }



}
