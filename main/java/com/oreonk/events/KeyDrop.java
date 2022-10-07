package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class KeyDrop implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void keyDrop(BlockBreakEvent event) {
        Random r = new Random();
        int percent = r.nextInt(1000);
        if (percent < 1){
            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Normal").getValues(true));
            Msg.send(event.getPlayer(), "&2Вы нашли ключ!");
            event.getPlayer().getInventory().addItem(key);
        }
    }
}


