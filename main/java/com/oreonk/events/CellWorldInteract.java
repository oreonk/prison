package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class CellWorldInteract implements Listener {
    @EventHandler
    public void menuInteract (InventoryClickEvent event){
        if (event.getView().getTitle().contains("Улучшение начальной камеры") && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)){
            Player player = (Player) event.getWhoClicked();
            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
            ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
            double price = Double.parseDouble(config.get(0));
            int balance = (int) Prison.getEconomy().getBalance(player);
            int lvl = Integer.parseInt(config.get(2));
            if (cellLvl < 4 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl){
                int newCellLvl = cellLvl + 1;
                Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                });
                Msg.send(player, ChatColor.GREEN + "Камера успешна улучшена!");
            } else if (cellLvl >= 4){
                Msg.send(player, ChatColor.RED + "Улучшите камеру в новом месте. Используйте /cell для телепортации");
            } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl){
                Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
            }
        } else if (event.getView().getTitle().contains("Улучшение небольшой камеры") && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)){
            Player player = (Player) event.getWhoClicked();
            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
            ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
            double price = Double.parseDouble(config.get(0));
            int balance = (int) Prison.getEconomy().getBalance(player);
            int lvl = Integer.parseInt(config.get(2));
            if (cellLvl < 8 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl){
                int newCellLvl = cellLvl + 1;
                Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                });
                Msg.send(player, ChatColor.GREEN + "Камера успешна улучшена!");
            } else if (cellLvl >= 8){
                Msg.send(player, ChatColor.RED + "Улучшите камеру в новом месте. Используйте /cell для телепортации");
            } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl){
                Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
            }
        } else if (event.getView().getTitle().contains("Улучшение средней камеры") && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)){
            Player player = (Player) event.getWhoClicked();
            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
            ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
            double price = Double.parseDouble(config.get(0));
            int balance = (int) Prison.getEconomy().getBalance(player);
            int lvl = Integer.parseInt(config.get(2));
            if (cellLvl < 14 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl){
                int newCellLvl = cellLvl + 1;
                Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                });
                Msg.send(player, ChatColor.GREEN + "Камера успешна улучшена!");
            } else if (cellLvl >= 14){
                Msg.send(player, ChatColor.RED + "Улучшите камеру в новом месте. Используйте /cell для телепортации");
            } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl){
                Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
            }
        } else if (event.getView().getTitle().contains("Улучшение большой камеры") && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)){
            Player player = (Player) event.getWhoClicked();
            int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
            ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
            double price = Double.parseDouble(config.get(0));
            int balance = (int) Prison.getEconomy().getBalance(player);
            int lvl = Integer.parseInt(config.get(2));
            if (cellLvl < 14 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl){
                int newCellLvl = cellLvl + 1;
                Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                    Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                });
                Msg.send(player, ChatColor.GREEN + "Камера успешна улучшена!");
            } else if (cellLvl >= 14){
                Msg.send(player, ChatColor.DARK_GREEN + "Камера максимальна.");
            } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl){
                Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
            }
        }
    }
}
