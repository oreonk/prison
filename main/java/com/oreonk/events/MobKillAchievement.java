package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.io.IOException;
import java.util.List;

public class MobKillAchievement implements Listener {
    @EventHandler
    public void mobKill (EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof LivingEntity && event.getEntity().getCustomName() != null) {
            if (((LivingEntity) event.getEntity()).getHealth() <= event.getDamage() && event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (!Prison.getInstance().BOSS_ONE.contains(player.getUniqueId().toString())){
                    if (event.getEntity().getCustomName().contains("Шахтёр") || event.getEntity().getCustomName().contains("Злой фермер") || event.getEntity().getCustomName().contains("Ледяное чудовище") || event.getEntity().getCustomName().contains("Ведьма") || event.getEntity().getCustomName().contains("Воришка") || event.getEntity().getCustomName().contains("Хранитель леса") || event.getEntity().getCustomName().contains("Страж сатаны") || event.getEntity().getCustomName().contains("Иссушитель")) {
                        Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Машина " + ChatColor.GREEN + "проверьте его в /achievements");
                        List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("BOSS_ONE");
                        list.add(player.getUniqueId().toString());
                        try {
                            Prison.getInstance().getAchievementsConfig().set("BOSS_ONE", list);
                            Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            Prison.getInstance().BOSS_ONE.add(player.getUniqueId().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (!Prison.getInstance().HUNTER_ONE.contains(player.getUniqueId().toString())){
                    if (event.getEntity().getCustomName().contains("Опоссум") && player.getStatistic(Statistic.MOB_KILLS, EntityType.SILVERFISH) >= 25){
                        Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Охотник " + ChatColor.GREEN + "проверьте его в /achievements");
                        List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("HUNTER_ONE");
                        list.add(player.getUniqueId().toString());
                        try {
                            Prison.getInstance().getAchievementsConfig().set("HUNTER_ONE", list);
                            Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            Prison.getInstance().HUNTER_ONE.add(player.getUniqueId().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
