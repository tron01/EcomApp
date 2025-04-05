package com.ty.EcomApp.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ty.EcomApp.Model.CustomUserDetail;

@Controller
public class AdminController {
	
	@GetMapping("/admin/")
	private String adminhome(@AuthenticationPrincipal CustomUserDetail user,Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getAuthorities().toString());
		return "admin_dashboard";
	}
}
