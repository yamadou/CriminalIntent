package com.android.bignerdranc.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;


/**
 * Created by YamadouTraore on 7/7/15.
 */
public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME = "com.android.bignerdranc.criminalintent.time";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        TimePicker timePicker = new TimePicker(getActivity());

        return new AlertDialog.Builder(getActivity())
                .setView(timePicker)
                .setTitle(R.string.time_date_picker)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }


    /**
     * Created by YamadouTraore on 7/31/15.
     */
    public static class CriminalIntentJSONSerializer {

        private Context mContext;
        private String mFileName;

        public CriminalIntentJSONSerializer(Context c, String f) {
            mContext = c;
            mFileName = f;
        }

        public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException {

            //Build an array in JSON
            JSONArray array = new JSONArray();
            for(Crime c: crimes)
                array.put(c.toJSON());

            //Write the file to disk
            Writer writer = null;
            try {
                OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
                writer = new OutputStreamWriter(out);
                writer.write(array.toString());
            }finally {
                if(writer != null)
                    writer.close();
            }

        }
    }
}
