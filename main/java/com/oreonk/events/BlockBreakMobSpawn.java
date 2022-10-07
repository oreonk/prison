package com.oreonk.events;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;
import java.util.Random;

public class BlockBreakMobSpawn implements Listener {
    public HashMap<Entity, Player> opossum = new HashMap<>() {};
    @EventHandler
    public void mobSpawn(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.STONE)) {
            if (!opossum.containsValue(event.getPlayer())) {
                //Спавн опоссума при ломании камня на базе
                double xOne = 100;
                double xTwo = 200;
                double zOne = 100;
                double zTwo = 100;
                Location location = event.getBlock().getLocation();
                if (location.getX() >= xOne && location.getX() <= xTwo) {
                    if (location.getZ() >= zOne && location.getZ() <= zTwo) {
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 5) {
                            MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("opossum").orElse(null);
                            if (mob != null) {
                                ActiveMob activeMob = mob.spawn(BukkitAdapter.adapt(location), 1);
                                Entity entity = activeMob.getEntity().getBukkitEntity();
                                opossum.put(entity, event.getPlayer());
                            }
                        }
                    }
                }
                //Спаввн опоссума при ломании камня на шахте золота
                double xThree = 300;
                double xFour = 400;
                double zThree = 300;
                double zFour = 400;
                if (location.getX() >= xThree && location.getX() <= xFour) {
                    if (location.getZ() >= zThree && location.getZ() <= zFour) {
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 20) {
                            MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("opossum").orElse(null);
                            if (mob != null) {
                                ActiveMob activeMob = mob.spawn(BukkitAdapter.adapt(location), 1);
                                Entity entity = activeMob.getEntity().getBukkitEntity();
                                opossum.put(entity, event.getPlayer());
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void mobKill(EntityDeathEvent event){
        if (opossum.containsKey(event.getEntity())){
            opossum.remove(event.getEntity());
        }
    }
}
