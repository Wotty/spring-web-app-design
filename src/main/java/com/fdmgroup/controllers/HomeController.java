package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.daos.ItemDAO;
import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.daos.OrderDetailsDAO;
import com.fdmgroup.daos.PaymentDAO;
import com.fdmgroup.daos.SiteUserDAO;
import com.fdmgroup.entities.Item;
import com.fdmgroup.entities.Order;
import com.fdmgroup.entities.OrderDetails;
import com.fdmgroup.entities.Payment;
import com.fdmgroup.entities.SiteUser;

@Controller
public class HomeController {

	//Entity Manager
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("finalproject");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	//Basket Map
	private HashMap<Item,Integer> basket = new HashMap<>();

	//Initialise method
	@RequestMapping(value="/")
	public String login(Model model) {
		model.addAttribute("user",new SiteUser());
		return "login";
	}

	//Login & Register methods
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
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			return "home";
		}
		return "login";
	}

	//Submit Register
	@RequestMapping(value = "submitRegister")
	public String registerSubmitHandler(Model model, SiteUser user, HttpServletRequest request) {
		model.addAttribute("user", user);

		SiteUserDAO userDAO = new SiteUserDAO();
		userDAO.setEntityManager(entityManager);
		String username = user.getUsername();
		SiteUser userInDB = userDAO.getUser(username);

		if(userInDB!=null) {
			request.setAttribute("message", "Username is already taken");
		}else if(!user.getEmail().contains("@") || !user.getEmail().contains(".")){
			//Checks email contains @ and . There are more complex validation
			request.setAttribute("message", "Check email format");
		}else {
			userDAO.addUser(user);
			return "home";
		}
		return "register";
	}
	@RequestMapping(value = "register")
	public String registrationHandler(Model model) {
		model.addAttribute("user", new SiteUser());
		return "register";
	} 

	//Page Navigation Methods
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
	@RequestMapping(value = "basket")
	public String basket(Model model) {
		model.addAttribute("basket",basket);
		return "basket";
	}

	@RequestMapping(value = "orderDetails")
	public String orders(Model model, HttpSession session) {
		OrderDetailsDAO orderDAO = new OrderDetailsDAO();
		orderDAO.setEntityManager(entityManager);
		String username = (String) session.getAttribute("username");

		List<OrderDetails> listOfOrderDetails = orderDAO.listOrderDetails(username);
		model.addAttribute("listOfOrderDetails", listOfOrderDetails);
		return "orderDetails";
	}
	@RequestMapping(value = "payments")
	public String payments(Model model, HttpSession session) {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);
		String username = (String) session.getAttribute("username");

		List<Payment> listOfPayments = paymentDAO.listPayments(username);
		model.addAttribute("listOfPayments", listOfPayments);
		return "payments";
	}
	@RequestMapping(value = "logout")
	public String logout(Model model) {
		model.addAttribute("user", new SiteUser());
		return "login";
	}
	@RequestMapping(value = "addItemToBasket/{itemID}")
	public String addItemToBasket(@PathVariable int itemID, Model model) {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);
		Item itemInDB = itemDAO.getItem(itemID);
		if (basket.get(itemInDB) == null) {
			basket.put(itemInDB,1);
		}else {
			int quantity = basket.get(itemInDB) + 1;
			basket.put(itemInDB,quantity);
		}

		model.addAttribute("basket",basket);
		return "basket";
	}	
	@RequestMapping(value = "removeItemFromBasket/{itemID}")
	public String removeItemFromBasket(@PathVariable int itemID, Model model) {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);
		Item itemInDB = itemDAO.getItem(itemID);
		basket.remove(itemInDB);
		model.addAttribute("basket",basket);
		return "basket";
	}
	@RequestMapping(value = "payForOrder")
	public String payForOrder(Model model, HttpSession session) {

		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);
		String username = (String) session.getAttribute("username");
		SiteUser siteUserInDB = siteUserDAO.getUser(username);

		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		Order order = new Order();
		order.setSiteUser(siteUserInDB);
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		orderDAO.addOrder(order);

		OrderDetailsDAO OrderDetailsDAO = new OrderDetailsDAO();
		OrderDetailsDAO.setEntityManager(entityManager);
		OrderDetailsDAO.addAllOrderDetails(basket,order);
		basket.clear();



		List<OrderDetails> listOfOrderDetails = OrderDetailsDAO.listOrderDetails(username);
		model.addAttribute("listOfOrderDetails", listOfOrderDetails);
		return "orderDetails";
	}
}
