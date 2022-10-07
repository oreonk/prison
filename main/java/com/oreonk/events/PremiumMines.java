package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PremiumMines implements Listener {
    @EventHandler
    public void useMines (PlayerInteractEvent event){
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BOOK) && Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta()).hasDisplayName() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Пропуск в") && !event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)){
            Player player = event.getPlayer();
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            if (item.getItemMeta().getDisplayName().equals("Пропуск в премиум шахту")){
                if (!player.hasPermission("prison.premium.mine")) {
                    Permission perms = Prison.getPermissions();
                    perms.playerAdd(player, "prison.premium.mine");
                    ItemStack itemCopy = item.clone();
                    itemCopy.setAmount(1);
                    player.getInventory().remove(itemCopy);
                    Msg.send(player, ChatColor.GREEN + "Вы активировали пропуск в шахту!");
                } else {
                    Msg.send(player, ChatColor.RED + "У вас уже есть пропуск!");
                }
            } else if (item.getItemMeta().getDisplayName().equals("Пропуск в подвал")){
                if (!player.hasPermission("prison.premium.podval")) {
                    Permission perms = Prison.getPermissions();
                    perms.playerAdd(player, "prison.premium.podval");
                    ItemStack itemCopy = item.clone();
                    itemCopy.setAmount(1);
                    player.getInventory().remove(itemCopy);
                    Msg.send(player, ChatColor.GREEN + "Вы активировали пропуск в шахту!");
                } else {
                    Msg.send(player, ChatColor.RED + "У вас уже есть пропуск!");
                }
            } else if (item.getItemMeta().getDisplayName().equals("Пропуск в канализацию")){
                if (!player.hasPermission("prison.premium.slums")) {
                    Permission perms = Prison.getPermissions();
                    perms.playerAdd(player, "prison.premium.slums");
                    ItemStack itemCopy = item.clone();
                    itemCopy.setAmount(1);
                    player.getInventory().remove(itemCopy);
                    Msg.send(player, ChatColor.GREEN + "Вы активировали пропуск в шахту!");
                } else {
                    Msg.send(player, ChatColor.RED + "У вас уже есть пропуск!");
                }
            } else if (item.getItemMeta().getDisplayName().equals("Пропуск в цитадель")){
                if (!player.hasPermission("prison.premium.castle")) {
                    Permission perms = Prison.getPermissions();
                    perms.playerAdd(player, "prison.premium.castle");
                    ItemStack itemCopy = item.clone();
                    itemCopy.setAmount(1);
                    player.getInventory().remove(itemCopy);
                    Msg.send(player, ChatColor.GREEN + "Вы активировали пропуск в шахту!");
                } else {
                    Msg.send(player, ChatColor.RED + "У вас уже есть пропуск!");
                }
            }
        }
    }
}
