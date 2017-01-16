package de.fhdw.bfwi115a.caltracker.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by BFWI115Ako on 09.01.2017.
 */

public class FragmentCalenderDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private TextView tvDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        tvDate = (TextView) getActivity().findViewById(R.id.textview_selectedDate);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.GERMAN);
        String formattedDate = sdf.format(c.getTime());
        Toast.makeText(getActivity(),tvDate.getText(), Toast.LENGTH_SHORT).show();
        tvDate.setText(formattedDate);
        tvDate.postInvalidate();
        Toast.makeText(getActivity(), tvDate.getText(), Toast.LENGTH_SHORT).show();
    }
}