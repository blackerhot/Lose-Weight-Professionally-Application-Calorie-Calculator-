package asayaporn.pichet.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by IMSICK on 4/4/2560.
 */

public class infoAct extends Activity  {

    static Double bmr;
    static String bmi;
    public static String msg="";

    static Boolean gender ;

    SharedPreferences sp ;
    SharedPreferences.Editor editor ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(infoAct.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        final Button butinfoCal =(Button)findViewById(R.id.butinfoCalv2);

        final EditText editUser =(EditText)findViewById(R.id.editUser);
        final EditText editWeight =(EditText)findViewById(R.id.editWeight);
        final EditText editHeight =(EditText)findViewById(R.id.editHeight);
        final EditText editAge =(EditText)findViewById(R.id.editAge);
        final RadioButton radioFemale =(RadioButton)findViewById(R.id.radioFemale);
        final RadioButton radioMale =(RadioButton)findViewById(R.id.radioMale);


        butinfoCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Double weightin;
                Double heightin;
                Double bmiin = null;

                Intent intent=new Intent(v.getContext(),MainActivity.class);


                if(radioFemale.isChecked()){
                    gender=false;
                    if(editHeight.getText().toString().equals("")||editWeight.getText().toString().equals("")
                            ||editAge.getText().toString().equals("")||editUser.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"กรุณาใส่ข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    }
                    else {
                        weightin = Double.parseDouble(editWeight.getText().toString());
                        heightin = Double.parseDouble(editHeight.getText().toString());


                        bmiin = weightin/((heightin/100)*(heightin/100));
                        bmi=bmiin.toString();

                        if (bmiin < 18.5) {
                            msg = "น้ำหนักน้อย ผอม ";
                        } else if (bmiin >= 18.5 && bmiin<=22.9){
                            msg = "น้ำหนักปกติ สมส่วน";
                        }
                        else if (bmiin >= 23 && bmiin <=24.9){
                            msg = "น้ำหนักเกิน";
                        }
                        else if (bmiin >= 25 &&bmiin <=29.9){
                            msg = "อ้วน";
                        }
                        else{
                            msg = "อ้วนมาก";
                        }
                        bmr*=665+(9.6*weightin)+(1.8*heightin)-(4.7*Double.parseDouble(editAge.getText().toString()));

                       sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
                      editor = sp.edit();
                        editor.putString("name",editUser.getText().toString());
                        editor.putString("stand",msg);
                        editor.putString("weight",String.valueOf(weightin));
                        editor.putString("height",String.valueOf(heightin));
                        editor.putString("age",String.valueOf(editAge.getText()));
                        editor.putBoolean("gender",gender);
                        editor.putFloat("bmr",Float.parseFloat(bmr.toString()));
                        editor.putInt("pos",spinner.getSelectedItemPosition());
                        editor.putFloat("bmi",Float.parseFloat(bmi.toString()));
                        editor.commit();

                        startActivity(intent);
                    }
                }else if(radioMale.isChecked()){
                    gender=true;
                    if(editHeight.getText().toString().equals("")||editWeight.getText().toString().equals("")
                            ||editAge.getText().toString().equals("")||editUser.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"กรุณาใส่ข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                    }
                    else {
                        weightin = Double.parseDouble(editWeight.getText().toString());
                        heightin = Double.parseDouble(editHeight.getText().toString());


                        bmiin = weightin/((heightin/100)*(heightin/100));
                        bmi=bmiin.toString();

                        if (bmiin < 18.5) {
                            msg = "น้ำหนักน้อย ผอม ";
                        } else if (bmiin >= 18.5 && bmiin<=22.9){
                            msg = "น้ำหนักปกติ สมส่วน";
                        }
                        else if (bmiin >= 23 && bmiin <=24.9){
                            msg = "น้ำหนักเกิน";
                        }
                        else if (bmiin >= 25 &&bmiin <=29.9){
                            msg = "อ้วน";
                        }
                        else{
                            msg = "อ้วนมาก";
                        }
                        bmr*=66+(13.7*weightin)+(5*heightin)-(6.8*Double.parseDouble(editAge.getText().toString()));
                        editor = sp.edit();
                        editor.putString("name",editUser.getText().toString());
                        editor.putString("stand",msg);
                        editor.putString("weight",String.valueOf(weightin));
                        editor.putString("height",String.valueOf(heightin));
                        editor.putString("age",String.valueOf(editAge.getText()));
                        editor.putBoolean("gender",gender);
                        editor.putFloat("bmr",Float.parseFloat(bmr.toString()));
                        editor.putFloat("bmi",Float.parseFloat(bmi.toString()));
                        editor.putInt("pos",spinner.getSelectedItemPosition());
                        editor.commit();
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"กรุณาใส่ข้อมูลให้ครบ",Toast.LENGTH_LONG).show();
                }



            }



        });

        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
        boolean isRemember = sp.getBoolean("gender", false);
        if(isRemember) {
            editor.putString("genderType","หญฺิง");
            radioMale.setChecked(true);
            radioFemale.setChecked(false);
            editor.commit();
        }else{
            editor.putString("genderType","ชาย");
            radioFemale.setChecked(true);
            radioMale.setChecked(false);
            editor.commit();
        }

        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
        editUser.setText(sp.getString("name", ""));
        editUser.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString("name", s.toString());
                editor.commit();
            }
        });

        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();

        editWeight.setText(sp.getString("weight", ""));
        editWeight.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString("weight", s.toString());
                editor.commit();
            }
        });
        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
        editHeight.setText(sp.getString("height", ""));
        editHeight.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString("height", s.toString());
                editor.commit();
            }
        });

        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();

        editAge.setText(sp.getString("age",""));
        editAge.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) {
                editor.putString("age", s.toString());
                editor.commit();
            }
        });


        radioMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("gender", !isChecked);
                editor.commit();
            }
        });


        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
        spinner.setSelection( sp.getInt("pos", Integer.parseInt(String.valueOf(-1))));
        editor.commit();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3) {

                switch (position) {

                    case 0:
                        bmr=1.2;
                        break;
                    case 1:
                        bmr=1.375;
                        break;
                    case 2:
                        bmr=1.55;
                        break;
                    case 3:
                        bmr=1.725;
                        break;
                    case 4:
                        bmr=1.725;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private Spinner spinner;
    private static final String[]paths = {"ไม่ได้ออกกำลังกายเลย", "อาทิตย์ละ 1-3 วัน", "อาทิตย์ละ 3-5 วัน","อาทิตย์ละ 6-7 วัน" ,"ทุกวันเช้าเย็น"};



}
