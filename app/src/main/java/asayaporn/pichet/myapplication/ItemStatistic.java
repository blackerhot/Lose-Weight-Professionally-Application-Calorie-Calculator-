package asayaporn.pichet.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by IMSICK on 5/10/2017.
 */

public class ItemStatistic extends Fragment  {
    public static Fragment newInstance() {
        ItemStatistic is=new ItemStatistic();
        return is;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_date) {
            showDatePickerDialog(getView());
            return true;
        }
        if (id == R.id.action_edit) {
            Intent intent = new Intent(getActivity(),showinfoAct.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    double allcallv2=0;
    double allcall=0;
    String lister="";
    String listerv2="";
    String ret = "";
    String retv2 = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.statistic_layout, container, false);
        setHasOptionsMenu(true);
        TextView tv1 = (TextView)view.findViewById(R.id.textdate);




        SharedPreferences sp1 ;
        SharedPreferences.Editor editor1 ;
        sp1 = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor1 = sp1.edit();


        tv1.setText(String.valueOf("ข้อมูลประจำวันที่ : "+sp1.getInt("D", Integer.parseInt("-1"))+
                " / "+sp1.getInt("M", Integer.parseInt("-1"))+" / "+sp1.getInt("Y", Integer.parseInt("-1"))));
        editor1.commit();
        try {
            SharedPreferences sp ;
            SharedPreferences.Editor editor ;

            sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
            editor = sp.edit();



            InputStream inputStream = getContext().openFileInput(sp.getString("dating","")+"ac.txt");
            editor.commit();
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {

                    stringBuilder.append(receiveString);

                    Log.d("", "result = " + receiveString);
                }

                inputStream.close();

                retv2 = stringBuilder.toString();
                String[] separated ;
                separated = retv2.split("/");
                int num = 0;
                for (String item : separated)
                {num++;
                    if(num%3==0){
                        listerv2+=item+"\n";
                        allcallv2+=Double.parseDouble(item);
                    }else{
                        listerv2+=item+" ";
                    }

                }

            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        try {
            SharedPreferences sp ;
            SharedPreferences.Editor editor ;

            sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
            editor = sp.edit();



            InputStream inputStream = getContext().openFileInput(sp.getString("dating","")+".txt");
            editor.commit();
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {

                    stringBuilder.append(receiveString);

                    Log.d("", "result = " + receiveString);
                }

                inputStream.close();

                ret = stringBuilder.toString();
                String[] separated ;
                separated = ret.split("/");
                int num = 0;
                for (String item : separated)
                {num++;
                    if(num%3==0){
                        lister+=item+"\n";
                        allcall+=Double.parseDouble(item);
                    }else{
                        lister+=item+" ";
                    }

                }

            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        BarChart chart = (BarChart) view.findViewById(R.id.bar_chart);

        final ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0, (float) allcall));
        entries.add(new BarEntry(1, (float) allcallv2));
        SharedPreferences sp = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        entries.add(new BarEntry(2,sp.getFloat("bmr", Float.parseFloat("0")) ));
        editor.commit();
        BarDataSet dataset = new BarDataSet(entries, "แคลอรี่ที่รับมา , แคลอรี่ที่เผาผลาญ , BMR");
        dataset.setValueTextSize(30);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // set the color
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(dataset);

        BarData data = new BarData(dataSets);
        chart.setData(data);

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setLabelRotationAngle(80);
        chart.setHorizontalScrollBarEnabled(true);


        final XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(20);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawLabels(false);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(1){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return "";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        chart.animateXY(2000,5000, Easing.EasingOption.EaseInBounce, Easing.EasingOption.EaseInElastic);
        YAxis RightAxis = chart.getAxisRight();
        RightAxis.setEnabled(false);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
if(e.getX()==0){
    String x = "";
    String[] separated ;
    separated = ret.split("/");
    int num = 0;
    for (String item : separated)
    {num++;
        if(num%3==0){
            x+=item+" แคลอรี่\n";

        }else if (num%2==0){
            x+=item+" อัน ";
        }else{
            x+=item+" ";
        }

    }
    Toast.makeText(getContext(), x, Toast.LENGTH_LONG).show();
}else if (e.getX()==1){
    String x = "";
    String[] separated ;
    separated = retv2.split("/");
    int num = 0;
    for (String item : separated)
    {num++;
        if(num%3==0){
            x+=item+" แคลอรี่\n";

        }else if (num%2==0){
            x+=item+" นาที ";
        }else{
            x+=item+" ";
        }


}
    Toast.makeText(getContext(), x, Toast.LENGTH_LONG).show();

            }}

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }



}

