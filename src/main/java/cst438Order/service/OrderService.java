package cst438Order.service;

import java.util.List;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import cst438Order.domain.Order;
import cst438Order.domain.OrderRepository;
import cst438Order.domain.RestaurantInfo;


@Service
public class OrderService {
	
   private RestTemplate restTemplate;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order getOrder(String restaurantName) {
		
		List<Order> getOrder = orderRepository.findByRestaurantName(restaurantName);
		
		if(!getOrder.isEmpty()) {
			Order ofOrder = getOrder.get(0);
			
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
            String items) {
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
	
	
	public RestaurantInfo getRestaurantByName(String restName) {
	   String restUrl = "http://localhost:8090/restaurant/name/" + restName;
	   System.out.println(restUrl);
	   RestaurantInfo r = new RestaurantInfo();
	   ResponseEntity<JsonNode> response = restTemplate.getForEntity(restUrl, JsonNode.class);
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
