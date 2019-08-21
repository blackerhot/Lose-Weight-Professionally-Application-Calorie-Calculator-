package asayaporn.pichet.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IMSICK on 5/10/2017.
 */

public class ItemBurn extends Fragment {
    public static Fragment newInstance() {
        ItemBurn ib= new ItemBurn();
        return ib;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.burnoutyoutube_layout, container, false);
        return view;
    }
}
