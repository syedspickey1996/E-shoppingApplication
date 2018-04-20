/*package com.niit.shoppingcart.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Product;
import com.niit.util.FileUtil;
@Controller
public class ProductController {


	// we need to call ProductDAO methods
	// get,save,update,delete,list

	// 1. inject the ProductDAO and Product
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	
	@Autowired
	private SupplierDAO supplierDAO;


	@Autowired
	private Product product;

	@Autowired
	HttpSession httpSession;
	
	private static final String imageDirectory = "resources//images//";
	private static String rootPath = System.getProperty("catalina.home");


	@GetMapping("/product/get/{id}")
	public ModelAndView getProduct(@PathVariable("id") String id)
	{
		product = productDAO.get(id);
		
		ModelAndView mv = new ModelAndView("redirect:/");
		mv.addObject("selectedproduct", product);
		mv.addObject("isUserSelectedProduct", true);
		
		mv.addObject("selectedProductImage", 
				rootPath +File.separator +imageDirectory +File.separator +id + ".PNG");
		
		return mv;
	}
	
	//first selected product and second selected product
	//are appending in the URL - and hence getting 404
	//http://localhost:8080/shoppingcart/product/get/prd001  - 1st
	//http://localhost:8080/shoppingcart/product/get/prd001/product/get/prd003  - 1st
	// Get select product details
	@GetMapping("/product/get/{id}")
		public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
			
			ModelAndView mv = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("selectedProduct",  productDAO.get(id));
			redirectAttributes.addFlashAttribute("isUserSelectedProduct",  true);
			redirectAttributes.addFlashAttribute("selectedProductImage", rootPath +File.separator +imageDirectory +File.separator +id + ".PNG");
			return mv;

		}
	
	

	@PostMapping("/product/save/")
	public ModelAndView saveProduct(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("price") String price,
			@RequestParam("categoryID") String categoryID,
			@RequestParam("supplierID") String supplierID,
			@RequestParam("file") MultipartFile file
			
			) {

		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		price = price.replace(",","");
		product.setPrice(Integer.parseInt(price));
		//product.setCategory(categoryDAO.get(categoryID));
		//product.setSupplier(supplierDAO.get(supplierID));
		product.setCategoryId(categoryID);
		product.setSupplierId(supplierID);
		if (productDAO.save(product)) {
			mv.addObject("productSuccessMessage", "The product created successfully");
			// call upload image method
			if(FileUtil.fileCopyNIO(file, id +".PNG"))
			{
				mv.addObject("uploadMessage", "product image successfully updated");
			}
			else
			{
				mv.addObject("uploadMessage", "Coulod not upload image");
			}
		} else {
			mv.addObject("productErrorMessage", "Could not able to create product.  please contact admin");
		}
		return mv;

	}

	@PutMapping("/product/update/")
	public ModelAndView updateProduct(@ModelAttribute Product product) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

		// call save method of productDAO
		if (productDAO.update(product) == true) {
			// add success message
			mv.addObject("successMessage", "The product updated successfully");
		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not update the product.");

		}
		return mv;

	}

	@GetMapping("/product/delete")
	public ModelAndView deleteProduct(@RequestParam String id) {
		System.out.println("going to delete product : " + id);
		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		// we supposed to fetch the latest categories
		// and add to httpSession
		// based on id, fetch the details from productDAO
		if (productDAO.delete(id) == true) {
			// add success message
			mv.addObject("successMessage", "The product deleted successfully");

		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not delete the product.");

		}

		return mv;

	}

	@GetMapping("/product/edit")
	public ModelAndView editProduct(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		// based on product id fetch product details.
		product = productDAO.get(id);
		// mv.addObject("selectedProduct", product);
		httpSession.setAttribute("selectedProduct", product);

		return mv;
	}

	@GetMapping("/products")
	public ModelAndView getAllCategories() {
		ModelAndView mv = new ModelAndView("home");
		List<Product> categories = productDAO.list();
		mv.addObject("products", categories);
		return mv;
	}
	
	 * @GetMapping("/product/edit") public ModelAndView editProduct(@RequestParam
	 * String id) { ModelAndView mv = new
	 * ModelAndView("redirect:/manageproducts");
	 * 
	 * product = productDAO.get(id);
	 * 
	 * httpSession.setAttribute("product", product); return mv;
	 * 
	 * }
	 



}*/








package com.niit.shoppingcart.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Product;
import com.niit.util.FileUtil;
@Controller
public class ProductController {


	// we need to call ProductDAO methods
	// get,save,update,delete,list

	// 1. inject the ProductDAO and Product
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	
	@Autowired
	private SupplierDAO supplierDAO;


	@Autowired
	private Product product;

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	FileUtil fileUtil;
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	//private static final String imageDirectory = "ShoppingCartImages";
	//private static String rootPath = System.getProperty("catalina.home");
    private static String rootPath = "C:\\Users\\syeds\\eclipse-workspace\\ShoppingCartFrontEnd\\src\\main\\webapp\\resources\\images";

/*	@GetMapping("/product/get/{id}")
	public ModelAndView getProduct(@PathVariable("id") String id)
	{
		product = productDAO.get(id);
		
		ModelAndView mv = new ModelAndView("redirect:/");
		mv.addObject("selectedproduct", product);
		mv.addObject("isUserSelectedProduct", true);
		
		mv.addObject("selectedProductImage", 
				rootPath +File.separator +imageDirectory +File.separator +id + ".PNG");
		
		return mv;
	}*/
	
	//first selected product and second selected product
	//are appending in the URL - and hence getting 404
	//http://localhost:8080/shoppingcart/product/get/prd001  - 1st
	//http://localhost:8080/shoppingcart/product/get/prd001/product/get/prd003  - 1st
	// Get select product details
	@GetMapping("/product/get/{id}")
		public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
			
			ModelAndView mv = new ModelAndView("redirect:/");
			redirectAttributes.addFlashAttribute("selectedProduct",  productDAO.get(id));
			redirectAttributes.addFlashAttribute("isUserSelectedProduct",  true);
		//	redirectAttributes.addFlashAttribute("productID", rootPath +File.separator +imageDirectory +File.separator +id + ".PNG");
			
			return mv;

		}
	
	

	@PostMapping("/product/save/")
	public ModelAndView saveProduct(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("price") String price,
			@RequestParam("categoryID") String categoryID,
			@RequestParam("supplierID") String supplierID,
			@RequestParam("file") MultipartFile file
			
			) {

		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		price = price.replace(",","");
		product.setPrice(Integer.parseInt(price));
		//product.setCategory(categoryDAO.get(categoryID));
		//product.setSupplier(supplierDAO.get(supplierID));
		product.setCategoryId(categoryID);
		product.setSupplierId(supplierID);
		if (productDAO.save(product)) {
			mv.addObject("productSuccessMessage", "The product created successfully");
			// call upload image method
			if(fileUtil.fileCopyNIO(file, id +".PNG"))
			{
				mv.addObject("uploadMessage", "product image successfully updated");
			}
			else
			{
				mv.addObject("uploadMessage", "Coulod not upload image");
			}
		} else {
			mv.addObject("productErrorMessage", "Could not able to create product.  please contact admin");
		}
		return mv;

	}

	@PutMapping("/product/update/")
	public ModelAndView updateProduct(@ModelAttribute Product product) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

		// call save method of productDAO
		if (productDAO.update(product) == true) {
			// add success message
			mv.addObject("successMessage", "The product updated successfully");
		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not update the product.");

		}
		return mv;

	}

	@GetMapping("/product/delete")
	public ModelAndView deleteProduct(@RequestParam String id) {
		System.out.println("going to delete product : " + id);
		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		// we supposed to fetch the latest categories
		// and add to httpSession
		// based on id, fetch the details from productDAO
		if (productDAO.delete(id) == true) {
			// add success message
			mv.addObject("successMessage", "The product deleted successfully");

		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not delete the product.");

		}

		return mv;

	}

	@GetMapping("/product/edit")
	public ModelAndView editProduct(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		// based on product id fetch product details.
		product = productDAO.get(id);
		// mv.addObject("selectedProduct", product);
		httpSession.setAttribute("selectedProduct", product);

		return mv;
	}

	@GetMapping("/products")
	public ModelAndView getAllCategories() {
		ModelAndView mv = new ModelAndView("home");
		List<Product> categories = productDAO.list();
		mv.addObject("products", categories);
		return mv;
	}
	@GetMapping("search")
	public ModelAndView searchProduct(@RequestParam("searchString") String searchString)
	{
		ModelAndView mv = new ModelAndView("home");
		List<Product> products =  productDAO.search(searchString);
		mv.addObject("products", products);
		mv.addObject("isUserSelectedProduct", true);
		log.info("Number of products with search string " +searchString +  " is/are : " + products.size());
		return mv;
		
	}
	
	


	
	
	

}
