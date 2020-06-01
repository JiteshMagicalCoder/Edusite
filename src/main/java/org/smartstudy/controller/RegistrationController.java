package org.smartstudy.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.smartstudy.config.Encryptor;
import org.smartstudy.model.User;
import org.smartstudy.model.UserFiles;
import org.smartstudy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController{
	
@Autowired
private UserService userService;
    

@Autowired
private Encryptor encryptor;

	@GetMapping(value="/registerthym")
	public String users(Model model)
	{
		List<User> users= userService.getAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("user", new User());
		model.addAttribute("userfiles", new ArrayList<UserFiles>());
		model.addAttribute("isAdd", true);                       //using same form for edit and create also for dat purpose dis is created
		return "jsp/register";
	}
	
	@PostMapping(value="/save")
	public String save(@Valid @ModelAttribute User user,BindingResult result, Model model, RedirectAttributes redirectAttributes )
	{
		
		User existing = userService.findByEmail(user.getEmail());
		  if (existing != null){
	            result.rejectValue("email", null, "There is already an account registered with that email");
	        }	
		  if (result.hasErrors()){
	            return "jsp/register";
	        }
		
		User dbuser= userService.save(user);
		if(dbuser!= null)
		{
			redirectAttributes.addFlashAttribute("successmessage", "user is saved successfully");
			return "redirect:/login";
		}
		else
		{
			redirectAttributes.addFlashAttribute("errormessage", "user is not saved ! please try again ");
			model.addAttribute("user",user);
			return "jsp/register";
			
		}
	}
	
	
	@GetMapping(value="/edituser/{userId}")
	public String edituser(@PathVariable Long userId,Model model) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		  
		User user= userService.findById(userId);
		if (user.getPassword() != null) {
			user.setPassword(encryptor.decrypt(user.getPassword()));
		} else {
			user.setPassword("");
		}
		List<UserFiles> userFiles= userService.findFilesByUserId(userId);
		List<User> users= userService.getAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("user", user);
		model.addAttribute("userfiles", userFiles);
		model.addAttribute("isAdd", false);                       
		return "thymeleaf/editForm";
	}
	
	@PostMapping(value="/update")
	public String update(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model )
	{
		
		user.setPassword(encryptor.encrypt(user.getPassword()));
		
		User dbuser= userService.update(user);
		if(dbuser!= null)
		{
			redirectAttributes.addFlashAttribute("successmessage", "user is updated successfully");
			return "redirect:userdashboard";
		}
		else
		{
			model.addAttribute("errormessage", "user is not updated ! please try again ");
			model.addAttribute("user",user);
			return "thymeleaf/editForm";
			
		}
	}
	
	
	@PostMapping(value="/cancel")
	public String cancel(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model )
	{
		
		user.setPassword(encryptor.encrypt(user.getPassword()));
		
		User dbuser= userService.update(user);

		
			
			return "redirect:userdashboard";
		
	
	}
	
}
