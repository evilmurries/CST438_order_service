package cst438Order.serviceTest;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.awt.PageAttributes.MediaType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.thymeleaf.spring5.expression.Mvc;

import cst438Order.domain.Order;
import cst438Order.domain.OrderRepository;
import cst438Order.service.OrderService;


import static org.mockito.ArgumentMatchers.anyString;
 
///do multiple test for rest.
@SpringBootTest
public class OrderServiceTest {
	
	@Mock
	private OrderService orderService; // this is class under test

	@MockBean
	private OrderRepository orderRepository;
	
	@BeforeEach
	public void setupEach(){
		MockitoAnnotations.initMocks(this);
		orderService = new OrderService(orderRepository);	
	}
	@Test
	public void getOrderTest() {
		Order testOrder =
				new Order(1, "Mcdonalds", "American", "$1", "1", "2", "3", "OK");
	
	//mocked OrderRepository will return testOrder when findById(1)is called
	given(orderRepository.findById(1)).willReturn(testOrder);
	
	//call the OrderService
	Order actual = orderService.getOrder(1);
	
	//Verify the actual Order returned is correct
	assertThat(actual).isEqualTo(testOrder);
	assertThat(actual.getMessage()).isEqualTo("OK");
	
	}	
	@Test
	public void deleteOrderTest() {
		//given
		Order testOrder =
				new Order(1, "Mcdonalds", "American", "$1", "1", "2", "3", "OK");
		
		//given (our mocked OrderRepository will return testOrder when findById(1)is called
		given(orderRepository.findById(1)).willReturn(testOrder);
		
		//when		we need a method deleteOrder in orderservice
		boolean result = orderService.deleteOrder(1);
		
		//assertThat(result).isTrue();
		verify(orderRepository).delete(testOrder);
		
	}
	@Test
	public void createOrderTest() {
		//create the fake data
		Order input =
				new Order();
		input.setRestaurantName("Mcdonalds");
		input.setCuisine("American");
		input.setPrice("$");
		input.setItems("4");
		
		Order indb =
		 new Order(0, "Mcdonalds", "American", "$1", "1", "2", "3", "OK");
		
		Order outdb = //same as indb except the Order id is updated
				 new Order(10, "Mcdonalds", "American", "$1", "1", "2", "3","OK");
		
		Order expected = 
				 new Order(10, "Mcdonalds", "American","$1", "1", "2", "3","OK");
		
		given(orderRepository.findByRestaurantName("Mcdonalds")).willReturn(
				new Order(1, "Mcdonalds", "American","$1", "1", "2", "3","OK"));		
		
		given(orderRepository.save(indb)).willReturn(outdb);
		
		Order actual = orderService.createOrder(input);
		
		//Verify the actual Order returned is correct
		assertThat(actual).isEqualTo(expected);
		assertThat(actual.getMessage()).isEqualTo(expected.getMessage());
				
				
		
	}
	
	@Test
	public void createOrderBy() {
		//create the fake data
		Order input =
				new Order();
		input.setId(0);
		input.setRestaurantName("Mcdonalds");
		input.setCuisine("American");
		input.setPrice("$");
		input.setItems("4");
		
		
		Order indb =
		 new Order(0, "Mcdonalds", "American", "$1", "1", "2", "3","OK");
		
		Order outdb = //same as indb except the Order id is updated
				 new Order(0, "Mcdonalds", "American", "$1", "$2" ,"$3", "4","OK");
		
		Order expected = //same as indb except the Order id is updated
				 new Order(0, "Mcdonalds", "American", "$1", "1", "2", "3", "OK");
		//
		given(orderRepository.findByRestaurantName("Mcdonalds")).willReturn
		(new Order(0, "Mcdonalds", "American", "$1", "1", "2", "3", "OK"));
		
		given(orderRepository.save(indb)).willReturn(outdb);
					
		//when 
		Order actual = orderService.createOrder(input);
		
		//Verify the actual Order returned is correct
		assertThat(actual).isEqualTo(expected);
		assertThat(actual.getMessage()).isEqualTo("OK");
		
	}

}
	