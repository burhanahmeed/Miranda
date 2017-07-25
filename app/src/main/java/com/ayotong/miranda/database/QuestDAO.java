package com.ayotong.miranda.DBCtrl;

import com.ayotong.miranda.model.Quest;

import java.util.ArrayList;

/**
 * Created by Alpha on 11/07/2017.
 */

public interface QuestDAO {

    public Quest insert(Quest quest);

    public Quest insert(int questID, Quest quest);

    public ArrayList<Quest> readQuest();

    public void ClearQuest();
}
