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
        if (player.hasPermission("rank.shulker")) {
            multiplier = "1.5";
        } else if (player.hasPermission("rank.magma")) {
            multiplier = "1.4";
        } else if (player.hasPermission("rank.emerald")) {
            multiplier = "1.3";
        } else if (player.hasPermission("rank.diamond")) {
            multiplier = "1.2";
        } else if (player.hasPermission("rank.gold")) {
            multiplier = "1.1";
        }
        String finalMultiplier = multiplier;
        Bukkit.getScheduler().runTaskAsynchronously(TestPlug.getInstance(), () -> {
            TestPlug.getPlugin(TestPlug.class).getDatabase().createPlayer(player, finalMultiplier);
            TestPlug.getPlugin(TestPlug.class).getDatabase().updateMultiplier(player.getUniqueId(), finalMultiplier);
        });
        TestPlug.getPlugin(TestPlug.class).mltp.put(player,Double.parseDouble(finalMultiplier));
        TestPlug.getPlugin(TestPlug.class).bst.put(player,TestPlug.getPlugin(TestPlug.class).getDatabase().getBoost(player));
        //Если для игрока в хешмап нет уровня - запихнуть
        TestPlug.getPlugin(TestPlug.class).lvl.putIfAbsent(player,TestPlug.getPlugin(TestPlug.class).getDatabase().getLVL(player));
    }
}
