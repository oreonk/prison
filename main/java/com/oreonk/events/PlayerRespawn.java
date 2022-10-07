package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawn implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void playerRespawnEvent(PlayerRespawnEvent event ){
        new BukkitRunnable() {
            public void run() {
                PotionEffect effect = new PotionEffect(PotionEffectType.WEAKNESS, 30, 2);
                event.getPlayer().addPotionEffect(effect);
            }
        }.runTaskLater(Prison.getInstance(),40);
    }
}
