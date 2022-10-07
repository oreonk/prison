package com.oreonk.commands;

import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            if (arguments.length == 0) {
                    return false;
                }
            else if (command.getName().equalsIgnoreCase("hide")) {
                if ("on".equals(arguments[0])) {
                    for (Player toHide : Bukkit.getServer().getOnlinePlayers()) {
                        Player player = (Player) sender;
                        player.hidePlayer(Prison.getInstance(), toHide);
                        Prison.getInstance().hide.put(player, 1);
                    }
                    return true;
                } else if ("off".equals(arguments[0])) {
                    for (Player toShow : Bukkit.getServer().getOnlinePlayers()) {
                        Player player = (Player) sender;
                        player.showPlayer(Prison.getInstance(), toShow);
                        Prison.getInstance().hide.remove(player);
                    }
                    return true;
                }
            }
        }
        return true;
    }
}
