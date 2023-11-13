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

public class LocalBlockBooster implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        //Если 1 после времени - множитель 1.5, если 2 - множитель 2
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (arguments.length != 3) {
            return false;
        } else if (Bukkit.getPlayer(arguments[0]) != null){
            if (NumberUtils.isNumber(arguments[1]) && NumberUtils.isNumber(arguments[2])) {
                if (Bukkit.getPlayer(arguments[0]).isOnline()){
                    UUID uuid = Bukkit.getPlayer(arguments[0]).getUniqueId();
                    if (!Prison.getInstance().privateBlockBooster.containsKey(uuid) && !Prison.getInstance().privateBlockBoosterMultiplier.containsKey(uuid)) {
                        if (Integer.parseInt(arguments[2])<1 || Integer.parseInt(arguments[2])>2){
                            if(sender == null){
                                Prison.getInstance().getLogger().info("Третий аргумент должен быть 1 или 2");
                                return true;
                            } else {
                                Msg.send(player, "Третий аргумент должен быть 1 или 2");
                                return true;
                            }
                        } else {
                            Prison.getInstance().privateBlockBooster.put(uuid, Integer.parseInt(arguments[1]));
                            Prison.getInstance().privateBlockBoosterMultiplier.put(uuid, Integer.parseInt(arguments[2]));
                            return true;
                        }
                } else {
                        if (Integer.parseInt(arguments[2])<1 || Integer.parseInt(arguments[2])>2){
                            if(sender == null){
                                Prison.getInstance().getLogger().info("Третий аргумент должен быть 1 или 2");
                                return true;
                            } else {
                                Msg.send(player, "Третий аргумент должен быть 1 или 2");
                                return true;
                            }
                        } else {
                            Prison.getInstance().privateBlockBooster.replace(uuid, Integer.parseInt(arguments[1]));
                            Prison.getInstance().privateBlockBoosterMultiplier.replace(uuid, Integer.parseInt(arguments[2]));
                            return true;
                        }
                }
                } else {
                    if (sender == null) {
                        Prison.getInstance().getLogger().info("Игрок оффлайн!");
                        return true;
                    } else {
                        Msg.send(player, "Игрок оффлайн!");
                        return true;
                    }
                }
            } else {
                if (sender != null) {
                    Msg.send(player, "Второй и третий аргументы должен быть числом!");
                    return true;
                } else {
                    Prison.getInstance().getLogger().info("Второй и третий аргументы должен быть числом!");
                    return true;
                }
            }
        } else {
            if (sender != null) {
                Msg.send(player, "Первым аргументом команды должен быть ник игрока!");
                return true;
            } else {
                Prison.getInstance().getLogger().info("Первым аргументом команды должен быть ник игрока!");
                return true;
            }
        }
    }
}
