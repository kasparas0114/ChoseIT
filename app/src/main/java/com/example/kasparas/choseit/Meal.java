package com.example.kasparas.choseit;

/**
 * Created by kaspa on 11/27/2015.
 */
public class Meal {
    private String mealName;
    private double Price;
    private String Ingredients;

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
