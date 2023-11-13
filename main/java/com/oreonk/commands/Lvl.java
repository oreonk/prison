package com.oreonk.commands;

import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Lvl implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int level = Prison.getPlugin(Prison.class).lvl.get(player);
            Economy economy = Prison.getEconomy();
            Inventory gui = Bukkit.createInventory(player, 27, "Повышение уровня");
            ItemStack confirm = new ItemStack(Material.GREEN_CONCRETE);
            ItemStack cancel = new ItemStack(Material.RED_CONCRETE);
            ItemStack stat = new ItemStack(Material.EXPERIENCE_BOTTLE);

            ItemMeta confirm_meta = confirm.getItemMeta();
            confirm_meta.setDisplayName(ChatColor.GREEN + "Повысить уровень");
            confirm.setItemMeta(confirm_meta);

            ItemMeta cancel_meta = cancel.getItemMeta();
            cancel_meta.setDisplayName(ChatColor.RED + "Назад");
            cancel.setItemMeta(cancel_meta);

            ItemMeta stat_meta = stat.getItemMeta();
            ArrayList<String> stat_lore = new ArrayList<>();
            int balance = (int) economy.getBalance(player);
            String st = String.valueOf(Prison.getInstance().getMinedBlocks(player));
            int maxLevel = Prison.getPlugin(Prison.class).getLvlConfig().getConfigurationSection("Lvl").getKeys(true).size();
            if (level < maxLevel) {
                int needLvl = level+1;
                String key = String.valueOf(needLvl);
                ArrayList<String> configs = (ArrayList<String>) Prison.getPlugin(Prison.class).getLvlConfig().getConfigurationSection("Lvl").getStringList(key);
                stat_meta.setDisplayName(ChatColor.YELLOW + "Повышение до " + key + " уровня");
                stat_lore.add(ChatColor.GRAY + String.valueOf(balance) + " / " + configs.get(1) + ChatColor.WHITE + " ");
                stat_lore.add(ChatColor.GRAY + st + " / " + configs.get(0) + " Блоков");
                stat_meta.setLore(stat_lore);
            } else {
                stat_meta.setDisplayName(ChatColor.GREEN + "Максимальный уровень!");
            }
            stat.setItemMeta(stat_meta);
            ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta filler_meta = filler.getItemMeta();
            filler_meta.setDisplayName("");
            filler.setItemMeta(filler_meta);
            for (int slot = 0; slot < gui.getSize(); slot++)
                gui.setItem(slot, filler);
            ItemStack air = new ItemStack(Material.AIR);

            gui.setItem(10, air);
            gui.setItem(11, cancel);
            gui.setItem(12, air);
            gui.setItem(13, stat);
            gui.setItem(14, air);
            gui.setItem(15, confirm);
            gui.setItem(16, air);
            player.openInventory(gui);
            return true;
        }
        return false;
    }
}
