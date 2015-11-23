package com.example.kasparas.choseit;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectOptions.OnFragmentInteractionListener,
                                                            RestaurantList.OnFragmentInteractionListener {

    private static int SPLASH_TIME_OUT = 1000;
    private static SeekBar seek_bar;
    private static TextView text_view;

    @Override
    public void onFragmentInteraction(Uri uri) {

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
    *
    * Events
    *
    * */
    /******************************************************************************************/
    public void button_select_options_next_click (View view) {
        changeFragment(R.id.main, new RestaurantList());
    }

    /******************************************************************************************/

    public void changeFragment (int containerId, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(containerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
