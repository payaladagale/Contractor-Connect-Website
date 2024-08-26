package br.com.carlosjunior.registrationlogin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.carlosjunior.registrationlogin.services.UserService;
import br.com.carlosjunior.registrationlogin.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/userregistration")
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "user-register";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model) {
	    try {
	        userService.save(registrationDto);
	        return "redirect:/userregistration?success";
	    } catch (IllegalArgumentException e) {
	        model.addAttribute("errorMessage", e.getMessage());
	        return "user-register";
	    }
	}

}

