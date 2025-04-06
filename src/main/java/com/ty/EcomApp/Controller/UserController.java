package com.ty.EcomApp.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ty.EcomApp.Model.User;
import com.ty.EcomApp.Repository.UserRepository;

@Controller
@RequestMapping("/admin/")
public class UserController {

	private final UserRepository userRepository;
	
    public UserController(UserRepository repository) {
		this.userRepository = repository;
	}

	@GetMapping("/users")
    public String userList(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "user_list"; 
    }
    
    // Show edit user form
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "edituser"; 
    }

    //user update
    @PostMapping("/User_update")
    public String updateUser(@ModelAttribute User user, Model model) {
        userRepository.save(user); // Save updated user data
        return "redirect:/admin/users"; // Redirect to user list
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
