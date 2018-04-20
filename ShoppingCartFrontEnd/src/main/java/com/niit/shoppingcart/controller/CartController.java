/*package com.niit.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Product;

@Controller
public class CartController {
	
	Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired  private Cart cart;
	@Autowired  private Product product;
	@Autowired private HttpSession httpSession;
	
	@GetMapping("/buy")
	public ModelAndView order()
	{
		
		ModelAndView mv = new ModelAndView("home");
		
		//there should be on update method which take 
		//email id as paramter.
		String loggedInUserID =(String) httpSession.getAttribute("loggedInUserID");
		if (cartDAO.update(loggedInUserID))
		{
			mv.addObject("successMessage", "Your order placed successfully...");
		}
		else
		{
			mv.addObject("errorMessage", "Your order could placed.   please try after some time.");
		}
		
		return mv;
		
	}
	
	
	@PostMapping("/product/cart/add")
	public ModelAndView addToCart(@RequestParam String productName,
			@RequestParam int price, @RequestParam String quantity)
	
	{
		ModelAndView mv = new ModelAndView("home");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		
		cart.setEmailID(loggedInUserID);
		cart.setPrice(price);
		cart.setQuantity(Integer.parseInt(quantity));
		
		if(cartDAO.save(cart))
		{
			mv.addObject("successMessage", "The product add to cart successfully");
		}
		else
		{
			mv.addObject("errorMessage", "Could not add the product to cart..please try after some time");
		}
		return mv;
		
	}
	
	@GetMapping("/cart/add/{productID}")
	public ModelAndView addToCart(
			@PathVariable String productID	)
	
	{
		//ModelAndView mv = new ModelAndView("home");
		ModelAndView mv = new ModelAndView("redirect:/");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		//get the other details of product from productDAO.get()
		product = productDAO.get(productID);
		
		cart.setEmailID(loggedInUserID);
		cart.setPrice(product.getPrice());
		cart.setProductID(productID);
		cart.setProductName(product.getName());
		cart.setQuantity(1);
		cart.setId();  //to set a random number.
		if(cartDAO.save(cart))
		{
			mv.addObject("successMessage", "The product add to cart successfully");
		}
		else
		{
			mv.addObject("errorMessage", "Could not add the product to cart..please try after some time");
		}
		return mv;
		
	}
	
	
	//get my cart details
	@GetMapping("/mycart")
	public ModelAndView  getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		//it will return all the products which are added to cart
		//??
		String loggedInUserID = (String)httpSession.getAttribute("loggedInUserID");
		log.info("Logged in user id : " + loggedInUserID);
		if(loggedInUserID==null)
		{
		  mv.addObject("errorMessage", "Please login to see your cart details");
		  return mv;
		}
		 List<Cart> cartList = cartDAO.list(loggedInUserID);
		 mv.addObject("cartList", cartList);
		 log.debug("not of products in cart " + cartList.size());
		 log.debug("Ending of the method getMyCartDetails");
		 return mv;
	}
	
	
	
	
	
	
	
	
	

}













*/

package com.niit.shoppingcart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.domain.Cart;
import com.niit.shoppingcart.domain.Product;

@Controller
public class CartController {
	
	Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired  private Cart cart;
	@Autowired  private Product product;
	@Autowired private HttpSession httpSession;
	
	@GetMapping("/buy")
	public ModelAndView order()
	{
		
		ModelAndView mv = new ModelAndView("home");
		
		//there should be on update method which take 
		//email id as paramter.
		String loggedInUserID =(String) httpSession.getAttribute("loggedInUserID");
		if (cartDAO.update(loggedInUserID))
		{
			mv.addObject("successMessage", "Your order placed successfully...");
		}
		else
		{
			mv.addObject("errorMessage", "Your order placed.");
		}
		
		return mv;
		
	}
	
	
	/*@PostMapping("/product/cart/add")
	public ModelAndView addToCart(@RequestParam String productName,
			@RequestParam int price, @RequestParam String quantity)
	
	{
		ModelAndView mv = new ModelAndView("home");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		
		cart.setEmailID(loggedInUserID);
		cart.setPrice(price);
		cart.setQuantity(Integer.parseInt(quantity));
		
		if(cartDAO.save(cart))
		{
			mv.addObject("successMessage", "The product add to cart successfully");
		}
		else
		{
			mv.addObject("errorMessage", "Could not add the product to cart..please try after some time");
		}
		return mv;
		
	}*/
	
	@GetMapping("/cart/add/{productID}")
	public ModelAndView addToCart(
			@PathVariable String productID	)
	
	{
		//ModelAndView mv = new ModelAndView("home");
		ModelAndView mv = new ModelAndView("redirect:/");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		//get the other details of product from productDAO.get()
		product = productDAO.get(productID);
		
		cart.setEmailID(loggedInUserID);
		cart.setPrice(product.getPrice());
		cart.setProductID(productID);
		cart.setProductName(product.getName());
		cart.setQuantity(1);
		cart.setId();  //to set a random number.
		if(cartDAO.save(cart))
		{
			mv.addObject("successMessage", "The product add to cart successfully");
		}
		else
		{
			mv.addObject("errorMessage", "Could not add the product to cart..please try after some time");
		}
		return mv;
		
	}
	
	
	//get my cart details
	@GetMapping("/mycart")
	public ModelAndView  getMyCartDetails()
	{
		log.debug("Starting of the method getMyCartDetails");
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedMyCart", true);
		//it will return all the products which are added to cart
		//??
		String loggedInUserID = (String)httpSession.getAttribute("loggedInUserID");
		log.info("Logged in user id : " + loggedInUserID);
		if(loggedInUserID==null)
		{
		  mv.addObject("errorMessage", "Please login to see your cart details");
		  return mv;
		}
		 List<Cart> cartList = cartDAO.list(loggedInUserID);
		 mv.addObject("cartList", cartList);
		 log.debug("not of products in cart " + cartList.size());
		 log.debug("Ending of the method getMyCartDetails");
		 return mv;
	}
	
	
	
	
	
	
	
	
	

}



























