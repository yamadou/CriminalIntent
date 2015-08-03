package com.android.bignerdranc.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import java.util.Date;




/**
 * Created by YamadouTraore on 7/7/15.
 */
public class MenuPickerFragment extends DialogFragment{

    private Crime mCrime;
    private Date mDate;
    private Intent data;
    private static final int FIRST_POS = 0;
    private static final String DIALOG_TIME = "time";
    private static final String DIALOG_DATE = "date";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

       mDate = (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.time_date_picker)
                .setItems(R.array.menu_array, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FragmentManager fm = getActivity ().getSupportFragmentManager();

                        if (which == FIRST_POS) {
                            DatePickerFragment datepicker = DatePickerFragment.newInstance(mDate);
                            datepicker.setTargetFragment(getTargetFragment(), CrimeFragment.getRequestDate());
                            datepicker.show(fm, DIALOG_DATE);

                        } else {
                            TimePickerFragment timepicker = new TimePickerFragment();
                            timepicker.show(fm, DIALOG_TIME);
                        }

                    }
                })
                .create();
    }

    public static MenuPickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(DatePickerFragment.EXTRA_DATE, date);

        MenuPickerFragment fragment = new MenuPickerFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
