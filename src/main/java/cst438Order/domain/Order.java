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
   private String item1;
   private String item2;
   private String item3;
   private String item1Count;
   private String item2Count;
   private String item3Count;
   

   @Transient
   private String message;
   
   public Order() {
	   	this.id = 0;
	      this.restaurantName = "name";
	      this.cuisine = "cuisineType";
	      this.price = "price";
	      this.item1 = "item1";
	      this.item2 = "item2";
	      this.item3 = "item3";
	      this.items = "item 1, 2 and 3";
	      this.item1Count = "item1 count";
	      this.item2Count = "item2 count";
	      this.item3Count = "item 3 count";
	      this.message = "simple message";
	}
   
   public Order(int i, String name, String cuisineType, String price, String item1, String item2, String item3,
		   String item1Count, String item2Count, String item3Count, String message)
   {
      super();
      this.id = i;
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
      this.item1 = item1;
      this.item2 = item2;
      this.item3 = item3;
      this.items = item1 +" "+ item2 +"  " + item3;
      this.item1Count = item1Count;
      this.item2Count = item2Count;
      this.item3Count = item3Count;
      this.message = message;
   }
   
   public Order(String name, String cuisineType, String price, String item1, String item2, String item3,
		   String item1Count, String item2Count, String item3Count)
   {
      super();
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
      this.item1 = item1;
      this.item2 = item2;
      this.item3 = item3;
      this.items = item1 +" "+ item2 +"  " + item3;
      this.item1Count = item1Count;
      this.item2Count = item2Count;
      this.item3Count = item3Count;
   }

   @Override
   public String toString( ) {
      return this.restaurantName + " " + this.cuisine + " " + this.price+ " " + this.item1
    		  + this.item2 + this.item3 + " " + this.message + " ";
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
		
		public void setItem1(String item)
		   {
		      this.item1 = item;
		   }

		   public String getItem1()
		   {
		      return this.item1;
		   }

		   public void setItem2(String item)
		   {
		      this.item2 = item;
		   }

		   public String getItem2()
		   {
		      return this.item2;
		   }

		   public void setItem3(String item)
		   {
		      this.item3 = item;
		   }

		   public String getItem3()
		   {
		      return this.item3;
		   }

		   public void setItem1Count(String count)
		   {
		      this.item1Count = count;
		   }

		   public String getItem1Count()
		   {
		      return this.item1Count;
		   }

		   public void setItem2Count(String count)
		   {
		      this.item2Count = count;
		   }

		   public String getItem2Count()
		   {
		      return this.item2Count;
		   }

		   public void setItem3Count(String count)
		   {
		      this.item3Count = count;
		   }

		   public String getItem3Count()
		   {
		      return this.item3Count;
		   }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((cuisine == null) ? 0 : cuisine.hashCode());
			result = prime * result + id;
			result = prime * result + ((item1 == null) ? 0 : item1.hashCode());
			result = prime * result + ((item2 == null) ? 0 : item2.hashCode());
			result = prime * result + ((item3 == null) ? 0 : item3.hashCode());
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
			if (item1 == null) {
				if (other.item1 != null)
					return false;
			} else if (!item1.equals(other.item1))
				return false;
			if (item2 == null) {
				if (other.item2 != null)
					return false;
			} else if (!item2.equals(other.item2))
				return false;
			if (item3 == null) {
				if (other.item3 != null)
					return false;
			} else if (!item3.equals(other.item3))
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
