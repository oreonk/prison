package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
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
                switch (arguments[0]) {
                    case "on":
                        if (player.hasPermission("prison.autosell")) {
                            if (!player.hasPermission("prison.autosell.on")) {
                                TestPlug.getPermissions().playerAdd(player, "prison.autosell.on");
                                TestPlug.getPermissions().playerRemove(player, "prison.autosell.off");
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
                                TestPlug.getPermissions().playerAdd(player, "prison.autosell.off");
                                TestPlug.getPermissions().playerRemove(player, "prison.autosell.on");
                                Msg.send(player, ChatColor.GREEN + "Автоселл выключен!");
                                return true;
                            } else {
                                Msg.send(player, ChatColor.RED + "Автоселл уже выключен!");
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
