package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class Thx implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("thx")) {
                if (!Prison.getPlugin(Prison.class).booster.isEmpty()){
                    if (!Prison.getPlugin(Prison.class).thx.containsKey((Player)sender)){
                        Player finalPlayer = null;
                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                            finalPlayer = Bukkit.getPlayer(entry.getKey());
                        }
                        Prison.getPlugin(Prison.class).thx.put((Player) sender, 1);
                        Economy economy = Prison.getEconomy();
                        economy.depositPlayer(finalPlayer, 70);
                        economy.depositPlayer((Player)sender, 40);
                        Msg.send((Player)sender, ChatColor.DARK_GREEN + "Вы отблагодарили игрока " + finalPlayer.getName());
                        return true;
                    } else {
                        Msg.send(sender, ChatColor.RED + "Вы уже отблагодарили этого игрока!");
                        return true;
                    }
                } else{
                    Msg.send(sender, ChatColor.RED + "Нет активного бустера!");
                    return true;
                }
            }
        }
        return false;
    }
}