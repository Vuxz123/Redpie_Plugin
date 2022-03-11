package com.ethnicthv.redpie;

import com.ethnicthv.redpie.command.Status;
import com.ethnicthv.redpie.player.data.LoadPlayerDataListener;
import com.ethnicthv.redpie.player.data.RPPlayerDataManager;
import com.ethnicthv.redpie.player.gui.RPPlayerStatusGUI;
import com.ethnicthv.redpie.player.listener.BuffListener;
import com.ethnicthv.redpie.quest.data.PlayerQuestData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Redpie extends JavaPlugin {

    public static Redpie plugin;

    private static PlayerQuestData questdata;

    private static RPPlayerStatusGUI statusGUI;

    private Logger log;

    @Override
    public void onEnable() {
        log = this.getLogger();
        super.onEnable();
        plugin = this;

        statusGUI = new RPPlayerStatusGUI();

        //Load Data

        RPPlayerDataManager.setup(this);
        log.log("Load Player Data:");

        log.log("50%");
        questdata = new PlayerQuestData();

        log.log("100%");

        //Listener
        //getServer().getPluginManager().registerEvents(new ActionGotoXYZListener(), this);
        getServer().getPluginManager().registerEvents(new LoadPlayerDataListener(), this);
        //getServer().getPluginManager().registerEvents(new BuffListener(), this);
        getServer().getPluginManager().registerEvents( statusGUI, this);

        //Command
        getCommand("status").setExecutor(new Status());

        //test
        log.log("Test Are Loaded!");
        test();
    }

    @Override
    public void onDisable() {
        super.onDisable();


    }

    public PlayerQuestData getQuestdata(){
        return questdata;
    }

    public RPPlayerStatusGUI getStatusGUI(){
        return statusGUI;
    }

    void test(){
        getCommand("test").setExecutor(new Test());
        getCommand("testload").setExecutor(new TestLoad());
        getCommand("testforce").setExecutor(new TestForce());
        getCommand("testcommand").setExecutor(new TestCommand());
    }
}
