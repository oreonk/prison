package com.oreonk.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Rubbish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 27, "Мусорка");
            player.openInventory(gui);
            return true;
        }
        return true;
    }
}
