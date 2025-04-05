package com.ty.EcomApp.Controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@GetMapping("/")
	private String home(Model model ,Principal principal) {
		//get user login info on home page
		if(principal!=null) {
			model.addAttribute("username",principal.getName());
		}
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
	@GetMapping("/logout")
	void logout(HttpServletRequest  request) {
		 // Manually invalidate session
        request.getSession().invalidate();
        // Remove authentication details
        SecurityContextHolder.clearContext();
	}
}
