package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatWipe implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (arguments.length != 1) {
            return false;
        }else if (Bukkit.getPlayer(arguments[0]) != null){
                Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    Prison.getInstance().getDatabase().updateBlockTop(Bukkit.getPlayer(arguments[0]).getUniqueId(), 0);
                });
                return true;
        } else if (Bukkit.getOfflinePlayer(arguments[0]) != null){
            Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    Prison.getInstance().getDatabase().updateBlockTop(Bukkit.getOfflinePlayer(arguments[0]).getUniqueId(), 0);
            });
            return true;
        }
        else if (Bukkit.getOfflinePlayer(arguments[0]) == null && Bukkit.getPlayer(arguments[0]) == null){
            Msg.send(player, "Аргументом должен быть ник игрока");
            return true;
        }
        return false;
    }
}
