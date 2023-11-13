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

import java.io.IOException;
import java.util.List;

public class Cell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (Prison.getInstance().lvl.get(player) >= 3) {
            if (!Prison.getInstance().timer.containsKey(player)) {
                Prison.getInstance().timer.put(player, 3);
                Msg.send(player, ChatColor.GREEN + "Телепортация произойдёт через 3 секунды.");
                new BukkitRunnable() {
                    public void run() {
                        if (!Prison.getInstance().timer.containsKey(player)) {
                            this.cancel();
                        }
                        int time = Prison.getInstance().timer.get(player);
                        time--;
                        Prison.getInstance().timer.replace(player, time);
                        if (time == 0) {
                            this.cancel();
                            Prison.getInstance().timer.remove(player);
                            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                            Location location;
                            if (cellLvl < 4) {
                                location = new Location(Bukkit.getWorld("cells"), -29, -59, -21);
                            } else if (cellLvl < 7){
                                location = new Location(Bukkit.getWorld("cells"), -18, -59, -21);
                            } else if (cellLvl < 13){
                                location = new Location(Bukkit.getWorld("cells"), -5, -59, -21);
                            } else {
                                location = new Location(Bukkit.getWorld("cells"), 8, -59, -22);
                            }
                            player.teleport(location);
                            if (!Prison.getInstance().HOME_ONE.contains(player.getUniqueId().toString())) {
                                Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Владелец недвижимости " + ChatColor.GREEN + "проверьте его в /achievements");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("HOME_ONE");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("HOME_ONE", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                    Prison.getInstance().HOME_ONE.add(player.getUniqueId().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 0, 20);
            }
            return true;
        } else {
            Msg.send(player, ChatColor.RED + "Личная камера доступна с третьего уровня!");
            return true;
        }
    }
}

