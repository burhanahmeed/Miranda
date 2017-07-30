package com.ayotong.miranda.database;

import android.database.Cursor;

import com.ayotong.miranda.model.ExpLog;

import java.util.ArrayList;

/**
 * Created by Alpha on 16/07/2017.
 */

public interface ExpLogDAO {

    public ExpLog insert(ExpLog expLog);

    public ExpLog insert(String timestamp, int mont, int exp_gain);

    public ArrayList<ExpLog> readLog();

    public ArrayList<ExpLog> readLogByMonth(int month);

    public Cursor SumOfXP();

    public int deleteLog();

    public int deleteLogByMonth(int month);

    public void closeDB();
}
