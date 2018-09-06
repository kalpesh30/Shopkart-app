package com.example.kalpesh.shopkart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Phone withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Phone withModel(String model) {
        this.model = model;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Phone withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Phone withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Phone withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Phone withImage(String image) {
        this.image = image;
        return this;
    }

}
