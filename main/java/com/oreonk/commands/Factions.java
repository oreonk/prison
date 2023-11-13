package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
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

public class Factions implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (Prison.getInstance().lvl.get(player) >= 5) {
            if (Prison.getInstance().faction.get(player).equals("0")) {
                Inventory gui = Bukkit.createInventory(player, 27, "Выбор фракции");

                ItemStack white = new ItemStack(Material.WHITE_WOOL);
                ItemStack black = new ItemStack(Material.COAL_BLOCK);
                ItemStack asian = new ItemStack(Material.GOLD_BLOCK);
                ItemStack cancel = new ItemStack(Material.RED_WOOL);

                ItemMeta white_meta = white.getItemMeta();
                ItemMeta black_meta = black.getItemMeta();
                ItemMeta asian_meta = asian.getItemMeta();
                ItemMeta cancel_meta = cancel.getItemMeta();

                white_meta.setDisplayName(ChatColor.WHITE + "Выбрать фракцию белых");
                black_meta.setDisplayName(ChatColor.DARK_GRAY + "Выбрать фракцию черных");
                asian_meta.setDisplayName(ChatColor.YELLOW + "Выбрать фракцию азиатов");
                cancel_meta.setDisplayName(ChatColor.RED + "Закрыть");
                white.setItemMeta(white_meta);
                black.setItemMeta(black_meta);
                asian.setItemMeta(asian_meta);
                cancel.setItemMeta(cancel_meta);

                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);
                gui.setItem(10, air);
                gui.setItem(11, white);
                gui.setItem(12, air);
                gui.setItem(13, asian);
                gui.setItem(14, air);
                gui.setItem(15, black);
                gui.setItem(16, air);
                gui.setItem(22, cancel);
                player.openInventory(gui);
                return true;
            } else {
                Msg.send(player, ChatColor.RED + "Вы уже состоите во фракции!");
                return true;
            }
        }
        else {
            Msg.send(player, ChatColor.RED + "Для вступления во фракцию вам необходим 5 уровень!");
            return true;
        }
    }
}

