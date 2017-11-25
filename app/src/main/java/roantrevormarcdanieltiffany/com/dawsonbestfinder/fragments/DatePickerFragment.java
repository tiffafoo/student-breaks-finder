package roantrevormarcdanieltiffany.com.dawsonbestfinder.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

import roantrevormarcdanieltiffany.com.dawsonbestfinder.CalendarActivity;
import roantrevormarcdanieltiffany.com.dawsonbestfinder.ChooseTeacherActivity;

/**
 * Created by sirMerr on 2017-11-24.
 */

public class DatePickerFragment extends DialogFragment {
    private static final String TAG = ChooseTeacherActivity.class.getSimpleName();

    private CalendarActivity calendarActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "Called onCreateDialog");
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (CalendarActivity) getActivity(), year, month, day);
    }

}