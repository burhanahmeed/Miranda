package com.ayotong.miranda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ayotong.miranda.R;
import com.ayotong.miranda.model.Quest;
import com.ayotong.miranda.model.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burhan on 17/07/17.
 */

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {
    private Context context;
    private List<Stat> items;

    public StatAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }
    public void setItems(List<Stat> items) {
        this.items = items;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView bulan;
        private TextView xp_stat;
        private TextView xp_overall;

        public ViewHolder(View itemView) {
            super(itemView);

            this.bulan = (TextView)itemView.findViewById(R.id.txtBulan);
            this.xp_stat = (TextView)itemView.findViewById(R.id.txtXP_stat);
            this.xp_overall = (TextView)itemView.findViewById(R.id.txtXP_overall);
        }

        public void bind(Stat stat) {
            this.bulan.setText(stat.getBulan());
            this.xp_stat.setText(String.valueOf(stat.getXp()));
            this.xp_overall.setText(String.valueOf(stat.getXp_overall()));
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public StatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(this.context).inflate(R.layout.stat_items, parent, false);
        return new StatAdapter.ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(StatAdapter.ViewHolder holder, final int position) {
        holder.bind(this.items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
