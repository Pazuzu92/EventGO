package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.PostStatus;
import com.innopolis.eventgo.dto.CategoryDto;
import com.innopolis.eventgo.dto.CityDto;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.service.CategoryService;
import com.innopolis.eventgo.service.CityService;
import com.innopolis.eventgo.service.PostService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final CategoryService categoryService;
    private final CityService cityService;
    private final PostService postService;

    public MainPageController(CategoryService categoryService,
                              CityService cityService,
                              PostService postService) {
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.postService = postService;
    }

    @SneakyThrows
    @GetMapping(value = {"{cityName}", ""})
    public String load(@PathVariable(required = false) Optional<String> cityName,
                       @RequestParam(required = false, defaultValue = "Спорт") Optional<String> category,
                       Model model) {
        List<CategoryDto> categories = categoryService.findAll();
        List<CityDto> cities = cityService.findAll();

        List<PostDto> posts = postService.getPostsByFilter(
                cityName,
                category,
                Optional.of(PostStatus.ACTIVE),
                Optional.of(0),
                Optional.of(3),
                Optional.of("id"));

        model.addAttribute("categories", categories);
        model.addAttribute("cities", cities);
        model.addAttribute("cityName", cityName.orElse("Выберите город"));
        model.addAttribute("posts", posts);
        return "main";
    }
}
