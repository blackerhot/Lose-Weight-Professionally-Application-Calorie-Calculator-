package asayaporn.pichet.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by IMSICK on 5/4/2560.
 */

public class SplashScreen extends AppCompatActivity {


    protected BufferedReader reader;
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    Thread logoThread =new Thread(){
        @Override
        public void run() {

            try {
                int splashTime = 0;

                while(splashTime < 600){
                    sleep(10);


                    if(splashTime < 200){
                        setText("Loading.");
                    }
                    else if(splashTime >= 200 && splashTime < 400 ){
                        setText("Loading..");
                    }else if (splashTime >= 400){
                        setText("Loading...");
                    }
                    splashTime = splashTime + 10;

                }
                prefs = getSharedPreferences("asayaporn.pichet.myapplication", MODE_PRIVATE);
                if (prefs.getBoolean("firstrun", true)) {

                    matching();
                    Intent intent = new Intent(SplashScreen.this,infoAct.class);
                    startActivity(intent);

                    prefs.edit().putBoolean("firstrun", false).commit();
                }else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                finish();
            }
        }
    };
    logoThread.start();
    }
    private void setText(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.textload)).setText(text);
            }
        });
    }
    public void matching() {
        String result = "";
        try {
            reader = new BufferedReader(
                    new InputStreamReader(SplashScreen.this.getAssets().open("calkcal.json")));

            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput( "cal.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(String.valueOf(result));
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        try {

            InputStream inputStream = getApplicationContext().openFileInput("cal.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {

                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String ret ;
                ret = stringBuilder.toString();
                SharedPreferences sp ;
                SharedPreferences.Editor editor ;

                sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putString("json", ret);

                editor.commit();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

    }
}
