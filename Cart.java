package com.example.project123;

import java.util.*;

public class Cart {
    private Map< Product,Integer>  CartMap;
    private double totalPrice;
    Cart(){
        totalPrice=0;
        this.CartMap= new HashMap<>();
    }
    public Map<Product, Integer> getCartMap() {
        return CartMap;
    }
    public void setCartMap(Product p,Integer pc) {
        CartMap.put(p,pc);
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


}

