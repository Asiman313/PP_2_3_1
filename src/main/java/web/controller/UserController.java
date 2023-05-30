package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "showUsers";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.readUser(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("{id}/del")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
