package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Mine implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (arguments.length == 0) {
            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cpanel mines " + player.getName());
            return true;
        }
        switch (arguments[0]) {
                case "3":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 3) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_stone " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "5":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 5) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_stone_brick " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "7":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 7) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_hell " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player,ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "9":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 9) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_clay " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "11":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 11) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_sandstone " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "13":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 13) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_japan " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                    case "15":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 15) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_snow " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "17":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 17) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_wood " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else{
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "19":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 19) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_prismarine " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        } else {
                            Msg.send(player,ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "21":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 21) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_ender " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        }
                        else {
                            Msg.send(player, "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "23":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 23) {
                            Prison.getInstance().timer.put(player, 4);
                            Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                            new BukkitRunnable() {
                                public void run() {
                                    if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                    int time = Prison.getInstance().timer.get(player);
                                    time--;
                                    Prison.getInstance().timer.replace(player, time);
                                    if (time == 1) {
                                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_obsiender " + player.getName());
                                        Prison.getInstance().timer.remove(player);
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 20);
                        }
                        else {
                            Msg.send(player, "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "2":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 2) {
                            if (player.hasPermission("prison.premium.dirt")) {
                                Prison.getInstance().timer.put(player, 4);
                                Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                                new BukkitRunnable() {
                                    public void run() {
                                        if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                        int time = Prison.getInstance().timer.get(player);
                                        time--;
                                        Prison.getInstance().timer.replace(player, time);
                                        if (time == 1) {
                                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp donate_dirt " + player.getName());
                                            Prison.getInstance().timer.remove(player);
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 20);
                            } else {
                                Msg.send(player, ChatColor.RED + "У вас нет пропуска..");
                                return true;
                            }
                        } else {
                          Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                          return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "6":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 6) {
                            if (player.hasPermission("prison.premium.slums")) {
                                Prison.getInstance().timer.put(player, 4);
                                Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                                new BukkitRunnable() {
                                    public void run() {
                                        if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                        int time = Prison.getInstance().timer.get(player);
                                        time--;
                                        Prison.getInstance().timer.replace(player, time);
                                        if (time == 1) {
                                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp stone_under " + player.getName());
                                            Prison.getInstance().timer.remove(player);
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 20);
                            }else {
                                Msg.send(player, ChatColor.RED + "У вас нет пропуска..");
                                return true;
                            }
                        } else {
                            Msg.send(player,ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "10":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 10) {
                            if (player.hasPermission("prison.premium.podval")) {

                                Prison.getInstance().timer.put(player, 4);
                                Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                                new BukkitRunnable() {
                                    public void run() {
                                        if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                        int time = Prison.getInstance().timer.get(player);
                                        time--;
                                        Prison.getInstance().timer.replace(player, time);
                                        if (time == 1) {
                                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp podval " + player.getName());
                                            Prison.getInstance().timer.remove(player);
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 20);
                            } else {
                            Msg.send(player, ChatColor.RED + "У вас нет пропуска..");
                            return true;
                        }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вам не хватает уровня..");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
                return true;
                case "20":
                    if (!Prison.getInstance().timer.containsKey(player)) {
                        if (Prison.getInstance().lvl.get(player) >= 20) {
                            if (player.hasPermission("prison.premium.castle")) {
                                Prison.getInstance().timer.put(player, 4);
                                Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                                new BukkitRunnable() {
                                    public void run() {
                                        if (!Prison.getInstance().timer.containsKey(player)){
                                            this.cancel();
                                        }
                                        int time = Prison.getInstance().timer.get(player);
                                        time--;
                                        Prison.getInstance().timer.replace(player, time);
                                        if (time == 1) {
                                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cmi warp mine_basalt " + player.getName());
                                            Prison.getInstance().timer.remove(player);
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 20);
                            } else {
                                Msg.send(player, ChatColor.RED + "У вас нет пропуска");
                                return true;
                            }
                        } else {
                            Msg.send(player,ChatColor.RED + "Вам не хватает уровня");
                            return true;
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                        return true;
                    }
        }
        return false;
    }
}

