package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.PostService;
import com.innopolis.eventgo.service.RoleService;
import com.innopolis.eventgo.service.PostStatusService;
import com.innopolis.eventgo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;
    private final PostService postService;
    private final PostStatusService postStatusService;

    public AdminController(RoleService roleService, UserService userService, PostService postService, PostStatusService postStatusService) {
        this.roleService = roleService;
        this.userService = userService;
        this.postService = postService;
        this.postStatusService = postStatusService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/roles")
    public String rolesList(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("new_role", new Role());
        return "admin/roles";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/roles/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/admin/roles";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/roles")
    public String saveRole(@ModelAttribute("newRole") Role role) {

        if (!role.getRoleCode().isEmpty()) {
            roleService.save(role);
        }
        return "redirect:/admin/roles";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String usersList(Model model, Authentication authentication) {

        String currentUserLogin = authentication.getName();

        model.addAttribute("users", userService.findAllOrderById());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("currentUserLogin", currentUserLogin);

        return "/admin/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user")
    public String editUser(@RequestParam Long id,
                           @RequestParam Long role) throws NotFoundException {
        User user = userService.findUser(id);
        userService.updateByRole(user, role);

        return "redirect:/admin/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/posts")
    public String postsList(Model model) {

        model.addAttribute("posts", postService.findAllOrderById());
        model.addAttribute("statuses", postStatusService.findAll());

        return "/admin/posts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/post")
    public String editPost(@RequestParam Long id,
                           @RequestParam Long status)  {

        Optional<Post> post = postService.findById(id);
        post.ifPresent(value -> postService.updatePostByStatus(value, status));

        return "redirect:/admin/posts";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("")
    public String admin(){
        return "/admin/admin";
    }
}
