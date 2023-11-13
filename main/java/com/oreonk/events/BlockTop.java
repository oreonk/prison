package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

                //Для ачивки на 300к блоков
                if (Prison.getInstance().SWEAT_ONE_COUNT.containsKey(event.getPlayer())){
                    int achievementAmount = Prison.getInstance().SWEAT_ONE_COUNT.get(event.getPlayer());
                    Prison.getInstance().SWEAT_ONE_COUNT.replace(event.getPlayer(), achievementAmount+10);

                    if ((achievementAmount+10) == 300000){
                        Player player = event.getPlayer();
                        Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Отдых? Это кто? " + ChatColor.GREEN + "проверьте его в /achievements");
                        Bukkit.broadcastMessage(ChatColor.GOLD + "Игрок " + ChatColor.WHITE + player.getName() + ChatColor.GOLD + " получил достижение, выкопав 300 000 блоков за сутки!");
                        List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("SWEAT_ONE");
                        list.add(player.getUniqueId().toString());
                        try {
                            Prison.getInstance().getAchievementsConfig().set("SWEAT_ONE", list);
                            Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            Prison.getInstance().SWEAT_ONE.add(player.getUniqueId().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                        Prison.getInstance().SWEAT_ONE_COUNT.put(event.getPlayer(), 0);
                    }

                blockCount.remove(event.getPlayer());
            }
        }
    }
}
