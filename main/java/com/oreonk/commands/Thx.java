package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class Thx implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("thx")) {
                if (!TestPlug.getPlugin(TestPlug.class).booster.isEmpty()){
                    if (!TestPlug.getPlugin(TestPlug.class).thx.containsKey((Player)sender)){
                        Player finalPlayer = null;
                        for (Map.Entry<Player, Integer> entry : TestPlug.getPlugin(TestPlug.class).booster.entrySet()) {
                            finalPlayer = entry.getKey();
                        }
                        TestPlug.getPlugin(TestPlug.class).thx.put((Player) sender, 1);
                        Economy economy = TestPlug.getEconomy();
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