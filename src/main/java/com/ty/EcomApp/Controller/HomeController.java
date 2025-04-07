package com.ty.EcomApp.Controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ty.EcomApp.Model.User;
import com.ty.EcomApp.Repository.UserRepository;
import com.ty.EcomApp.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	private final UserService service;
	private final UserRepository repository;

	public HomeController(UserService service, UserRepository repository) {
		this.service = service;
		this.repository = repository;
	}


	@GetMapping("/")
	private String home(Model model ,Principal principal) {
		//get user login info on home page
		if(principal!=null) {
			model.addAttribute("username",principal.getName());
		}
		//get role
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     String role = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .findFirst().orElse("ROLE_USER");

	     model.addAttribute("role", role);
		return "home";
	}
	
	@GetMapping("/login")
	String login(HttpServletRequest request) {
		// If user is already authenticated, redirect to home
        if (request.getUserPrincipal() != null) {
            return "redirect:/";
        }
        // Show login page if not authenticated
        return "login";
	}
	
	@GetMapping("/signup")
	private String signup(Model model) {
		 // Ensure 'user' is added to the model for using thyemleaf -> th:object="${user}" on post request. 
		 model.addAttribute("user", new User()); 
	 return "signup";
	}
	
	@PostMapping("/signup")
	private String create_user(@ModelAttribute User user, Model model) {
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match!");
            return "signup";
        }
        if(repository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }
        User u1=service.create_user(user);
		System.out.println(u1);
        return "redirect:/login"; // Redirect to login page with success message		
	}	

}
