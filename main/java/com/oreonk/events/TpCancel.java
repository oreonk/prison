package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TpCancel implements Listener {
    @EventHandler
    public void tpCancel(PlayerTeleportEvent event){
        if (Prison.getInstance().pvpTimer.containsKey(event.getPlayer())){
            event.setCancelled(true);
        } else {
            Prison.getInstance().tpTimer.put(event.getPlayer(), 3);
            new BukkitRunnable() {
                public void run() {
                    int count = Prison.getInstance().tpTimer.get(event.getPlayer());
                    count --;
                    Prison.getInstance().tpTimer.replace(event.getPlayer(), count);
                    if (count==0){
                        Prison.getInstance().tpTimer.remove(event.getPlayer());
                        this.cancel();
                    }
            }
        }.runTaskTimer(Prison.getInstance(), 20, 20);
        }

    }
}
