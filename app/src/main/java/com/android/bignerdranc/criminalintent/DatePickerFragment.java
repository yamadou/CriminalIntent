package com.android.bignerdranc.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by YamadouTraore on 7/6/15.
 */
public class DatePickerFragment extends DialogFragment {

    private Date mDate;
    public static final String EXTRA_DATE = "com.android.bignerdranc.criminalintent.date";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // using a layout make modification easy if you change your mind about what the dialog
        //should present
       // View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        mDate =(Date)getArguments().getSerializable(EXTRA_DATE);

        // Create a calendar to get the year, month, and day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);



        DatePicker datePicker = new DatePicker(getActivity());
        datePicker.init(year, month, day, new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();

                //update argument to preserve selected value on rotation
                getArguments().putSerializable(EXTRA_DATE, mDate);

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(datePicker)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();

    }


    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }


    private void sendResult(int resultCode) {

        if(getTargetFragment() == null)
            return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    public static String getExtraDate() {
        return EXTRA_DATE;
    }
}
