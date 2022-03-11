package com.ethnicthv.redpie.guisetup;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public enum GuiComponents {
    BLANK(createGuiCom(Material.BLACK_STAINED_GLASS_PANE,"","")),
    NEXT_BUTTON(createGuiCom(Material.RED_STAINED_GLASS_PANE,"NEXT","")),
    PREVIOUS_BUTTON(createGuiCom(Material.RED_STAINED_GLASS_PANE,"BACK",""));

    public final ItemStack com;

    GuiComponents(ItemStack itemStack){
        this.com = itemStack;
    }

    public ItemStack getCom(){
        return this.com;
    }

    public static ItemStack createGuiCom(final Material material, final String name, final String... lore){
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
}
