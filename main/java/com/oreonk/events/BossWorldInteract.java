package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

//public class BossWorldInteract implements Listener {
//    @EventHandler
//    public void onDeath(PlayerDeathEvent event){
//        Player player = event.getEntity();
//        if (player.getWorld().getName().contains("boss")) {
//            Msg.send(player, "Смерть. Должна прокать только в мире босса");
//            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
//            if (cellLvl < 5 && cellLvl >= 3) {
//                Prison.getInstance().ezkiel_arena_one.remove(player.getUniqueId());
//            } else if (cellLvl < 7 && cellLvl >= 5) {
//                Prison.getInstance().ezkiel_arena_two.remove(player.getUniqueId());
//            } else if (cellLvl < 11 && cellLvl >= 7) {
//                Prison.getInstance().ezkiel_arena_three.remove(player.getUniqueId());
//            } else if (cellLvl < 13 && cellLvl >= 11) {
//                Prison.getInstance().ezkiel_arena_four.remove(player.getUniqueId());
//            } else if (cellLvl >= 13) {
//                Prison.getInstance().ezkiel_arena_five.remove(player.getUniqueId());
//            }
//        }
//    }
//    @EventHandler
//    public void onTeleport(PlayerChangedWorldEvent event){
//        Player player = event.getPlayer();
//        if (event.getFrom().getName().equalsIgnoreCase("boss")){
//
//            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
//            if (player.getWorld().getName().equalsIgnoreCase("world")) {
//                Msg.send(player, "Телепорт с босса в обычный мир");
//                if (cellLvl < 5 && cellLvl >= 3) {
//                    Prison.getInstance().ezkiel_arena_one.remove(player.getUniqueId());
//                } else if (cellLvl < 7 && cellLvl >= 5) {
//                    Prison.getInstance().ezkiel_arena_two.remove(player.getUniqueId());
//                } else if (cellLvl < 11 && cellLvl >= 7) {
//                    Prison.getInstance().ezkiel_arena_three.remove(player.getUniqueId());
//                } else if (cellLvl < 13 && cellLvl >= 11) {
//                    Prison.getInstance().ezkiel_arena_four.remove(player.getUniqueId());
//                } else if (cellLvl >= 13) {
//                    Prison.getInstance().ezkiel_arena_five.remove(player.getUniqueId());
//                }
//            } else if (player.getWorld().getName().equalsIgnoreCase("cells")){
//                Msg.send(player, "Телепорт с босса в камеру");
//                if (cellLvl < 5 && cellLvl >= 3) {
//                    Prison.getInstance().ezkiel_arena_one.remove(player.getUniqueId());
//                } else if (cellLvl < 7 && cellLvl >= 5) {
//                    Prison.getInstance().ezkiel_arena_two.remove(player.getUniqueId());
//                } else if (cellLvl < 11 && cellLvl >= 7) {
//                    Prison.getInstance().ezkiel_arena_three.remove(player.getUniqueId());
//                } else if (cellLvl < 13 && cellLvl >= 11) {
//                    Prison.getInstance().ezkiel_arena_four.remove(player.getUniqueId());
//                } else if (cellLvl >= 13) {
//                    Prison.getInstance().ezkiel_arena_five.remove(player.getUniqueId());
//                }
//            }
//        }
//    }
//    @EventHandler
//    public void onRelog(PlayerQuitEvent event){
//        if (event.getPlayer().getWorld().getName().contains("boss")) {
//            event.getPlayer().setHealth(0);
//        }
//    }
//    @EventHandler
//    public void mobDamage(EntityDamageByEntityEvent event) {
//        if (event.getEntity() instanceof LivingEntity && event.getEntity().getCustomName() != null) {
//            if (((LivingEntity) event.getEntity()).getHealth() <= event.getDamage() && event.getDamager() instanceof Player) {
//                Player player = (Player) event.getDamager();
//                if (event.getEntity().getCustomName().contains("Эзкиэль")) {
//                    int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
//                    //Добавить выдачу звёзд
//                    if (cellLvl < 5 && cellLvl >= 3) {
//                        new BukkitRunnable(){
//                            @Override
//                            public void run() {
//                                Prison.getInstance().ezkiel_arena_one.remove(player.getUniqueId());
//                                ItemStack stars = new ItemStack(Material.NETHER_STAR);
//                                ItemMeta stars_meta = stars.getItemMeta();
//                                stars_meta.setDisplayName("Звезда");
//                                stars.setItemMeta(stars_meta);
//                                stars.setAmount(1);
//                                player.getInventory().addItem(stars);
//                                double cashAmount = 50;
//                                Location location = new Location(Bukkit.getWorld("world"), -157, 22, -42);
//                                player.teleport(location);
//                                Prison.getEconomy().depositPlayer(player, cashAmount);
//                                Msg.send(player, ChatColor.GREEN + "Вы получили " + cashAmount + ChatColor.WHITE + " " + ChatColor.GREEN + " за успешное завершение стрелки");
//                            }
//                        }.runTaskLater(Prison.getInstance(), 10);
//                    } else if (cellLvl < 7 && cellLvl >= 5) {
//                        new BukkitRunnable(){
//                            @Override
//                            public void run() {
//                                Prison.getInstance().ezkiel_arena_two.remove(player.getUniqueId());
//                                ItemStack stars = new ItemStack(Material.NETHER_STAR);
//                                ItemMeta stars_meta = stars.getItemMeta();
//                                stars_meta.setDisplayName("Звезда");
//                                stars.setItemMeta(stars_meta);
//                                stars.setAmount(2);
//                                player.getInventory().addItem(stars);
//                                double cashAmount = 150;
//                                Location location = new Location(Bukkit.getWorld("world"), -157, 22, -42);
//                                player.teleport(location);
//                                Prison.getEconomy().depositPlayer(player, cashAmount);
//                                Msg.send(player, ChatColor.GREEN + "Вы получили " + cashAmount + ChatColor.WHITE + " " + ChatColor.GREEN + " за успешное завершение стрелки");
//                            }
//                        }.runTaskLater(Prison.getInstance(), 10);
//                    } else if (cellLvl < 11 && cellLvl >= 7) {
//                        new BukkitRunnable(){
//                            @Override
//                            public void run() {
//                                Prison.getInstance().ezkiel_arena_three.remove(player.getUniqueId());
//                                ItemStack stars = new ItemStack(Material.NETHER_STAR);
//                                ItemMeta stars_meta = stars.getItemMeta();
//                                stars_meta.setDisplayName("Звезда");
//                                stars.setItemMeta(stars_meta);
//                                stars.setAmount(3);
//                                player.getInventory().addItem(stars);
//                                double cashAmount = 400;
//                                Location location = new Location(Bukkit.getWorld("world"), -157, 22, -42);
//                                player.teleport(location);
//                                Prison.getEconomy().depositPlayer(player, cashAmount);
//                                Msg.send(player, ChatColor.GREEN + "Вы получили " + cashAmount + ChatColor.WHITE + " " + ChatColor.GREEN + " за успешное завершение стрелки");
//                                }
//                        }.runTaskLater(Prison.getInstance(), 10);
//                    } else if (cellLvl < 13 && cellLvl >= 11) {
//                        new BukkitRunnable(){
//                            @Override
//                            public void run() {
//                                Prison.getInstance().ezkiel_arena_four.remove(player.getUniqueId());
//                                ItemStack stars = new ItemStack(Material.NETHER_STAR);
//                                ItemMeta stars_meta = stars.getItemMeta();
//                                stars_meta.setDisplayName("Звезда");
//                                stars.setItemMeta(stars_meta);
//                                stars.setAmount(5);
//                                player.getInventory().addItem(stars);
//                                double cashAmount = 1500;
//                                Location location = new Location(Bukkit.getWorld("world"), -157, 22, -42);
//                                player.teleport(location);
//                                Prison.getEconomy().depositPlayer(player, cashAmount);
//                                Msg.send(player, ChatColor.GREEN + "Вы получили " + cashAmount + ChatColor.WHITE + " " + ChatColor.GREEN + " за успешное завершение стрелки");
//                            }
//                        }.runTaskLater(Prison.getInstance(), 10);
//                    } else if (cellLvl >= 13) {
//                        new BukkitRunnable() {
//                            @Override
//                            public void run() {
//                                Prison.getInstance().ezkiel_arena_five.remove(player.getUniqueId());
//                                ItemStack stars = new ItemStack(Material.NETHER_STAR);
//                                ItemMeta stars_meta = stars.getItemMeta();
//                                stars_meta.setDisplayName("Звезда");
//                                stars.setItemMeta(stars_meta);
//                                stars.setAmount(7);
//                                player.getInventory().addItem(stars);
//                                double cashAmount = 2500;
//                                Location location = new Location(Bukkit.getWorld("world"), -157, 22, -42);
//                                player.teleport(location);
//                                Prison.getEconomy().depositPlayer(player, cashAmount);
//                                Msg.send(player, ChatColor.GREEN + "Вы получили " + cashAmount + ChatColor.WHITE + " " + ChatColor.GREEN + " за успешное завершение стрелки");
//                            }
//                        }.runTaskLater(Prison.getInstance(), 10);
//                    }
//                }
//            }
//        }
//    }
//}
