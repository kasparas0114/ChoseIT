package com.example.kasparas.choseit;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kaspa on 11/23/2015.
 */
public class RandomRestaurantFragment extends DialogFragment implements View.OnClickListener{

    Button yes,no;

    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_random_restaurant, null);
        yes = (Button) view.findViewById(R.id.yes);
        no = (Button) view.findViewById(R.id.no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==R.id.yes)
        {
            communicator.onDialogMessage("Yes was clicked");
            dismiss();
        }
        else
        {
            communicator.onDialogMessage("No was clicked");
            dismiss();
        }
    }

    public interface Communicator
    {
        public void onDialogMessage (String message);
    }
}
