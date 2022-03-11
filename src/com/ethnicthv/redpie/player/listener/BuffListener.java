package com.ethnicthv.redpie.player.listener;

import com.ethnicthv.redpie.player.data.RPPlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public class BuffListener implements Listener {

    @EventHandler
    public void speedModifier(PlayerMoveEvent event){
        double AGI = (double) RPPlayerDataManager.getPlayerdata().getPlayer(event.getPlayer().getUniqueId()).getMeta().getAGI();
        event.getPlayer().sendMessage("" + ((float)AGI/100 + 1)/10);
        event.getPlayer().setWalkSpeed((float) (AGI/100 + 1)/10);

    }

}
