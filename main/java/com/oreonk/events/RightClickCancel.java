package com.oreonk.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickCancel implements Listener {
    @EventHandler
    public void cancel(PlayerInteractEvent event){
        if (event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("SHOVEL")||event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("AXE")||event.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("SHOVEL")||event.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("AXE")){
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                event.setCancelled(true);
            }
        }
    }
}
