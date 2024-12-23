package com.example.project123;
import java.util.*;
enum gender{
    male,female
}

public class Customer extends Person implements showingOrders{
    private static int num_of_customers=0;
    private String phonenumber;
    private Cart cart=new Cart();
    private double balance;
    private String address;
    private String interest;
    private gender gen;

    public ArrayList<Order>orders;
    private double tempbalance;
    Scanner input=new Scanner(System.in);
    Customer(){
        num_of_customers++;
        orders=new ArrayList<>();
    }
    //interst should be category
    Customer(String user, String pass, String date, double balance,gender g,String inter,String phonenumber,String address){
        super(user, pass, date);
        this.phonenumber=phonenumber;
        this.balance=balance;
        this.tempbalance=this.balance;
        this.gen=g;
        this.interest=inter;
        this.address=address;
        orders=new ArrayList<Order>();//kanet met3rafa marten
        Database.customers.add(this);
        num_of_customers++;
//        //dummy data
//        Product p1 = new Product("h&m", 110, 2);
//        Product p2 = new Product("north", 110, 2);
//        Product p3 = new Product("ae", 110, 2);
//        Product p4 = new Product("manga", 110, 2);
//        Product p5 = new Product("bershka", 110, 2);
//        cart.setCartMap(p1,1);
//        cart.setCartMap(p2,1);
//        cart.setCartMap(p3,1);
//        cart.setCartMap(p4,1);
//        cart.setCartMap(p5,1);

    }
    public Cart getcartt(){
        return cart;
    }
    public  void setorder(Order o){
        orders.add(o);

    }
    public double getTempbalance() {
        return tempbalance;
    }

    public void setTempbalance(double tempbalance) {
        this.tempbalance = tempbalance;
    }

    public static int getNum_of_customers() {
        return num_of_customers;
    }

    public String getInterest() {
        return interest;
    }

    public ArrayList<Order> getOrders() {
        if(!orders.isEmpty()) {
            return orders;
        }
        else{
            throw new IllegalStateException("No orders available");
        }

    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    public void setInterest(String inter)
    {
        this.interest=inter;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return super.getUsername();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public gender getGen() {
        return gen;
    }

    public void setGen(gender gen) {
        this.gen = gen;
    }

    public void viewCart_general(){
        if(cart.getCartMap().isEmpty()){
            System.out.println("cart is empty");
        }
        else {
            for (Map.Entry<Product, Integer> entry : cart.getCartMap().entrySet()) {
                String item = entry.getKey().getName();
                Integer quantity = entry.getValue();
                System.out.println("Product: " + item + ", Quantity: " + quantity);
            }
            System.out.println("Total price of cart: " + cart.getTotalPrice());
        }
    }
    Category c=new Category();


    public void viewCart() {
        // Check if empty
        // Try-catch
        viewCart_general();
        System.out.println("[1] Add To Cart\n[2] Remove From Cart\n[3] Checkout\n[4] Home");

        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            try {
                choice = input.nextInt();
                validChoice = true; // Exit loop if input is valid
            } catch (InputMismatchException ex) {
                System.out.println("Error: Please enter a valid integer.");
                input.nextLine(); // Clear the invalid input
            }
        }

        if (choice == 1) {
            c.ShowCategories_general();
            System.out.println("Input Product to Add:");
            String selectedProduct = input.next();

            int quantity = 0;
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Input Quantity to Add:");
                try {
                    quantity = input.nextInt();
                    validInput = true; // Exit loop if input is valid
                } catch (InputMismatchException ex) {
                    System.out.println("Error: Please enter a valid integer for quantity.");
                    input.nextLine(); // Clear the invalid input
                }
            }
            addToCart(selectedProduct, quantity);
        }
        else if (choice == 2) {
            System.out.println("Input Product to Remove:");
            String selectedProduct = input.next();

            int quantity = 0;
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Input Quantity to Remove:");
                try {
                    quantity = input.nextInt();
                    validInput = true; // Exit loop if input is valid
                } catch (InputMismatchException ex) {
                    System.out.println("Error: Please enter a valid integer for quantity.");
                    input.nextLine(); // Clear the invalid input
                }
            }
            RemoveFromCart(selectedProduct, quantity);
        }
//        else if (choice == 3) {
//            checkout();
//        }
        else if (choice == 4) {
            return;
        }
        else {
            System.out.println("Invalid number. Please try again.");
        }
    }

    public void addToCart(String p, Integer pc1) {
        if (pc1 == 0 || pc1 <= 0) {
            System.out.println("Error: Quantity must be a positive number.");
            handleExit();
            return;
        }

        Product p1 = null;

        // Find the product by name in the database
        for (Product products : Database.products) {
            if (p.equals(products.getName())) {
                p1 = products;
                break;
            }
        }

        if (p1 == null) {
            System.out.println("Product isn't found");
            handleExit();
            return;
        }

        Integer currentQuantity = cart.getCartMap().getOrDefault(p1, 0);
        int availableStock = p1.getStock() - currentQuantity;

        if (availableStock <= 0) {
            System.out.println("No available stock for this product.");
            handleExit();
            return;
        }

        if (pc1 > availableStock) {
            System.out.println("No available number of products. Available quantity: " + availableStock);
            handleExit();
            return;
        }

        double totalCost = p1.getPrice() * pc1;

        if (tempbalance< totalCost) {
            System.out.println("Not enough balance.");
            System.out.println("Exceeded your balance by " + (totalCost - tempbalance));
            handleExit();
            return;

        }

        // Update cart and balance
        cart.setCartMap(p1, currentQuantity + pc1);
        cart.setTotalPrice(cart.getTotalPrice() + totalCost);
        tempbalance -= totalCost;

        System.out.println("Added to cart successfully!");
        System.out.println("If you are interested to check out, your temporary balance will be: " + tempbalance);
        System.out.println("The total price is: " + cart.getTotalPrice());

    }

    private boolean handleExit() {
        System.out.println("Input another product name (or enter 'exit' to exit):");
        String x = input.nextLine();

        if ("exit".equalsIgnoreCase(x)) {
            return false;
        }

        System.out.println("Input another quantity:");
        try {
            int pc1 = input.nextInt();
            input.nextLine(); // Clear buffer
            addToCart(x, pc1);
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please enter a valid integer for quantity.");
            input.nextLine(); // Clear the invalid input
        }
        return true;
    }

    public void RemoveFromCart(String p, Integer pc2) {
        if (pc2 == null || pc2 < 1) {
            System.out.println("Error: Quantity must be a positive number.");
            return;
        }

        Product p2 = null;

        // Check if the product exists in the database
        for (Product products : Database.products) {
            if (p.equals(products.getName())) {
                p2 = products;
                break;
            }
        }

        if (p2 == null) {
            System.out.println("Product not found in the database.");
            handleRetryForRemoval();
            return;
        }

        // Check if the cart is empty
        if (cart.getCartMap().isEmpty()) {
            System.out.println("The cart is empty. Nothing to remove.");
            return;
        }

        Integer currentQuantity = cart.getCartMap().get(p2);

        // Check if the product exists in the cart
        if (currentQuantity == null) {
            System.out.println("Product does not exist in the cart.");
            handleRetryForRemoval();
            return;
        }

        // Validate the quantity to be removed
        if (pc2 > currentQuantity) {
            System.out.println("Error: Quantity of removed items exceeds available quantity in the cart.");
            System.out.println("Available quantity: " + currentQuantity);
            System.out.println("Input another Quantity:");
            pc2 = input.nextInt();
            input.nextLine(); // Clear the buffer
            RemoveFromCart(p, pc2);
            return;
        }

        // Perform removal or adjustment
        if (pc2.equals(currentQuantity)) {
            cart.getCartMap().remove(p2); // Remove product entirely from the cart
        } else {
            cart.getCartMap().replace(p2, currentQuantity - pc2); // Adjust the quantity in the cart
        }

        // Update the cart's total price and the temporary balance
        double totalPriceAdjustment = p2.getPrice() * pc2;
        cart.setTotalPrice(cart.getTotalPrice() - totalPriceAdjustment);
        tempbalance += totalPriceAdjustment;

        System.out.println("Product successfully removed. Updated cart:");
        System.out.println("Temporary balance: " + tempbalance);
        System.out.println("Total price in cart: " + cart.getTotalPrice());
    }

    private void handleRetryForRemoval() {
        System.out.println("Input another product name (or enter 'exit' to exit):");
        String x = input.nextLine();

        if ("exit".equalsIgnoreCase(x)) {
            return;
        }

        System.out.println("Input another quantity:");
        try {
            int pc2 = input.nextInt();
            input.nextLine(); // Clear buffer
            RemoveFromCart(x, pc2);
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please enter a valid integer for quantity.");
            input.nextLine(); // Clear invalid input
        }
    }
    public void Search() {
        System.out.println("Enter the product: ");
        String selected = input.next();
        Product p1=null;
        boolean found = false;
        for (Product prod : Database.products) {
            if (selected.equals(prod.getName())) {
                System.out.println(prod.toString());
                found = true;
                p1=prod;
                break;
            }
        }

        if (!found) {
            System.out.println("item not found!");
            return;
        }
        else{

            System.out.println("you might also like: ");
            for (Category cat1 : Database.categories) {
                for (Product prod : cat1.getProducts()) {
                    if (p1.getName().equals(prod.getName()) && cat1.getProducts().contains(p1)) {
                        for (Product pro : cat1.getProducts()) {
                            if(!p1.getName().equals(pro.getName()))
                                System.out.println(pro.toString());
                        }
                    }
                }

            }
            System.out.println("[1]Add item to cart\n[2]Home");
            try {
                int choice = input.nextInt();
                input.nextLine();
                if (choice == 1) {
                    System.out.println("Enter quantity you want:");
                    int quant = input.nextInt();
                    input.nextLine();
                    addToCart(p1.getName(), quant);
                } else if (choice == 2)
                    return;
                else
                    System.out.println("invalid choice:");
            }
            catch (InputMismatchException ex){
                System.out.println("Error: Please enter a valid integer.");
                input.nextLine();
            }
        }

    }
    //    public void checkout(){
//        Order o=new Order(cart,balance,super.getUsername(),this.address,this.phonenumber);
//        o.Checkout1();
//        Database.orders.add(o);
//        this.orders.add(o);
//        if(o.retmethod()==2){
//            balance=o.customerBalance;
//        }
//        o.copycart();
//        cart.getCartMap().clear();
//        cart.setTotalPrice(0);
//        return;
//    }
    @Override
    public void View_data(){
        System.out.println("Username "+this.getUsername()+"\npassword: "+this.getPassword());
        System.out.println("Address:"+this.getAddress()+"\nDate of birth: "+this.getDate_of_birth()+"\nGender: "+this.getGen());
        System.out.println("Phone number is: "+this.phonenumber);
        System.out.println("Balance: "+this.getTempbalance());
        System.out.println("Intersts:"+this.getInterest());

    }
    @Override
    public void Modify_data() {
        View_data();
        System.out.println("What data do you want to modify?");
        System.out.println("[1] Username\n[2] Password\n[3] Address\n[4] Interest\n[5] Balance\n[6]Phone Number\n[7]Home");

        int choice;
        try {
            choice = input.nextInt(); // Read the choice
            input.nextLine(); // Clear the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.println("Enter new username: ");
                    String username = input.next();
                    this.setUsername(username);
                    System.out.println("Username updated successfully!");
                    break;
                case 2:
                    boolean check=false;
                    int trial=4;
                    while (!check&&trial>0) {
                        System.out.println("Enter your password: ");
                        String selectedPass = input.next();
                        trial--;
                        if (selectedPass.equals(this.password)) {
                            check = true;
                            System.out.println("Enter new password: ");
                            String password = input.next();
                            this.setPassword(password);
                            System.out.println("Password updated successfully!");
                        } else {
                            if (trial > 0)
                                System.out.println("Error:Invalid password, please enter your password correctly<3");
                        }
                    }
                    if(trial==0)
                        System.out.println("You have reached to your limit please remember your old password to be able to change it");
                    break;
                case 3:
                    System.out.println("Enter new address: ");
                    String address = input.next();
                    this.setAddress(address);
                    System.out.println("Address updated successfully!");
                    break;
                case 4:
                    System.out.println("Enter new interest: ");
                    String interest = input.next();
                    this.setInterest(interest);
                    System.out.println("Interest added successfully!");
                    break;
                case 5:
                    System.out.println("Enter amount to recharge your balance: ");
                    while (!input.hasNextInt()) { // Handle non-integer input
                        System.out.println("Invalid input. Please enter a number.");
                        input.next(); // Clear invalid input
                    }
                    int balance = input.nextInt();
                    this.setTempbalance(balance+this.tempbalance);
                    System.out.println("Balance updated successfully!");
                    break;
                case 6:
                    System.out.println("Enter new phone number: ");
                    String phonenumber = input.next();
                    this.setPhonenumber(phonenumber);
                    System.out.println("Phone number updated successfully!");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please try again.");
            input.nextLine(); // Clear the invalid input
        }
    }
    @Override
    public Customer Login(String user, String pass) {
        for (Customer customer : Database.customers) {
            if (user.equals(customer.getUsername()) && pass.equals(customer.getPassword())) {
                return customer; // Return the customer if match
            }
        }
        return null; // Return null if no match is found
    }
    //override tostring



    @Override
    public void SignUp() {
        //Customer c = new Customer();

        System.out.println("Enter address:");
        setAddress(input.next());
        System.out.println("Enter Date of birth:");
        setDate_of_birth(input.next());
        while (true) {
            System.out.println("Enter gender (male/female):");
            try {
                setGen(gender.valueOf(input.next().toLowerCase()));
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input! Please enter 'male' or 'female'.");
                input.nextLine();
            }
        }
        System.out.println("charge ur balance:");
        setBalance(input.nextInt());
        setTempbalance(this.balance);
        System.out.println("Enter your Phone number:");
        setPhonenumber(input.next());
        c.ShowInterests();
        boolean finterest=false;
        while (!finterest) {
            System.out.println("Select your interest: ");
            String selectedinterest = input.next();
            for (Category category : Database.categories) {
                if (category.getName().equals(selectedinterest)) {
                    setInterest(selectedinterest);
                    finterest = true;
                    break;
                }
            }

            if (!finterest)
                System.out.println("Invalid interest! Please try again.");

        }
        boolean signupComplete = false;

        while (!signupComplete) {
            // Step 1: Validate Username
            System.out.println("Enter username:");
            String user = input.next();
            boolean usernameExists = false;

            for (Customer c : Database.customers) {
                if (user.equals(c.getUsername())) {
                    System.out.println("Username already exists. Please try again.");
                    usernameExists = true;
                    break;
                }
            }

            if (!usernameExists) {
                setUsername(user);

                // Step 2: Validate Password
                boolean passwordsMatch = false;
                while (!passwordsMatch) {
                    System.out.println("Enter password:");
                    String pass = input.next();

                    System.out.println("Enter password again:");
                    String pass2 = input.next();

                    if (pass.equals(pass2)) {
                        setPassword(pass);
                        passwordsMatch = true;
                        signupComplete = true;
                        Database.customers.add(this); // Add user to database
                        System.out.println("Sign-up successful!");
                    } else {
                        System.out.println("Passwords do not match. Please try again.");
                    }
                }
            }
        }

    }
    int i=0;
    public void vieworders(int i){
        try {
            for (Order od : getOrders()) {
                if (!getOrders().isEmpty()) {
                    System.out.print("Phone Number: ");
                    System.out.println(od.phoneno);
                    System.out.print("Address: ");
                    System.out.println(od.adress);
                    System.out.print("Name: ");
                    System.out.println(od.customername);
                    System.out.print("Order Data: ");
                    System.out.println(od.orderDate);
                    System.out.print("Total Price");
                    System.out.println(od.totalprice);
                    System.out.println("Customer Order");
                    for (Map.Entry<Product, Integer> entry : od.getcustomerorder().getCartMap().entrySet()) {
                        String item = entry.getKey().getName();
                        Integer quantity = entry.getValue();
                        System.out.println("Product: " + item + ", Quantity: " + quantity);
                    }
                } else {
                    System.out.println("No orders avalibale");
                }

            }
        }catch (Exception e){
            System.out.println("No orders avalibale");
        }


    }

    @Override
    public void showCategories() {
        for (Category cat : Database.categories) {
            System.out.print("Category:" + cat.getName());
            System.out.print(" ID: " + cat.getId());
            System.out.println();
            for (Product prod : cat.getProducts()) {
                System.out.println("\tProduct: " + prod.getName());
            }

        }

        System.out.println();
        boolean value = true;
        while (value) {
            System.out.println("[1] More details\n[2] Home");
            int choice = 0;
            try {
                choice = input.nextInt();
                input.nextLine(); // Clear the buffer
            } catch (InputMismatchException ex) {
                System.out.println("Error: Expected an integer number.");
                input.nextLine(); // Clear the invalid input
                continue;
            }

            if (choice == 2) {
                value = false; // Ensures the loop stops before returning
                return;
            } else if (choice == 1) {
                boolean ffound = false;
                boolean fffound = false;
                value = false;

                while (!ffound) {
                    System.out.println("Enter name of Category to see more details:");
                    String selected1 = input.nextLine();

                    for (Category cat1 : Database.categories) {
                        if (selected1.equals(cat1.getName())) {
                            ffound = true;

                            // Product selection loop
                            while (!fffound) {
                                try {
                                    System.out.println("[1]Product\n[2]All Category");
                                    int cho = input.nextInt();
                                    switch (cho) {
                                        case 1:
                                            System.out.println("Enter name of product to see more details:");
                                            String selected = input.next();

                                            for (Product prod : cat1.getProducts()) {
                                                if (selected.equals(prod.getName())) {
                                                    fffound = true;
                                                    System.out.println("Name: " + prod.getName());
                                                    System.out.println("Price: " + prod.getPrice());
                                                    System.out.println("Stock: " + prod.getStock());
                                                    break;
                                                }
                                            }

                                            if (!fffound) {
                                                System.out.println("Invalid product. Please try again.");
                                            }
                                            return;
                                        case 2:
                                            cat1.ShowProducts();
                                            return;
                                        default:
                                            System.out.println("Invalid!");
                                            break;
                                    }
                                }
                                catch (InputMismatchException ex){
                                    System.out.println("Error: Please enter a valid integer.");
                                    input.nextLine();
                                }
                            }
                            break;
                        }
                    }

                    if (!ffound) {
                        System.out.println("Invalid category. Please try again.");
                    }
                }
            } else
                System.out.println("Invalid choice. Please try again.");
        }
    }
}