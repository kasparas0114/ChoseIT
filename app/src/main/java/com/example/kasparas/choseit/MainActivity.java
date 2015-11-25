package com.example.kasparas.choseit;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.util.Log;

//public class MainActivity extends AppCompatActivity implements SelectOptions.OnFragmentInteractionListener,
//                                                            RestaurantList.OnFragmentInteractionListener {

public class MainActivity extends AppCompatActivity implements SelectOptions.OnFragmentInteractionListener,
        RestaurantList.OnFragmentInteractionListener,RandomRestaurantFragment.Communicator,
        MealsList.OnFragmentInteractionListener, MapFragment.OnFragmentInteractionListener,
        OnMapReadyCallback{

    String TAG = "MainActivity";
    private static int SPLASH_TIME_OUT = 1000;
    private static SeekBar seek_bar;
    private static TextView text_view;
    private GoogleMap mMap;

    /*
    * Fragments
    * */
    /******************************************************************************************/
    private RestaurantList restaurantListFragment;
    private RandomRestaurantFragment randomRestaurantFragment;
    private MealsList mealsListFragment;
    /******************************************************************************************/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void initPriceSpinners (View view)
    {
        double previous_price_from = 3.50;
        double previous_price_to = 5.0;
        Spinner spinnerFrom = (Spinner) view.findViewById(R.id.sp_pr_from);
        Spinner spinnerTo = (Spinner) view.findViewById(R.id.sp_pr_to);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("item1");
        spinnerArray.add("item2");

//        List<Double>spinnerArray =  new ArrayList<Double>();
//        for (double x = 0;x<=10;x+=0.5)
//        spinnerArray.add(x);
        //spinnerArray.add("item2");

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spinner sItems = (Spinner) view.findViewById(R.id.spinner1);
//        sItems.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    /*
    * Events
    * */
    /******************************************************************************************/
    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.w(TAG,"onFragmentInteraction");
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
        Log.w(TAG,"onCreate");
    }

    public void button_select_options_next_click (View view) {
        if (restaurantListFragment == null) {
            restaurantListFragment = new RestaurantList();
        }

        changeFragment(R.id.main, restaurantListFragment);
        Log.w(TAG, "button_select_options_next_click");
    }
    public void button_random_restaurant (View view) {

        FragmentManager manager = getSupportFragmentManager();
        RandomRestaurantFragment random = new RandomRestaurantFragment();
        random.getShowsDialog();
        random.show(manager, "MyDialog");
        Log.w(TAG, "button_random_restaurant");
    }

    public void item_clicked_restaurant (View view) {

        if (mealsListFragment == null) {
            mealsListFragment = new MealsList();
        }
        changeFragment(R.id.main, new MealsList());
        Log.w(TAG,"item_clicked_restaurant");

    }

    @Override
    public void onDialogMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Log.w(TAG, "onDialogMessage");
    }
    /******************************************************************************************/

    /*
    * Methods
    * */
    /******************************************************************************************/
    public void changeFragment (int containerId, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(containerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
        Log.w(TAG, "changeFragment");
    }

    /*public void showMapFragment (int containerId, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(containerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
        Log.w(TAG, "changeFragment");
    }*/
    /******************************************************************************************/
}
