package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class Balance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        double balance = Prison.getEconomy().getBalance(player);
        DecimalFormat decimalFormat = new DecimalFormat("##################0.###");
        String finalBalance =  decimalFormat.format(balance).replace("," , ".");
        Msg.send(player, "Баланс: " + ChatColor.WHITE + finalBalance + " ");
        return true;
    }
}
