package asayaporn.pichet.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter{

        protected LayoutInflater mInflater;
        protected ViewHolder mViewHolder;
        List<Post> mPosts;
    protected Post mPost;


    public CustomAdapter(Activity activity, List<Post> posts) {
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
            convertView = mInflater.inflate(R.layout.post, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.type = (ImageView) convertView.findViewById(R.id.post_type);
            mViewHolder.unit = (TextView) convertView.findViewById(R.id.post_unit);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.post_title);
            mViewHolder.calories = (TextView) convertView.findViewById(R.id.post_calories);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mPost = mPosts.get(position);

        if(mPost.getType().equals("เครื่องดื่ม")){
            mViewHolder.type.setImageResource(R.drawable.drink);
        }else if(mPost.getType().equals("นม")){
            mViewHolder.type.setImageResource(R.drawable.milkk);
        }else if(mPost.getType().equals("ผลไม้")){
            mViewHolder.type.setImageResource(R.drawable.fruit);
        }else if(mPost.getType().equals("กับข้าว")){
            mViewHolder.type.setImageResource(R.drawable.curry);
        }else if(mPost.getType().equals("ผัก")){
            mViewHolder.type.setImageResource(R.drawable.veg);
        }else if(mPost.getType().equals("ขนม")){
            mViewHolder.type.setImageResource(R.drawable.snackk);
        }else if(mPost.getType().equals("แป้ง")){
            mViewHolder.type.setImageResource(R.drawable.food);
        }else if(mPost.getType().equals("จานเดี่ยว")){
            mViewHolder.type.setImageResource(R.drawable.food2);
        }else if(mPost.getType().equals("ธัญพืช")){
            mViewHolder.type.setImageResource(R.drawable.grain);
        }else if(mPost.getType().equals("เบเกอรี่")){
            mViewHolder.type.setImageResource(R.drawable.bakery);
        }else{
            mViewHolder.type.setImageResource(R.drawable.food2);
        }

        mViewHolder.title.setText(mPost.title);
        mViewHolder.calories.setText(mPost.calories + " kCal");
        mViewHolder.unit.setText("ปริมาณต่อ "+ mPost.quantity +" "+mPost.unit);

        return convertView;
    }


}