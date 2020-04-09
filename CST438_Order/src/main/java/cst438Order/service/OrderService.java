package cst438Order.service;

import java.util.List;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cst438Order.domain.Order;
import cst438Order.domain.OrderRepository;


@Service
public class OrderService {
	
	
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
	
	public void requestReservation( 
            String name, 
            String cuisine, 
            String price,
            String items) {
	String msg  = "{\"Name\": \""+ name + 
        "\" \"cuisine\": \""+cuisine+
        "\" \"price\": \""+price+
        "\" \"items\": \""+items+"\"}" ;
	System.out.println("Sending message:"+msg);
	rabbitTemplate.convertSendAndReceive(
         fanout.getName(), 
         "",   // routing key none.
         msg);
	}
}
