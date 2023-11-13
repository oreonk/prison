package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class WoodEvent implements Listener {
    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        if (event.getBlock().getType().equals(Material.OAK_LOG)){
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.OAK_PLANKS);
                }
            }.runTaskLater(Prison.getInstance(),1);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.OAK_LOG);
                }
            }.runTaskLater(Prison.getInstance(),1200);
        } else if (event.getBlock().getType().equals(Material.ACACIA_LOG)) {
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.ACACIA_PLANKS);
            }
                }.runTaskLater(Prison.getInstance(),1);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.ACACIA_LOG);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.BIRCH_LOG)) {
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.BIRCH_PLANKS);
            }
                }.runTaskLater(Prison.getInstance(),1);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.BIRCH_LOG);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.DARK_OAK_LOG)) {
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.DARK_OAK_PLANKS);
            }
                }.runTaskLater(Prison.getInstance(),1);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.DARK_OAK_LOG);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.JUNGLE_LOG)) {
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.JUNGLE_PLANKS);
            }
                }.runTaskLater(Prison.getInstance(),1);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.JUNGLE_LOG);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.SPRUCE_LOG)) {
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.SPRUCE_PLANKS);
            }
                }.runTaskLater(Prison.getInstance(),1);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.SPRUCE_LOG);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        }
        if (event.getBlock().getType().equals(Material.SPRUCE_PLANKS) || event.getBlock().getType().equals(Material.OAK_PLANKS) || event.getBlock().getType().equals(Material.ACACIA_PLANKS) || event.getBlock().getType().equals(Material.BIRCH_PLANKS) || event.getBlock().getType().equals(Material.DARK_OAK_PLANKS)){
            event.setCancelled(true);
        }
    }
}
