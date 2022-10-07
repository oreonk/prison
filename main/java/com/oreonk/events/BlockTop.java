package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;

public class BlockTop implements Listener {
    public HashMap<Player, Integer> blockCount = new HashMap<>() {};
    @EventHandler
    public void blockTop(BlockBreakEvent event){
        if (!blockCount.containsKey(event.getPlayer())){
            blockCount.put(event.getPlayer(), 1);
        } else if (blockCount.containsKey(event.getPlayer())){
            int count = blockCount.get(event.getPlayer());
            count ++;
            blockCount.replace(event.getPlayer(), count);
            if (count == 10){
                Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    int amount = Prison.getInstance().getDatabase().getBlockTopValue(event.getPlayer().getUniqueId());
                    Prison.getInstance().getDatabase().updateBlockTop(event.getPlayer().getUniqueId(), amount + 10);
                });
                blockCount.remove(event.getPlayer());
            }
        }
    }
}
