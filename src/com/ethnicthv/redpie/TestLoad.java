package com.ethnicthv.redpie;

import com.ethnicthv.redpie.player.data.RPPlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestLoad implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player){
            Player player = ((Player) commandSender);
            player.sendMessage(RPPlayerDataManager.getPlayerdata().toString());
            player.sendMessage(RPPlayerDataManager.getPlayerdata().getPlayer(player.getUniqueId()).toString());
            return true;
        }
        return false;
    }
}
