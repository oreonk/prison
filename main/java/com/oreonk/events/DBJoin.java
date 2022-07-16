package com.oreonk.events;

import com.oreonk.TestPlug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DBJoin implements Listener {
    @EventHandler
        public void update(PlayerJoinEvent event) {
        String multiplier = "1.0";
        Player player = event.getPlayer();
        if (player.hasPermission("rank.sponsor")) {
            multiplier = "1.6";
        } else if (player.hasPermission("rank.beequeen")) {
            multiplier = "1.5";
        } else if (player.hasPermission("rank.honeybeeplus")) {
            multiplier = "1.4";
        } else if (player.hasPermission("rank.honeybee")) {
            multiplier = "1.3";
        } else if (player.hasPermission("rank.beeplus")) {
            multiplier = "1.2";
        } else if (player.hasPermission("rank.bee")) {
            multiplier = "1.1";
        }
        String finalMultiplier = multiplier;
        Bukkit.getScheduler().runTaskAsynchronously(TestPlug.getInstance(), () -> {
            TestPlug.getPlugin(TestPlug.class).getDatabase().createPlayer(player, finalMultiplier);
            TestPlug.getPlugin(TestPlug.class).getDatabase().updateMultiplier(player.getUniqueId(), finalMultiplier);
            TestPlug.getPlugin(TestPlug.class).lvl.put(player,TestPlug.getPlugin(TestPlug.class).getDatabase().getLVL(player));
            TestPlug.getPlugin(TestPlug.class).bst.put(player,TestPlug.getPlugin(TestPlug.class).getDatabase().getBoost(player));
        });
        TestPlug.getPlugin(TestPlug.class).mltp.put(player,Double.parseDouble(finalMultiplier));
        //Если для игрока в хешмап нет уровня - запихнуть
    }
}
