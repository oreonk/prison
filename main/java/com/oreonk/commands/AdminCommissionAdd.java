package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommissionAdd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        //Добавить 1 - +0.05, 2 - +0.1 и т.д
        if (arguments.length != 2) {
            return false;
        }
        if (Bukkit.getPlayer(arguments[0]) != null){
            if (NumberUtils.isNumber(arguments[1])) {
                    int commission = Integer.parseInt(arguments[1]);
                    if (commission == 1 || commission == 2) {
                        int finalCommission = (Prison.getInstance().getDatabase().getCommission(Bukkit.getPlayer(arguments[0]))) + commission;
                        Prison.getInstance().getDatabase().setCommission(Bukkit.getPlayer(arguments[0]), finalCommission);
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
