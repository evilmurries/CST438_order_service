package cst438Order.domain;

import java.util.function.IntPredicate;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestParam;

@Entity
@Table(name="orders")
public class Order {
   

   @Id
   @GeneratedValue
   private int id;
   
   private String restaurantName;
   private String cuisine;
   private String price;
   private String items;
   
 

   
   

   @Transient
   private String message;
   
   public Order() {
	 		
	}
   
   public Order(int id, String name, String cuisineType, String price, String items, String message) {
	   super();
	  this.id = id;
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
      this.items = items;
  	  this.message = message;
   }

   @Override
   public String toString( ) {
      return this.restaurantName + " " + this.cuisine + " " + this.price+ " " + this.items + " " + this.message + " ";
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

		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cuisine == null) ? 0 : cuisine.hashCode());
			result = prime * result + id;
			result = prime * result + ((items == null) ? 0 : items.hashCode());
			result = prime * result + ((message == null) ? 0 : message.hashCode());
			result = prime * result + ((price == null) ? 0 : price.hashCode());
			result = prime * result + ((restaurantName == null) ? 0 : restaurantName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Order other = (Order) obj;
			if (cuisine == null) {
				if (other.cuisine != null)
					return false;
			} else if (!cuisine.equals(other.cuisine))
				return false;
			if (id != other.id)
				return false;
			if (items == null) {
				if (other.items != null)
					return false;
			} else if (!items.equals(other.items))
				return false;
			/*if (message == null) {
				if (other.message != null)
					return false;
			} else if (!message.equals(other.message))
				return false;
				*/
			if (price == null) {
				if (other.price != null)
					return false;
			} else if (!price.equals(other.price))
				return false;
			if (restaurantName == null) {
				if (other.restaurantName != null)
					return false;
			} else if (!restaurantName.equals(other.restaurantName))
				return false;
			return true;
		}
	
}
