package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.RoleService;
import com.innopolis.eventgo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/roles")
    public String rolesList(Model model) {
        model.addAttribute("all_roles", roleService.findAll());
        model.addAttribute("new_role", new Role());
        return "admin/role_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/roles/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/admin/roles";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/roles")
    public String saveRole(@ModelAttribute("new_role") Role role) {
        roleService.save(role);
        return "redirect:/admin/roles";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("all_users", userService.findAll());
        return "admin/user_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("edit_user", userService.findUser(id));
        model.addAttribute("all_roles", roleService.findAll());
        return "admin/edit_user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    public String saveUser(@ModelAttribute("edit_user") User editUser) throws NotFoundException {
        UserDto currentUser = userService.findUser(editUser.getId());
        userService.update(currentUser, editUser);
        return "redirect:/admin/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
