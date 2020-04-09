package cst438Order.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

   Order findById(int id);
   
   List<Order> findByRestaurantName(String restaurantName);
   

   @Query(value="select * from restaurant order by id desc", nativeQuery=true)
   public List<Order> findAllRestaurants();
   
}
