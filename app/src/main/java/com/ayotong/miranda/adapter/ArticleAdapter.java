package com.ayotong.miranda.adapter;

/**
 * Created by tehhutan on 14/07/17.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayotong.miranda.WebViewActivity;
import com.ayotong.miranda.model.Article;
import com.ayotong.miranda.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Marco Gomiero on 12/02/2015.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<Article> articles;

    private int rowLayout;
    private Context mContext;

    public ArticleAdapter(ArrayList<Article> list, int rowLayout, Context context) {

        this.articles = list;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }


    @Override
    public long getItemId(int item) {
        // TODO Auto-generated method stub
        return item;
    }
    public void clearData(){
        if (articles != null)
            articles.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)  {

        Article currentArticle = articles.get(position);

        Locale.setDefault(Locale.getDefault());
        Date date = currentArticle.getLastBuildDate();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf = new SimpleDateFormat("dd MMMM yyyy");
        final String pubDateString = sdf.format(date);

        viewHolder.title.setText(currentArticle.getTitle());

        //load the image. If the parser did not find an image in the article, it set a placeholder.
        Picasso.with(mContext)
                .load(currentArticle.getImage())
                .placeholder(R.drawable.ic_logo)
                .fit()
                .centerCrop()
                .into(viewHolder.image);    

        viewHolder.lastBuildDate.setText(pubDateString);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String mimeType = "text/html";
                final String encoding = "UTF-8";
                String html = "<br /><br />Read the handouts please for tomorrow.<br /><br /><!--homework help homework" +
                        "help help with homework homework assignments elementary school high school middle school" +
                        "// --><font color='#60c000' size='4'><strong>Please!</strong></font>" +
                        "<img src='http://www.homeworknow.com/hwnow/upload/images/tn_star300.gif'  />";

                //show article content inside a dialog
//                articleView = new WebView(mContext);
//
//                articleView.getSettings().setLoadWithOverviewMode(true);
//
//                String title = articles.get(position).getTitle();
//                String content = articles.get(position).getLink();
//
//                articleView.getSettings().setJavaScriptEnabled(true);
//                articleView.setHorizontalScrollBarEnabled(false);
//                articleView.setWebChromeClient(new WebChromeClient());
////                articleView.loadDataWithBaseURL("", html, mimeType, encoding, "");
//                articleView.loadUrl(content);
//
//                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mContext).create();
//                alertDialog.setTitle(title);
//                alertDialog.setView(articleView);
//                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
//
//                ((TextView) alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                String link = articles.get(position).getLink();
                String title = articles.get(position).getTitle();
                Intent i = new Intent(mContext, WebViewActivity.class);
                i.putExtra("GET_LINK", link);
                i.putExtra("GET_TITLE", title);
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {

        return articles == null ? 0 : articles.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView lastBuildDate;
        ImageView image;

        public ViewHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            lastBuildDate = (TextView) itemView.findViewById(R.id.lastBuildDate);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}