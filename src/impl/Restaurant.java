package impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Restaurant class.
class Restaurant{
	// Mambers of class.
	String name;
	Map<String, Item> items;
	Integer noOfEmployees;
	Map<Integer, Order> liveOrdersMap;

    // Restaurant constructor.
	Restaurant(String name, Integer noOfEmployees){
		this.name = name;
		this.noOfEmployees = noOfEmployees;
		this.items = new HashMap<String,Item>();
		this.liveOrdersMap = new HashMap<Integer,Order>();
	}

	// Member fucntion to add items to the menu of each restaurant.
	void addItems(Item item){
		if(this.items.containsKey(item.name)){
			System.out.println("Item: " + item.name + " already exists");
		} else {
			this.items.put(item.name, item);
		}
	}

    // Member function to get the price of the item.
	Double getPrice(List<String> itemNames){
		Double price = 0.0;
		for(String name: itemNames){
			price += this.items.get(name).price;
		}
		return price;
	}

    // Member function to process the order given by the user.
	void processOrder(Order order){
		liveOrdersMap.put(order.orderId, order);
		order.startTime = System.currentTimeMillis();
		noOfEmployees = noOfEmployees - order.orderItems.size();
	}

    // Member function to get the track of the orders.
	List<Order> completeOrders(){
		// List to keep track of the completed orders by the restaurants.
		List<Order> completedOrders = new ArrayList<Order>();
		for(Map.Entry<Integer, Order> order : liveOrdersMap.entrySet()){
			// If the difference between the current time and the start time is greater than 6 sec.
			if(System.currentTimeMillis() - order.getValue().startTime >= 6000){
				// Order is prepared.
				order.getValue().isProcessed = true;
				// No of employees restored.
				this.noOfEmployees = this.noOfEmployees + order.getValue().orderItems.size();
				completedOrders.add(order.getValue());
				this.liveOrdersMap.remove(order.getValue().orderId);
				//System.out.println("Restaurant: "+ name + " order completed: " + order.getValue().orderId);
			}
		}
		return completedOrders;
	}
}