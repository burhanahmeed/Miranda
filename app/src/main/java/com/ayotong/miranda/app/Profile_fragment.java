package com.ayotong.miranda.app;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayotong.miranda.database.UserInfoDB;
import com.ayotong.miranda.R;
import com.ayotong.miranda.UpdateProfileActivity;
import com.ayotong.miranda.model.UserInfo;

public class Profile_fragment extends Fragment {
    TextView nama, umur, berat, gender;
    UserInfo user;
    UserInfoDB userdb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file

        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageButton btnUpdate = (ImageButton)v.findViewById(R.id.edit_profile_btn);
        ImageView img = (ImageView)v.findViewById(R.id.profile_image);

        nama = (TextView)v.findViewById(R.id.isi_nama);
        umur = (TextView)v.findViewById(R.id.data_umur);
        berat = (TextView)v.findViewById(R.id.data_berat);
        gender = (TextView)v.findViewById(R.id.gender);

        userdb = new UserInfoDB(getActivity().getApplicationContext());
        user = new UserInfo();
        Log.i("Action ", "userdb created");
        user = userdb.loadInfo();
        Log.i("Action ", "success loading info");
        nama.setText(user.getUsername());
        umur.setText(Integer.toString(user.getAge()));
        berat.setText(Integer.toString(user.getWeight()));
        gender.setText(user.genderToText(user.getGender()));
        Log.i("Action", "Text Set");

        Resources res = getResources(); // need this to fetch the drawable
        Drawable draw = res.getDrawable( R.drawable.ic_logo );
        img.setImageDrawable(draw);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;
    }

}
