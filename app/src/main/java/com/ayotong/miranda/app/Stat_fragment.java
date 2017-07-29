package com.ayotong.miranda.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ayotong.miranda.R;
import com.ayotong.miranda.adapter.StatAdapter;
import com.ayotong.miranda.database.ExpLogDB;
import com.ayotong.miranda.model.ExpLog;
import com.ayotong.miranda.model.Stat;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class Stat_fragment extends Fragment {

    private Context context;
    private RecyclerView statRecycle;
    private StatAdapter sAdapter;
    private ExpLogDB xpDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v =inflater.inflate(R.layout.fragment_stat, container, false);

        LineChart lineChart = (LineChart) v.findViewById(R.id.chart);
                // creating list of entry<br />
                ArrayList<Entry> entries = new ArrayList<>();
                entries.add(new Entry(100, 0));
                entries.add(new Entry(50, 1));
                entries.add(new Entry(100, 2));
                entries.add(new Entry(40, 3));
        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
//        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
        dataset.setFillColor(Color.parseColor("#30c078"));
        dataset.setColor(Color.parseColor("#187878"));

        // creating labels<br />
        ArrayList<String> labels = new ArrayList<String>();
                labels.add("July");
                labels.add("August");
                labels.add("September");
                labels.add("October");

        LineData data = new LineData(labels, dataset);
                lineChart.setData(data);
        lineChart.setDescription("Description");
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
//        pieChart.animateY(5000);

        //RECYCLE VIEW
        this.statRecycle = (RecyclerView)v.findViewById(R.id.stat_list);
        this.statRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.sAdapter = new StatAdapter(getActivity());
        this.statRecycle.setAdapter(sAdapter);

//        List<Stat> stat = new ArrayList<>();
//        stat.add(new Stat("July", 100, 100));
//        stat.add(new Stat("August", 50, 150));
//        stat.add(new Stat("September", 100, 250));
//        stat.add(new Stat("October", 40, 290));
        xpDB = new ExpLogDB(getContext().getApplicationContext());
        this.sAdapter.setItems(xpDB.sortByMonth());

        return v;
    }
}
