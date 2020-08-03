package impl;

import java.util.ArrayList;
import java.util.List;

// User class
public class User{
	// Class members.
	String name;
	String email;
	String password;
	List<Order> orders;

    // User constructor.
	User(String name, String email, String password){
		this.name = name;
		this.email = email;
		this.password = password;
		this.orders = new ArrayList<Order>();
	}

    // Order of each of the customer to be added in order list.
	void addOrder(Order order){
		this.orders.add(order);
	}

    // Function to get the orders.
	List<Order> getOrders(){
		return this.orders;
	}
}