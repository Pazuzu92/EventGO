package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Photo;
import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.repository.CityDAO;
import com.innopolis.eventgo.dto.PhotoDto;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ControllerPost {

    @Autowired
    private PostService postService;
    @Autowired
    private CityDAO cityDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Authentication authentication) throws NotFoundException {
        User principal = null;
        boolean isAuthorized = false;
        if (authentication != null && authentication.isAuthenticated()) {
            principal = (User) authentication.getPrincipal();
            isAuthorized = true;
        }

        List<PhotoDto> photos = new ArrayList<>();
        Post post = postService.getPostEntity(id);
        post.getPostPhotos().forEach(p -> {
            PhotoDto photoDto = new PhotoDto();
            photoDto.setId(p.getPhoto().getId());
            photoDto.setImage(p.getPhoto().getImage());
            photos.add(photoDto);
        });

        model.addAttribute("photos", photos);
        model.addAttribute("user", principal);
        model.addAttribute("isAuthorized", isAuthorized);
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("city", cityDAO.findAll());
        return "pages/post";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/photo/{id}", produces = "image/jpg")
    public ResponseEntity getImage(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(postService.getPhoto(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/post/create")
    public String createPost(Authentication authentication, Model model) {
        User principal = null;
        boolean isAuthorized = false;
        if (authentication != null && authentication.isAuthenticated()) {
            principal = (User) authentication.getPrincipal();
            isAuthorized = true;
        }
        model.addAttribute("user", principal);
        model.addAttribute("isAuthorized", isAuthorized);
        model.addAttribute("city", cityDAO.findAll());
        return "pages/create_post";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post/create")
    public String createPost(@RequestBody PostDto postDto, @RequestParam("img") MultipartFile[] multipartFile) {
        //TODO
        return "redirect:templates/main";
    }

}
