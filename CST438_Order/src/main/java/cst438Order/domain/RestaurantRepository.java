package cst438Order.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

   Restaurant findById(int id);
   
   List<Restaurant> findByRestaurantName(String restaurantName);
   

   @Query(value="select * from restaurant order by id desc", nativeQuery=true)
   public List<Restaurant> findAllRestaurants();
   
}
