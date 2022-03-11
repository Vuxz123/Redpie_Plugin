package com.ethnicthv.redpie.player.gui;

import com.ethnicthv.redpie.guisetup.GuiComponents;
import net.minecraft.server.v1_16_R3.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;

public class RPPlayerStatusGUI implements Listener {
    private final ItemStack Button1 = GuiComponents.createGuiCom(Material.YELLOW_STAINED_GLASS_PANE,"Give Diamond","");
    private Inventory gui;

    public RPPlayerStatusGUI(){

        gui = Bukkit.createInventory(null, 27, "Status");

        init();

    }

    private void init(){
        ItemStack[] slot = new ItemStack[27];
        for(int i = 0; i < 9; i ++){
            slot[i] = GuiComponents.BLANK.getCom();
            slot[26-i] = GuiComponents.BLANK.getCom();
        }
        slot[9] = GuiComponents.BLANK.getCom();
        slot[10] = this.Button1;
        slot[18] = GuiComponents.BLANK.getCom();
        gui.addItem(slot);
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != gui) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        if(clickedItem == this.Button1) this.giveDiamondAction(p);

        // Using slots click is a best option for your inventory click's
        p.sendMessage("You clicked at slot " + e.getRawSlot());
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(gui)) {
            e.setCancelled(true);
        }
    }

    private void giveDiamondAction(HumanEntity p){
        p.getInventory().addItem(new ItemStack(Material.DIAMOND, 10));
    }
}
