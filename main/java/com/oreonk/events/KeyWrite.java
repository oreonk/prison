package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class KeyWrite implements Listener {
    @EventHandler
    public void keyWrite(PlayerInteractEvent event){
            ItemStack key = event.getPlayer().getInventory().getItemInMainHand();
            Prison.getPlugin(Prison.class).getConfig().set("Normal", key);
            Prison.getInstance().saveConfig();
    }
}
