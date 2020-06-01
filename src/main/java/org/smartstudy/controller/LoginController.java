package org.smartstudy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.smartstudy.model.User;
import org.smartstudy.services.FilesService;
import org.smartstudy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller

public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private FilesService dfilestorageService;

	

	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		return "jsp/home";
	}

	@RequestMapping("/aboutus")
	public String aboutus(HttpServletRequest request) {
		return "jsp/aboutus";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, ModelMap map) {

		return "jsp/login";
	}

	@RequestMapping("/userdashboard")
	public String showAllUsers(HttpServletRequest request, ModelMap model, User u, HttpSession session) {
        Long userid=(Long) session.getAttribute("userid");
		request.setAttribute("dbfiles", dfilestorageService.showAllUsers()); // Fetching all users data form database
		return "jsp/userdashboard";
	}

	
	
	private void addUserInSession(User u, HttpSession session) {
		session.setAttribute("user", u);
		session.setAttribute("username", u.getUsername());
		session.setAttribute("userid", u.getId());
	}
	
	@RequestMapping("/logout")
   public String logout(HttpSession session) {
	   session.invalidate();
	   
	   return "redirect:/login";
   }
 
	@PostMapping("/login-user")
	public String loginStudent(User u, ModelMap model, HttpSession session) {
		
		
	  User loggedInUser= userService.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		
		
		if (userService.findByUsernameAndPassword(u.getUsername(), u.getPassword()) != null) {
			session.setAttribute("userid", u.getId());
		
			addUserInSession(loggedInUser, session);
			return "redirect:/userdashboard";
		} else {
			model.addAttribute("error", "Invalid Username or password");
			return "jsp/login";
		}
	}



}
