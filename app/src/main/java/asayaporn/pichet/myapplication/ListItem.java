package asayaporn.pichet.myapplication;

import android.graphics.Color;

/**
 * Created by admin on 29/4/2560.
 */

public class ListItem {
    private String head;
    private String desc;
    private int imageView;
    private Color color;

    public ListItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public ListItem(String head, String desc, int imageView) {
        this.head = head;
        this.desc = desc;
        this.imageView = imageView;
    }

    public ListItem(String head, String desc, int imageView, Color color) {
        this.head = head;
        this.desc = desc;
        this.imageView = imageView;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
