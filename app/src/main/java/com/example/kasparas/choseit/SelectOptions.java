package com.example.kasparas.choseit;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;
import android.graphics.Color;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectOptions.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectOptions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectOptions extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static SeekBar seek_bar;
    private static TextView text_view;
    private static Spinner spinnerFrom;
    private static Spinner spinnerTo;
    ArrayAdapter<CharSequence> adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectOptions.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectOptions newInstance(String param1, String param2) {
        SelectOptions fragment = new SelectOptions();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectOptions() {
        // Required empty public constructor
    }

    public void initPriceSpinners (View view)
    {
        Double[] spinnerArr = new Double[20];
        int iter = 0;
        for (double x = 0;x<=10;x+=0.5)
        {
            if (iter>=20) break;
            spinnerArr[iter] = x;
            iter++;

        }

        ArrayAdapter<Double> spinnerAdapter = new ArrayAdapter<Double>(this.getActivity(),android.R.layout.simple_spinner_item,spinnerArr);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom = (Spinner) view.findViewById(R.id.sp_pr_from);
        spinnerTo = (Spinner) view.findViewById(R.id.sp_pr_to);
        spinnerFrom.setAdapter(spinnerAdapter);
        spinnerTo.setAdapter(spinnerAdapter);
    }

    public void initSeekbar(View view)
    {
        int previous_progress = 7;
        seek_bar = (SeekBar) view.findViewById(R.id.sb_dist);
        seek_bar.setMax (30);
        seek_bar.setDrawingCacheBackgroundColor(Color.CYAN);
        seek_bar.setProgress(previous_progress);
        text_view = (TextView) view.findViewById(R.id.tv_prog);
        text_view.setText("Pasirinkta : " + seek_bar.getProgress() + " kilometrų");
        final int progress_value;
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;

                    @Override

                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText("Pasirinkta : " + progress + " kilometrų");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        text_view.setText("Pasirinkta : " + progress_value + " kilometrų");
                    }
                }
        );
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_options, container, false);
        initSeekbar(view);
        initPriceSpinners(view);
        activity = (MainActivity)getActivity();
        Button mapButton = (Button) view.findViewById(R.id.btn_select_options_next);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((MainActivity)getActivity()).getLocation();
                    Double valueFrom = Double.valueOf(spinnerFrom.getSelectedItem().toString());
                    Double valueTo = Double.valueOf(spinnerTo.getSelectedItem().toString());

                    if (valueFrom > valueTo)
                        throw new Exception ("Incorrect choice");

                    mListener.saveOptionsValues(Integer.toString(seek_bar.getProgress()),spinnerFrom.getSelectedItem().toString(),spinnerTo.getSelectedItem().toString());
                    RestaurantList mf = new RestaurantList();
                    ((MainActivity) getActivity()).changeFragment(R.id.main, mf);
                } catch (NullPointerException ex) {
                    Context context = getActivity();
                    CharSequence text = "Please enable GPS";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } catch (Exception ex) {
                    Context context = getActivity();
                    CharSequence text = ex.getMessage();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

        return view;
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
        void saveOptionsValues (String seekBarValue, String spinnerFromValue, String spinnerToValue);
        public void onFragmentInteraction(Uri uri);
    }

}
