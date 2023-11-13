package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Random;

public class ReligionBlockBreak implements Listener {
    public HashMap<Player, Integer> ccd = new HashMap<>() {};
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
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 140, 0);
                    player.addPotionEffect(pot);
                    Block mainBlock = event.getBlock();
                    Block block1 = mainBlock.getRelative(BlockFace.EAST);
                    Block block2 = mainBlock.getRelative(BlockFace.WEST);
                    Block block3 = mainBlock.getRelative(BlockFace.NORTH);
                    Block block4 = mainBlock.getRelative(BlockFace.SOUTH);
                    Block block7 = mainBlock.getRelative(BlockFace.NORTH_EAST);
                    Block block8 = mainBlock.getRelative(BlockFace.NORTH_WEST);
                    Block block9 = mainBlock.getRelative(BlockFace.SOUTH_EAST);
                    Block block10 = mainBlock.getRelative(BlockFace.SOUTH_WEST);
                    Block block11 = block1.getRelative(BlockFace.EAST);
                    Block block12 = block2.getRelative(BlockFace.WEST);
                    Block block13 = block3.getRelative(BlockFace.NORTH);
                    Block block14 = block4.getRelative(BlockFace.SOUTH);
                    if (!ccd.containsKey(event.getPlayer())) {
                        if (event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("PICKAXE") || event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("SHOVEL")) {
                            ItemStack stack = player.getInventory().getItemInMainHand();
                            if (!block1.getDrops(stack).isEmpty()) {
                                player.breakBlock(block1);
                            }
                            if (!block2.getDrops(stack).isEmpty()) {
                                player.breakBlock(block2);
                            }
                            if (!block3.getDrops(stack).isEmpty()) {
                                player.breakBlock(block3);
                            }
                            if (!block4.getDrops(stack).isEmpty()) {
                                player.breakBlock(block4);
                            }
                            if (!block7.getDrops(stack).isEmpty()) {
                                player.breakBlock(block7);
                            }
                            if (!block8.getDrops(stack).isEmpty()) {
                                player.breakBlock(block8);
                            }
                            if (!block9.getDrops(stack).isEmpty()) {
                                player.breakBlock(block9);
                            }
                            if (!block10.getDrops(stack).isEmpty()) {
                                player.breakBlock(block10);
                            }
                            if (!block11.getDrops(stack).isEmpty()) {
                                player.breakBlock(block11);
                            }
                            if (!block12.getDrops(stack).isEmpty()) {
                                player.breakBlock(block12);
                            }
                            if (!block13.getDrops(stack).isEmpty()) {
                                player.breakBlock(block13);
                            }
                            if (!block14.getDrops(stack).isEmpty()) {
                                player.breakBlock(block14);
                            }
                            player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1f,40f);
                            player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL,mainBlock.getLocation(),20);
                            ccd.put(event.getPlayer(), 10);
                            new BukkitRunnable() {
                                public void run() {
                                    int time = ccd.get(event.getPlayer());
                                    Msg.send(player, "Тайм " + time);
                                    time = time - 1;
                                    ccd.replace(player,time);
                                    if (time <= 0) {
                                        this.cancel();
                                        ccd.remove(player);
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        }
                    }
                }
            } else if (religion >= 80) {
                if (percent < 4) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 120, 0);
                    player.addPotionEffect(pot);
                    Block mainBlock = event.getBlock();
                    Block block1 = mainBlock.getRelative(BlockFace.EAST);
                    Block block2 = mainBlock.getRelative(BlockFace.WEST);
                    Block block3 = mainBlock.getRelative(BlockFace.NORTH);
                    Block block4 = mainBlock.getRelative(BlockFace.SOUTH);
                    Block block5 = mainBlock.getRelative(BlockFace.UP);
                    Block block6 = mainBlock.getRelative(BlockFace.DOWN);
                    if (!ccd.containsKey(event.getPlayer())) {
                        if (event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("PICKAXE") || event.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("SHOVEL")) {
                            ItemStack stack = player.getInventory().getItemInMainHand();
                            if (!block1.getDrops(stack).isEmpty()) {
                                player.breakBlock(block1);
                            }
                            if (!block2.getDrops(stack).isEmpty()) {
                                player.breakBlock(block2);
                            }
                            if (!block3.getDrops(stack).isEmpty()) {
                                player.breakBlock(block3);
                            }
                            if (!block4.getDrops(stack).isEmpty()) {
                                player.breakBlock(block4);
                            }
                            if (!block5.getDrops(stack).isEmpty()) {
                                player.breakBlock(block5);
                            }
                            if (!block6.getDrops(stack).isEmpty()) {
                                player.breakBlock(block6);
                            }
                            ccd.put(event.getPlayer(), 10);
                            new BukkitRunnable() {
                                public void run() {
                                    int time = ccd.get(event.getPlayer());
                                    time = time - 1;
                                    ccd.replace(player,time);
                                    if (time <= 0) {
                                        this.cancel();
                                        ccd.remove(player);
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        }
                    }
                }
            } else if (religion >= 60) {
                if (percent < 3) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 0);
                    player.addPotionEffect(pot);
                }
            } else if (religion >= 40) {
                if (percent < 2) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 0);
                    player.addPotionEffect(pot);
                }
            } else if (religion >= 20) {
                if (percent < 1) {
                    PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 0);
                    player.addPotionEffect(pot);
                }
            }
        }
    }
}
