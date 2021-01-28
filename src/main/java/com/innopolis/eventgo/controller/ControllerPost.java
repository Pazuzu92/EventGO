package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.repository.UserRepository;
import com.innopolis.eventgo.dto.CategoryDto;
import com.innopolis.eventgo.dto.CityDto;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.CategoryService;
import com.innopolis.eventgo.service.CityService;
import com.innopolis.eventgo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class ControllerPost {

    @Autowired
    private PostService postService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, value = "/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Authentication authentication) throws NotFoundException {
        User principal = null;
        boolean isAuthorized = false;
        if (authentication != null && authentication.isAuthenticated()) {
            principal = (User) authentication.getPrincipal();
            isAuthorized = true;
        }


        model.addAttribute("user", principal);
        model.addAttribute("isAuthorized", isAuthorized);
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("cityList", cityService.findAll());

        return "pages/post";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/photo/{id}", produces = "image/jpg")
    public ResponseEntity getImage(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok().body(postService.getPost(id).getPhoto());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/create")
    public String createPost(Authentication authentication, Model model) {
        User principal = (User) authentication.getPrincipal();
        com.innopolis.eventgo.db.entity.User user = userRepository.getUser(principal.getUsername());

        PostDto postDto = new PostDto();
        CategoryDto categoryDto = new CategoryDto();
        CityDto cityDto = new CityDto();
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        postDto.setCategory(categoryDto);
        postDto.setCity(cityDto);
        postDto.setAuthor(userDto);

        Long userId = user.getId();

        model.addAttribute("postDto", postDto);
        model.addAttribute("user", principal);
        model.addAttribute("cityList", cityService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("userId", userId);

        return "pages/create_post";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post/create")
    public String createPost(@RequestParam("img") MultipartFile file,
                             @ModelAttribute PostDto postDto,
                             Authentication authentication, Model model) throws IOException, NotFoundException {

        postDto.setPhoto(file.getBytes());
        postService.createPost(postDto);

        return createPost(authentication, model);
    }

}
