package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.economy.Economy;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.UUID;

public class Pay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        int commission = Prison.getInstance().getCommission(player);
        if (arguments.length != 2) {
            return false;
        } else if (Bukkit.getPlayer(arguments[0]) != null || Prison.getInstance().getDatabase().playerNameExists(arguments[0])){
            if (!Bukkit.getPlayer(arguments[0]).getUniqueId().equals(player.getUniqueId())) {
                if (NumberUtils.isNumber(arguments[1])) {
                    if (Integer.parseInt(arguments[1]) < 1) {
                        Economy economy = Prison.getEconomy();
                        Player onlinePlayer = Bukkit.getPlayer(arguments[0]);
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getUUID(arguments[0])));
                        double amount = Double.parseDouble(arguments[1]);
                        double finalAmount = amount - (amount * commission / 100);
                        DecimalFormat decimalFormat = new DecimalFormat("###########0.##");
                        String stringFinalAmount = decimalFormat.format(finalAmount);
                        if (Bukkit.getPlayer(arguments[0]) != null) {
                            economy.withdrawPlayer(player, Double.parseDouble(stringFinalAmount));
                            Msg.send(player, ChatColor.GREEN + "Вы перечислили " + stringFinalAmount + "  игроку " + onlinePlayer.getName() + " комиссия: " + (decimalFormat.format(amount * commission / 100)) + ChatColor.WHITE + " " + ChatColor.GREEN  + " (" + Prison.getInstance().getCommission(player) + " %)");
                            economy.depositPlayer(onlinePlayer, Double.parseDouble(stringFinalAmount));
                            Msg.send(onlinePlayer, ChatColor.GREEN + "Вы получили " + stringFinalAmount + "  от игрока " + player.getName());
                        } else {
                            economy.withdrawPlayer(player, Double.parseDouble(stringFinalAmount));
                            Msg.send(player, ChatColor.GREEN + "Вы перечислили " + stringFinalAmount + "  игроку " + offlinePlayer.getName()  + " комиссия: " + (decimalFormat.format(amount * commission / 100)) + ChatColor.WHITE + " " + ChatColor.GREEN + " (" + Prison.getInstance().getCommission(player) + " %)");
                            economy.depositPlayer(offlinePlayer, Double.parseDouble(stringFinalAmount));
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Минимальная сумма перевода - 1 ");
                        return true;
                    }
                } else {
                    Msg.send(player, ChatColor.RED + "Второй аргумент должен быть числом!");
                    return true;
                }
            } else {
                Msg.send(player, ChatColor.RED + "Вы не можете отправлять деньги самому себе!");
                return true;
            }
        } else {
            Msg.send(player, ChatColor.RED + "Игрока с таким именем не существует!");
            return true;
        }
        return true;
    }
}
