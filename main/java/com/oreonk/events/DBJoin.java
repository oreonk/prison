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

import java.text.DecimalFormat;
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
        String finalMultiplier1 ;
        // Множитель от стата интеллекта
        int intellect = Prison.getInstance().getDatabase().getIntellectStat(player);
        if (!Prison.getInstance().intellect.containsKey(player)) {
            Prison.getInstance().intellect.put(player, intellect);
        } else {
            Prison.getInstance().intellect.replace(player, intellect);
        }
        if (intellect >= 20 && intellect < 40) {
            finalMultiplier1 = String.valueOf(plusMultiplier + 0.1);
        } else if (intellect >= 40 && intellect < 60){
            finalMultiplier1 = String.valueOf(plusMultiplier + 0.15);
        } else if (intellect >= 60 && intellect < 80){
            finalMultiplier1 = String.valueOf(plusMultiplier + 0.2);
        } else if (intellect >= 80 && intellect < 100){
            finalMultiplier1 = String.valueOf(plusMultiplier + 0.25);
        } else if (intellect >= 100){
            finalMultiplier1 = String.valueOf(plusMultiplier + 0.3);
        } else {
            finalMultiplier1 = multiplier;
        }
        double db = Double.parseDouble(finalMultiplier1);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String finalMultiplier = decimalFormat.format(db).replace("," , ".");
        Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
            Prison.getPlugin(Prison.class).getDatabase().createPlayer(player, finalMultiplier);
            Prison.getPlugin(Prison.class).getDatabase().updateMultiplier(player.getUniqueId(), finalMultiplier);
            if (!Prison.getInstance().lvl.containsKey(player)) {
                Prison.getPlugin(Prison.class).lvl.put(player, Prison.getPlugin(Prison.class).getDatabase().getLVL(player));
            }
            Integer religion = Prison.getInstance().getDatabase().getReligionStat(player);
            if (!Prison.getInstance().religion.containsKey(player)){
                Prison.getInstance().religion.put(player, religion);
            }
            if (!Prison.getInstance().bst.containsKey(player)) {
                Prison.getPlugin(Prison.class).bst.put(player, Prison.getPlugin(Prison.class).getDatabase().getBoost(player));
            }
            if (!Prison.getInstance().faction.containsKey(player)) {
                Prison.getPlugin(Prison.class).faction.put(player, Prison.getPlugin(Prison.class).getDatabase().getFaction(player));
            }
            if (!Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())) {
                if (Prison.getInstance().getDatabase().getLocalBlockBoosterTime(player.getUniqueId()) > 0) {
                    Prison.getInstance().privateBlockBooster.put(player.getUniqueId(), Prison.getInstance().getDatabase().getLocalBlockBoosterTime(player.getUniqueId()));
                    Prison.getInstance().privateBlockBoosterMultiplier.put(player.getUniqueId(), Prison.getInstance().getDatabase().getLocalBlockBoosterMultiplier(player.getUniqueId()));
                }
            }
            if (!Prison.getInstance().privateMobBooster.containsKey(player.getUniqueId())) {
                if (Prison.getInstance().getDatabase().getLocalMobBoosterTime(player.getUniqueId()) > 0) {
                    Prison.getInstance().privateMobBooster.put(player.getUniqueId(), Prison.getInstance().getDatabase().getLocalMobBoosterTime(player.getUniqueId()));
                    Prison.getInstance().privateMobMultiplier.put(player.getUniqueId(), Prison.getInstance().getDatabase().getLocalMobBoosterMultiplier(player.getUniqueId()));
                }
            }
            if (!Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
                if (Prison.getInstance().getDatabase().getLocalMoneyBoosterTime(player.getUniqueId()) > 0) {
                    Prison.getInstance().privateBooster.put(player.getUniqueId(), Prison.getInstance().getDatabase().getLocalMoneyBoosterTime(player.getUniqueId()));
                    Prison.getInstance().privateBoosterMultiplier.put(player.getUniqueId(), Prison.getInstance().getDatabase().getLocalMoneyBoosterMultiplier(player.getUniqueId()));
                }
            }
            if (!Prison.getInstance().authorityStat.containsKey(player)){
                Prison.getInstance().authorityStat.put(player, Double.valueOf(Prison.getInstance().getDatabase().getAuthorityStat(player)));
            }
        });
        if (!Prison.getInstance().mltp.containsKey(player)) {
            Prison.getPlugin(Prison.class).mltp.put(player, Double.parseDouble(finalMultiplier));
        }
        if (!Prison.getInstance().plusMultiplier.containsKey(player)) {
            Prison.getInstance().plusMultiplier.put(player, Prison.getInstance().getDatabase().getPlusMultiplier(player.getUniqueId()));
        }
        //СтатХуд
        //double globalBoost = 0;
        //if (!Prison.getPlugin(Prison.class).booster.isEmpty()){
        //    globalBoost = 1.0;
        //}
        //int privateBoost = 0;
        //if (Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
        //    privateBoost = 1;
        //}
        //Double boost = Prison.getInstance().getDatabase().getBoost(player);
        //double finalMultiplier2 = Double.parseDouble(finalMultiplier)+boost+globalBoost+privateBoost;
        //BossBar statHud = Bukkit.createBossBar("                                                                                                                                   Множитель " + finalMultiplier2 + " Ещё один множитель: " +finalMultiplier2 + " Ещё один множитель: " +finalMultiplier2, BarColor.WHITE, BarStyle.SOLID);
        //statHud.setVisible(true);
        //statHud.addPlayer(player);
        //new BukkitRunnable() {
        //        public void run() {
        //            if (statHud.getPlayers().contains(player)){
        //                double globalBoost = 0;
        //                if (!Prison.getPlugin(Prison.class).booster.isEmpty()){
        //                    globalBoost = 1.0;
        //                }
        //                int privateBoost = 0;
        //                if (Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
        //                    privateBoost = 1;
        //                }
        //                Double boost = 0.0;
        //                if (Prison.getInstance().bst.containsKey(player)) {
        //                boost = Prison.getPlugin(Prison.class).bst.get(player);
        //                }
        //                double finalMultiplier2 = Prison.getInstance().mltp.get(player)+boost+globalBoost+privateBoost;
        //                statHud.setTitle("                                                                                                                                   Множитель " + finalMultiplier2 + " Ещё один множитель: " +finalMultiplier2 + " Ещё один множитель: " +finalMultiplier2);
        //            } else {
        //                statHud.addPlayer(player);
        //            }
        //        }
        //}.runTaskTimer(Prison.getInstance(), 0, 1200);
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
