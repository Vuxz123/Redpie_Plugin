package com.ethnicthv.redpie.quest.action;

import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;

public class ActionGotoXYZ implements Action, Serializable {

    final ActionType action = ActionType.GOTOXYZ;
    private final Location first,last;
    private final World world;

    public ActionGotoXYZ(int x1,  int z1, int x2,  int z2, World world){
        this.world = world;
        this.first = new Location(world, x1, 0, z1);
        this.last = new Location(world, x2, 0, z2);
    }

    @Override
    public ActionType getActionType() {
        return action;
    }

    public boolean isInRegion(Location location){
        double x = location.getX();
        double z = location.getZ();
        return (func(x, first.getX(), last.getX()) && func(z, first.getZ(), last.getZ())) ? true : false;
    }

    private boolean func(double x, double y, double z){
        if(y < z) {
            return (x <= z && x >= y) ? true : false;
        }else{
            return (x >= z && x <= y) ? true : false;
        }
    }



    public World getWorld(){
        return this.world;
    }
}
