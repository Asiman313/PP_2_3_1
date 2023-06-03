package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

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
    public String newUser(@ModelAttribute User user) {
        return "newUser";
    }

    @PostMapping()
    public String create(@ModelAttribute() User user) {
        if(user.getName() != null && user.getLastName() != null && user.getAge() > 0) {
            userService.createUser(user);
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable() long id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.readUser(id));
        return "updateUser";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("{id}/del")
    public String deleteUser(@PathVariable() long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
