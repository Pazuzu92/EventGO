package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.service.CityService;
import com.innopolis.eventgo.service.PostService;
import com.innopolis.eventgo.service.UserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountPageController {

    private final CityService cityService;
    private final PostService postService;
    private final UserService userService;

    public AccountPageController(UserService userService,
                                 CityService cityService,
                                 PostService postService) {
        this.userService = userService;
        this.cityService = cityService;
        this.postService = postService;
    }

    @SneakyThrows
    @GetMapping("/{userId}")
    public String load(@PathVariable() long userId,
                       Model model) {
        UserDto userDto = userService.findUserDto(userId);

        List<PostDto> posts = postService.getPostsByAuthor(Optional.of(userId),
                Optional.of(0),
                Optional.of(10),
                Optional.of("dateFrom"));

        List<Post> groups = postService.getGroupsPosts(Optional.of(userId));

        model.addAttribute("userName", userDto);
        model.addAttribute("posts", posts);
        model.addAttribute("groups", groups);
        model.addAttribute("userId", userDto.getId());

        return "account";
    }
}