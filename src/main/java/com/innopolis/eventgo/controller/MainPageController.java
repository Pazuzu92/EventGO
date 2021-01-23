package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.service.CategoryService;
import com.innopolis.eventgo.service.CityService;
import com.innopolis.eventgo.service.PostService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final CategoryService categoryService;
    private final CityService cityService;
    private final PostService postService;

    public MainPageController(CategoryService categoryService, CityService cityService, PostService postService) {
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.postService = postService;
    }

    @SneakyThrows
    @GetMapping(value = {"{cityName}", ""})
    public String load(@PathVariable(required = false) Optional<String> cityName, Model model) {
        List<Category> categories = categoryService.findAll();
        List<City> cities = cityService.findAll();
        List<Post> posts = Collections.singletonList(postService.getPost(1L));
        model.addAttribute("categories", categories);
        model.addAttribute("cities", cities);
        model.addAttribute("cityName", cityName.orElse("Выберите город"));
        model.addAttribute("posts", posts);
        return "main";
    }
}
