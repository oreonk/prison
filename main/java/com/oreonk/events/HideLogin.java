package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HideLogin implements Listener {
    @EventHandler
    public void onLogin (PlayerJoinEvent event) {
        if (!Prison.getInstance().hide.isEmpty()) {
            Prison.getInstance().hide.keySet().forEach(hider -> {
                hider.hidePlayer(Prison.getInstance(), event.getPlayer());
            });
        }
    }
}
