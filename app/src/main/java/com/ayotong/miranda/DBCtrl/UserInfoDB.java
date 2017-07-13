package com.ayotong.miranda.DBCtrl;

import android.content.Context;

import com.ayotong.miranda.model.UserInfo;

/**
 * Created by Alpha on 13/07/2017.
 */

public class UserInfoDB extends DatabaseDAO implements UserInfoDAO {

    private Context context;

    public UserInfoDB(Context context){
        super(context, DatabaseDAO.dName, UserInfo.TABLE_CREATE, UserInfo.DATABASE_TABLE, UserInfo.DATABASE_VERSION);
        this.context = context;
    }
}
