package asayaporn.pichet.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 29/4/2560.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<ListItem> listItems;
    private Context context;
    private LayoutInflater layoutInflater;


    public Adapter(List<ListItem> listItem, Context context) {
        this.listItems = listItem;
        this.context = context;

        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.head.setText(listItem.getHead());
        holder.description.setText(listItem.getDesc());
        holder.imageView.setImageResource(listItems.get(position).getImageView());
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView head;
        public TextView description;
        public ImageView imageView;
        public Color color;


        public ViewHolder(View itemView) {
            super(itemView);


            head = (TextView) itemView.findViewById(R.id.head);
            description = (TextView) itemView.findViewById(R.id.descript);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }


    }

}


