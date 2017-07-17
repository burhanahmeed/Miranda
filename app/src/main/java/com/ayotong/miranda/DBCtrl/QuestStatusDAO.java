package com.ayotong.miranda.DBCtrl;

import com.ayotong.miranda.model.QuestStatus;

import java.util.ArrayList;

/**
 * Created by Alpha on 16/07/2017.
 */

public interface QuestStatusDAO {

    public QuestStatus insert(QuestStatus quest_status);

    public QuestStatus insert(String timestamp, int questID, int quest_flag);

    public int updateQuestStatus(QuestStatus queststatus);

    public int updateQuestStatus(String timestamp, int questID, int quest_flag);

    public ArrayList<QuestStatus> readQuestStatus();

    public ArrayList<QuestStatus> readQuestStatus(int status);

    public int delete(int quest_id);
}
