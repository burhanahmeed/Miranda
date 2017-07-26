package com.ayotong.miranda.database;

import com.ayotong.miranda.model.QuestStatus;

import java.util.ArrayList;

/**
 * Created by Alpha on 16/07/2017.
 */

public interface QuestStatusDAO {

    public QuestStatus insert(QuestStatus quest_status);

    public QuestStatus insert(String timestamp, int questID, String quest_time, int quest_exp, int quest_flag);

    public int updateQuestStatus(QuestStatus queststatus);

    public int updateQuestStatus(String timestamp, int quest_id, String quest_time, int quest_exp, int quest_flag);

    public ArrayList<QuestStatus> readAllStatus();

    public ArrayList<QuestStatus> readAllQuestByStatus(int status);

    public int deleteQuest(int quest_id);

    public void refreshTable();
}
