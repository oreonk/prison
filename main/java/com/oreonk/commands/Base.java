package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Base implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (!Prison.getInstance().faction.get(player).equals("0")){
            if (Prison.getInstance().faction.get(player).equals("white")){
                if (!Prison.getInstance().timer.containsKey(player)) {
                    Prison.getInstance().timer.put(player, 3);
                    Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                    new BukkitRunnable() {
                        public void run() {
                            if (!Prison.getInstance().timer.containsKey(player)){
                                this.cancel();
                            }
                            int time = Prison.getInstance().timer.get(player);
                            time--;
                            Prison.getInstance().timer.replace(player, time);
                            if (time == 0) {
                                this.cancel();
                                Prison.getInstance().timer.remove(player);
                                Location location = new Location((Bukkit.getWorld("world")), 2331, 97, 307, -179, 4);
                                player.teleport(location);
                            }
                        }
                    }.runTaskTimer(Prison.getInstance(), 0, 20);
                } else {
                    Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                }
            } else if (Prison.getInstance().faction.get(player).equals("black")){
                if (!Prison.getInstance().timer.containsKey(player)) {
                    Prison.getInstance().timer.put(player, 3);
                    Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                    new BukkitRunnable() {
                        public void run() {
                            if (!Prison.getInstance().timer.containsKey(player)){
                                this.cancel();
                            }
                            int time = Prison.getInstance().timer.get(player);
                            time--;
                            Prison.getInstance().timer.replace(player, time);
                            if (time == 0) {
                                this.cancel();
                                Prison.getInstance().timer.remove(player);
                                Location location = new Location((Bukkit.getWorld("world")), 2430, 96, 368, 91,  1);
                                player.teleport(location);
                            }
                        }
                    }.runTaskTimer(Prison.getInstance(), 0, 20);
                } else {
                    Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                }
            } else if (Prison.getInstance().faction.get(player).equals("asian")){
                if (!Prison.getInstance().timer.containsKey(player)) {
                    Prison.getInstance().timer.put(player, 3);
                    Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                    new BukkitRunnable() {
                        public void run() {
                            if (!Prison.getInstance().timer.containsKey(player)){
                                this.cancel();
                            }
                            int time = Prison.getInstance().timer.get(player);
                            time--;
                            Prison.getInstance().timer.replace(player, time);
                            if (time == 0) {
                                this.cancel();
                                Prison.getInstance().timer.remove(player);
                                Location location = new Location((Bukkit.getWorld("world")), 2431, 96, 208, 90,  1);
                                player.teleport(location);
                            }
                        }
                    }.runTaskTimer(Prison.getInstance(), 0, 20);
                } else {
                    Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь!");
                }
            }
            return true;
        } else {
            Msg.send(player, ChatColor.RED + "Вы не состоите во фракции!");
            return true;
        }
    }
}
