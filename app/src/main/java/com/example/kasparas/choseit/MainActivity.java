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
import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity implements SelectOptions.OnFragmentInteractionListener,
        RestaurantList.OnFragmentInteractionListener, RandomRestaurantFragment.Communicator,
        MealsList.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {

    //Getters and Setters

    private String seekBarProgress;
    private String spinnerPriceFrom;
    private String spinnerPriceTo;

    public void SetSeekBarProgress (String seekBarProgress)
    {
        this.seekBarProgress = seekBarProgress;
    }
    public String GetSeekBarBrogress ()
    {
        return this.seekBarProgress;
    }

    public void SetSpinnerPriceFrom (String spinnerPriceFrom)
    {
        this.spinnerPriceFrom = spinnerPriceFrom;
    }
    public String GetSpinnerPriceFrom ()
    {
        return this.spinnerPriceFrom;
    }
    public void SetSpinnerPriceTo (String spinnerPriceTo)
    {
        this.spinnerPriceTo = spinnerPriceTo;
    }
    public String GetSpinnerPriceTo ()
    {
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


//        AssetManager assetManager = getAssets();
//        FileInputStream inputStream = null;
//        try{
//            inputStream = assetManager.open("restaurant_data.xml");
//        }
//        catch (IOException e) {
//            Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
//        }

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
    public void button_random_restaurant (View view) {
        
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
    /******************************************************************************************/
}
