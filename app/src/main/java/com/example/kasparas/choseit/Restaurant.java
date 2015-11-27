package com.example.kasparas.choseit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaspa on 11/27/2015.
 */
public class Restaurant {

    private String restName;
    private String adress;
    private float lattitude;
    private float longtitude;
    private ArrayList<Meal> mealList;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Meal getMeal(int id) {
        return mealList.get(id);
    }

    public void setMealList(Meal meal) {
        if (mealList == null) {
            this.mealList = new ArrayList<Meal>();
        }
        this.mealList.add(meal);

    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }
}
