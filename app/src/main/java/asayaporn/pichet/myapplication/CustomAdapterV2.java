package asayaporn.pichet.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by IMSICK on 4/17/2017.
 */

public class CustomAdapterV2 extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected ViewHolder mViewHolder;
    List<Post> mPosts;
    protected Post mPost;

    public CustomAdapterV2 (Activity activity, List<Post> posts) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mPosts = posts;
    }
    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return mPosts.get(position);
    }
    private static class ViewHolder {
        ImageView type;
        TextView title;
        TextView unit;
        TextView calories;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.postburnout, parent, false);
            mViewHolder = new CustomAdapterV2.ViewHolder();
            mViewHolder.type = (ImageView) convertView.findViewById(R.id.post_type2);
            mViewHolder.unit = (TextView) convertView.findViewById(R.id.post_unit2);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.post_title2);
            mViewHolder.calories = (TextView) convertView.findViewById(R.id.post_calories2);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (CustomAdapterV2.ViewHolder) convertView.getTag();
        }


        mPost = mPosts.get(position);
        DecimalFormat numberFormat = new DecimalFormat();
        numberFormat.setDecimalSeparatorAlwaysShown(false);

        Double x = Double.parseDouble(mPost.calories_10m);


        mViewHolder.title.setText(mPost.title);
        mViewHolder.calories.setText( numberFormat.format(x) + " kCal");
        mViewHolder.unit.setText("ระยะเวลา "+ mPost.time +" "+mPost.unit);

        return convertView;
    }

}
