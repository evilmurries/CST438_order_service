package cst438Order.serviceTest;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import cst438Order.service.OrderService;
import static org.mockito.ArgumentMatchers.anyString;
 
@SpringBootTest
public class OrderServiceTest {

	@MockBean
	private OrderService orderService;
	
	

	
	@Test
	public void contextLoads() {
	}



}
