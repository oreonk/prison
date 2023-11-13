package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class KeyDrop implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void keyDrop(BlockBreakEvent event) {
        if (!event.getBlock().getType().toString().contains("LEAVES")) {
            Random r = new Random();
            int percent = r.nextInt(1000);
            if (percent < 1) {
                ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Normal").getValues(true));
                Msg.send(event.getPlayer(), "&2Вы нашли ключ!");
                event.getPlayer().getInventory().addItem(key);
                //Ачивка на ключ
                if (!Prison.getInstance().KEY_ONE.contains(event.getPlayer().getUniqueId().toString())) {
                    Msg.send(event.getPlayer(), ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "И куда это вставлять? " + ChatColor.GREEN + "проверьте его в /achievements");
                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("KEY_ONE");
                    list.add(event.getPlayer().getUniqueId().toString());
                    try {
                        Prison.getInstance().getAchievementsConfig().set("KEY_ONE", list);
                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                        Prison.getInstance().KEY_ONE.add(event.getPlayer().getUniqueId().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


