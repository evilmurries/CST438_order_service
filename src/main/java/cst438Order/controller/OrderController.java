package cst438Order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cst438Order.domain.Order;
import cst438Order.domain.OrderRepository;
import cst438Order.domain.RestaurantInfo;
import cst438Order.service.OrderService;


@Controller
public class OrderController
{
   @Autowired
   OrderService orderService;
   
   @Autowired
   OrderRepository orderRepository;
   
   @GetMapping("/order")
   public String createOrder( Model model) {
       Order order = new Order();
       model.addAttribute("order", order);
       return "welcome";
   }
   
   @PostMapping("/order")
   public String processOrderForm(@RequestParam("rName") String rName, Model model) {
       RestaurantInfo r = orderService.getRestaurantByName(rName);
       if (r == null) {
          return "welcome";
       }
       model.addAttribute("restaurant", r);
       System.out.println(r.toString());
       return "order";
   }
   
    
   @PostMapping("/order/confirmed")
	public String createReservation(
			@RequestParam("orderName") String oName, 
			@RequestParam("price") String price,
			@RequestParam("cuisine") String cuisine,
         @RequestParam("item1") String item1,
         @RequestParam("item2") String item2,
         @RequestParam("item3") String item3,
			@RequestParam("item1Count") String item1Count,
			@RequestParam("item2Count") String item2Count,
			@RequestParam("item3Count") String item3Count,
			Model model) {
	   String items = item1 + " " + item2 + " " + item3;
	   model.addAttribute("orderName", oName);
	   model.addAttribute("items", items);
	   model.addAttribute("price", price);
	   model.addAttribute("cuisine", cuisine);
	   Order formOrder = new Order(oName, cuisine, price, item1, item2, item3, item1Count, item2Count, item3Count);
	   model.addAttribute("formOrder", formOrder);
 		orderRepository.save(formOrder);
		orderService.requestOrder(oName, cuisine, price, item1, item2, item3, item1Count, item2Count, item3Count);
		return "Order_Confirmed";
	}
 
   @PostMapping("/order/survey")
  	public String createSurvey(
  			@RequestParam("level") String level, @Valid Order formOrder,	
  			Model model) {
  	   model.addAttribute("level", level);
  		orderService.requestSurvey(level);
  		return "order_survey";
  	}
   
   
 }