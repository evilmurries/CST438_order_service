package cst438Order.domain;

import javax.persistence.*;

@Entity
public class Restaurant {
   
   @Id
   @GeneratedValue
   private int id;
   
   private String restaurantName;
   private String cuisine;
   private String price;
   
   
//   // transient attributes are not stored in database. 
//   @Transient
//   private double toCityTemp;
//   @Transient
//   private String toCityWeatherCondition;
   
   @Transient
   private String message;
   
   public Restaurant() {
      
   }
  
   public Restaurant(String name, String cuisineType, String price) {
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
   }

   @Override
   public String toString( ) {
      return this.restaurantName + " " + this.cuisine + " " + this.price;
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
   
}
