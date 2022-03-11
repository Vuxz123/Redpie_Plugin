package com.ethnicthv.redpie.quest;

public enum QuestID {
    FIRSTQUEST;

    public Quest Create(){
        switch (this){
            case FIRSTQUEST:
                return new FirstQuest();
        }
        return null;
    }
}
