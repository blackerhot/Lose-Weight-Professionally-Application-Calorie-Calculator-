package asayaporn.pichet.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    String ret;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SharedPreferences sp ;
        SharedPreferences.Editor editor ;
         ret=String.valueOf(year+month+day);
        sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
       editor.putString("dating", ret);

        editor.commit();
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

@Override
    public void onDateSet(DatePicker view, int year, int month, int day) {


    ret = String.valueOf(view.getYear()+view.getMonth()+
            +view.getDayOfMonth());
    SharedPreferences sp ;
    SharedPreferences.Editor editor ;
    sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
    editor = sp.edit();
    editor.putString("dating", ret);
    editor.putInt("D",day);
    editor.putInt("M",month+1);
    editor.putInt("Y",year);

    editor.commit();

    Fragment newFragment = ItemStatistic.newInstance();
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    ft.replace(R.id.main_container, newFragment).commit();
    }
}