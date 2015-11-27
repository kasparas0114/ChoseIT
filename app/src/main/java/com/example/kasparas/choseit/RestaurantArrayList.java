package com.example.kasparas.choseit;

import java.util.ArrayList;

/**
 * Created by kaspa on 11/27/2015.
 */
public class RestaurantArrayList {
    private ArrayList<Restaurant> restaurantArrayList;

    public Restaurant getRestaurantArrayList(int id) {
        return restaurantArrayList.get(id);
    }

    public void setRestaurantArrayList(Restaurant restaurant) {
        if (this.restaurantArrayList == null)
            this.restaurantArrayList = new ArrayList<Restaurant>();
        this.restaurantArrayList.add(restaurant);
    }
}
