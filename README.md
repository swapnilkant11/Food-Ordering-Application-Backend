# Food-Ordering-Application-Backend

## This documentation is about the Cafeteria System designed, where I have assumed two very important conditions stated below:

1. The admin userId by default is chosen as 111 which is common to every admin.
2. The admin Password is default chosen as “admin” (without quotes) which is common to every admin.

### Some of the other important input conditions are:

1. Enter only the first name of the person or restaurant as directed in the terminal while you run the program e.g. "Enter the first name of restaurant" then enter only the first word (without spaces).
2. Please do enter your item names from the menu without leaving any space after the comma as directed in the application e.g. here when prompted "Please provide items in comma separated format from the below List:" enter your orders as example roti,paneer,chicken (without space after the comma)

### Some of the important point to be noted is mentioned below:

1. First, please do log in as admin and add restaurant/items so that you can place orders later on as a user.
2. Then, register as a new user before you order any new item from the application.
3. Then, after following the above two steps sequentially login as a user you have registered and order now!! To enjoy your meal.

How to run the application?
1. Download the file.
2. (Run through the terminal) Go to the src folder and type “javac impl/*.java” (without quotes).
3. The program shall compile and then type “java impl.Cafeteria”  (without quotes).
4. The program is working!!.

## Note: All files should be present in the same folder/directory.


### Example Input and Output:

*** Welcome To Food Ordering Service ***
Press 1 to login as User
Press 2 to Login as Admin
New User?? Press 3 to Register
Press 4 to logout
2
Enter the AdminID
111
Enter the password
admin
Admin Authenticated
Press 1 to add new Restaurant!
Press 2 to add items to existing Restaurant!
1
Enter the first name of restaurant
Rainbow
Enter the no of employees
12
Your restaurantId is: 1
Press 1 to add Items
1
Enter the first name of the item to be added
Paneer
Enter the item price
200
Item added: Paneer
Press 1 to add more item or Press 2 to exit
1
Enter the first name of the item to be added
Chicken
Enter the item price
400
Item added: Chicken
Press 1 to add more item or Press 2 to exit
1
Enter the first name of the item to be added
Pizza
Enter the item price
500
Item added: Pizza
Press 1 to add more item or Press 2 to exit
2
*** Welcome To Food Ordering Service ***
Press 1 to login as User
Press 2 to Login as Admin
New User?? Press 3 to Register
Press 4 to logout
3
Enter your first name
Swapnil
Enter your e-mail
swapnilkant11@gmail.com
Enter your password
swapnil123
Please do confirm your password
swapnil123
Your userId is: 1
*** Welcome To Food Ordering Service ***
Press 1 to login as User
Press 2 to Login as Admin
New User?? Press 3 to Register
Press 4 to logout
1
Enter your userID
1
Enter your password
swapnil123
User Authenticated
Press 1 to Order Now!!
Press 2 to track your order
1
Please provide items in comma separated format from the below List:
Pizza
Chicken
Paneer
Pizza,Chicken
Restaurant: Rainbow making the order
Your order is tracked by orderId: 0
*** Welcome To Food Ordering Service ***
Press 1 to login as User
Press 2 to Login as Admin
New User?? Press 3 to Register
Press 4 to logout
1
Enter your userID
1
Enter your password
swapnil123
User Authenticated
Press 1 to Order Now!!
Press 2 to track your order
2
Please do enter your Order id
Your order ids are:
Order name: [Pizza, Chicken] and ID:0
2
Order not found: 2
*** Welcome To Food Ordering Service ***
Press 1 to login as User
Press 2 to Login as Admin
New User?? Press 3 to Register
Press 4 to logout
1
Enter your userID
1
Enter your password
swapnil123
User Authenticated
Press 1 to Order Now!!
Press 2 to track your order
2
Please do enter your Order id
Your order ids are:
Order name: [Pizza, Chicken] and ID:0
0
Order is ready to be pickedup
*** Welcome To Food Ordering Service ***
Press 1 to login as User
Press 2 to Login as Admin
New User?? Press 3 to Register
Press 4 to logout
4

### For time complexity of the code will be fetching from the map is O(1).
