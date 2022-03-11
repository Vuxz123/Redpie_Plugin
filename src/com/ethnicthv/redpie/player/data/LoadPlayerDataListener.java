package com.ethnicthv.redpie.player.data;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LoadPlayerDataListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin (AsyncPlayerPreLoginEvent event){
        UUID uuid = event.getUniqueId();
        RPPlayerDataManager.load(uuid);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeave (PlayerQuitEvent event) {
        if(event.getPlayer() instanceof Player){
            Player player = (Player) event.getPlayer();
            UUID uuid = player.getUniqueId();
            RPPlayerDataManager.save(uuid,true);
        }
    }
}
