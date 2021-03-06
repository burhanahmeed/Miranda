package com.ayotong.miranda.database;

import com.ayotong.miranda.model.UserInfo;

/**
 * Created by Alpha on 11/07/2017.
 */

public interface UserInfoDAO {

    public UserInfo insert(int ID, UserInfo userinfo);

    public int updateInfo(UserInfo userinfo);

    public int updateInfo(int id, String username, int age, int gender, int weight, int height, boolean ispregnant, String startnap, String startsleep);

    public int boolsql (boolean value);

    public boolean boolsql (int value);

    public UserInfo loadInfo();
}