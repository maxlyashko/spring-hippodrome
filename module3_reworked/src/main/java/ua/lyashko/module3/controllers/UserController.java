package ua.lyashko.module3.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.lyashko.module3.model.User;
import ua.lyashko.module3.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController ( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/hello1")
    public String ma () {
        return "hello";
    }

    @GetMapping("/registration")
    public String getRegistration ( Model model ) {
        model.addAttribute ( "user" , new User ( ) );
        return "registration_user";
    }

    @PostMapping("/registration")
    public String postRegistration ( @ModelAttribute User user ,
                                     Model model ) {
        if (user == null) {
            return "registration_user";
        }
        if (userService.loginValidation ( user, model )) {
            return "registration_user";
        } else {
            userService.saveUser ( user );
        }
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getList ( Model model ) {
        model.addAttribute ( "users" , userService.getAllUsers ( ) );
        return "users";
    }

    @GetMapping("/login")
    public String getLogin ( Model model ) {
        model.addAttribute ( "user" , new User ( ) );
        return "login_user";
    }

    @PostMapping("/login")
    public String postLogin ( @ModelAttribute User user ,
                              Model model ) {
        if (user == null) {
            return "login_user";
        }
        model.addAttribute ( "name" , userService.userValidation ( user ) );
        return "hello";
    }


    @GetMapping("/login/reset")
    public String getLoginReset ( Model model ) {
        model.addAttribute ( "user" , new User ( ) );
        return "login_reset";
    }

    @PostMapping("/login/reset")
    public String postLoginReset ( @ModelAttribute User user ) {
        if (user == null) {
            return "login_reset";
        }
        userService.resetPassword ( user );
        return "redirect:/list";
    }
}
