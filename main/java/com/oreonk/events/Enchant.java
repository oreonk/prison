package com.oreonk.events;

import com.oreonk.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class Enchant implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void itemEnchant(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Inventory gui1 = Bukkit.createInventory(player,27,"Ауф");
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getInventory().getItemInMainHand().getType() == Material.ENCHANTED_BOOK) {
                Map<Enchantment, Integer> enchants = player.getInventory().getItemInMainHand().getItemMeta().getEnchants();
                Inventory gui = Bukkit.createInventory(player, 27, "Зачарование");

                ItemStack item1 = player.getInventory().getItemInMainHand().clone();
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName("");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);
                gui.setItem(11, air);
                if (gui.getItem(11) != null){
                    player.openInventory(gui1);
                }
                gui.setItem(13, item1);
                gui.setItem(21, new ItemStack(Material.RED_WOOL));
                gui.setItem(23, new ItemStack(Material.GREEN_WOOL));
                player.openInventory(gui);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void itemEnchant(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() != null && event.getCurrentItem() != null && event.getView().getTitle().equalsIgnoreCase("Зачарование") && event.getSlot() != 11 && event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
            if (event.getCurrentItem().getType().equals(Material.GREEN_WOOL)&&event.getAction() != InventoryAction.HOTBAR_MOVE_AND_READD && event.getAction() != InventoryAction.HOTBAR_SWAP) {

            }
            if (event.getCurrentItem().getType().equals(Material.RED_WOOL)&&event.getAction() != InventoryAction.HOTBAR_MOVE_AND_READD && event.getAction() != InventoryAction.HOTBAR_SWAP) {
                player.closeInventory();
            }
            event.setCancelled(true);
        }
        if (event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || event.getAction() == InventoryAction.HOTBAR_SWAP){
            if (!event.getView().getTitle().equalsIgnoreCase("Crafting")) {
                event.setCancelled(true);
            }
        }
        if (event.getClickedInventory() != null && event.getCurrentItem() != null) {
            String name = event.getCurrentItem().getType().toString();
            if (event.getClickedInventory().getType() == InventoryType.PLAYER && !name.endsWith("_HELMET") && !name.endsWith("_CHESTPLATE") && !name.endsWith("_LEGGINGS") && !name.endsWith("_BOOTS") && !name.endsWith("_PICKAXE") && !name.endsWith("_AXE") && !name.endsWith("_SWORD") && event.getView().getTitle().equalsIgnoreCase("Зачарование")) {
                event.setCancelled(true);
            }
        }
    }
}


