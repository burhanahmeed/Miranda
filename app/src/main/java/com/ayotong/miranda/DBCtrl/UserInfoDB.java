package com.ayotong.miranda.DBCtrl;

import android.content.ContentValues;
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

    public UserInfo insert(int ID, UserInfo userinfo){
        ContentValues cv = new ContentValues();
        cv.put(UserInfo.COL_ID, ID);
        cv.put(UserInfo.COL_UNAME, userinfo.getUsername());
        cv.put(UserInfo.COL_AGE, userinfo.getAge());
        cv.put(UserInfo.COL_GENDER, userinfo.getGender());
        cv.put(UserInfo.COL_WEIGHT, userinfo.getWeight());
        cv.put(UserInfo.COL_HEIGHT, userinfo.getHeight());
        cv.put(UserInfo.COL_ISPREGNANT, userinfo.ispregnant());
        cv.put(UserInfo.COL_ISNAP, userinfo.isnap());
        cv.put(UserInfo.COL_STARTNAP, userinfo.getStartnap());
        cv.put(UserInfo.COL_STARTSLEEP, userinfo.getStartsleep());

        return new UserInfo((int)super.insert(UserInfo.DATABASE_TABLE, cv), userinfo.getUsername(), userinfo.getAge(), userinfo.getGender(),
                userinfo.getWeight(), userinfo.getHeight(), userinfo.ispregnant(), userinfo.isnap(), userinfo.getStartnap(), userinfo.getStartsleep());
    }
}
