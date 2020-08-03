package impl;

import java.util.ArrayList;
import java.util.List;

// Order class
public class Order{
	// Members of Order class.
	Integer orderId;
	String orderName;
	List<OrderItem> orderItems;
	Boolean isProcessed;
	Integer userId;
	Integer restaurantId;
	Long startTime;
	
	// Order constructor.
	Order(Integer orderId, List<String> itemList, Integer userId){
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(String item: itemList){
			orderItems.add(new OrderItem(item, 0));
		}
		this.orderId = orderId;
		this.orderName = itemList.toString();
		this.orderItems = orderItems;
		this.isProcessed = false;
		this.userId = userId;
		this.restaurantId = null;
		this.startTime = System.currentTimeMillis();
	}

	void setRestaurantId(Integer restaurantId){
		this.restaurantId = restaurantId;
	}
}