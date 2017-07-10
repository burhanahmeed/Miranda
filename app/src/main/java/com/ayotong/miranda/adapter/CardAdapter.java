package com.ayotong.miranda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ayotong.miranda.R;
import com.ayotong.miranda.model.LocationInformation;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context context;
    private List<LocationInformation> items;


    public CardAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setItems(List<LocationInformation> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJam;
        private TextView txtXP;
        private TextView txtQuest;

        public ViewHolder(View itemView) {
            super(itemView);

            this.txtJam = (TextView)itemView.findViewById(R.id.txtJam);
            this.txtXP = (TextView)itemView.findViewById(R.id.txtXP);
            this.txtQuest = (TextView)itemView.findViewById(R.id.txtQuest);
        }

        public void bind(LocationInformation locationInformation) {
            this.txtJam.setText(locationInformation.getJam());
            this.txtXP.setText(locationInformation.getXp());
            this.txtQuest.setText(locationInformation.getQuest());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(this.context).inflate(R.layout.carousel_home, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(this.items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}