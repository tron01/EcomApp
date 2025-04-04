package com.ty.EcomApp.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@GetMapping("/")
	private String home() {
		return "home";
	}
	@GetMapping("/login")
	String login() {
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
