package com.oreonk.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class CraftCancel implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void blockBreak(InventoryClickEvent event) {
        if(event.getClickedInventory().getType().equals(InventoryType.CRAFTING)){
            event.setCancelled(true);
        }
    }
}
