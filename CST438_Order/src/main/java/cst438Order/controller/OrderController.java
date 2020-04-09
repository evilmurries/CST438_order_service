package cst438Order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cst438Order.domain.Order;
import cst438Order.service.OrderService;


@RestController
public class OrderController
{
   @Autowired
   OrderService orderService;
   
   /*
   @GetMapping("/restaurant/{id}")
   public ResponseEntity<Restaurant> findOrderById(@PathVariable("id") int id) {

   }
   */
   
   
   @GetMapping("/order")
   public String createOrder( Model model) {
       Order order = new Order();
       model.addAttribute("order", order);
       return "request_reservation";
   }
}