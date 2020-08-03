package impl;

import java.util.*;

public class Cafeteria {
	// Map consisting of all orders given by the user (Fields include from the Order Class).
	private Map<Integer, Order> allOrders;
	// Map to store all the restaurant records which is to be added by the admin.
	private Map<Integer, Restaurant> restaurantMap;
	// Map to store the user records of the user.
	private Map<Integer, User> userInfo;
	// Map to store the different items present and map it to the restaurant.
	private Map<String, List<Integer>> itemToRestaurantMap;

	private int noOfUser;
	private int orderId;
	private int noOfRestaurant;

	// Assuming adminid and password to be fixed.
	private static final int adminId = 111;
	private static final String adminPassword = "admin";

	// Cafeteria constructor.
	Cafeteria(){
		// Initialize all class items.
		this.allOrders = new HashMap<>();
		this.restaurantMap = new HashMap<>();
		this.userInfo = new HashMap<>();
		this.itemToRestaurantMap = new HashMap<>();
		this.noOfRestaurant = 1;
		this.noOfUser = 1;
		this.orderId = 0;
	}
    
    // Function to handle the orders requested by the customer.
	private Boolean handleOrder(Order order) {

		// List to store the items.
		List<String> items = new ArrayList<>();
		// Iterate through the orderItems requested by the user.
		for(OrderItem orderItem: order.orderItems) {
			// If the current item is not present in the restaurant menu return false.
			if(!this.itemToRestaurantMap.containsKey(orderItem.itemName)){
				System.out.println(orderItem.itemName + "does not Exist!");
				return false;
			}
			// Else add the item to the list.
			items.add(orderItem.itemName);
		}

		// Assume that the first restaurant is having the orders requested by the user.
		List<Integer> commonRestaurantIds = this.itemToRestaurantMap.get(items.get(0));
		// Iterate through the items and find out the items which are present in the menu of the restaurants.
		for(String itemName: items){
			List<Integer> restaurantIds = new ArrayList<>();
			for (Integer id : itemToRestaurantMap.get(itemName)) {
				// If the current restaurant has the item requested get the id of the restaurant and add it to the restaurantid list.
				if(commonRestaurantIds.contains(id)) {
					restaurantIds.add(id);
				}
			}
			// Place it in the commonretaurantid map.
			commonRestaurantIds = restaurantIds;
		}

		// If the map is empty it means there is no restaurant in common.
		if(commonRestaurantIds.isEmpty()){
			System.out.println("Sorry!! No Restaurants able to handle the request");
			return false;
		}

		// Now, we have got the list of restaurants which are having the order which is requested by the user.

		// Assume that the first restaurant having the order is the final restaurant which will prepare the order.
		Integer winnerRestaurantId = commonRestaurantIds.get(0);

        // Now, get the id of the common restaurants one by one and get the id.
		for(Integer id: commonRestaurantIds){
			// Now, look for the restaurant whose number of employees are greater than or equal to the number of orders requested bby the user.
			if(this.restaurantMap.get(id).noOfEmployees >= order.orderItems.size()){
				// The check the retaurant which has the cheapest price among all common restaurants.
				if(this.restaurantMap.get(winnerRestaurantId).getPrice(items) >
						this.restaurantMap.get(id).getPrice(items)){
					// Copy the id of the final restaurant which will be preparing the order.
					winnerRestaurantId = id;
				}
			} else {
				continue;
			}
		}

		// This logic is done becauseif there is no common restaurant then, we compare the number of employees and the total number of orders.
		if(this.restaurantMap.get(winnerRestaurantId).noOfEmployees >= order.orderItems.size()) {
			this.restaurantMap.get(winnerRestaurantId).processOrder(order);
			// Prin the retaurant which will prepare the order.
			System.out.println("Restaurant: " + this.restaurantMap.get(winnerRestaurantId).name + " making the order");
			return true;
		// Else no restaurant can prepare the order.
		} else {
			System.out.println("Sorry!! No Restaurants able to handle the request");
			return false;
		}
	}

	// Function to add the user as an object to the 'User' database.
	public void addUser(String username, String email, String password) {
		// Add the user.
		this.userInfo.put(noOfUser, new User(username, email, password));
		// Print the id of the current user.
		System.out.println("Your userId is: " + noOfUser);
		// Increment the user id for the next user.
		this.noOfUser++;
	}

	// Function for user authentication for login, where userid and password are the user credentials.
	public Boolean authenticateUser(Integer userId, String password) {
		// If the user id is present in the database then, get the password of the user from the 'User' database
		if(this.userInfo.containsKey(userId)){
			// Get the password from 'User' database.
			User user = this.userInfo.get(userId);
			// If the password from the user and the password from the 'User' database are equal then the user is finally authenticated.
			if(user.password.equals(password)){
				System.out.println("User Authenticated");
				return true;
				// Else user is not authenticated.
			} else {
				System.out.println("User password is incorrect");
			}
			// Else no such user exists in the database.
		} else {
			System.out.println("Unknown user: " + userId);
		}
		// Return false.
		return false;
	}

	// Function to authenticate the admin.
	public Boolean authenticateAdmin(Integer adminId, String password) {
		if(this.adminId == adminId && this.adminPassword.equals(password)){
			System.out.println("Admin Authenticated");
			return true;
		} else {
			System.out.println("User password is incorrect");
			return false;
		}
	}

	// Function to check for the order status when requested by the user.
	public void checkOrderStatus(Integer orderId) {
		// Check for the order which is present in the list.
		if(this.allOrders.containsKey(orderId)){
			// If the order has been processed then, notify the user that the order is ready to picked up.
			if(this.allOrders.get(orderId).isProcessed){
				System.out.println("Order is ready to be pickedup");
				// Else the order is getting prepared.
			} else {
				System.out.println("Order is getting prepared");
			}
			// No, such order with a given orderid present in the orders list.
		} else {
			System.out.println("Order not found: "+ orderId);
		}
		pollOrderStatus();
	}

	// Function to add the restaurant by the Admin, with the object of the restaurant.
	public Integer addRestaurant(String restaurantName, Integer noOfEmployees) {
		Restaurant restaurant = new Restaurant(restaurantName, noOfEmployees);
		// Add the restaurant to the 'restaurantMap' database.
		this.restaurantMap.put(noOfRestaurant, restaurant);
		// Print the id of the current restaurant.
		System.out.println("Your restaurantId is: " + noOfRestaurant);
		// Increment the id of the restaurant for the next restaurant to be added.
		noOfRestaurant++;
		return noOfRestaurant - 1;
	}

	// This function is for the Admin to add items to the existing restaurant, with parameter restaurantid and the item which is to be added.
	public void addItems(Integer restaurantId, Item item){
		// If the restaurantid is present in the 'restaurantMap' database given by the admin.
		if(this.restaurantMap.containsKey(restaurantId)){
			// Now, get reataurant id and add items to the particular restaurant whose id is present in the database.
			this.restaurantMap.get(restaurantId).addItems(item);
			// Also, if this item is not present in the menu, add the item to it for user to order.
			if(!this.itemToRestaurantMap.containsKey(item.name)) {
				this.itemToRestaurantMap.put(item.name, new ArrayList<Integer>());
			}
			List<Integer> restaurantIds = this.itemToRestaurantMap.get(item.name);
			restaurantIds.add(restaurantId);
			this.itemToRestaurantMap.put(item.name,restaurantIds);
			// Print item added.
			System.out.println("Item added: " + item.name);
			// Else the item is already present in the menu and the restaurant.
		} else {
			System.out.println("No restaurant exists of id " + restaurantId);
		}
	}

	// Function to get the order id of a particular user from the database.
	public void printOrderIdsForUser(Integer userId){
		// New list to get all the orders by the user.
		List<Integer> orderIds = new ArrayList<Integer>();
		// list which returns the order of the particular user.
		List<Order> orders = this.userInfo.get(userId).getOrders();
		// Iterate through all orders and keep adding them to the new list.
		System.out.println("Your order ids are:");
		for(Order order: orders){
			System.out.println("Order name: "+ order.orderName + " and ID:" + order.orderId);
			//orderIds.add(order.orderId);
		}
		// Return the order id list.
		//return orderIds;
	}

	// Function to print the order menu.
	public void printMenu(){
		// Traverse through the menu and print it.
		if(this.itemToRestaurantMap.isEmpty())
			System.out.println("No item to show!!");
		else
			for(Map.Entry<String,List<Integer>> itemMap : this.itemToRestaurantMap.entrySet())
				System.out.println(itemMap.getKey());
	}

	// Function to build the order given by the user.
	public void buildAndProcessOrder(String items, Integer userId){
		// Split the order items.
		List<String> itemList = Arrays.asList(items.split(","));
		// Create a new order demand for a particular user.
		Order order = new Order(this.orderId, itemList, userId);
		// Pass on the order created to process according to the conditions given,
		Boolean isRestaurantAssigned = this.handleOrder(order);
		if(isRestaurantAssigned) {
			this.userInfo.get(userId).addOrder(order);
			this.allOrders.put(order.orderId, order);
			// Get the order id which helps to track down your orders.
			System.out.println("Your order is tracked by orderId: " + orderId);
			orderId++;
		}
	}

	// Utility function to check for the status of the order.
	public void pollOrderStatus(){
		for(Map.Entry<Integer,Restaurant> restaurant: restaurantMap.entrySet()){
			restaurant.getValue().completeOrders();
		}
	}

    // Main funtion to check the above algorithm.
	public static void main(String[] args){
		// Main class object of cafeteria.
		Cafeteria cafe = new Cafeteria();
		boolean check = true;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("*** Welcome To Food Ordering Service ***");
			System.out.println("Press 1 to login as User");
			System.out.println("Press 2 to Login as Admin");
			System.out.println("New User?? Press 3 to Register");
			System.out.println("Press 4 to logout");
			Integer choice = sc.nextInt();
			if(choice == 4)
				check = false;
			else {

				// Login as current user (should be already registered before!).
				if(choice == 1){
					System.out.println("Enter your userID");
					Integer userId = sc.nextInt();
					System.out.println("Enter your password");
					String password = sc.next();
					if(cafe.authenticateUser(userId, password)){
						System.out.println("Press 1 to Order Now!!");
						System.out.println("Press 2 to track your order");
						Integer userChoice = sc.nextInt();
						if(userChoice == 1) {
							System.out.println("Please provide items in comma separated format from the below List:");
							cafe.printMenu();
							String items = sc.next();
							cafe.buildAndProcessOrder(items, userId);
						}
						else if(userChoice == 2) {
							System.out.println("Please do enter your Order id");
							cafe.printOrderIdsForUser(userId);
							Integer orderid = sc.nextInt();
							cafe.checkOrderStatus(orderid);
						}
					} else
						System.out.println("Incorrect userId/password");

				// Login as admin condition.
				} else if(choice == 2) {
					System.out.println("Enter the AdminID");
					Integer username = sc.nextInt();
					System.out.println("Enter the password");
					String password = sc.next();
					if(cafe.authenticateAdmin(username, password)) {
						System.out.println("Press 1 to add new Restaurant!");
						System.out.println("Press 2 to add items to existing Restaurant!");
						Integer adminChoice = sc.nextInt();
						if(adminChoice == 1) {
							System.out.println("Enter the first name of restaurant");
							String name = sc.next();
							System.out.println("Enter the no of employees");
							Integer noOfEmployees = sc.nextInt();
							Integer currentid = cafe.addRestaurant(name, noOfEmployees);
							System.out.println("Press 1 to add Items");
							Integer adminchoiceitem = sc.nextInt();
							do{
								System.out.println("Enter the first name of the item to be added");
								String nameitem = sc.next();
								System.out.println("Enter the item price");
								Double price = sc.nextDouble();
								cafe.addItems(currentid,new Item(nameitem, price));
								System.out.println("Press 1 to add more item or Press 2 to exit");
								adminchoiceitem = sc.nextInt();

							}while(adminchoiceitem == 1);

						// Adding to existing retaurant condition.
						} else if (adminChoice == 2){
							System.out.println("Enter the restaurantId");
							Integer id = sc.nextInt();
							System.out.println("Enter the first name of the item to be added");
							String name = sc.next();
							System.out.println("Enter the item price");
							Double price = sc.nextDouble();
							cafe.addItems(id,new Item(name, price));
						}
					}

				// Register condition.
				} else if(choice == 3) {
					System.out.println("Enter your first name");
					String newUserName = sc.next();
					System.out.println("Enter your e-mail");
					String newUseremail = sc.next();
					System.out.println("Enter your password");
					String newUserpassword = sc.next();
					System.out.println("Please do confirm your password");
					String newUserpasswordConfirm = sc.next();
					if (newUserpassword.equals(newUserpasswordConfirm)) {
						cafe.addUser(newUserName, newUseremail, newUserpassword);
					} else {
						System.out.println("Please enter the correct password!");
					}
				}

			}
		} while(check);

	}
}