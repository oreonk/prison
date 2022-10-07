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

public class AdminLvl implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (arguments.length != 2) {
            return false;
        }else if (Bukkit.getPlayer(arguments[0]) != null){
            if (NumberUtils.isNumber(arguments[1])) {
                if (Integer.parseInt(arguments[1]) <= 23) {
                    int finalLvl = Integer.parseInt(arguments[1]);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        Prison.getInstance().getDatabase().updateLVL(Bukkit.getPlayer(arguments[0]), finalLvl);
                    });
                    Prison.getInstance().lvl.replace(Bukkit.getPlayer(arguments[0]), finalLvl);
                    return true;
                } else {
                    return false;
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
