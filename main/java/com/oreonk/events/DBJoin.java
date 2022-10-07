package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class DBJoin implements Listener {
    @EventHandler
        public void update(PlayerJoinEvent event) {
        String multiplier = "1.0";
        Player player = event.getPlayer();
        if (player.hasPermission("rank.sponsor")){
            multiplier = "1.6";
        } else if (player.hasPermission("rank.netherite")) {
            multiplier = "1.5";
        } else if (player.hasPermission("rank.diamond")) {
            multiplier = "1.4";
        } else if (player.hasPermission("rank.emerald")) {
            multiplier = "1.3";
        } else if (player.hasPermission("rank.gold")) {
            multiplier = "1.2";
        } else if (player.hasPermission("rank.iron")) {
            multiplier = "1.1";
        } else if (player.hasPermission("rank.coal")) {
            multiplier = "1.05";
        }
        double plusMultiplier = Double.parseDouble(multiplier);
        String finalMultiplier ;
        // Множитель от стата интеллекта
        int intellect = Prison.getInstance().getDatabase().getIntellectStat(player);
        if (intellect >= 20 && intellect < 40) {
            finalMultiplier = String.valueOf(plusMultiplier + 0.1);
        } else if (intellect >= 40 && intellect < 60){
            finalMultiplier = String.valueOf(plusMultiplier + 0.15);
        } else if (intellect >= 60 && intellect < 80){
            finalMultiplier = String.valueOf(plusMultiplier + 0.2);
        } else if (intellect >= 80 && intellect < 100){
            finalMultiplier = String.valueOf(plusMultiplier + 0.25);
        } else if (intellect >= 100){
            finalMultiplier = String.valueOf(plusMultiplier + 0.3);
        } else {
            finalMultiplier = multiplier;
        }
        Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
            Prison.getPlugin(Prison.class).getDatabase().createPlayer(player, finalMultiplier);
            Prison.getPlugin(Prison.class).getDatabase().updateMultiplier(player.getUniqueId(), finalMultiplier);
            Prison.getPlugin(Prison.class).lvl.put(player, Prison.getPlugin(Prison.class).getDatabase().getLVL(player));
            Integer religion = Prison.getInstance().getDatabase().getReligionStat(player);
            Prison.getInstance().religion.put(player, religion);
            Prison.getPlugin(Prison.class).bst.put(player, Prison.getPlugin(Prison.class).getDatabase().getBoost(player));
            Prison.getPlugin(Prison.class).faction.put(player, Prison.getPlugin(Prison.class).getDatabase().getFaction(player));
        });
        Prison.getPlugin(Prison.class).mltp.put(player, Double.parseDouble(finalMultiplier));
        if (!Prison.getInstance().boosterBlocks.isEmpty()) {
            String playerName = null;
            int timer = 0;
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                if (Bukkit.getPlayer(entry.getKey()) != null) {
                    playerName = Bukkit.getPlayer(entry.getKey()).getName();
                } else {
                    playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                }
            }
            BossBar bossBar = Bukkit.createBossBar("Активен бустер блоков игрока " + playerName + ". Осталось " + timer + " минут", BarColor.BLUE, BarStyle.SOLID);
            bossBar.setVisible(true);
            bossBar.addPlayer(player);
            new BukkitRunnable() {
                public void run() {
                    for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                        int time;
                        time = entry.getValue();
                        String player;
                        if (Bukkit.getPlayer(entry.getKey()) != null) {
                            player = Bukkit.getPlayer(entry.getKey()).getName();
                        } else {
                            player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                        }
                        bossBar.setTitle("Активен бустер блоков игрока " + player + ". Осталось " + time + " минут");
                        if (time <= 0) {
                            this.cancel();
                            bossBar.setVisible(false);
                        }
                    }
                }
            }.runTaskTimer(Prison.getInstance(), 0, 1200);
        }
        if (!Prison.getInstance().booster.isEmpty()) {
            String playerName = null;
            int timer = 0;
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                if (Bukkit.getPlayer(entry.getKey()) != null) {
                    playerName = Bukkit.getPlayer(entry.getKey()).getName();
                } else {
                    playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                }
            }
            BossBar bossBar = Bukkit.createBossBar("Активен бустер монет игрока " + playerName + ". Осталось " + timer + " минут", BarColor.BLUE, BarStyle.SOLID);
            bossBar.setVisible(true);
            new BukkitRunnable() {
                public void run() {
                    for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                        int time;
                        time = entry.getValue();
                        String player;
                        if (Bukkit.getPlayer(entry.getKey()) != null) {
                            player = Bukkit.getPlayer(entry.getKey()).getName();
                        } else {
                            player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                        }
                        bossBar.setTitle("Активен бустер монет игрока " + player + ". Осталось " + time + " минут");
                        bossBar.addPlayer(event.getPlayer());
                        if (time <= 0) {
                            this.cancel();
                            bossBar.setVisible(false);
                        }
                    }
                }
            }.runTaskTimer(Prison.getInstance(), 0, 1200);
        }
        if (!Prison.getInstance().getDatabase().topExists(player.getUniqueId())){
            Prison.getInstance().getDatabase().insertFirstTop(player.getUniqueId());
        }
        if (!Prison.getInstance().getDatabase().topLvlExists(player.getUniqueId())){
            Prison.getInstance().getDatabase().insertFirstLvlTop(player.getUniqueId());
        }
    }
}
