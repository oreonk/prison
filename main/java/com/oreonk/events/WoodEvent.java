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
        if (event.getBlock().getType().equals(Material.OAK_WOOD)){
            event.getBlock().setType(Material.OAK_PLANKS);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.OAK_WOOD);
                }
            }.runTaskLater(Prison.getInstance(),1200);
        } else if (event.getBlock().getType().equals(Material.ACACIA_WOOD)) {
            event.getBlock().setType(Material.ACACIA_PLANKS);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.ACACIA_WOOD);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.BIRCH_WOOD)) {
            event.getBlock().setType(Material.BIRCH_PLANKS);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.BIRCH_WOOD);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.DARK_OAK_WOOD)) {
            event.getBlock().setType(Material.DARK_OAK_PLANKS);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.DARK_OAK_WOOD);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.JUNGLE_WOOD)) {
            event.getBlock().setType(Material.JUNGLE_PLANKS);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.JUNGLE_WOOD);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        } else if (event.getBlock().getType().equals(Material.SPRUCE_WOOD)) {
            event.getBlock().setType(Material.SPRUCE_PLANKS);
            new BukkitRunnable() {
                public void run() {
                    event.getBlock().setType(Material.SPRUCE_WOOD);
                }
            }.runTaskLater(Prison.getInstance(), 1200);
        }
        if (event.getBlock().getType().equals(Material.SPRUCE_PLANKS) || event.getBlock().getType().equals(Material.OAK_PLANKS) || event.getBlock().getType().equals(Material.ACACIA_PLANKS) || event.getBlock().getType().equals(Material.BIRCH_PLANKS)){
            event.setCancelled(true);
        }
    }
}
