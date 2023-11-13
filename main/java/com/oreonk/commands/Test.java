package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.UUID;

public class Test implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 54, "Тест");

            ItemStack authority_up = new ItemStack(Material.LEATHER_HELMET);
            ItemStack authority_down = new ItemStack(Material.LEATHER_BOOTS);
            ItemStack religion_up = new ItemStack(Material.IRON_HELMET);
            ItemStack religion_down = new ItemStack(Material.IRON_BOOTS);
            ItemStack lvl_up = new ItemStack(Material.GOLDEN_HELMET);
            ItemStack lvl_down = new ItemStack(Material.GOLDEN_BOOTS);
            ItemStack money = new ItemStack(Material.GOLD_INGOT);
            ItemStack cell_up = new ItemStack(Material.NETHERITE_HELMET);
            ItemStack cell_down = new ItemStack(Material.NETHERITE_BOOTS);
            ItemStack blessing = new ItemStack(Material.CLOCK);

            ItemMeta authority_up_meta = authority_up.getItemMeta();
            authority_up_meta.setDisplayName("Повысить авторитет на 10");
            authority_up.setItemMeta(authority_up_meta);

            ItemMeta authority_down_meta = authority_up.getItemMeta();
            authority_down_meta.setDisplayName("Понизить авторитет на 10");
            authority_down.setItemMeta(authority_down_meta);

            ItemMeta religion_up_meta = religion_up.getItemMeta();
            religion_up_meta.setDisplayName("Повысить религию на 10");
            religion_up.setItemMeta(religion_up_meta);

            ItemMeta religion_down_meta = religion_down.getItemMeta();
            religion_down_meta.setDisplayName("Понизить религию на 10");
            religion_down.setItemMeta(religion_down_meta);

            ItemMeta lvl_up_meta = lvl_up.getItemMeta();
            lvl_up_meta.setDisplayName("Повысить уровень на 1");
            lvl_up.setItemMeta(lvl_up_meta);

            ItemMeta lvl_down_meta = lvl_down.getItemMeta();
            lvl_down_meta.setDisplayName("Понизить уровень на 1");
            lvl_down.setItemMeta(lvl_down_meta);

            ItemMeta money_meta = money.getItemMeta();
            money_meta.setDisplayName("Выдать 100 монет");
            money.setItemMeta(money_meta);

            ItemMeta cell_up_meta = cell_up.getItemMeta();
            cell_up_meta.setDisplayName("+1 уровень камеры");
            cell_up.setItemMeta(cell_up_meta);

            ItemMeta cell_down_meta = cell_down.getItemMeta();
            cell_down_meta.setDisplayName("-1 уровень камеры");
            cell_down.setItemMeta(cell_down_meta);

            ItemMeta blessing_meta = blessing.getItemMeta();
            blessing_meta.setDisplayName("Сбросить кд благославления");
            blessing.setItemMeta(blessing_meta);

            ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemStack diamond_helmet = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack diamond_chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack diamond_leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack diamond_boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack diamond_pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);

            gui.setItem(0, authority_up);
            gui.setItem(9, authority_down);
            gui.setItem(1, religion_up);
            gui.setItem(10, religion_down);
            gui.setItem(2, lvl_up);
            gui.setItem(11, lvl_down);
            gui.setItem(3, money);
            gui.setItem(12, cell_up);
            gui.setItem(4, cell_down);
            gui.setItem(5, blessing);
            gui.setItem(18, diamond_sword);
            gui.setItem(19, diamond_helmet);
            gui.setItem(20, diamond_chestplate);
            gui.setItem(21, diamond_leggings);
            gui.setItem(22, diamond_boots);
            gui.setItem(23, diamond_pickaxe);
            player.openInventory(gui);
        }
        return true;
    }
}
