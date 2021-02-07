package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.RoleName;
import web.model.User;
import web.service.UserService;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String listCustomers(Model theModel) {
        List<User> theUsers = userService.getUsers();
        theModel.addAttribute("users", theUsers);
        return "list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("roles", RoleName.values());
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User theUser, @RequestParam("role_name") String[] theRoleName) {
        Set<Role> roles = theUser.getRoles();
        if (roles != null) {
            roles.clear();
        }
        for (String r : theRoleName) {
            theUser.addRole(new Role(r));
        }
        userService.saveUser(theUser);
        return "redirect:/";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId,
                                    Model theModel) {
        User theUser = userService.getUser(theId);
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("roles", theUser.getRoles());
        return "user-form";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        userService.deleteUser(theId);
        return "redirect:/";
    }
}
