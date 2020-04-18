package cst438Order.domain;

public class RestaurantInfo {

   
   private String restaurantName;
   private String cuisine;
   private String price;
   private String foodItem1;
   private String foodItem2;
   private String foodItem3;
   
   
   public RestaurantInfo() {
      
   }
  
   public RestaurantInfo(String name, String cuisineType, String price) {
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
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
   
   public void setFoodItem1(String food) {
      this.foodItem1 = food;
   }
   
   
   public String getFoodItem1() {
      return this.foodItem1;
   }
   
   public void setFoodItem2(String food) {
      this.foodItem2 = food;
   }
   
   
   public String getFoodItem2() {
      return this.foodItem2;
   }
   
   public void setFoodItem3(String food) {
      this.foodItem3 = food;
   }
   
   
   public String getFoodItem3() {
      return this.foodItem3;
   }
   
   @Override
   public String toString() {
      return this.foodItem1 + " " + this.foodItem2 + " " + this.foodItem3;
   }
   
}
