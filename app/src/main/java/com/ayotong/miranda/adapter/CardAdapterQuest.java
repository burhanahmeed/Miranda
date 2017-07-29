package com.ayotong.miranda.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ayotong.miranda.DialogActivity;
import com.ayotong.miranda.MainActivity;
import com.ayotong.miranda.R;
import com.ayotong.miranda.database.ExpLogDB;
import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.ExpLog;
import com.ayotong.miranda.model.Quest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardAdapterQuest extends RecyclerView.Adapter<CardAdapterQuest.ViewHolder> {

    private Context context;
    private List<Quest> items;
    QuestDB qDB;
    ExpLog xpLog;
    ExpLogDB xpDB;

    public CardAdapterQuest(Context context) {
        this.context = context;
        this.items = new ArrayList<Quest>();
    }

    public void setItems(List<Quest> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtJam;
        private TextView txtXP;
        private TextView txtQuest;
        ImageButton accomplish;
        ImageButton dismiss;

        public ViewHolder(View itemView) {
            super(itemView);

            this.txtJam = (TextView)itemView.findViewById(R.id.txtJam);
            this.txtXP = (TextView)itemView.findViewById(R.id.txtXP);
            this.txtQuest = (TextView)itemView.findViewById(R.id.txtQuest);
            accomplish = (ImageButton)itemView.findViewById(R.id.id_accomplish);
            dismiss= (ImageButton)itemView.findViewById(R.id.id_dissmiss);

            dismiss.setOnClickListener(this);
            accomplish.setOnClickListener(this);

            qDB = new QuestDB(context);
            xpLog = new ExpLog();
            xpDB = new ExpLogDB(context);
        }

        public void bind(Quest quest) {
            this.txtJam.setText(quest.getJam());
            this.txtXP.setText(String.valueOf(quest.getExp()));
            this.txtQuest.setText(quest.getQuestDescription());
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(this.context).inflate(R.layout.carousel_home, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.bind(this.items.get(position));
        final int Qid = items.get(position).getId();
        final int xp = items.get(position).getExp();

        holder.dismiss.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Quest itemLabel = items.get(position);
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,items.size());
                qDB = new QuestDB(context);
                qDB.deleteQuest(qDB.getQuest(Qid));
                Toast.makeText(context, "Task dismissed", Toast.LENGTH_LONG).show();
                Log.d("ID", "berapa idnya "+Qid);
            }
        });

        holder.accomplish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,items.size());
                qDB = new QuestDB(context);
                qDB.deleteQuest(qDB.getQuest(Qid));

                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                xpLog.setExp_gain(xp);
                xpLog.setQuest_id(Qid);
                xpLog.setTimestamp(ts);
                xpDB.insert(xpLog);
                Toast.makeText(context, "Task completed +"+xp+" XP", Toast.LENGTH_LONG).show();
                Log.d("XP insert", ts);
                //realtime set text
//                MainActivity ma = new MainActivity();
//                TextView level = ma.getTextView();
//                ExpLogDB xpDB = new ExpLogDB(context);
//                Cursor cXP = xpDB.SumOfXP();
//                int total=0;
//                if (cXP.moveToNext()) {
//                    total = cXP.getInt(cXP.getColumnIndex("Total"));
//                }
//                level.setText(total);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}