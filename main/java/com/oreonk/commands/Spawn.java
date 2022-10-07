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

import java.util.HashMap;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
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
                            Location location = new Location((Bukkit.getWorld("world")), -157, 22, -42, 1, 1);
                            player.teleport(location);
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 0, 20);
            } else {
                Msg.send(player, ChatColor.RED + "Вы уже телепортируетесь");
            }
            return true;
        }
        return true;
    }
}
