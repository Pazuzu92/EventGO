package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.dto.UserRegistrationDto;
import com.innopolis.eventgo.service.UserService;
import com.innopolis.eventgo.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserValidator userValidator;

    public RegistrationController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult,
                               Model model) {

        userValidator.validate(userRegistrationDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User registered = userService.save(userRegistrationDto);
        model.addAttribute("user", registered);

        return "redirect:/account/" + registered.getId();
    }
}
