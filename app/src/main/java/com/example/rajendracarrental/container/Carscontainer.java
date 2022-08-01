package com.example.rajendracarrental.container;

public class Carscontainer {
    public boolean availability;
    public String category,manufacturer,model,vehicleimageurl,mileage,price,seats,vehicleid,year;
    public double lat,lng;

    public Carscontainer(boolean availability, String category, String manufacturer, String model, String vehicleimageurl, double lat, double lng, String mileage, String price, String seats, String vehicleid, String year) {
        this.availability = availability;
        this.category = category;
        this.manufacturer = manufacturer;
        this.model = model;
        this.vehicleimageurl = vehicleimageurl;
        this.lat = lat;
        this.lng = lng;
        this.mileage = mileage;
        this.price = price;
        this.seats = seats;
        this.vehicleid = vehicleid;
        this.year = year;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleimageurl() {
        return vehicleimageurl;
    }

    public void setVehicleimageurl(String vehicleimageurl) {
        this.vehicleimageurl = vehicleimageurl;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
