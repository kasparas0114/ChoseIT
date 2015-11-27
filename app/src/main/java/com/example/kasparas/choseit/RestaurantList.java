package com.example.kasparas.choseit;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantList extends Fragment {

    private OnFragmentInteractionListener mListener;

    public static RestaurantList newInstance(String param1, String param2) {
        RestaurantList fragment = new RestaurantList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RestaurantList() {
        // Required empty public constructor
    }

    MainActivity activity;

    ListView list;
    RestaurantListAdapter adapter;
    public RestaurantList restaurantList = null;
   // public ArrayList<RestaurantListModel> RestaurantListItems = new ArrayList<RestaurantListModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_resaurant, container, false);

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/

        Resources res = getResources();
        list = (ListView) view.findViewById(R.id.lv_restaurantList);  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter = new RestaurantListAdapter(activity, this, (ArrayList)activity.getRestaurantList(Integer.valueOf(activity.GetSeekBarBrogress())*1000), res);
        list.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    private MealsList mealsListFragment;

    /*****************
     * This function used by adapter
     ****************/
    public void onItemClick(int mPosition) {
        if (mealsListFragment == null) {
            mealsListFragment = new MealsList();
        }

        mealsListFragment.restaurant = activity.getRestaurantList().get(mPosition);
        ((MainActivity) getActivity()).changeFragment(R.id.main, mealsListFragment);
    }

  /*  public void showRestaurantList(View view, String[] restaurantList) {
        final ListView listView = (ListView) view.findViewById(R.id.lv_restaurantList);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < restaurantList.length; ++i) {
            list.add(restaurantList[i]);
        }
        final OSArrayAdapter adapter = new OSArrayAdapter(this.getActivity(),
                R.layout.restaurant_list_item, R.id.tv_restaurantListItem_title, list);
        listView.setAdapter(adapter);
    }*/

  /*  public class OSArrayAdapter extends ArrayAdapter<String> {
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
*/
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
