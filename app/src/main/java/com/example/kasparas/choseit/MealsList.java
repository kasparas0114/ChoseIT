package com.example.kasparas.choseit;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MealsList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MealsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealsList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public Restaurant restaurant;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealsList.
     */
    // TODO: Rename and change types and number of parameters
    public static MealsList newInstance(String param1, String param2) {
        MealsList fragment = new MealsList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MealsList() {
        // Required empty public constructor
    }

    private MainActivity activity;
    ListView list;
    MealsListAdaptor adapter;
    public MealsList mealsList = null;
    public ArrayList<Meal> MealsListItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity)getActivity();
    }


    private MapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_meal, container, false);

        //setListData();
        Resources res = getResources();
        list = (ListView) view.findViewById(R.id.lv_mealsList);
        ((TextView)view.findViewById(R.id.tv_restaurant_name)).setText(restaurant.getRestName());
        ((TextView)view.findViewById(R.id.tv_adress)).setText(restaurant.getAddress());
        ((TextView)view.findViewById(R.id.tv_phone_nr)).setText(restaurant.getPhoneNumber());

        List<Meal> mealList = activity.getRestaurantMeals(restaurant, Double.valueOf(activity.GetSpinnerPriceFrom()),Double.valueOf( activity.GetSpinnerPriceTo()));
        adapter = new MealsListAdaptor(activity, this, (ArrayList)mealList, res);
        list.setAdapter(adapter);

        Button mapButton = (Button) view.findViewById(R.id.btn_map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragment mf = new MapFragment();
                mf.restaurant = restaurant;
                ((MainActivity) getActivity()).changeFragment(R.id.main, mf);
            }
        });


        return view;
    }

    /*public void setListData() {

        for (int i = 0; i < 8; i++) {

            final Meal item = new Meal();

            item.setMealName("Maistas" + i);
            item.setPrice(i + "€");

            MealsListItems.add(item);
        }

    }*/

    public void onItemClick(int mPosition) {
        Meal tempValues = MealsListItems.get(mPosition);

        Toast.makeText(getActivity(),
                "" + tempValues.getMealName() + "\n"
                        + "Price: " + tempValues.getPrice(),
                Toast.LENGTH_LONG)
                .show();
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
