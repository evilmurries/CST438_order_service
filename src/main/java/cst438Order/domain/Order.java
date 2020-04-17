package cst438Order.domain;

import javax.persistence.*;

@Entity
@Table(name = "orders")
//@SecondaryTable(name = "restaurant", pkJoinColumns = @PrimaryKeyJoinColumn(name = "restaurantName", referencedColumnName = "id"))
public class Order
{

   @Id
   @GeneratedValue
   private long id;

   private String restaurantName;
   private String cuisine;
   private String price;
   private String item1;
   private String item2;
   private String item3;
   private String item1Count;
   private String item2Count;
   private String item3Count;

   @Transient
   private String message;

   public Order()
   {

   }

   public Order(long i, String name, String cuisineType, String price, String item1, String item2, String item3)
   {
      super();
      this.id = i;
      this.restaurantName = name;
      this.cuisine = cuisineType;
      this.price = price;
      this.item1 = item1;
      this.item2 = item2;
      this.item3 = item3;
   }

   @Override
   public String toString()
   {
      return this.restaurantName + " " + this.cuisine + " " + this.price + " ";
   }

   public void setRestaurantName(String name)
   {
      this.restaurantName = name;
   }

   public String getRestaurantName()
   {
      return this.restaurantName;
   }

   public void setCuisine(String name)
   {
      this.cuisine = name;
   }

   public String getCuisine()
   {
      return this.cuisine;
   }

   public void setPrice(String price)
   {
      this.price = price;
   }

   public String getPrice()
   {
      return this.price;
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
}
