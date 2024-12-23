package com.example.project123;

import java.util.*;
public class Category {
    private String name;
    private String id;
    private ArrayList<Product> products;
    Scanner input=new Scanner(System.in);
    public Category() {
    }



    public Category(String name, String id) {//admin calls this constructor
        this.name = name;
        this.id = id;
        this.products = new ArrayList<Product>();
        Database.categories.add(this);

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(Product p) {
        products.add(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void ShowInterests(){
        for (int i = 0; i < Database.categories.size(); i++) {
            System.out.println("Category name:" + Database.categories.get(i).getName());
            System.out.println("Category id:" + Database.categories.get(i).getId());
        }
        System.out.println("\n");
    }

    public void ShowCategories_general() {
        for (int i = 0; i < Database.categories.size(); i++) {
            System.out.println("Category name:" + Database.categories.get(i).getName());
            System.out.println("Category id:" + Database.categories.get(i).getId());
            System.out.println("Category products:");
            Database.categories.get(i).ShowProducts();
        }
        System.out.println("\n");
    }

    public void ShowProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(" Name: " + products.get(i).getName());
            System.out.println(" Price: " + products.get(i).getPrice());
            System.out.println(" Stock: " + products.get(i).getStock());
        }
    }


}
