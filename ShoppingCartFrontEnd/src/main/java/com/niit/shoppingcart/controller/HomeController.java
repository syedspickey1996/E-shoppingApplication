package com.niit.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class HomeController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@Autowired
	private HttpSession httpSession;
	
	// http://localhost:8080/ShoppingCartFrontEnd/
	@GetMapping("/")
	public ModelAndView home() {

		ModelAndView mv = new ModelAndView("home");
		// we need to fetch all the categories
		// Autowire CategoryDAO and category
		List<Category> categories = categoryDAO.list();
		// add the data to mv
		//mv.addObject("categories", categories);
		httpSession.setAttribute("categories", categories);
		
		
		return mv;

	}
	
	
	@GetMapping("/login")
	public ModelAndView login() {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedLogin", true);
		return mv;

	}
	
	@GetMapping("/logout")
	public ModelAndView logout()
	{
		//at the time of login, we add user id in http session
		//at the time of logout, we need to remove user id from http session.
		ModelAndView mv = new ModelAndView("home");
		
		httpSession.invalidate();
		
		mv.addObject("logoutMessage", "You are successfully logged out");
		return mv;
		
	}

	@GetMapping("/register")
	public ModelAndView registration() {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedRegister", true);
		return mv;

	}

}