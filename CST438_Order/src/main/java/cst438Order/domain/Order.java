package cst438Order.domain;

import javax.persistence.*;

@Entity
public class Order {
   
   @Id
   @GeneratedValue
   private int id;
   
   private String restaurantName;
   private String cuisine;
   private String price;
   private String items;
   
   
//   // transient attributes are not stored in database. 
//   @Transient
//   private double toCityTemp;
//   @Transient
//   private String toCityWeatherCondition;
   
   @Transient
   private String message;
   
   public Order() {
      
   }
  
   public Order(String name, String cuisineType, String price, String items) {
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
      this.items = items;
   }

   @Override
   public String toString( ) {
      return this.restaurantName + " " + this.cuisine + " " + this.items+ " " + this.price;
   }
   
   
   public void setRestaurantName(String name) {
	      this.restaurantName = name;
	   }
	   
	   
	   public String getRestaurantName() {
	      return this.restaurantName;
	   }
	   
	   public void setCuisine(String name) {
	      this.cuisine = name;
	   }
	   
	   
	   public String getCuisine() {
	      return this.cuisine;
	   }
	   
	   public void setPrice(String price) {
	      this.price = price;
	   }
	   
	   
	   public String getPrice() {
	      return this.price;
	   }
   
	   
	   public void setItems(String items){
		   this.items = items;
	   }
	   
	   public String getItems(String items) {
		   return this.items = items;
	   }
}
