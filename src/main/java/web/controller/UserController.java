package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String showFormUser(Model theModel, Authentication authentication) {
        String name = authentication.getName();
        theModel.addAttribute("user", userService.getUserByName(name));
        return "user-no-editable-form";
    }
}
