
package cst438Order.controllerTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438Order.controller.OrderController;
import cst438Order.domain.Order;
import cst438Order.domain.OrderRepository;
import cst438Order.domain.RestaurantInfo;
import cst438Order.service.OrderService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest
{
   @MockBean
   @Autowired
   private OrderService orderService;
   
   @MockBean
   private OrderRepository orderRepository;

   @Autowired
   private MockMvc mvc;

   private JacksonTester<Order> json;

   @BeforeEach
   public void setup() {
      JacksonTester.initFields(this, new ObjectMapper());
   }

   @Test
   public void contextLoads() {
   }

   @Test
   public void testCreateOrder() throws Exception {

      Order attempt = new Order(0, "Mcdonalds", "American", "$1", "taco", "burrito", "", "1", "2", "3", "OK");
      Order expected = new Order(0, "Mcdonalds", "American", "$1", "taco", "burrito", "", "1", "2", "3", "OK");

      given(orderService.createOrder(expected)).willReturn(expected);

      // when
      MockHttpServletResponse response = mvc.perform(
            get("api/order").contentType(MediaType.APPLICATION_JSON)
               .content(json.write(attempt).getJson()))
            .andReturn().getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).isEqualTo(
            json.write(expected).getJson());
   }

   @Test
   public void testProcessOrderForm() throws Exception {
      Order attempt = new Order(0, "Mcdonalds", "American", "$1", "taco", "burrito", "", "1", "2", "3", "OK");
      RestaurantInfo expected = new RestaurantInfo("Mcdonalds", "American", "$1");

      given(orderService.getRestaurantByName("Mcdonalds")).willReturn(expected);

      // when
      MockHttpServletResponse response = mvc.perform(
            post("api/order").contentType(MediaType.APPLICATION_JSON)
               .content(json.write(attempt).getJson()))
            .andReturn().getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      //assertThat(response.getContentAsString()).isEqualTo(
          //  json.write(expected.getRestaurantName()).getJson());
   }

   @Test
   public void testOrderConfirmed() throws Exception {
      Order attempt = new Order(0, "Mcdonalds", "American", "$1", "taco", "burrito", "", "1", "2", "3", "OK");
      Order expected = new Order(0, "Mcdonalds", "American", "$1", "taco", "burrito", "", "1", "2", "3", "OK");

      given(orderService.createOrder(expected)).willReturn(expected);

      // when
      MockHttpServletResponse response = mvc.perform(
            post("api/order/confirmed").contentType(MediaType.APPLICATION_JSON)
               .content(json.write(attempt).getJson()))
            .andReturn().getResponse();

      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).isEqualTo(
            json.write(expected).getJson());
   }

}