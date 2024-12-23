package com.example.project123;

import java.util.*;

public class Order {
    String method;
    Date orderDate;
    static int id;
    Cart customerorder=new Cart();
    double customerBalance;
    String customername;
    String adress;
    String phoneno;
    double totalprice = 0;
    private Map<Product, Integer> viewcart = new HashMap<>();
    public Order(){}
    //    public Order(Cart c, double balace, String name, String AD, String ph) {
//        this.customerorder = c;
//        this.customerBalance = balace;
//        this.customername = name;
//        this.adress = AD;
//        this.phoneno = ph;
//        orderDate = new Date();
//        id++;
//    }
    void corder(Customer c){
        this.customername=c.getUsername();
        for (Map.Entry<Product, Integer> entry : c.getcartt().getCartMap().entrySet()) {
            Product p1 = entry.getKey();
            Integer q = entry.getValue();
            customerorder.getCartMap().put(p1,q);
        }
        this.adress=c.getAddress();
        this.phoneno=c.getPhonenumber();
        this.totalprice=customerorder.getTotalPrice();

    }
//    public Order(Cart c, String name, String AD, String ph) {
//        this.customerorder = c;
//        this.customername = name;
//        this.adress = AD;
//        this.phoneno = ph;
//        orderDate = new Date();
//        id++;
//    }


//    public Map<Product, Integer> getViewcart() {
//        return viewcart;
//    }
//
    ////    public Cart getCustomerorder() {
//        return customerorder;
//    }

    void copycart() {
        for (Map.Entry<Product, Integer> entry : customerorder.getCartMap().entrySet()) {
            Product p1 = entry.getKey();
            Integer q = entry.getValue();
            viewcart.put(p1, q);
        }

    }
    public Cart getcustomerorder(){
        return customerorder;
    }
//
//    private void balance(Scanner input) {
//        try {
//            if (customerBalance < customerorder.getTotalPrice()) {
//                System.out.println("Balance Insufficent");
//                System.out.println("Press (1) for COD  ");
//                System.out.println("Press (2) to return to home");
//
//                int choice = validate(input);
//                if (choice == 1) {
//                    input.nextLine();
//                    cod(input);
//
//                } else return;
//            } else {
//                System.out.println("NAME: " + customername);
//                System.out.println("Phone: " + phoneno);
//                System.out.println("Address: " + adress);
//                System.out.println("Balance after checkout is : " + (customerBalance - customerorder.getTotalPrice()));
//                System.out.println("Do you want to change anything? 1 - Yes, 2 - No");
//                int ans = validate(input);
//                while (ans == 1) {
//                    System.out.println("What do you want to change : 1-Address 2-Number");
//                    int choice = validate(input);
//                    switch (choice) {
//                        case 1:
//                            adress = updateadress(input);
//                            break;
//                        case 2:
//                            phoneno = updateno(input);
//                            break;
//                    }
//                    System.out.println("Address :" + adress);
//                    System.out.println("Number: " + phoneno);
//                    System.out.println("Do you want to change anything else 1-yes 2-no");
//                    ans = validate(input);
//                }
//                System.out.println("Order Placed Successfully");
//                customerBalance = customerBalance - customerorder.getTotalPrice();
//                updateStock();
//                return;
//            }
//
//        } catch (Exception e) {
//            System.out.println("An Unexpected Error Occured " + e.getMessage());
//        }
//
//    }
//
//    private void cod(Scanner input) {
//        try {
//            System.out.println("NAME: " + customername);
//            System.out.println("Phone: " + phoneno);
//            System.out.println("Address: " + adress);
//            System.out.println("Do you want to change anything? 1 - Yes, 2 - No");
//            int ans = validate(input);
//            while (ans == 1) {
//                System.out.println("what do you want to change : 1-address 2-number");
//                int choice = validate(input);
//                switch (choice) {
//                    case 1:
//                        adress = updateadress(input);
//                        break;
//                    case 2:
//                        phoneno = updateno(input);
//                        break;
//                }
//                System.out.println("address" + adress);
//                System.out.println("number" + phoneno);
//                System.out.println("do you want to change anything else 1-yes 2-no");
//                ans = validate(input);
//            }
//            System.out.println("order place successfully");
//            updateStock();
//            return;
//
//        } catch (Exception e) {
//            System.out.println("error ocurred " + e.getMessage());
//
//        }
//
//
//    }

//    private String updateadress(Scanner input) {
//        String newAddress = "";
//        while (true) {
//            System.out.println("Enter new address:");
//            input.nextLine(); // tefady el input stream
//            newAddress = input.nextLine();
//            System.out.println("Are you sure? 1 - Yes, 2 - No");
//            int confirm = validate(input);
//            if (confirm == 1) {
//                System.out.println("New address set: " + newAddress);
//                break;
//            }
//        }
//        return newAddress;
//    }


//    private String updateno(Scanner input) {
//        String newPhone = "";
//        while (true) {
//            System.out.println("Enter new phone number:");
//            newPhone = input.next();
//            System.out.println("Are you sure? 1 - Yes, 2 - No");
//            int confirm = validate(input);
//            if (confirm == 1) {
//                System.out.println("New phone number set: " + newPhone);
//                break;
//            }
//        }
//        return newPhone;
//    }


//
//    private int validate(Scanner input) {
//        int choice = 0;
//        boolean flag = false;
//        while (!flag) {
//            try {
//                choice = input.nextInt();
//                if (choice == 1 || choice == 2) {
//                    flag = true;
//                } else {
//
//                    System.out.println("invalid input enter again<3");
//
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("invalid input please enter a valid number (1 or 2)");
//                input.next();
//
//            }
//        }
//        return choice;
//    }

    public void updateStock() {
        for (Map.Entry<Product, Integer> entry : customerorder.getCartMap().entrySet()) {

            int item = entry.getKey().getStock();//stock of product
            int quantity = entry.getValue();
            entry.getKey().setStock(item - quantity);
        }
    }
//
//    int m = 0;
//
//    int retmethod() {
//        return m;
//    }

//    public void Checkout1() {
//        Scanner input = new Scanner(System.in);
//        try {
//            if (customerorder.getCartMap().isEmpty()) {
//                System.out.println("Cart is empty ");
//                System.out.println("Returning home...");
//                return;
//            }
//            for (Map.Entry<Product, Integer> entry : customerorder.getCartMap().entrySet()) {
//                System.out.println("Product: " + entry.getKey() + " quantity :" + entry.getValue());
//                double mult = entry.getValue();
//                double price = entry.getKey().getPrice();
//                totalprice += (mult * price);
//            }
//            System.out.println("Total price of order is :" + totalprice);
//            System.out.println("1-Place order 2-Return to home");
//            int order = validate(input);
//            switch (order) {
//                case 1:
//                    System.out.println("Select method : 1-COD 2-Balance");
//                    int method = validate(input);
//                    if (method == 1) {
//                        cod(input);
//                        m = 1;
//                    } else {
//                        balance(input);
//                        m = 2;
//
//                    }
//                    break;
//                case 2:
//                    System.out.println("Returning Home");
//                    return;
//            }
//
//        } catch (Exception e) {
//            System.out.println("An Unexpected Error Occured");
//        }
//    }

    //  void showorders() {
//        for (int i = 0; i < Database.orders.size(); i++) {
//            if (!Database.orders.isEmpty()) {
//                System.out.println("=================================");
//                System.out.println("Customer Name   : " + this.customername);
//                System.out.println("Phone Number    : " + this.phoneno);
//                System.out.println("Address         : " + this.adress);
//                System.out.println("Order Date      : " + this.orderDate);
//                System.out.println("Total Price     : $" + this.totalprice);
//                System.out.println("Order Details:");
//                System.out.println("---------------------------------");
//                for (Map.Entry<Product, Integer> entry : this.getViewcart().entrySet()) {
//                    String item = entry.getKey().getName();
//                    Integer quantity = entry.getValue();
//                    System.out.println("Product: " + item + ", Quantity: " + quantity);
//                }
//            } else {
//                System.out.println("no orders avalibale");
//            }
//
//        }
//
//    }
}