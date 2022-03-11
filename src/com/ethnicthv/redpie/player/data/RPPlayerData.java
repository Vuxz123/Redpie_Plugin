package com.ethnicthv.redpie.player.data;

import com.ethnicthv.redpie.player.RPPlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class RPPlayerData{

    private Map<UUID, RPPlayer> data;

    public RPPlayerData(){
        data = new HashMap<>();
    }

    public void addPlayer(UUID uuid,RPPlayer player){
        this.data.put(uuid,player);
        System.out.println("Add 1 player");
    }

    public boolean hasPlayer(UUID uuid){
        return this.data.containsKey(uuid);
    }

    public RPPlayer getPlayer(UUID uuid){
        return this.data.get(uuid);
    }

    public boolean removePlayer(UUID uuid){
        return this.data.remove(uuid,getPlayer(uuid));
    }


    @Override
    public String toString() {
        return "RPPlayerData{" +
                "data=" + data.toString() +
                '}';
    }
}
