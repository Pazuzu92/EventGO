package com.innopolis.eventgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class SearchController {

    @GetMapping({"/search", "{cityName}/search"})
    public String search(@PathVariable(required = false) Optional<String> cityName,
                         @ModelAttribute String searchInput,
                         Model model) {
        return null;
    }
}
