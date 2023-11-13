package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.DecimalFormat;

public class AuctionCommission implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent event){
        if (event.getMessage().toLowerCase().contains("/ah sell ")){
            String digits = event.getMessage().replace("/ah sell ", "");
            //Замена цифр на юникод, для проверки наличия пробелов итп итд
            String digitsCheck = digits.replaceAll("[^0-9]", "");
            if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                if (digitsCheck.contains("")) {
                    Msg.send(event.getPlayer(), ChatColor.RED + "Команда содержит пробел или символы!");
                    event.setCancelled(true);
                } else {
                    String finalDigit = digits.replaceAll("[^0-9]", "");
                    DecimalFormat decimalFormat = new DecimalFormat("######0.##");
                    double commission = Double.parseDouble(finalDigit) * Prison.getInstance().getCommission(event.getPlayer()) / 100;
                    String commissionString = decimalFormat.format(commission).replace("," , ".");
                    double finalCommission = Double.parseDouble(commissionString);
                    Prison.getEconomy().withdrawPlayer(event.getPlayer(), finalCommission);
                    Msg.send(event.getPlayer(), ChatColor.WHITE + "Комиссия за выставление предмета: " + commissionString + "  (" + Prison.getInstance().getCommission(event.getPlayer()) + " %)");
                }
            }
        } else if (event.getMessage().toLowerCase().contains("/auc sell ")){
            String digits = event.getMessage().replace("/auc sell ", "");
            //Замена цифр на юникод, для проверки наличия пробелов итп итд
            String digitsCheck = digits.replaceAll("[^0-9]", "");
            if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                if (digitsCheck.contains("")) {
                    Msg.send(event.getPlayer(), ChatColor.RED + "Команда содержит пробел или символы!");
                    event.setCancelled(true);
                } else {
                    String finalDigit = digits.replaceAll("[^0-9]", "");
                    DecimalFormat decimalFormat = new DecimalFormat("######0.##");
                    double commission = Double.parseDouble(finalDigit) * Prison.getInstance().getCommission(event.getPlayer()) / 100;
                    String commissionString = decimalFormat.format(commission).replace("," , ".");
                    double finalCommission = Double.parseDouble(commissionString);
                    Prison.getEconomy().withdrawPlayer(event.getPlayer(), finalCommission);
                    Msg.send(event.getPlayer(), ChatColor.WHITE + "Комиссия за выставление предмета: " + commissionString + "  (" + Prison.getInstance().getCommission(event.getPlayer()) + " %)");
                }
            }
        } else if (event.getMessage().toLowerCase().contains("/auction sell ")) {
            String digits = event.getMessage().replace("/auction sell ", "");
            //Замена цифр на юникод, для проверки наличия пробелов итп итд
            String digitsCheck = digits.replaceAll("[^0-9]", "");
            if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                if (digitsCheck.contains("")) {
                    Msg.send(event.getPlayer(), ChatColor.RED + "Команда содержит пробел или символы!");
                    event.setCancelled(true);
                } else {
                    String finalDigit = digits.replaceAll("[^0-9]", "");
                    DecimalFormat decimalFormat = new DecimalFormat("######0.##");
                    double commission = Double.parseDouble(finalDigit) * Prison.getInstance().getCommission(event.getPlayer()) / 100;
                    String commissionString = decimalFormat.format(commission).replace(",", ".");
                    double finalCommission = Double.parseDouble(commissionString);
                    Prison.getEconomy().withdrawPlayer(event.getPlayer(), finalCommission);
                    Msg.send(event.getPlayer(), ChatColor.WHITE + "Комиссия за выставление предмета: " + commissionString + "  (" + Prison.getInstance().getCommission(event.getPlayer()) + " %)");
                }
            }
        } else if (event.getMessage().toLowerCase().contains("/auctionhouse sell ")) {
            String digits = event.getMessage().replace("/auctionhouse sell ", "");
            //Замена цифр на юникод, для проверки наличия пробелов итп итд
            String digitsCheck = digits.replaceAll("[^0-9]", "");
            if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                if (digitsCheck.contains("")) {
                    Msg.send(event.getPlayer(), ChatColor.RED + "Команда содержит пробел или символы!");
                    event.setCancelled(true);
                } else {
                    String finalDigit = digits.replaceAll("[^0-9]", "");
                    DecimalFormat decimalFormat = new DecimalFormat("######0.##");
                    double commission = Double.parseDouble(finalDigit) * Prison.getInstance().getCommission(event.getPlayer()) / 100;
                    String commissionString = decimalFormat.format(commission).replace(",", ".");
                    double finalCommission = Double.parseDouble(commissionString);
                    Prison.getEconomy().withdrawPlayer(event.getPlayer(), finalCommission);
                    Msg.send(event.getPlayer(), ChatColor.WHITE + "Комиссия за выставление предмета: " + commissionString + "  (" + Prison.getInstance().getCommission(event.getPlayer()) + " %)");
                }
            }
        }
    }
}
