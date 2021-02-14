package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<User> theUsers = userService.getUsers();
        theModel.addAttribute("users", theUsers);
        return "list-users";
    }

    //showFormUser
    @GetMapping("/showFormUser")
    public String showFormUser(Model theModel, HttpSession session ) {
        String name = (String) session.getAttribute("userName");
        theModel.addAttribute("user", userService.getUserByName(name));
        theModel.addAttribute("roles", roleService.findAll());
        return "user-form";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("roles", roleService.findAll());
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User theUser, @RequestParam("roles") String[] roles) {
        String encode = theUser.getPassword();

        if (theUser.getId() != 0) { // update user
            theUser = userService.getUser(theUser.getId());
            if (!encode.isEmpty()) { //  password changed
                passwordChanged(theUser, encode);
            }
        } else { //new user
            passwordChanged(theUser, encode);
        }

        theUser.getRoles().clear();
        for (String r : roles) {
            theUser.addRole(roleService.getRole(Integer.parseInt(r)));
        }

        userService.saveUser(theUser);
        return "redirect:/users/list";
    }

    private void passwordChanged(@ModelAttribute("user") User theUser, String encode) {
        encode = passwordEncoder.encode(encode);
        theUser.setPassword(encode);
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId,
                                    Model theModel) {

        User theUser = userService.getUser(theId);
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("roles", roleService.findAll());
        return "user-form";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        userService.deleteUser(theId);
        return "redirect:/users/list";
    }
}
