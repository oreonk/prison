package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class LocalBoost implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (arguments.length != 2) {
            return false;
        }else if (Bukkit.getPlayer(arguments[0]) != null){
            if (NumberUtils.isNumber(arguments[1])) {
                if (Bukkit.getPlayer(arguments[0]).isOnline()){
                    UUID uuid = Bukkit.getPlayer(arguments[0]).getUniqueId();
                    if (!Prison.getInstance().privateBooster.containsKey(uuid)) {
                    Prison.getInstance().privateBooster.put(uuid, Integer.parseInt(arguments[1]));
                } else {
                    Prison.getInstance().privateBooster.replace(uuid, Integer.parseInt(arguments[1]));
                }
                new BukkitRunnable() {
                    public void run() {
                        if (Prison.getInstance().privateBooster.get(uuid) != null) {
                            int time = Prison.getInstance().privateBooster.get(uuid);
                            time--;
                            Prison.getInstance().privateBooster.replace(uuid, time);
                            if (time == 0) {
                                Prison.getInstance().privateBooster.remove(uuid);
                                this.cancel();
                            }
                        } else {
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 1200, 1200);
                    return true;
                } else {
                    Msg.send(player, "Игрок оффлайн!");
                    return true;
                }
            } else {
                if (sender != null) {
                    Msg.send(player, "Второй аргумент должен быть числом!");
                    return true;
                } else {
                    Prison.getInstance().getLogger().info("Второй аргумент должен быть числом!");
                    return true;
                }
            }
        } else {
            if (sender != null) {
                Msg.send(player, "Первым аргументом команды должен быть ник игрока!");
                return true;
            } else {
                Prison.getInstance().getLogger().info("Второй аргумент должен быть числом!");
                return true;
            }
        }
    }
}
