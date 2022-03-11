package com.ethnicthv.redpie;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player) commandSender;
        UUID uuid = p.getUniqueId();
        p.sendMessage(p.getLocation().getDirection().toString());
        p.sendMessage(p.getVelocity().toString());
        p.sendMessage("" + p.getVelocity().length());
        return true;

    }
}
