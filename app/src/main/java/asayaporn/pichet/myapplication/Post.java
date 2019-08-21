package asayaporn.pichet.myapplication;

/**
 * Created by IMSICK on 4/15/2017.
 */

public class Post {

    public Post(String type, String title, int quantity, String unit, String calories) {
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.unit = unit;
        this.calories = calories;
    }

    String type;
    String title;
    int quantity;
    String unit;
    String calories;
    String calories_10m;


    public Post(String text, String s) {
        this.title=text;
        this.calories_10m=s;
        this.unit="นาที";
        this.time="10";
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCalories_10m() {
        return calories_10m;
    }

    public void setCalories_10m(String calories_10m) {
        this.calories_10m = calories_10m;
    }


}
