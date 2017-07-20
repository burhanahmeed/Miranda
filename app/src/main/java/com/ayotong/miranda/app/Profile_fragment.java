package com.ayotong.miranda.app;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayotong.miranda.R;
import com.ayotong.miranda.UpdateProfileActivity;

public class Profile_fragment extends Fragment {
    TextView nama, umur, berat, gender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageButton btnUpdate = (ImageButton)v.findViewById(R.id.edit_profile_btn);

        nama = (TextView)v.findViewById(R.id.isi_nama);
        umur = (TextView)v.findViewById(R.id.data_umur);
        berat = (TextView)v.findViewById(R.id.data_berat);
        gender = (TextView)v.findViewById(R.id.gender);

        nama.setText("TEHHU");
        umur.setText("20");
        berat.setText("60");
        gender.setText("Lanang");
        ImageView img = (ImageView)v.findViewById(R.id.profile_image);
        Resources res = getResources(); // need this to fetch the drawable
        Drawable draw = res.getDrawable( R.drawable.ic_logo );
        img.setImageDrawable(draw);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
