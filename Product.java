package com.example.project123;

public class Product {
    private double price;
    private String name;
    private int stock;
    private String imagePath; // Add an image path field

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        Database.products.add(this);
    }

    // Constructor that includes imagePath
    public Product(String name, double price, int stock, String imagePath) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
        Database.products.add(this);
    }

    public Product() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", stock=" + stock + ", imagePath=" + imagePath + "}";
    }
}