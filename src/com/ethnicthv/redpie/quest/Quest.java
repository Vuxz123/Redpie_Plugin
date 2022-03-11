package com.ethnicthv.redpie.quest;

import com.ethnicthv.redpie.quest.action.Action;
import com.ethnicthv.redpie.quest.reward.Reward;

import java.util.ArrayList;
import java.util.List;

public interface Quest {

    void setState(int state);
    int getState();
    void nextState();
    Action getAction();
    QuestID getQuestID();

}
