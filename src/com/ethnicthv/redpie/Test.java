package com.ethnicthv.redpie;

import com.ethnicthv.redpie.player.RPPlayer;
import com.ethnicthv.redpie.player.data.RPPlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Test implements Listener, CommandExecutor {

    @EventHandler
    public void testQuestSystem(){

    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player){


            Player p = (Player) commandSender;
            UUID uuid = p.getUniqueId();

            int A = 20;

            A = Integer.valueOf(strings[0]);
            //Redpie.plugin.getQuestdata().addQuestToPlayer(p, QuestID.FIRSTQUEST);

            p.sendMessage("" + A);

            RPPlayer.RPPlayerMeta meta = RPPlayerDataManager.getPlayerdata().getPlayer(p.getUniqueId()).getMeta();

            int B = meta.getAGI();

            meta.setAGI(B+A);

            RPPlayerDataManager.getPlayerdata().getPlayer(p.getUniqueId()).setMeta(meta);

            p.sendMessage("" +RPPlayerDataManager.getPlayerdata().getPlayer(p.getUniqueId()).getMeta().getAGI());

            RPPlayerDataManager.save(uuid,false);

            /*for(Quest quest : Redpie.plugin.getQuestdata().getPlayer(p)) {
                p.sendMessage(quest.getQuestID().toString());
                p.sendMessage(((ActionGotoXYZ)quest.getAction()).getWorld().getName());
                boolean a = p.getWorld() == ((ActionGotoXYZ)quest.getAction()).getWorld();
                String b = "" + a;
                p.sendMessage(b);
            }*/

            return true;

        }

        return false;
    }
}
