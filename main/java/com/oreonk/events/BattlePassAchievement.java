package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;
import java.util.List;

public class BattlePassAchievement implements Listener{
    @EventHandler
    public void clickEvent (PlayerInteractEvent event){
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
                Player player = event.getPlayer();
                if (!Prison.getInstance().BATTLEPASS_ONE.contains(player.getUniqueId().toString()) && Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%battlepass_completed_quests%")) > 0) {
                    Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Пай-мальчик " + ChatColor.GREEN + "проверьте его в /achievements");
                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("BATTLEPASS_ONE");
                    list.add(player.getUniqueId().toString());
                    try {
                        Prison.getInstance().getAchievementsConfig().set("BATTLEPASS_ONE", list);
                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                        Prison.getInstance().BATTLEPASS_ONE.add(player.getUniqueId().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
