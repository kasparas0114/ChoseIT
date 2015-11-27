package com.example.kasparas.choseit;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.maps.model.LatLng;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements SelectOptions.OnFragmentInteractionListener,
        RestaurantList.OnFragmentInteractionListener, RandomRestaurantFragment.Communicator,
        MealsList.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {

    //Getters and Setters

    private String seekBarProgress;
    private String spinnerPriceFrom;
    private String spinnerPriceTo;

    public void SetSeekBarProgress(String seekBarProgress) {
        this.seekBarProgress = seekBarProgress;
    }

    public String GetSeekBarBrogress() {
        return this.seekBarProgress;
    }

    public void SetSpinnerPriceFrom(String spinnerPriceFrom) {
        this.spinnerPriceFrom = spinnerPriceFrom;
    }

    public String GetSpinnerPriceFrom() {
        return this.spinnerPriceFrom;
    }

    public void SetSpinnerPriceTo(String spinnerPriceTo) {
        this.spinnerPriceTo = spinnerPriceTo;
    }

    public String GetSpinnerPriceTo() {
        return this.spinnerPriceTo;
    }


    String TAG = "MainActivity";
    private static int SPLASH_TIME_OUT = 1000;

    /*
    * Fragments
    * */
    /******************************************************************************************/
    private RestaurantList restaurantListFragment;
    private RandomRestaurantFragment randomRestaurantFragment;
    private MealsList mealsListFragment;

    /******************************************************************************************/


    //this gets called by SelectOptionsFragment to get values
    @Override
    public void saveOptionsValues(String seekBarValue, String spinnerFromValue, String spinnerToValue) {

        SetSeekBarProgress(seekBarValue);
        SetSpinnerPriceFrom(spinnerFromValue);
        SetSpinnerPriceTo(spinnerToValue);

        //  RestaurantList restaurantFragment = (RestaurantList) getSupportFragmentManager().findFragmentById(R.id.selectRestaurantId);
        //  restaurantFragment.setValues(seekBarValue,spinnerFromValue,spinnerToValue);
    }

    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/
    public LatLng getLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Location bestLocation = null;
        for (String provider : locationManager.getProviders(true)) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }

        return new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
    }

    /*
    * Events
    * */

    /******************************************************************************************/
    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.w(TAG, "onFragmentInteraction");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);
                changeFragment(R.id.main, new SelectOptions());
            }
        }, SPLASH_TIME_OUT);
        Log.w(TAG, "onCreate");
    }

    public void button_select_options_next_click(View view) {
        if (restaurantListFragment == null) {
            restaurantListFragment = new RestaurantList();
        }

        changeFragment(R.id.main, restaurantListFragment);
        Log.w(TAG, "button_select_options_next_click");
    }

    //    public void button_select_options_next_click (View view) {
//        if (restaurantListFragment == null) {
//            restaurantListFragment = new RestaurantList();
//        }
//
//        changeFragment(R.id.main, restaurantListFragment);
//        Log.w(TAG, "button_select_options_next_click");
//    }
    public void button_random_restaurant(View view) {

        FragmentManager manager = getSupportFragmentManager();
        RandomRestaurantFragment random = new RandomRestaurantFragment();
        random.getShowsDialog();
        random.show(manager, "MyDialog");
        Log.w(TAG, "button_random_restaurant");
    }

    public void item_clicked_restaurant(View view) {

        if (mealsListFragment == null) {
            mealsListFragment = new MealsList();
        }
        changeFragment(R.id.main, new MealsList());
        Log.w(TAG, "item_clicked_restaurant");
    }

    @Override
    public void onDialogMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.w(TAG, "onDialogMessage");
    }
    /******************************************************************************************/

    /*
    * Methods
    * */

    /******************************************************************************************/
    public void changeFragment(int containerId, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(containerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
        Log.w(TAG, "changeFragment");
    }

    public String readJSONStringFromFile() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.restaurant_data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private List<Restaurant> restaurantList;

    public List<Restaurant> getRestaurantList() {
        if (restaurantList == null) {
            restaurantList = readRestaurantListData();
        }
        return restaurantList;
    }

    public List<Restaurant> getRestaurantList(int distance) {
        List <Restaurant> allRestaurants = getRestaurantList();
        List <Restaurant> resultRestaurants = new ArrayList<>();
        LatLng currentLoc = getLocation();

        for (int i = 0; i < allRestaurants.size(); i++) {
            Restaurant restaurant = allRestaurants.get(i);
            Location restaurantLocation = new Location("RestaurantLocation");
            restaurantLocation.setLatitude(restaurant.getLattitude());
            restaurantLocation.setLongitude(restaurant.getLongtitude());
            Location currentLocation = new Location("Current");
            currentLocation.setLatitude(currentLoc.latitude);
            currentLocation.setLongitude(currentLoc.longitude);

            if (restaurantLocation.distanceTo(currentLocation) <= distance) {
                resultRestaurants.add(restaurant);
            }
        }
        return resultRestaurants;
    }

    public List <Meal> getRestaurantMeals (Restaurant restaurant, double minPrice, double maxPrice) {
        List <Meal> allMeals = restaurant.getMealList();
        List <Meal> resultMeals = new ArrayList<>();

        for (int i=0; i<allMeals.size(); i++) {
            double price = allMeals.get(i).getPrice();
            if (price >= minPrice && price <= maxPrice) {
                resultMeals.add(allMeals.get(i));
            }
        }

        return resultMeals;
    }

    public List<Restaurant> readRestaurantListData() {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();

        JSONArray restaurants = readRestaurantDataFromFile();
        try {
            for (int i = 0; i < restaurants.length(); i++) {
                JSONObject obj = (JSONObject) restaurants.get(i);
                Restaurant restaurant = new Restaurant();
                restaurant.setAddress(obj.getString("address"));
                restaurant.setRestName(obj.getString("name"));
                restaurant.setLattitude(obj.getDouble("Latitude"));
                restaurant.setLongtitude(obj.getDouble("Longtitude"));
                restaurant.setPhoneNumber(obj.getString("phoneNumber"));
                restaurant.setMealList(getMealListFromRestaurantJSONObject(obj));
                restaurantList.add(restaurant);
            }
        } catch (Exception ex) {

        }
        return restaurantList;
    }

    public List<Meal> getMealListFromRestaurantJSONObject(JSONObject restaurant) {
        List<Meal> mealList = new ArrayList<Meal>();

        try {
            JSONArray mealJSONArray = restaurant.getJSONArray("Meals");
            for (int i = 0; i < mealJSONArray.length(); i++) {
                JSONObject obj = (JSONObject) mealJSONArray.get(i);

                Meal meal = new Meal();
                meal.setIngredients(obj.getString("Ingredients"));
                meal.setMealName(obj.getString("Name"));
                meal.setPrice(obj.getDouble("Price"));
                mealList.add(meal);
            }
        } catch (Exception ex) {
        }

        return mealList;
    }

    public JSONArray readRestaurantDataFromFile() {
        JSONArray result = null;
        try {
            JSONObject obj = new JSONObject(readJSONStringFromFile());
            result = obj.getJSONArray("Restaurants");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public LatLng getRestaurantLocation (Restaurant restaurant) {
        return new LatLng(restaurant.getLattitude(), restaurant.getLongtitude());
    }

    /******************************************************************************************/
}
