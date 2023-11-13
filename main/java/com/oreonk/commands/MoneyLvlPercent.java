package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyLvlPercent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = null;
        if (sender instanceof Player){
            player = (Player) sender;
        }
        if (arguments.length != 2) {
            return false;
        }else if (Bukkit.getPlayer(arguments[0]) != null) {
            if (NumberUtils.isNumber(arguments[1])) {
                String percent = arguments[1];
                double cash = Double.parseDouble(PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(arguments[0]), "%prison_lvlpercent_" + percent + "%"));
                Prison.getEconomy().depositPlayer(Bukkit.getPlayer(arguments[0]), cash);
                return true;
            } else {
                if (player != null) {
                    Msg.send(player, "Второй аргумент должен быть числом!");
                    return true;
                } else {
                    Prison.getInstance().getLogger().info("Второй аргумент должен быть числом!");
                    return true;
                }
            }
        } else {
            if (player != null) {
                Msg.send(player, "Первым аргументом команды должен быть ник игрока!");
                return true;
            } else {
                Prison.getInstance().getLogger().info("Второй аргумент должен быть числом!");
                return true;
            }
        }
    }
}
