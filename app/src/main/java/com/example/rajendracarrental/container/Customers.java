package com.example.rajendracarrental.container;

public class Customers {
    public String name;
    public String username;
    public String phone;
    public String address;

    // Constructor
    public Customers(String name, String username, String phone, String address) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
    }

    // getter setter method
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
