package com.ayotong.miranda.app;

//import android.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
;
import com.ayotong.miranda.R;
import com.ayotong.miranda.adapter.CardAdapterQuest;
import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.Quest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by burhan on 06/07/17.
 */

public class Home_fragment extends Fragment {

    private RecyclerView cardRecyclerView;
    private CardAdapterQuest cardAdapterQuest;
    private TextView time, date, greet;
    private ImageView cuaca;
    private QuestDB qDB;
    Runnable updater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // 1
        this.cardRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        this.cardRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        this.cardAdapterQuest = new CardAdapterQuest(getActivity());
        this.cardRecyclerView.setAdapter(this.cardAdapterQuest);

        // 4
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(this.cardRecyclerView);
        this.cardRecyclerView.setOnFlingListener(snapHelper);
        this.cardRecyclerView.addItemDecoration(new VerticalOffsetDecoration(getActivity())); // 9

        // 2
//        List<Quest> quests = new ArrayList<>();
//        quests.add(new Quest(0, 10, "Waktunya Eok", "15:15"));
//        quests.add(new Quest(1,10,"Waktunya minum gelas yang banyak 1000ml","15:15"));
//        quests.add(new Quest(2,10,"Enak enak dulu","15:15"));
//        quests.add(new Quest(3,11,"Rehat jenak","15:15"));
        qDB = new QuestDB(getContext().getApplicationContext());
//        qDB.readAllQuest();
        Log.d("DB", "terbaca ");
        // 3
        this.cardAdapterQuest.setItems(qDB.readAllQuest());

        time = (TextView)v.findViewById(R.id.txttime);
        date = (TextView)v.findViewById(R.id.txtdate);
        greet = (TextView)v.findViewById(R.id.txt_wheater);
        cuaca = (ImageView)v.findViewById(R.id.img_cuaca);

//        cuaca.setImageDrawable();

        time();
        date();
        greet();

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

    public class UnscrollableLinearLayoutManager extends LinearLayoutManager {
        public UnscrollableLinearLayoutManager(Context context) {
            super(context);
        }

        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }

    public void time(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
        final String times = tm.format(Calendar.getInstance().getTime());
//        time.setText(times);
        final Handler timerHandler = new Handler();
        updater = new Runnable() {
            @Override
            public void run() {
                time.setText(times);
                timerHandler.postDelayed(updater,50000);
            }
        };
        timerHandler.post(updater);
    }
    public  void date(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("EEEE, d MMMM yyyy");
        String dates = dt.format(Calendar.getInstance().getTime());
        date.setText(dates);
    }
    public void greet(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        Random r = new Random();
        int value = r.nextInt(3);

        if(timeOfDay >= 0 && timeOfDay < 12){
            switch(value) {
                case 0 :
                    greet.setText("GOOD MORNING");
                    break;
                case 1 :
                    greet.setText("HAVE A NICE DAY");
                    break;
                case 2 :
                    greet.setText("DO YOUR BEST");
                    break;
            }
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            switch(value) {
                case 0 :
                    greet.setText("GOOD AFTERNOON");
                    break;
                case 1 :
                    greet.setText("KEEP SPIRIT");
                    break;
                case 2 :
                    greet.setText("YOU'RE DOING SO GOOD");
                    break;
            }
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            switch(value) {
                case 0 :
                    greet.setText("GOOD EVENING");
                    break;
                case 1 :
                    greet.setText("TAKE A REST, OK");
                    break;
                case 2 :
                    greet.setText("TODAY IS A GOOD DAY");
                    break;
            }
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            switch(value) {
                case 0 :
                    greet.setText("GOOD NIGHT");
                    break;
                case 1 :
                    greet.setText("SWEET DREAM");
                    break;
                case 2 :
                    greet.setText("DONT FORGET TO DRINK WATER");
                    break;
            }
        }
    }

}
