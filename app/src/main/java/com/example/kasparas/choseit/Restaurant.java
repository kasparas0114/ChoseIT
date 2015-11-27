package com.example.kasparas.choseit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaspa on 11/27/2015.
 */
public class Restaurant {

    private String restName;
    private String adress;
    private double lattitude;
    private double longtitude;
    private List<Meal> mealList;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Meal getMeal(int id) {
        return mealList.get(id);
    }

    public void setMealList(List<Meal> mealList) {
        if (mealList == null) {
            this.mealList = new ArrayList<Meal>();
        } else {
            this.mealList = mealList;
        }


    }

    public void addMeal (Meal meal) {
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

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }
}
