package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class PlayerDamageEvent implements Listener {
    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player & event.getEntity() instanceof Player){
            Player damager = (Player) event.getDamager();
            Player damaged = (Player) event.getEntity();
            if (Objects.equals(Prison.getInstance().faction.get(damaged), Prison.getInstance().faction.get(damager))){
                event.setCancelled(true);
            }
            if (Prison.getInstance().tpTimer.containsKey(damaged) || Prison.getInstance().tpTimer.containsKey(damager)){
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void teleportCancel(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
            if (Prison.getInstance().timer.containsKey((Player)event.getEntity()) || Prison.getInstance().timer.containsKey((Player) event.getDamager())){
                Prison.getInstance().timer.remove((Player) event.getEntity());
                Prison.getInstance().timer.remove((Player) event.getDamager());
                Prison.getInstance().pvpTimer.put((Player) event.getEntity(), 5);
                Prison.getInstance().pvpTimer.put((Player) event.getDamager(), 5);
                Msg.send(event.getDamager(), ChatColor.RED + "Телепортация отменена!");
                Msg.send(event.getEntity(), ChatColor.RED + "Телепортация отменена!");
                new BukkitRunnable() {
                    public void run() {
                        Prison.getInstance().pvpTimer.remove((Player) event.getEntity());
                        Prison.getInstance().pvpTimer.remove((Player) event.getDamager());
                    }
                }.runTaskLater(Prison.getInstance(), 100);
            }
        }
    }
}
