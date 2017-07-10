package com.ayotong.miranda.app;

//import android.app.Fragment;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
;
import com.ayotong.miranda.R;
import com.ayotong.miranda.adapter.CardAdapter;
import com.ayotong.miranda.model.LocationInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burhan on 06/07/17.
 */

public class Home_fragment extends Fragment {

    private RecyclerView cardRecyclerView;
    private CardAdapter cardAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // 1
        this.cardRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        this.cardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        this.cardAdapter = new CardAdapter(getActivity());
        this.cardRecyclerView.setAdapter(this.cardAdapter);

        // 4
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(this.cardRecyclerView);
        this.cardRecyclerView.setOnFlingListener(snapHelper);
        this.cardRecyclerView.addItemDecoration(new VerticalOffsetDecoration(getActivity())); // 9

        // 2
        List<LocationInformation> locations = new ArrayList<>();
        locations.add(new LocationInformation("19.20", "10xp", "Waktunya Eok"));
        locations.add(new LocationInformation("11.20", "10xp","Waktunya minum gelas yang banyak 1000ml"));
        locations.add(new LocationInformation("09.20", "10xp","Enak enak dulu"));
        locations.add(new LocationInformation("11.22", "11xp","Rehat jenak"));

        // 3
        this.cardAdapter.setItems(locations);

        return v; //return the fragmentview
    }

    public class VerticalOffsetDecoration extends RecyclerView.ItemDecoration {
        private Activity context;

        public VerticalOffsetDecoration(Activity context) {
            this.context = context;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int total = parent.getAdapter().getItemCount();


            // 5
            if (position != 0 && position != total - 1)
                return;

            // 6
            Display display = context.getWindowManager().getDefaultDisplay();
            Point displaySize = new Point();
            display.getSize(displaySize);
            int displayWidth = displaySize.x;
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            float viewWidth = params.width;

            // 7
            int offset = (int)(displayWidth - viewWidth) / 2 ;

            // 8
            if (position == 0)
                outRect.left = offset - params.getMarginStart();

            if (position == total - 1)
                outRect.right = offset- params.getMarginEnd();
        }
    }

}
