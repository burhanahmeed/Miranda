package com.ayotong.miranda.DBCtrl;

import com.ayotong.miranda.model.ExpLog;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Alpha on 16/07/2017.
 */

public interface ExpLogDAO {

    public ExpLog insert(ExpLog expLog);

    public ExpLog insert(String timestamp, int quest_id, int exp_gain);

    public ArrayList<ExpLog> readLog();

    public ArrayList<ExpLog> readLog(String timestamp);

    public int clearLog();

    public int clearLog(String timestamp);
}
