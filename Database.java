package com.example.project123;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Customer> customers=new ArrayList<Customer>();
    public static ArrayList<Admin> admins=new ArrayList<Admin>();
    public static ArrayList<Category> categories=new ArrayList<Category>();
    public static ArrayList<Product>products=new ArrayList<Product>();
    public static ArrayList<Order>orders=new ArrayList<Order>();
    static {
        // Add Categories and Products
        Category cat = new Category("jackets", "001");
        Category cat1 = new Category("jeans", "002");
        Category cat2 = new Category("hoodies", "003");

        Product prod = new Product("northface", 100, 4,"C:\\Users\\Hassan\\Desktop\\oop\\demo\\src\\main\\resources\\com\\example\\demo\\north.jpg");
        //  Product prod1 = new Product("calvinklein", 300, 1);
        //     Product prod2 = new Product("AE", 200, 0);
        //  Product prod3 = new Product("pull&bear", 75, 10);
        // Product prod4 = new Product("h&m", 110, 2);
        //  Product prod5 = new Product("bershka", 250, 7);

        cat.setProducts(prod);
        //   cat.setProducts(prod1);
        //   cat.setProducts(prod2);
        //  cat1.setProducts(prod3);
        //   cat1.setProducts(prod4);
        //  cat1.setProducts(prod5);


        Cart cart=new Cart();
        cart.setCartMap(prod,2);
        Database.orders.add(new Order(cart, 1000, "Eslam", "Nasr", "01050216605"));


        new Customer("eslam", "eslam123", "18/4/2005", 1000, gender.male, "jackets", "01050216605", "nasr");
        new Customer("wael", "wael123", "18/9/2005", 100, gender.male, "hoodies", "010000056", "october");
        new Customer("carol", "carol123", "10/9/2005", 110, gender.female, "jeans", "0115984211", "libya");
        new Customer("hassan", "hassan123", "1/12/2005", 1301, gender.male, "shorts", "010000056", "nasr");
        new Customer("lugine", "lugine123", "14/2/2005", 10, gender.male, "shoes", "010000056", "aa");

        // Add Admins
        new Admin("admin", "admin123", "18aug1998", "boss", 18);
    }

}