package com.jdkgroup.model;

import java.util.List;

/**
 * Created by LENOVO on 2/8/2017.
 */

public class GSONCategory
{
    private int status;
    private String message;

    private List<Category> category;
    private List<PlacesService> placesservice;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<PlacesService> getPlacesservice() {
        return placesservice;
    }

    public void setPlacesservice(List<PlacesService> placesservice) {
        this.placesservice = placesservice;
    }
}
