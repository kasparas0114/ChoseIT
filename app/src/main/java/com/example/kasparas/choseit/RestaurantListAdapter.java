package com.example.kasparas.choseit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Edvinas.Barickis on 11/24/2015.
 */
public class RestaurantListAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private Fragment fragment;
    private static LayoutInflater inflater=null;
    public Resources res;
    RestaurantListModel tempValues=null;
    int i=0;

    /*************  RestaurantListAdapter Constructor *****************/
    public RestaurantListAdapter(Activity a, Fragment f, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;
        fragment = f;
        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView tv_name;
        public TextView tv_description;
        public ImageView image;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.restaurant_list_item, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.tv_name = (TextView) vi.findViewById(R.id.tv_restaurantListItem_title);
            holder.tv_description=(TextView)vi.findViewById(R.id.tv_restaurantListItem_description);
            holder.image=(ImageView)vi.findViewById(R.id.iv_restaurantListItem_image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.tv_name.setText("No Data");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( RestaurantListModel ) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.tv_name.setText( tempValues.getRestaurantName() );
            holder.tv_description.setText( tempValues.getDescription() );
            /*holder.image.setImageResource(
                    res.getIdentifier(
                            "com.example.kasparas.choseit:drawable/"+tempValues.getImage()
                            ,null,null));*/

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new OnItemClickListener( position ));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("RestaurantListAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            ((RestaurantList)fragment).onItemClick(mPosition);;
        }
    }
}
