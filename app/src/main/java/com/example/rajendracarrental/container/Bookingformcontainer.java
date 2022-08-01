package com.example.rajendracarrental.container;

public class Bookingformcontainer {
    public String category,model,price,startfrom,destination,email,phone,msg;

    public Bookingformcontainer(String category, String model, String price, String startfrom, String destination, String email, String phone, String msg) {
        this.category = category;
        this.model = model;
        this.price = price;
        this.startfrom = startfrom;
        this.destination = destination;
        this.email = email;
        this.phone = phone;
        this.msg = msg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartfrom() {
        return startfrom;
    }

    public void setStartfrom(String startfrom) {
        this.startfrom = startfrom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
