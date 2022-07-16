package com.oreonk.events;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Drop implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void itemDrop(PlayerDeathEvent event){
        event.setKeepInventory(true);
        Player player = event.getEntity();
        ArrayList<ItemStack> delete = new ArrayList<>();
        List<ItemStack> drops = event.getDrops();
        ItemMeta dumpMeta = new ItemStack(Material.DIRT).getItemMeta();
        int size = drops.size();
        for (int c = 0; c < size;){
            if (size == 0){
                break;
            }
            ItemMeta mainMeta = drops.get(c).getItemMeta();
            String name = drops.get(c).getType().toString();
            if (name.endsWith("_HELMET")||name.endsWith("_CHESTPLATE")||name.endsWith("_LEGGINGS")||name.endsWith("_BOOTS")||name.endsWith("_SWORD")||name.endsWith("_PICKAXE")||name.endsWith("_AXE")||name.equals("SHIELD")||name.equals("FISHING_ROD")||name.equals("NETHER_STAR")||name.equals("SHEARS")){
                drops.remove(c);
            } else if (mainMeta.toString().equals(dumpMeta.toString())){
                player.getInventory().remove(event.getDrops().get(c));
            }
            if (drops.size() < size) size--;
            else c++;
        }
    }
}

