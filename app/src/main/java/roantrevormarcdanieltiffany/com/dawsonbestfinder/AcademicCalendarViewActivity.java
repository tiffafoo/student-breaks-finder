package roantrevormarcdanieltiffany.com.dawsonbestfinder;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by mrtvor on 2017-11-24.
 */

public class AcademicCalendarViewActivity extends Activity {
    private final static String TAG = "FRAG-CALENDARACTIVITY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

        if(savedInstanceState == null) {
            AcademicCalendarFragment calendar = new AcademicCalendarFragment();
            calendar.setArguments(getIntent().getExtras());

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(android.R.id.content, calendar);
            ft.commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    
}
