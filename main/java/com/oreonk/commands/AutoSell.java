package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AutoSell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("autosell")) {
                Player player = (Player) sender;
                if (arguments.length == 0){
                    Msg.send(player, ChatColor.YELLOW + "---- " + ChatColor.WHITE + "Автоматическая продажа " + ChatColor.GRAY + "(/as /autosell) " + ChatColor.YELLOW + "----");
                    Msg.send(player, "&e/as &7on&f: включить автоматическую продажу");
                    Msg.send(player, "&e/as &7on&ff: выключить автоматическую продажу");
                    return true;
                }
                switch (arguments[0]) {
                    case "on":
                        if (player.hasPermission("prison.autosell")) {
                            if (!player.hasPermission("prison.autosell.on")) {
                                Prison.getPermissions().playerAdd(player, "prison.autosell.on");
                                Prison.getPermissions().playerRemove(player, "prison.autosell.off");
                                Msg.send(player, ChatColor.GREEN + "Автоселл включен!");
                                return true;
                            } else {
                                Msg.send(player, ChatColor.RED + "Автоселл уже включен!");
                                return true;
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "У вас нет прав для использования автоселла!");
                        return true;
                    }
                    case "off":
                        if (player.hasPermission("prison.autosell")) {
                            if (!player.hasPermission("prison.autosell.off")) {
                                Prison.getPermissions().playerAdd(player, "prison.autosell.off");
                                Prison.getPermissions().playerRemove(player, "prison.autosell.on");
                                Msg.send(player, ChatColor.GREEN + "Автоселл выключен!");
                                return true;
                            } else {
                                Msg.send(player, ChatColor.RED + "Автоселл уже выключен!");
                                return true;
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "У вас нет прав для использования автоселла!");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
