package asayaporn.pichet.myapplication;

import java.util.List;

/**
 * Created by IMSICK on 4/15/2017.
 */

public class Blog {


    List<Post> Food;

    public List<Post> getFood() {
        return Food;
    }

    public void setFood(List<Post> food) {
        Food = food;
    }
    public void addFood( Post h ) {
        this.Food.add( h );
    }
    public void addActivity( Post h ) {
        this.Activity.add( h );
    }
    public List<Post> getActivity() {
        return Activity;
    }

    public void setActivity(List<Post> activity) {
        Activity = activity;
    }

    List<Post> Activity;

    public List<Post> getAdding() {
        return Adding;
    }

    public void setAdding(List<Post> adding) {
        Adding = adding;
    }

    List<Post> Adding;





}
