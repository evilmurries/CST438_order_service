package cst438Order.service;

import java.util.List;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.databind.JsonNode;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cst438Order.domain.Order;
import cst438Order.domain.OrderRepository;
import cst438Order.domain.RestaurantInfo;


@Service
public class OrderService {
	
	
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	private RestTemplate restTemplate;
	
	public OrderService() {
		
	}
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	
	}
	
	public Order createOrder(Order reserve) {
		// validate Order fields
		log.info("Request Order: "+reserve.toString());
		reserve.setId(0);
		String restaurantName = reserve.getRestaurantName();
		Order order = null;
		if (restaurantName != null) order = orderRepository.findByRestaurantName(restaurantName);
		if (order == null) {
			// resturantName is missing or invalid
			reserve.setMessage("Restaurant Name is not valid. "+ restaurantName);
			log.info("Request Not Processed: "+reserve.toString());
			return reserve;
		}	
				
		if (reserve.getCuisine() != null && reserve.getPrice() != null &&
				((!order.getCuisine().equals(reserve.getCuisine())) ||
				(!order.getPrice().equals(reserve.getPrice())))) {
			reserve.setMessage("Cuisine, and Price don't match Restaurant Name.");
			log.info("Request Not Processed: "+reserve.toString());
			return reserve;
		}
		if (reserve.getCuisine() == null || reserve.getPrice() == null) {
			reserve.setPrice(order.getPrice());
			reserve.setCuisine(order.getCuisine());
		}
	
		
		Order newOrder = orderRepository.save(reserve);
		
		newOrder.setMessage("OK");
		
		log.info("Create Order: "+newOrder.toString());
		
		// returned updated Order to caller
		return newOrder;
	}
	
	public boolean deleteOrder(int id) {
		Order r = orderRepository.findById(id);
		if (r !=null) {
			orderRepository.delete(r);
			log.info("Order deleted: "+id);
			return true;
		} else {
			log.info("Order delete failed. Not found. "+id);
			return false;
		}
	}

	public Order getOrder(int id) {
		log.info("Order retrieve: "+id);
		Order r = orderRepository.findById(id);
		if (r != null) {
			r.setMessage("OK");
			log.info("Order retrieved: "+r.toString());
			return r;
		} else {
			log.info("Order retrieve failed. Not found.: "+id);
			return null;
		}
	}
	
	

	public Order getOrder(String restaurantName, String items) {
		
		Order getOrder = orderRepository.findByRestaurantName(restaurantName);
		
		if(getOrder != null) {
			Order ofOrder = new Order();
			RestaurantInfo res = getRestaurantByName(restaurantName);
			
			ofOrder.setRestaurantName(restaurantName);
			ofOrder.setCuisine(res.getCuisine());
			ofOrder.setPrice(res.getPrice());
			ofOrder.setItems(items);
			ofOrder.setMessage("OK");
			
			return ofOrder;
		}
		
		return null;
	}

	//bottom stuff. work on above when you get chance
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private FanoutExchange fanout;
	
	public void requestOrder( 
            String restaurantName, 
            String cuisine, 
            String price,
            String items
           ) {
	String msg  = "{\"Name\": \""+ restaurantName + 
        "\" \"cuisine\": \""+cuisine+
        "\" \"price\": \""+price+
        "\" \"items\": \""+items+"\"}" ;
	System.out.println("Sending message:"+msg);
	rabbitTemplate.convertSendAndReceive(
         fanout.getName(), 
         "",   // routing key none.
         msg);
	}
	
	public void requestSurvey( 
            String level) {
	String msg  = "{\"level\": \""+level+"\"}" ;
	System.out.println("Sending message:"+msg);
	rabbitTemplate.convertSendAndReceive(
         fanout.getName(), 
         "",   // routing key none.
         msg);
	}
	
	public RestaurantInfo getRestaurantByName(String restName) {
	   String restUrl = "http://localhost:8090/restaurant/name/" + restName;
	   System.out.println(restUrl);
	   RestaurantInfo r = new RestaurantInfo();
	   RestTemplate restTemplate = new RestTemplate();
	   ResponseEntity<JsonNode> response = restTemplate.getForEntity(restUrl, JsonNode.class);
	   System.out.println(response.toString());
	   JsonNode json = response.getBody();
	   String name = json.get("restaurantName").toString();
	   String price = json.get("price").toString();
	   String cuisine = json.get("cuisine").toString();
	   r.setRestaurantName(name);
	   r.setPrice(price);
	   r.setCuisine(cuisine);
	   return r;
	}
	
}
	
	