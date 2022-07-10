package com.oreonk.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Donate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("shop")) {
                Player player = (Player) sender;
                Inventory gui = Bukkit.createInventory(player, 36, ChatColor.WHITE + ":offset_-16:");

                ItemStack booster = new ItemStack(Material.DIAMOND);
                ItemMeta booster_meta = booster.getItemMeta();
                booster_meta.setDisplayName(ChatColor.YELLOW + "Покупка приватного бустера");
                ArrayList<String> booster_lore = new ArrayList<>();
                booster_lore.add(ChatColor.GRAY + " ");
                booster_lore.add(ChatColor.GRAY + "Нажмите, для покупки приватного");
                booster_lore.add(ChatColor.GRAY + "бустера (множителя при продаже");
                booster_lore.add(ChatColor.GRAY + "блоков. 0.1 за покупку). Цена -");
                booster_lore.add(ChatColor.GRAY + "150 " + ChatColor.WHITE + "");
                booster_meta.setLore(booster_lore);
                booster.setItemMeta(booster_meta);

                ItemStack autoSell = new ItemStack(Material.GOLDEN_PICKAXE);
                ItemMeta autoSell_meta = autoSell.getItemMeta();
                autoSell_meta.setDisplayName(ChatColor.YELLOW + "Покупка автоселла");
                ArrayList<String> autoSell_lore = new ArrayList<>();
                autoSell_lore.add(ChatColor.GRAY + " ");
                autoSell_lore.add(ChatColor.GRAY + "Нажмите, для покупки автоматической");
                autoSell_lore.add(ChatColor.GRAY + "продажи выкопаных блоков. Цена -");
                autoSell_lore.add(ChatColor.GRAY + "250 " + ChatColor.WHITE + "");
                autoSell_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                autoSell_meta.setLore(autoSell_lore);
                autoSell.setItemMeta(autoSell_meta);

                ItemStack boosters = new ItemStack(Material.EXPERIENCE_BOTTLE);
                ItemMeta boosters_meta = boosters.getItemMeta();
                boosters_meta.setDisplayName(ChatColor.YELLOW + "Покупка глобальных бустеров");
                ArrayList<String> boosters_lore = new ArrayList<>();
                boosters_lore.add(ChatColor.GRAY + " ");
                boosters_lore.add(ChatColor.GRAY + "Нажмите, для покупки бустеров,");
                boosters_lore.add(ChatColor.GRAY + "распространяющихся на весь сервер");
                boosters_lore.add(ChatColor.RED + "В СЛЕДУЮЩЕМ МЕНЮ ПРЕДМЕТЫ ПОКУПАЮТСЯ");
                boosters_lore.add(ChatColor.RED + "ПО НАЖАТИЮ БЕЗ ПОДТВЕРЖДЕНИЯ");
                boosters_meta.setLore(boosters_lore);
                boosters.setItemMeta(boosters_meta);

                gui.setItem(1, booster);
                gui.setItem(2, autoSell);
                gui.setItem(3, boosters);


                player.openInventory(gui);
                return true;
            }
        }
        return true;
    }
}

