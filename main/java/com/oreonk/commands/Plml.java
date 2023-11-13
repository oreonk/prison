package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.UUID;

public class Plml implements CommandExecutor {
    @Override
    //Бесконечный бустер до конца вайпа.
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (arguments.length != 2) {
            return false;
        }
        if (Bukkit.getPlayer(arguments[0]) != null) {
            if (NumberUtils.isNumber(arguments[1])) {
                if (Bukkit.getPlayer(arguments[0]).isOnline()) {
                    UUID uuid = Bukkit.getPlayer(arguments[0]).getUniqueId();
                    if (!Prison.getInstance().plusMultiplier.containsKey(Bukkit.getPlayer(arguments[0]))) {
                        DecimalFormat decimalFormat = new DecimalFormat("######0.###");
                        String decimalFormatString = decimalFormat.format(Prison.getInstance().getDatabase().getPlusMultiplier(uuid)).replace("," , ".");
                        double finalDoubleAmount = (Double.parseDouble(decimalFormatString)) + (Double.parseDouble(arguments[1]));
                        String finalDoubleString = decimalFormat.format(finalDoubleAmount).replace("," , ".");
                        Prison.getInstance().plusMultiplier.put(Bukkit.getPlayer(arguments[0]), finalDoubleAmount);
                        Prison.getInstance().getDatabase().updatePlusMultiplier(uuid, finalDoubleString);
                        Prison.getInstance().getLogger().info("Нет велью в хеше. Добавил стринг " + finalDoubleString + " дабл " + finalDoubleAmount);
                        return true;
                    } else {
                        DecimalFormat decimalFormat = new DecimalFormat("######0.###");
                        String decimalFormatString = decimalFormat.format(Prison.getInstance().getDatabase().getPlusMultiplier(uuid)).replace("," , ".");
                        double finalDoubleAmount = (Double.parseDouble(decimalFormatString)) + (Double.parseDouble(arguments[1]));
                        String finalDoubleString = decimalFormat.format(finalDoubleAmount).replace("," , ".");
                        Prison.getInstance().plusMultiplier.replace(Bukkit.getPlayer(arguments[0]), finalDoubleAmount);
                        Prison.getInstance().getDatabase().updatePlusMultiplier(uuid, finalDoubleString);
                        Prison.getInstance().getLogger().info("Есть велью в хеше. Добавил стринг " + finalDoubleString + " дабл " + finalDoubleAmount);
                        return true;
                    }
                } else {
                    if (player == null){
                        Prison.getInstance().getLogger().info("Игрок оффлайн");
                    } else {
                        Msg.send(player, "Игрок оффлайн");
                    }
                    return true;
                }
            } else {
                if (player == null){
                    Prison.getInstance().getLogger().info("Аргумент - не число");
                } else {
                    Msg.send(player, "Аргумент - не число");
                }
                return true;
            }
        } else {
            if (player == null){
                Prison.getInstance().getLogger().info("Нет такого игрока");
            } else {
                Msg.send(player, "Нет такого игрока");
            }
            return true;
        }
    }
}