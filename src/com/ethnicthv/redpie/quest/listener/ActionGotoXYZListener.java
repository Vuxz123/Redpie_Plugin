package com.ethnicthv.redpie.quest.listener;

import com.ethnicthv.redpie.Redpie;
import com.ethnicthv.redpie.quest.Quest;
import com.ethnicthv.redpie.quest.action.ActionGotoXYZ;
import com.ethnicthv.redpie.quest.action.ActionType;
import com.ethnicthv.redpie.quest.data.PlayerQuestData;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class ActionGotoXYZListener implements Listener {

    PlayerQuestData data = Redpie.plugin.getQuestdata();

    private final ActionType type = ActionType.GOTOXYZ;

    @EventHandler
    public void onGotoXYZ(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(data.hasPlayer(p, type)) {
            Location location = e.getTo();
            World w = p.getWorld();
            List<Quest> list = data.getPlayerHasAction(p, type);
            for(Quest quest: list){
                ActionGotoXYZ action = (ActionGotoXYZ) quest.getAction();
                if(action.getWorld() == w){
                    p.sendMessage("checkworldpass");
                    /*if(location){
                        p.sendMessage("checklocationpass");
                        p.sendMessage("Chúc Mừng!!");
                        quest.nextState();
                    }*/
                }
            }
        }
    }
}
