package com.ayotong.miranda.app;

//import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
;
import com.ayotong.miranda.R;
import com.ayotong.miranda.adapter.HomeRecyclerCardViewAdapter;
import com.ayotong.miranda.model.HomeDataModel;
import com.ayotong.miranda.model.Home_carousel;

import java.util.ArrayList;

/**
 * Created by burhan on 06/07/17.
 */

public class Home_fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<String> Number;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    HomeRecyclerCardViewAdapter homeRecycle;
    LinearLayoutManager HorizontalLayout;
    View ChildView;
    int RecyclerViewItemPosition;
    ArrayList<HomeDataModel> allSampleData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        allSampleData = new ArrayList<HomeDataModel>();

        createDummyData();
        recyclerView = (RecyclerView) v.findViewById(R.id.home_carousel);
        recyclerView.setHasFixedSize(true);
        HomeRecyclerCardViewAdapter homeAdapter = new HomeRecyclerCardViewAdapter(getActivity(), allSampleData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(homeAdapter);

        return v; //return the fragmentview
    }

    // function to add items in RecyclerView.
    public void createDummyData() {
        for (int i = 1; i <= 5; i++) {

            HomeDataModel dm = new HomeDataModel();

            dm.setHeaderTitle("Section " + i);

            ArrayList<Home_carousel> singleItem = new ArrayList<Home_carousel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new Home_carousel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
}
