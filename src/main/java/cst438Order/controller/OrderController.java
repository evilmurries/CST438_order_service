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
       return "order";
   }
 
     
 }