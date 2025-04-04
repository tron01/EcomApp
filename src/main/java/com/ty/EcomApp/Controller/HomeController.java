package com.ty.EcomApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
