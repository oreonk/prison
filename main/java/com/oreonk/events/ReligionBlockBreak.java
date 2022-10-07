package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class ReligionBlockBreak implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void blockBreak (BlockBreakEvent event) {
        //Дроп спешки с шансом
        if (event.getPlayer().getPotionEffect(PotionEffectType.FAST_DIGGING) == null) {
            Random r = new Random();
            Player player = event.getPlayer();
            int percent = r.nextInt(100);
            int religion = Prison.getInstance().religion.get(player);
            if (religion >= 100) {
                if (percent < 5) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 7, 1);
                    player.addPotionEffect(pot);
                }
            } else if (religion >= 80) {
                if (percent < 4) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 6, 1);
                    player.addPotionEffect(pot);
                }
            } else if (religion >= 60) {
                if (percent < 3) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 5, 1);
                    player.addPotionEffect(pot);
                }
            } else if (religion >= 40) {
                if (percent < 2) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 4, 1);
                    player.addPotionEffect(pot);
                }
            } else if (religion >= 20) {
                if (percent < 1) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 3, 1);
                    player.addPotionEffect(pot);
                }
            }
        }
    }
}
