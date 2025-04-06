package com.ty.EcomApp.Controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ty.EcomApp.Model.CustomUserDetail;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/")
	private String adminhome(@AuthenticationPrincipal CustomUserDetail user,Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getAuthorities().toString());
		return "admin_dashboard";
	}
}
