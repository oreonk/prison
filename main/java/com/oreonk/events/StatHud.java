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

//public class StatHud implements Listener {
//    @EventHandler
//    public void onJoin(PlayerJoinEvent event){
//        Player player = event.getPlayer();
//        double globalBoost = 0.0;
//        if (!Prison.getPlugin(Prison.class).booster.isEmpty()){
//            globalBoost = 1.0;
//        }
//        int privateBoost = 0;
//        if (Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
//            privateBoost = 1;
//        }
//        Double multiplier = Prison.getPlugin(Prison.class).mltp.get(player);
//        Double boost = Prison.getPlugin(Prison.class).bst.get(player);
//        double finalMultiplier = multiplier+boost+globalBoost+privateBoost;
//        BossBar bossBar = Bukkit.createBossBar("                     Множитель " + finalMultiplier, BarColor.WHITE, BarStyle.SOLID);
//    }
//}
