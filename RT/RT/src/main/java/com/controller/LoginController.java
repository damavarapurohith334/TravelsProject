package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/signup")
	public String redirectToSignUp(HttpServletRequest req, Model model) {
		return "signup";
	}

	@RequestMapping("/signin")
	public String redirectToLogin(HttpServletRequest req, Model model) {
		return "signin";
	}

	@RequestMapping("/validate-login")
	public String validateLogin(HttpServletRequest req, Model model) {
		System.out.println("Validater Invoked --");
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("password");
		System.out.println(userName);
		System.out.println(passWord);

		if (userName.equals("Admin") && passWord.equals("Admin")) {
			model.addAttribute("userName", userName);
			return "travel-book";
		} else {
			return "error";
		}

	}
	
	@RequestMapping("/create-user")
	public String createUser(HttpServletRequest incomingRequest, Model model) {
		String email = incomingRequest.getParameter("email");
		String password = incomingRequest.getParameter("password");
		String confirmPassword = incomingRequest.getParameter("confirm-password");
		String dob = incomingRequest.getParameter("dob");
		
		System.out.println(String.format(" Details are email %s and password is %s and confirm password is %s and dob is %s ",email,password,confirmPassword,dob)
	);
		int rowCount = jdbcTemplate.update("INSERT INTO newgen_user (`email`, `password`) VALUES (?, ?)",email,password);
		System.out.println("Rows INserted "+rowCount);
		model.addAttribute("email",email);
		
		
		
		return "travel-book";
	}

}
