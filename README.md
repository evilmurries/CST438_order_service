# CST438 Final Project - Libera Order Service

Libera is a food ordering service web application composed of dedicated 
microservices. Order service is a frontend microservice that accesses restaurant
metadata and saves the orders of users to an orders database using an orders table. 

It communicates using RabbitMq with a backend service.

## Usage

* The order microservice should be ran with both the restaurant and backend microservice.

* The application.properties file will need to be changed to contain your
MySQL database username and password.

* In the resources folder is a order.sql file. On each run this should
automatically create a schema called 'orders'.
After completing your order, all orders will be saved in the 'orders' schema 
in a table called 'orders'.