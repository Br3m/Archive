package com.example.Archive;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import com.example.Archive.model.Image;
import com.example.Archive.service.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Archive.dto.UserDto;
import com.example.Archive.service.UserService;
import com.example.Archive.model.*;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private UserService userService;
	public UserController(UserService userService) {
	
		this.userService = userService;
	}
	@GetMapping("/userpage")
	public String userpage(){
		return "userpage";
	}

	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail" , userDetails);
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model, UserDto userDto) {
		model.addAttribute("user", userDto);
		return "login";
	}
	
	
	@GetMapping("/register")
	public String register(Model model, UserDto userDto) {
		
		model.addAttribute("user", userDto);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
		User user = userService.findByUsername(userDto.getUsername());
		if (user != null) {
			model.addAttribute("userexist", user);
			return "register";
			
		}
		userService.save(userDto);
		return "redirect:/register?success";
	}

}
