package com.example.kasparas.choseit;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
 //   private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantList.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantList newInstance(String param1, String param2) {
        RestaurantList fragment = new RestaurantList();
        Bundle args = new Bundle();
 //       args.putString(ARG_PARAM1, param1);
 //       args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RestaurantList() {
        // Required empty public constructor
    }

  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
  //          mParam1 = getArguments().getString(ARG_PARAM1);
  //          mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    ListView list;
    RestaurantListAdapter adapter;
    public  RestaurantList restaurantList = null;
    public  ArrayList<RestaurantListModel> RestaurantListItems = new ArrayList<RestaurantListModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_resaurant, container, false);

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res = getResources();
        list = ( ListView )view.findViewById(R.id.lv_restaurantList);  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new RestaurantListAdapter( getActivity(), this, RestaurantListItems, res );
        list.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    public void setListData()
    {

        for (int i = 0; i < 8; i++) {

            final RestaurantListModel item = new RestaurantListModel();

            item.setRestaurantName("Company "+i);
            item.setImage("image"+i);
            item.setDescription("Description " + i);

            RestaurantListItems.add( item );
        }

    }

    private MealsList mealsListFragment;
    /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {


        if (mealsListFragment == null) {
            mealsListFragment = new MealsList();
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace (R.id.main, new MealsList()).commit();
//        RestaurantListModel tempValues = RestaurantListItems.get(mPosition);
//
//        Toast.makeText(getActivity(),
//                "" + tempValues.getRestaurantName() + "\n"
//                        + "Image: "+tempValues.getImage() + "\n"
//            +"Description: "+tempValues.getDescription(),
//        Toast.LENGTH_LONG)
//        .show();

    }


    public String[] getRestaurantList () {
        return new String[] {"Kinieciai", "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas"
                , "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas"
                , "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas"
                , "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas"
                , "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas"
                , "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas", "Ciliakas", "Montuotojas"};
    }
    public void showRestaurantList (View view, String[] restaurantList) {
        final ListView listView = (ListView) view.findViewById(R.id.lv_restaurantList);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < restaurantList.length; ++i) {
            list.add(restaurantList[i]);
        }
        final OSArrayAdapter adapter = new OSArrayAdapter(this.getActivity(),
                R.layout.restaurant_list_item, R.id.tv_restaurantListItem_title, list);
        listView.setAdapter(adapter);

    }

    public class OSArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        public OSArrayAdapter(Context context, int rowId, int textViewResourceId,
                              List<String> objects) {
            super(context, rowId, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
