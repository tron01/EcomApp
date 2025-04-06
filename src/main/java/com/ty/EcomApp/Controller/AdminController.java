package com.ty.EcomApp.Controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ty.EcomApp.Model.CustomUserDetail;
import com.ty.EcomApp.Model.User;
import com.ty.EcomApp.Repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserRepository userRepository; 
	
	
	public AdminController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@GetMapping("/")
	private String adminhome(@AuthenticationPrincipal CustomUserDetail user,Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getAuthorities().toString());
		return "admin_dashboard";
	}
	

    @GetMapping("/user_list")
    public String userList(Model model) {
        List<User> users = (List<User>) userRepository.findAll();
        model.addAttribute("users", users);
        return "user_list"; 
    }
}
