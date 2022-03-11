package com.ethnicthv.redpie;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class TestForce implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player) commandSender;
        Vector x = p.getLocation().getDirection();
        Vector y = new Vector(0,1,0);
        Vector z = x.crossProduct(y);
        String a = strings[0];
        switch (a){
            case "side":
                p.setVelocity(z.normalize().multiply(5));
            case "up":
                p.setVelocity(y.normalize().multiply(5));
            case "forward":
                p.setVelocity(x.normalize().multiply(5));
        }

        return true;
    }
}
