package com.ty.EcomApp.Controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

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
	

}
