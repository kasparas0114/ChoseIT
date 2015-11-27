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
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SelectOptions.OnFragmentInteractionListener,
        RestaurantList.OnFragmentInteractionListener, RandomRestaurantFragment.Communicator,
        MealsList.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener {

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
    /******************************************************************************************/
}
