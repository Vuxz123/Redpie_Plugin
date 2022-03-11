package com.ethnicthv.redpie.quest;

import com.ethnicthv.redpie.Redpie;
import com.ethnicthv.redpie.quest.action.Action;
import com.ethnicthv.redpie.quest.action.ActionGotoXYZ;
import com.ethnicthv.redpie.quest.reward.Reward;
import org.bukkit.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirstQuest implements Quest, Serializable {

    private final QuestID id = QuestID.FIRSTQUEST;
    private final List<Reward> rewardList = new ArrayList<>();
    private int state, lenght;
    private boolean isFinish = false;

    private List<Action> stateList = new ArrayList<>();

    public FirstQuest(){
        World w = Redpie.plugin.getServer().getWorld("world");
        this.stateList.add(new ActionGotoXYZ(100,100,120, 120, w)) ;//0
        this.lenght = stateList.size();
        this.state = 0;
    }

    @Override
    public void setState(int state){
        this.state = state;
    }

    @Override
    public int getState(){
        return this.state;
    }

    @Override
    public void nextState() {
        if(state == lenght){
            this.isFinish = true;

            return;
        }
        state ++;
    }

    @Override
    public Action getAction(){
        return stateList.get(state);
    }

    @Override
    public QuestID getQuestID(){
        return this.id;
    }

    public void addReward(Reward reward){
        rewardList.add(reward);
    }

    public List<Reward> getRewardList(){
        return this.rewardList;
    }

}

