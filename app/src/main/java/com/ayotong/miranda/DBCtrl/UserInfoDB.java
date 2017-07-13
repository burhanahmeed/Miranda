package com.ayotong.miranda.DBCtrl;

import android.content.Context;

/**
 * Created by Alpha on 13/07/2017.
 */

public class UserInfoDB extends DatabaseDAO implements UserInfoDAO {

    private Context context;

    public UserInfoDB(Context context){
        super(context, DatabaseDAO.dName, Quest.TABLE_CREATE, Quest.DATABASE_TABLE, Quest.DATABASE_VERSION);
        this.context = context;
    }
}
