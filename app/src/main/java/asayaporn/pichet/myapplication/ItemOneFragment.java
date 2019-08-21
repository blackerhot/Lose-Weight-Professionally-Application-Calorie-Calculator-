package asayaporn.pichet.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IMSICK on 5/10/2017.
 */


public class ItemOneFragment extends Fragment {

    protected ListView mListView;
    protected CustomAdapter mAdapter;
    double[] numunit;


    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    int count =0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {

            EditText editText = (EditText)getView().findViewById(R.id.editSerach);

            if(count==0) {
                editText.setVisibility(View.VISIBLE);
                count=1;
            }else{
                editText.setVisibility(View.GONE);
                editText.setText("");
                count=0;
            }

            return true;
        }
        if (id == R.id.action_edit) {
            Intent intent = new Intent(getActivity(),showinfoAct.class);

            startActivity(intent);
            return true;
        }
        if (id == R.id.action_adding) {
            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = LayoutInflater.from(getContext());

            final View view = inflater.inflate(R.layout.adding, null);
            builder.setView(view);
            final SharedPreferences sp ;
            final SharedPreferences.Editor editor ;
            sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
            editor = sp.edit();

            final EditText title = (EditText)view.findViewById(R.id.eName1);
            final EditText type = (EditText)view.findViewById(R.id.eType);
            final EditText quantity = (EditText)view.findViewById(R.id.eQuan);
            final EditText unit = (EditText)view.findViewById(R.id.eUnit);
            final EditText calories = (EditText)view.findViewById(R.id.eCAl1);



            builder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                      int x= Integer.parseInt(String.valueOf(quantity.getText()));
                    Gson gson=new GsonBuilder().setPrettyPrinting().create();

                    Blog jsonColl = gson.fromJson( sp.getString("json",""), Blog.class );
                    editor.commit();
                    jsonColl.addFood(new Post(String.valueOf(type.getText()),String.valueOf(title.getText()),x,String.valueOf(unit.getText())
                            ,String.valueOf(calories.getText())));


                    String newJsonString =gson.toJson( jsonColl ).toString();

                    SharedPreferences sp ;
                    SharedPreferences.Editor editor ;

                    sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
                    editor = sp.edit();
                    editor.putString("json", newJsonString);

                    editor.commit();



                    Gson gson1 = new Gson();
                    Blog blog = gson1.fromJson(newJsonString, Blog.class);
                    List<Post> posts = blog.getFood();

                    mAdapter = new CustomAdapter(getActivity(), posts);

                    mListView.setAdapter(mAdapter);






                }
            });
            builder.setNegativeButton("กลับ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();

            return true;
        }
        if(id==R.id.action_sortbycalories1){
            Post post;
            final ArrayList<Post> arra=new ArrayList<>();



            for(int i = 0; i<mAdapter.getCount(); i++){
                    post=(Post) mAdapter.getItem(i);
                  arra.add(post);

                }


            Collections.sort(arra,new Comparator<Post>() {
                @Override
                public int compare(Post p0, Post p1) {
                    if (Double.parseDouble(p0.getCalories()) > Double.parseDouble(p1.getCalories())) {
                        return 1;
                    } else {
                        return -1;
                    }

                }
            });
            mListView.setAdapter(new CustomAdapter(getActivity(), arra));
            return true;
        }
        if(id==R.id.action_sortbycalories2){
            Post post;
            final ArrayList<Post> arra=new ArrayList<>();



            for(int i = 0; i<mAdapter.getCount(); i++){
                post=(Post) mAdapter.getItem(i);
                arra.add(post);

            }


            Collections.sort(arra,new Comparator<Post>() {
                @Override
                public int compare(Post p0, Post p1) {
                    if (Double.parseDouble(p0.getCalories()) < Double.parseDouble(p1.getCalories())) {
                        return 1;
                    } else {
                        return -1;
                    }

                }
            });
            mListView.setAdapter(new CustomAdapter(getActivity(), arra));
            return true;
        }
        if(id==R.id.action_sortbytitle1)
        {
            Post post;
            final ArrayList<Post> arra=new ArrayList<>();



            for(int i = 0; i<mAdapter.getCount(); i++){
                post=(Post) mAdapter.getItem(i);
                arra.add(post);

            }


            Collections.sort(arra,new Comparator<Post>() {
                @Override
                public int compare(Post p0, Post p1) {
                    return  p1.getTitle().compareTo(p0.getTitle());

                }
            });
            mListView.setAdapter(new CustomAdapter(getActivity(), arra));
            return true;
        }
        if(id==R.id.action_sortbytitle2)
        {
            Post post;
            final ArrayList<Post> arra=new ArrayList<>();



            for(int i = 0; i<mAdapter.getCount(); i++){
                post=(Post) mAdapter.getItem(i);
                arra.add(post);

            }


            Collections.sort(arra,new Comparator<Post>() {
                @Override
                public int compare(Post p0, Post p1) {
                    return  p0.getTitle().compareTo(p1.getTitle());

                }
            });
            mListView.setAdapter(new CustomAdapter(getActivity(), arra));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.food, container, false);
        setHasOptionsMenu(true);
        mListView = (ListView) view.findViewById(R.id.listview);

        SharedPreferences sp ;
        SharedPreferences.Editor editor ;

        sp = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sp.edit();
      showData(sp.getString("json", ""));

        editor.commit();
        final ListView listview = (ListView) view.findViewById(R.id.listview);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long rowId) {


                final AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final DecimalFormat numberFormat = new DecimalFormat("#.00");
                view = inflater.inflate(R.layout.dialog_food, null);
                builder.setView(view);
                final Post post = (Post) parent.getItemAtPosition(position);
                final TextView myOutputBox = (TextView) view.findViewById(R.id.calT);
                numunit = new double[]{1};
                final TextView unitT = (TextView) view.findViewById(R.id.unitText);
                Button button = (Button) view.findViewById(R.id.buttonsert);
                Button buttonAdd = (Button) view.findViewById(R.id.butAdd);
                Button buttonDel = (Button) view.findViewById(R.id.butDel);
                final int x = Integer.parseInt(post.getCalories());

                unitT.setText(String.valueOf(numberFormat.format(numunit[0])));
                myOutputBox.setText(String.valueOf(numberFormat.format(numunit[0]*x)));


                builder.setTitle(post.getTitle());

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        numunit[0] = numunit[0] + 0.25;
                        unitT.setText(String.valueOf(numberFormat.format(numunit[0])));
                    }


                });
                buttonDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        numunit[0] = numunit[0] - 0.25;
                        unitT.setText(String.valueOf(numberFormat.format(numunit[0])));
                    }
                });
                final EditText myTextBox = (EditText) view.findViewById(R.id.unitText);
                final String[] unitall = new String[1];
                final String[] cal = new String[1];
                myTextBox.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) {

                    }

                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {

                    }

                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {



                        if (numunit[0] >= 0) {
                                Double a = Double.parseDouble(String.valueOf(s));
                            unitall[0] =String.valueOf(s);
                                a = a * x;
                                DecimalFormat numberFormat = new DecimalFormat("#.00");
                                cal[0] = Double.toString(Double.parseDouble(numberFormat.format(a)));
                                myOutputBox.setText(cal[0]);


                            } else if (numunit[0] < .0) {
                                numunit[0] = 0.0;
                                show("Input only positive Number ! \n กรุณาใส่เฉพาะจำนวนเบวก");
                                myOutputBox.setText("error");
                                unitT.setText(String.valueOf(numberFormat.format(numunit[0])));
                            }



                    }
                });
                final AlertDialog show = builder.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ret = "";
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
                                }

                                inputStream.close();
                                ret = stringBuilder.toString();
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





                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getContext().openFileOutput( sp.getString("dating","")+".txt", Context.MODE_PRIVATE));
                            editor.commit();
                            outputStreamWriter.write(String.valueOf(ret+post.getTitle()+"/"+unitT.getText()+"/" +myOutputBox.getText()+"/"));


                            outputStreamWriter.close();
                        }
                        catch (IOException e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        show.dismiss();

                    }
                });

            }

        });




        final EditText inputSearch =(EditText)view.findViewById(R.id.editSerach);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                Post post;
                final ArrayList<Post> arra=new ArrayList<>();
                if(inputSearch.length()!=0) {
                for(int i=0;i<mAdapter.getCount();i++){
                    post= (Post) mAdapter.getItem(i);

                    if( post.getTitle().toString().contains(cs)){

                       arra.add(post);

                    }
                    mListView.setAdapter(new CustomAdapter(getActivity(), arra));
                }
                }else{

                    mListView.setAdapter(mAdapter);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });



        return view;
    }
    private void show(String jsonString) {

        Toast.makeText(getActivity(), jsonString, Toast.LENGTH_LONG).show();

    }



    private void showData(String jsonString) {
        Gson gson = new Gson();
        Blog blog = gson.fromJson(jsonString, Blog.class);
        List<Post> posts = blog.getFood();

        mAdapter = new CustomAdapter(this.getActivity(), posts);

        mListView.setAdapter(mAdapter);

    }


}