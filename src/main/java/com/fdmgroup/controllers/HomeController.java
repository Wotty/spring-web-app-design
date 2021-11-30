package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.ItemDAO;
import com.fdmgroup.daos.SiteUserDAO;
import com.fdmgroup.entities.Item;
import com.fdmgroup.entities.SiteUser;

@Controller
public class HomeController {

	//Entity Manager
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("finalproject");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	//Initialise method
	@RequestMapping(value="/")
	public String login(Model model) {
		model.addAttribute("user",new SiteUser());
		return "login";
	}
	//Login method
	@RequestMapping(value = "submitLogin", method = POST)
	public String loginSubmitHandler(Model model, SiteUser user, HttpServletRequest request) {
		model.addAttribute("user", user);

		String username = user.getUsername();
		String password = user.getPassword();

		SiteUserDAO userDAO = new SiteUserDAO();
		userDAO.setEntityManager(entityManager);
		SiteUser userInDB = userDAO.getUser(username);

		if(userInDB==null) {
			request.setAttribute("message", "Wrong username or password");
		}else if( !password.equals( userInDB.getPassword() )) {
			request.setAttribute("message", "Wrong username or password");
		}else {
			return "home";
		}
		return "login";
	}

	//Create account button
	@RequestMapping(value = "submitRegister")
	public String registerSubmitHandler(Model model, SiteUser user, HttpServletRequest request) {
		model.addAttribute("user", user);

		SiteUserDAO userDAO = new SiteUserDAO();
		userDAO.setEntityManager(entityManager);
		SiteUser userInDB = userDAO.getUser(user.getUsername());

		if(userInDB!=null) {
			request.setAttribute("message", "Username is already taken");
			//Checks email contains @ and . There are more complex validation 
		}else if(!user.getEmail().contains("@") || !user.getEmail().contains(".")){
			request.setAttribute("message", "Check email format");
		}else {
			System.out.println("Create user");
			userDAO.addUser(user);
			return "home";
		}
		return "register";
	}
	//Page Navigation Methods
	@RequestMapping(value = "register")
	public String registrationHandler(Model model) {
		model.addAttribute("user", new SiteUser());
		return "register";
	} 
	@RequestMapping(value = "login")
	public String loginHandler(Model model) {
		model.addAttribute("user", new SiteUser());
		return "login";
	}
	@RequestMapping(value = "home")
	public String home(Model model) {
		return "home";
	}
	@RequestMapping(value = "items")
	public String listTheItems(Model model) {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);
		List<Item> listOfItems = itemDAO.listItems();
		model.addAttribute("listOfItems", listOfItems);

		return "items";
	}

	@RequestMapping(value = "addItemToBasket/{itemID}")
	public String addItem(@PathVariable int itemID) {
	System.out.println(itemID+" was added to basket");
	return "basket";
	}
}
