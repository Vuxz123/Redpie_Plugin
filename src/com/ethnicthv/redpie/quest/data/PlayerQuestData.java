package com.ethnicthv.redpie.quest.data;

import com.ethnicthv.redpie.Redpie;
import com.ethnicthv.redpie.quest.FirstQuest;
import com.ethnicthv.redpie.quest.Quest;
import com.ethnicthv.redpie.quest.QuestID;
import com.ethnicthv.redpie.quest.action.ActionType;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.Serializable;
import java.util.*;

public class PlayerQuestData implements Serializable {

    Plugin plugin = Redpie.plugin;

    Map<UUID, List<Quest>> data;

    public PlayerQuestData(){
        data = new HashMap<>();
    }

    public void setup(){

    }

    public void save(){

    }

    public void addPlayer(Player p) {
        if(hasPlayer(p)){return;}
        data.getOrDefault(p.getUniqueId(), new ArrayList<>());
    }

    public void addQuestToPlayer(Player p, QuestID quest){
        List<Quest> list;
        if(data.get(p.getUniqueId()) == null){
            data.put(p.getUniqueId(), new ArrayList<>());
        }
        try {
            list = data.get(p.getUniqueId());
            list.add(quest.Create());
            data.put(p.getUniqueId(),list);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public List<Quest> getPlayer(Player p){
        return data.get(p.getUniqueId());
    }

    public boolean hasPlayer(Player p){
        return data.containsKey(p.getUniqueId());
    }

    public boolean hasPlayer(Player p, QuestID id){
        if(hasPlayer(p)){
            List<Quest> test = this.data.get(p.getUniqueId());
            for(Quest quest: test){
                if(quest.getQuestID() == id){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPlayer(Player p, ActionType action){
        if(data.containsKey(p.getUniqueId())){
            List<Quest> test = this.data.get(p.getUniqueId());
            for(Quest quest: test){
                if(quest.getAction().getActionType() == action){
                    return true;
                }
            }
        }
        return false;
    }

    public List<Quest> getPlayerHasAction(Player p,ActionType action){
        List<Quest> test;
        List<Quest> result;
        try{
            test = this.data.get(p.getUniqueId());
            result = new ArrayList<>();
            for(Quest quest: test){
                if(quest.getAction().getActionType() == action){
                    result.add(quest);
                }
            }
        }catch (NullPointerException exception){
            result = new ArrayList<>();
            exception.fillInStackTrace();
        }
        return result;
    }

}
