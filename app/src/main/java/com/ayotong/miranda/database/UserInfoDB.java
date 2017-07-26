package com.ayotong.miranda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
        cv.put(UserInfo.COL_ISPREGNANT, boolsql(userinfo.ispregnant()));
        cv.put(UserInfo.COL_STARTNAP, userinfo.getStartnap());
        cv.put(UserInfo.COL_STARTSLEEP, userinfo.getStartsleep());
        Log.i("user", "ID: "+ID+"\nusername: "+userinfo.getUsername()+"\nage"+userinfo.getAge());
        return new UserInfo((int)super.insert(UserInfo.DATABASE_TABLE, cv), userinfo.getUsername(), userinfo.getAge(),
                userinfo.getGender(),userinfo.getWeight(), userinfo.getHeight(), userinfo.ispregnant(), userinfo.getStartnap(),
                userinfo.getStartsleep());
    }

    public int updateInfo(UserInfo userinfo){
        ContentValues cv = new ContentValues();
        cv.put(UserInfo.COL_ID, userinfo.getId());
        cv.put(UserInfo.COL_UNAME, userinfo.getUsername());
        cv.put(UserInfo.COL_AGE, userinfo.getAge());
        cv.put(UserInfo.COL_GENDER, userinfo.getGender());
        cv.put(UserInfo.COL_WEIGHT, userinfo.getWeight());
        cv.put(UserInfo.COL_HEIGHT, userinfo.getHeight());
        cv.put(UserInfo.COL_ISPREGNANT, boolsql(userinfo.ispregnant()));
        cv.put(UserInfo.COL_STARTNAP, userinfo.getStartnap());
        cv.put(UserInfo.COL_STARTSLEEP, userinfo.getStartsleep());
        return super.update(UserInfo.DATABASE_TABLE, UserInfo.COL_ID + " = " + userinfo.getId(), cv);
    }

    public int updateInfo(int id, String username, int age, int gender, int weight, int height, boolean ispregnant,
                          String startnap, String startsleep){
        ContentValues cv = new ContentValues();
        cv.put(UserInfo.COL_ID, id);
        cv.put(UserInfo.COL_UNAME, username);
        cv.put(UserInfo.COL_AGE, age);
        cv.put(UserInfo.COL_GENDER, gender);
        cv.put(UserInfo.COL_WEIGHT, weight);
        cv.put(UserInfo.COL_HEIGHT, height);
        cv.put(UserInfo.COL_ISPREGNANT, boolsql(ispregnant));
        cv.put(UserInfo.COL_STARTNAP, startnap);
        cv.put(UserInfo.COL_STARTSLEEP, startsleep);
        return super.update(UserInfo.DATABASE_TABLE, UserInfo.COL_ID + " = " + id, cv);
    }

    public int boolsql (boolean value){
        if(value)
            return 1;
        else
            return 0;
    }

    public boolean boolsql (int value){
        if(value==1)
            return true;
        else
            return false;
    }

    public UserInfo loadInfo(){
        String[] columns = new String[]{UserInfo.COL_ID , UserInfo.COL_UNAME, UserInfo.COL_AGE, UserInfo.COL_GENDER,
                UserInfo.COL_WEIGHT, UserInfo.COL_HEIGHT, UserInfo.COL_ISPREGNANT, UserInfo.COL_STARTNAP,
                UserInfo.COL_STARTSLEEP };
        Cursor cursor = super.get(UserInfo.DATABASE_TABLE, columns , UserInfo.COL_ID + " = 0 ");
        Log.i("UserInfoDB: ", "cursor get data from db");
        UserInfo user = new UserInfo();
        if(cursor.getCount() != 0) {
            Log.i("UserInfoDB: ", "cursor not null");
            cursor.moveToFirst();
            Log.i("UserInfoDB: ", "cursor go to first");
            user.setId(cursor.getInt(cursor.getColumnIndex(UserInfo.COL_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(UserInfo.COL_UNAME)));
            user.setAge(cursor.getInt(cursor.getColumnIndex(UserInfo.COL_AGE)));
            user.setGender(cursor.getInt(cursor.getColumnIndex(UserInfo.COL_GENDER)));
            user.setWeight(cursor.getInt(cursor.getColumnIndex(UserInfo.COL_WEIGHT)));
            user.setHeight(cursor.getInt(cursor.getColumnIndex(UserInfo.COL_HEIGHT)));
            user.setIspregnant(boolsql(cursor.getInt(cursor.getColumnIndex(UserInfo.COL_ISPREGNANT))));
            user.setStartnap(cursor.getString(cursor.getColumnIndex(UserInfo.COL_STARTNAP)));
            user.setStartsleep(cursor.getString(cursor.getColumnIndex(UserInfo.COL_STARTSLEEP)));
            Log.i("UserInfoDB: ","user id: "+user.getId()+ "\n username: "+user.getUsername()+ "\n age: " +user.getAge()+
                "\ngender: " +user.genderToText(user.getGender()));
        }else{
            Log.e("error", "Cursor are null");
        }
        cursor.close();
        return user;
    }
}