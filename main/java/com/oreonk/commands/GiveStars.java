package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveStars implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = null;
        if (sender instanceof Player){
            player = (Player) sender;
        }
        if (arguments.length != 2) {
            return false;
        } else if (Bukkit.getPlayer(arguments[0]) != null) {
            if (NumberUtils.isNumber(arguments[1])) {
                Player targetPlayer = Bukkit.getPlayer(arguments[0]);
                ItemStack stars = new ItemStack(Material.NETHER_STAR);
                ItemMeta stars_meta = stars.getItemMeta();
                stars_meta.setDisplayName("Звезда");
                stars.setItemMeta(stars_meta);
                stars.setAmount(Integer.parseInt(arguments[1]));
                if (targetPlayer.getInventory().contains(Material.AIR)){
                    targetPlayer.getInventory().addItem(stars);
                } else {
                    Location location = targetPlayer.getLocation();
                    Bukkit.getWorld(targetPlayer.getWorld().getName()).dropItemNaturally(location, stars);
                }
                return true;
            } else {
                if (player != null) {
                    Msg.send(player, "Второй аргумент должен быть числом!");
                    return true;
                } else {
                    Prison.getInstance().getLogger().info("Второй аргумент должен быть числом!");
                    return true;
                }
            }
        } else {
            if (player != null) {
                Msg.send(player, "Первым аргументом команды должен быть ник игрока!");
                return true;
            } else {
                Prison.getInstance().getLogger().info("Второй аргумент должен быть числом!");
                return true;
            }
        }
    }
}
