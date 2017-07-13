package com.ayotong.miranda.DBCtrl;

import com.ayotong.miranda.model.Quest;

/**
 * Created by Alpha on 11/07/2017.
 */

public interface QuestDAO {

    public Quest insert(int questID, Quest quest);

    public int updateTime(int questID, Quest quest);

    public int updateQuestDesc(int questID, Quest quest);
}
