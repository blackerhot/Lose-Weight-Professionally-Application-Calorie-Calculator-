package asayaporn.pichet.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IMSICK on 5/10/2017.
 */

public class ItemThink extends Fragment{
    public static Fragment newInstance() {
        ItemThink it=new ItemThink();
        return  it;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.think_layout, container, false);

       RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        List<ListItem> listItems;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems = new ArrayList<>();

        ListItem exercise = new ListItem("Proper Exercise ", "You should not try hard to "
                + "lose your weight rapidly, you should do it gradually "
                + "or it can worse your health and your body. You should " +
                "defined" + "your goal that not higher than your capability " +
                "such as " + "such as each day your weight must be lose for " +
                "0.5 - 1 kg.");
        exercise.setImageView(R.drawable.exercise);

        listItems.add(exercise);

        ListItem chocoHealth = new ListItem("Is chocolate healthy ?  ",
                "       The answer is 'yes'. chocolate can control your weight if your eat in proper quantities" +
                        ",but I mean dark chocolate. Dark chocolate has less sugar, it can help you to control " +
                        "your weight eating dark chocolate can decrease your weight or stress, it also help you " +
                        "keep happy." + " However, if you eat too much , you can get more caffeine that will make " +
                        "you hard close your eyes. ");
        chocoHealth.setImageView(R.drawable.chocolate);
        listItems.add(chocoHealth);
        ListItem water = new ListItem("Water  ",
                "Water is good to your brains your body ,and your health. Water is the up to  60% of our bodies." +
                        "It good to drink to water at least eight cups per day for make your fresh and your brains " +
                        "can work normally." + "But someone think they can drink only water to lose their weight, drinks " +
                        "only water to make their weight decreasing quickly." + "It is good in some case, but you " +
                        "should do this for long time" + ", because you get not enough nutrition.");
        water.setImageView(R.drawable.water);
        listItems.add(water);
        ListItem work_out = new ListItem("How much weight is call 'fat' ",
                "The 'fat' is call over weight people, but over weight level that change" +
                        " due to weight , height ,or genetic identity of each people " +
                        "Then you will say why not use BMI to measure your weight , But BMI is almost accurate" +
                        "tool for to measure your weight, because in different countries the BMI table has different too "
                        + "The best way to say tour fat or not is check your weight your height and other factors like"
                        + "cholesterol rate in your blood or your diseases etc. ");
        work_out.setImageView(R.drawable.fat);
        listItems.add(work_out);
        ListItem eatting = new ListItem(" Can't stop eating ? ",
                "You have complained to yourself that you can't put anything into your mouth."
                        + " Then the question is There has any ways to stop eating? " +
                        " that best way is lock your self with a chair ... No! just kidding,"
                        + " we have better way to solved this problems candy that melt slowly in your mouth, " +
                        " health candy can buy from many convenient store you can eat them as ong as them in your mouth."
                        + "But don't forget, you must't shew them. ");
        eatting.setImageView(R.drawable.bread);
        listItems.add(eatting);
        ListItem sit_up = new ListItem("Sit-ups won't make your stomach flat!"
                ,
                "Many people think that they can get a flat stomach by doing crunches. But the truth is, " +
                        "endless amounts of crunches won’t give you the stomach of your dreams," + " you should eat healthy meal" +
                        "and your eating activity too.");
        sit_up.setImageView(R.drawable.situp);
        listItems.add(sit_up);
        ListItem desk_sitting = new ListItem("Sitting at a desk can cause a death"
                ,
                "According to the research from University of Sydney, " + "People that sit at a desk more than 10 hours like"
                        + "office workers, can cause the risk of death up to 10 per cent because you body is inactive. You can avoid getting " +
                        "this problem by take at least 5 minutes to stand-up or walk around that can help your body relax.");
        desk_sitting.setImageView(R.drawable.desk);
        listItems.add(desk_sitting);

        ListItem laughter_ex = new ListItem("Laughter Exercise"
                ,
                "Did you know ? " +
                        "When you laugh, you feel happy because you body will release "
                        + "Endorphin that can make you happy,and laughing will affect to you respiratory and blood system.It like you "
                        + "do an exercise,but you just laugh ");
        laughter_ex.setImageView(R.drawable.laugh);
        listItems.add(laughter_ex);

        ListItem diet_Trick = new ListItem("Refection of yourself"
                , "One study found that eating in front of mirrors slashed the amount people " +
                "ate by nearly one-third. Seems having to look yourself in the eye reflects " +
                "back some of your own inner standards and goals, and reminds you of why you’re " +
                "trying to lose weight in the first place.");
        diet_Trick.setImageView(R.drawable.mirror);
        listItems.add(diet_Trick);


        adapter = new Adapter(listItems, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }
}
