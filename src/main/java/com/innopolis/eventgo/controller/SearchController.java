package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Category;
import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.dto.CategoryDto;
import com.innopolis.eventgo.dto.CityDto;
import com.innopolis.eventgo.service.CategoryService;
import com.innopolis.eventgo.service.CityService;
import com.innopolis.eventgo.service.PostService;
import com.innopolis.eventgo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    private final PostService postService;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final UserService userService;

    public SearchController(PostService postService, CityService cityService, CategoryService categoryService, UserService userService) {
        this.postService = postService;
        this.cityService = cityService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping({"/search", "{cityName}/search"})
    public String searchByText(@PathVariable String cityName,
                               @RequestParam String text,
                               @RequestParam(required = false) Optional<Long> categ,
                               Model model,
                               Authentication authentication) {

        List<CategoryDto> categories = categoryService.findAll();
        List<CityDto> cities = cityService.findAllExceptBy(cityName);
        City city = cityService.findByShortName(cityName);
        Category category = categoryService.findById(categ);
        Long userId = null;
        String login = "";

        if (authentication != null) {
            login = authentication.getName();
            Optional<User> user = userService.findByLogin(login);

            if (user.isPresent()) {
                userId = user.get().getId();
            }
        }

        List<Post> posts = postService.findAllLikeBy(text, city, category);

        model.addAttribute("posts", posts);
        model.addAttribute("cities", cities);
        model.addAttribute("categories", categories);
        model.addAttribute("userId", userId);
        model.addAttribute("login", login);
        model.addAttribute("currentCity", city);

        return "search";
    }
}
