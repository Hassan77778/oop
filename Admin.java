package com.example.project123;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Person implements showingOrders {
    private String role;
    private int working_Hours;
    Scanner input = new Scanner(System.in);

    public Admin() {
    }

    public Admin(String user, String pass, String date, String role, int hours) {
        super(user, pass, date);
        this.role = role;
        this.working_Hours = hours;
        Database.admins.add(this);
    }
    public String getUsername(){
        return super.getUsername();
    }
    public void setRole(String role) {
        this.role = role;
    }

    public void setWorking_Hours(int working_Hours) {
        this.working_Hours = working_Hours;
    }

    public String getRole() {
        return role;
    }

    public int getWorking_Hours() {
        return working_Hours;
    }

    @Override
    public void View_data() {
    }

    @Override
    public void Modify_data() {

    }

    @Override
    public Admin Login(String user, String pass) {
        for (Admin adm : Database.admins) {
            if (user.equals(adm.getUsername()) && pass.equals(adm.getPassword())) {
                return adm; // Return the customer if credentials match
            }
        }
        return null; // Return null if no match is found
    }

    @Override
    public void SignUp() {

    }

//    public void showcustomers() {
//        for (int i = 0; i < Database.customers.size(); i++) {
//            System.out.println("Customer " + (i+1));
//            System.out.println(( "Username:" + Database.customers.get(i).getUsername()));
//            System.out.println("Password:" + Database.customers.get(i).getPassword());
//            System.out.println( "Date of birth:" + Database.customers.get(i).getDate_of_birth());
//            System.out.println( "Balance:" + Database.customers.get(i).getBalance());
//            System.out.println( "Gender:" + Database.customers.get(i).getGen());
//            System.out.println( "Phone Number:" + Database.customers.get(i).getPhonenumber());
//            System.out.println( "Address:" + Database.customers.get(i).getAddress());
//            System.out.println(  "Orders:");
//            vieworders(i);
//            System.out.println("Interests:");
//
//            for (String inters : Database.customers.get(i).getInterest()) {
//                System.out.println(inters + "\t");
//            }
//            System.out.println("\n");
//
//        }
//    }

    @Override
    public void vieworders(int i) {
        if(Database.customers.get(i) != null) {
            Database.customers.get(i).vieworders(i);
        }
        else{
            System.out.println("No orders to view");
        }
    }

    public void ShowCategories_general() {
        for (int i = 0; i < Database.categories.size(); i++) {
            System.out.println(" Category Name:" + Database.categories.get(i).getName());
            System.out.println(" Category Id:" + Database.categories.get(i).getId());
            System.out.println(" Category Products:");
            Database.categories.get(i).ShowProducts();
            System.out.println("\n");

        }
        boolean out = false;
        while (!out) {
            System.out.println("[1]Add category\n[2]Add products\n[3]Remove category\n[4]update category data\n[5]update product data\n[6]Exit\n");
            int x = input.nextInt();

            switch (x) {
                case 1:
                    addcategory();
                    ;
                    //format
                    break;
                case 2:
                    addproducts();
                    break;
                case 3:
                    removecategory();
                    break;
                case 4:
                    updateCategoryData();
                    break;
                case 5:
                    updateProductData();
                    break;
                case 6:
                    out = true;
                    break;
                default:
                    System.out.println("invalid");
                    break;
            }
        }
    }

    public void SHOWORDERS() {
        if (!Database.orders.isEmpty()) {
            for (int i = 0; i < Database.orders.size(); i++) {
                Database.orders.get(i).showorders();
            }
        } else {
            System.out.println("There is no orders yet\n");
        }

    }

    public void addcategory() {

        boolean found = false;
        System.out.println("Enter Category name:");
        String name = input.next();
        System.out.println("Enter Category id:");
        String id = input.next();

        for (int i = 0; i < Database.categories.size(); i++) {
            if (name.equals(Database.categories.get(i).getName())) {
                found = true;
            }
        }
        if (!found) {
            Category c1 = new Category(name, id);
            Database.categories.add(c1);
            System.out.println("Category is added successfully");
        } else {
            System.out.println("This category is already exists");
        }

    }


    public void addproducts() {
        System.out.println("Enter the name of the category that you want to add products to:");
        boolean found = false;

        try {
            String category_name = input.next();
            for (int i = 0; i < Database.categories.size(); i++) {
                if (category_name.equals(Database.categories.get(i).getName())) {
                    System.out.println("Enter how many products you want to add to this Category:");
                    found = true;

                    int no_of_products = input.nextInt();
                    for (int j = 0; j < no_of_products; j++) {
                        System.out.println("Enter product name:");
                        String product_name = input.next();
                        System.out.println("Enter product price:");
                        double price = input.nextDouble();
                        System.out.println("Enter the amount of this product in stock:");
                        int stock = input.nextInt();

                        // Check if the product already exists
                        boolean productExists = false;
                        for (Product existingProduct : Database.categories.get(i).getProducts()) {
                            if (existingProduct.getName().equals(product_name)) {
                                productExists = true;
                                break;
                            }
                        }
                        if (productExists) {
                            System.out.println("Product \"" + product_name + "\" already exists in this category.");
                        } else {
                            Product p = new Product(product_name, price, stock);
                            Database.categories.get(i).getProducts().add(p);
                            System.out.println("Product: \"" + product_name + "\" has been added.");
                        }
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("This Category does not exist.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            input.nextLine(); // Clear the invalid input from the scanner
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    public void removecategory() {

        System.out.println("Enter Category name you want to remove:");
        String name = input.next();
        System.out.println("Enter Category id:");
        String id = input.next();

        boolean found = false;
        for (int i = 0; i < Database.categories.size(); i++) {
            if (name.equals(Database.categories.get(i).getName()) && id.equals(Database.categories.get(i).getId())) {
                Database.categories.remove(Database.categories.get(i));
                System.out.println("The Category is removed");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("This Category does not exists");
        }
    }


    public void updateCategoryData() {
        System.out.println("Enter name of the category that you want to update data in");
        String cat = input.next();
        boolean found = false;
        for (int i = 0; i < Database.categories.size(); i++) {
            if (cat.equals(Database.categories.get(i).getName())) {
                found = true;
                System.out.println("What do you want to update?");
                System.out.println("[1] Name \n[2] ID ");
                try {
                    int a = input.nextInt();

                    switch (a) {
                        case 1:
                            System.out.println("Enter the new Name you want ");
                            String names = input.next();
                            (Database.categories.get(i)).setName(names);
                            System.out.println("Name of the category is updated");
                            break;

                        case 2:
                            System.out.println("Enter the new ID you want");
                            String id = input.next();
                            Database.categories.get(i).setId(id);
                            System.out.println("ID of the category is updated");
                            break;

                    }
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("invalid input");
                }
            }

        }
        if (!found) {
            System.out.println("This Category does not exists");
            return;
        }

    }

    public void updateProductData() {
        System.out.println("Enter name of the category of product that you want to update data in");
        String cat = input.next();
        System.out.println("Enter name of the product that you want to update data in");
        String prod = input.next();
        boolean foundc = false;
        boolean foundp = false;
        for (int i = 0; i < Database.categories.size(); i++) {
            if (cat.equals(Database.categories.get(i).getName())) {
                foundc = true;
                for (int j = 0; j < Database.categories.get(i).getProducts().size(); j++) {
                    if (prod.equals(Database.categories.get(i).getProducts().get(j).getName())) {
                        foundp = true;
                        System.out.println("What do you want to update?");
                        System.out.println("[1] Name \n [2] Price \n [3]Stock");
                        try {
                            int a = input.nextInt();
                            switch (a) {
                                case 1:
                                    System.out.println("Enter the new Name you want ");
                                    String names = input.next();
                                    Database.categories.get(i).getProducts().get(j).setName(names);
                                    break;

                                case 2:
                                    System.out.println("Enter the new Price you want");
                                    double prices = input.nextDouble();
                                    Database.categories.get(i).getProducts().get(j).setPrice(prices);
                                    break;
                                case 3:
                                    System.out.println("Enter the new Stock Number you want");
                                    Integer stocks = input.nextInt();
                                    Database.categories.get(i).getProducts().get(j).setStock(stocks);
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("invalid input");
                        }
                        if (!foundp) {
                            System.out.println("This Product does not exist");
                            return;
                        }
                    }
                }
            }
            if (!foundc) {
                System.out.println("This Category does not exist");
                return;
            }

        }


    }

    public void add_new_admin() {
        // check for the admin role
        if (this.role.equalsIgnoreCase("Boss")) {
            System.out.println("Add a new Admin");
            System.out.println("Enter username");
            String newuser = input.next();
            System.out.println("Enter a password:");
            String newpass = input.next();
            boolean founnd = false;
            for (int i = 0; i < Database.admins.size(); i++) {
                if ((Database.admins.get(i).getUsername()).equals(newuser) && (Database.admins.get(i).getPassword()).equals(newpass)) {
                    founnd = true;
                    break;
                }
            }
            if (founnd) {
                System.out.println("Admin already exist");
                return;
            } else {
                System.out.println("Enter Date of birth:");
                String newdate = input.next();

                System.out.println("Enter working hours:");
                int newworking = 0;
                boolean validInput = false;

                while (!validInput) {
                    try {
                        newworking = input.nextInt();
                        validInput = true; // Exit loop if input is valid
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for working hours:");
                        input.next(); // Clear the invalid input
                    }
                }

                System.out.println("Enter role of Admin:");
                input.nextLine(); // Ensure no leftover newlines
                String newrole = input.nextLine();

                Admin a = new Admin(newuser, newpass, newdate, newrole, newworking);

                System.out.println("Admin added successfully");
            }

        } else {
            System.out.println("Can not add a new admin your role is not a boss");
        }
    }

    @Override
    public void showCategories() {
        for (int i = 0; i < Database.categories.size(); i++) {
            System.out.println("Category name:" + Database.categories.get(i).getName());
            System.out.println("Category id:" + Database.categories.get(i).getId());
            System.out.println("Category products:");
            Database.categories.get(i).ShowProducts();
        }
        System.out.println("\n");
    }
}







