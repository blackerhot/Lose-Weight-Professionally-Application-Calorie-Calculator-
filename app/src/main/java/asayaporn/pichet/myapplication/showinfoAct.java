package asayaporn.pichet.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by IMSICK on 4/4/2560.
 */

public class showinfoAct extends Activity {

    TextView sBmi,sUser,sAge,sCal,sGender,sHeight,sWeight,sStand;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showinfo);

        sBmi = (TextView)findViewById(R.id.sBmi);
        sUser = (TextView)findViewById(R.id.sUser);
        sAge = (TextView)findViewById(R.id.sAge);
        sCal = (TextView)findViewById(R.id.sCal);
        sGender = (TextView)findViewById(R.id.sGender);
        sHeight = (TextView)findViewById(R.id.sHeight);
        sWeight = (TextView)findViewById(R.id.sWight);
        sStand = (TextView)findViewById(R.id.sStand);

        SharedPreferences sp ;
        SharedPreferences.Editor editor ;


        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
        sGender.setText(sp.getString("genderType",""));
        sUser.setText(sp.getString("name", ""));
        sWeight.setText(String.valueOf(sp.getString("weight","")));
        sHeight.setText(String.valueOf(sp.getString("height", "")));
        sAge.setText(String.valueOf(sp.getString("age", "")));
        sCal.setText(String.valueOf((int) sp.getFloat("bmr", Float.parseFloat("0"))));
        sBmi.setText(String.valueOf((int) sp.getFloat("bmi", Float.parseFloat("0"))));
        sStand.setText(sp.getString("stand",""));
    editor.commit();



    }
    public void backShowinfo(View view){

        Intent intent = new Intent(showinfoAct.this,MainActivity.class);
        startActivity(intent);
    }

    public void ShowinfoGoEdit(View view){

        Intent intent = new Intent(showinfoAct.this,infoAct.class);
        startActivity(intent);
    }
}
