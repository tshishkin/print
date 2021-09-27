package com.example.print.Controller;

import com.example.print.Model.User;
import com.example.print.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    UsersService usersService;

    /*GET*/
    @GetMapping("/main")
    public ModelAndView mainPage() {
        return new ModelAndView("/mainPage");
    }

    @GetMapping("/users")
    public ModelAndView usersPage() {
        ModelAndView result = new ModelAndView("/usersPage");
        result.addObject("users", usersService.getAll());
        return result;
    }

    @GetMapping("/createUser")
    public ModelAndView newUserPage() {
        return new ModelAndView("/newUserPage");
    }

    @GetMapping("/users/print/{userId}")
    public ModelAndView printUserTemplatePage(@PathVariable Long userId) {
        ModelAndView result = new ModelAndView("/printUserPage");
        User user = usersService.getUserById(userId);
        result.addObject("user", user);
        return result;
    }

    @ResponseBody
    @GetMapping( value = "/users/print/image/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] generateImage(@PathVariable Long userId) throws IOException {
        User user = usersService.getUserById(userId);
        return usersService.drawPass(user);
    }

    /*POST*/
    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute("model") User model) {
        if (StringUtils.isEmpty(model.getPassportNumber()) ||
                StringUtils.isEmpty(model.getPassportSerial()) ||
                StringUtils.isEmpty(model.getFirstName()) ||
                StringUtils.isEmpty(model.getLastName()) ||
                StringUtils.isEmpty(model.getMiddleName())) {
            ModelAndView result = new ModelAndView("/newUserPage");
            result.addObject("validationMessage", true);
            return result;
        }
        usersService.addNewUser(model);
        return new ModelAndView("redirect:/users");
    }

    @PostMapping("/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return  new ModelAndView("redirect:/users");
    }

}
