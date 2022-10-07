package com.oreonk.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AxeDamageCancel implements Listener {
    @EventHandler
    public void axeDamageCancel(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand().getType().toString().contains("AXE") || player.getInventory().getItemInOffHand().getType().toString().contains("AXE")){
                event.setCancelled(true);
            }
        }
    }
}
