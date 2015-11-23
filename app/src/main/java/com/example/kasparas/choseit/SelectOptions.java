package com.example.kasparas.choseit;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.SeekBar;
import android.graphics.Color;
import android.widget.Spinner;

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
    Spinner spinnerFrom;
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

    public void spinners (View view)
    {
        double previous_price_from = 3.50;
        double previous_price_to = 5.0;
        Spinner spinnerFrom = (Spinner) view.findViewById(R.id.sp_pr_from);
        Spinner spinnerTo = (Spinner) view.findViewById(R.id.sp_pr_to);
    }

    public void seekbar(View view)
    {
        int previous_progress = 7;
        seek_bar = (SeekBar) view.findViewById(R.id.sb_dist);
        seek_bar.setMax (30);
        seek_bar.setDrawingCacheBackgroundColor(Color.CYAN);
        seek_bar.setProgress(previous_progress);
        text_view = (TextView) view.findViewById(R.id.textView3);
        text_view.setText("Covered : " + seek_bar.getProgress() + "/" + seek_bar.getMax());
        final int progress_value;
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;

                    @Override

                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        text_view.setText("Covered : " + progress + "/" + seek_bar.getMax());

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        text_view.setText("CoveredB : " + progress_value + "/" + seek_bar.getMax());
                    }
                }
        );
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_options, container, false);
        seekbar (view);
        return view;

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
