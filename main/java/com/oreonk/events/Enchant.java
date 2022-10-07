package com.oreonk.events;

import com.oreonk.Msg;
import me.davidml16.acubelets.api.CubeletsAPI;
import me.davidml16.acubelets.handlers.CubeletBoxHandler;
import me.davidml16.acubelets.objects.Cubelet;
import me.davidml16.acubelets.objects.CubeletType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class Enchant implements Listener {
    int bool = 0;
    @EventHandler(priority = EventPriority.HIGH)
    public void itemEnchant(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.getInventory().getItemInMainHand().getType() == Material.ENCHANTED_BOOK) {
                bool = 0;
                Inventory gui = Bukkit.createInventory(player, 27, "Зачарование");

                ItemStack item1 = player.getInventory().getItemInMainHand().clone();
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);
                gui.setItem(11, air);
                gui.setItem(15, item1);
                ItemStack deny = new ItemStack(Material.RED_WOOL);
                ItemMeta deny_meta = deny.getItemMeta();
                deny_meta.setDisplayName(ChatColor.RED + "Отмена");
                deny.setItemMeta(deny_meta);
                gui.setItem(21, deny);
                ItemStack accept = new ItemStack(Material.GREEN_WOOL);
                ItemMeta accept_meta = accept.getItemMeta();
                accept_meta.setDisplayName(ChatColor.GREEN + "Продолжить");
                accept.setItemMeta(accept_meta);
                gui.setItem(23, accept);

                player.openInventory(gui);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void itemEnchant(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() != null && event.getView().getTitle().equalsIgnoreCase("Зачарование") && event.getClickedInventory() == event.getView().getTopInventory() && event.getCurrentItem()!=null) {
            if (event.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                if (player.getOpenInventory().getTopInventory().getItem(11)==null){
                    Msg.send(player, "&4Вы не положили предмет, который хотите зачаровать!");
                    bool = 1;
                    player.closeInventory();
                }else{
                    bool = 2;
                    player.updateInventory();
                    ItemStack item = player.getOpenInventory().getTopInventory().getItem(11).clone();
                    ItemStack itemCopy = player.getOpenInventory().getTopInventory().getItem(11).clone();
                    EnchantmentStorageMeta book = (EnchantmentStorageMeta)player.getOpenInventory().getTopInventory().getItem(15).getItemMeta();
                    Map<Enchantment, Integer> bookEnchants = book.getStoredEnchants();
                    Enchantment ench = null;
                    for (Map.Entry<Enchantment, Integer> mapEntry : bookEnchants.entrySet()){
                        ench = mapEntry.getKey();
                    }
                    if (ench.canEnchantItem(item)) {
                        item.addEnchantments(bookEnchants);
                        Inventory gui = Bukkit.createInventory(player, 27, "Подтверждение зачарования");
                        ItemStack item1 = player.getInventory().getItemInMainHand().clone();
                        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                        ItemMeta filler_meta = filler.getItemMeta();
                        filler_meta.setDisplayName(" ");
                        filler.setItemMeta(filler_meta);
                        for (int slot = 0; slot < gui.getSize(); slot++)
                            gui.setItem(slot, filler);
                        ItemStack air = new ItemStack(Material.AIR);
                        gui.setItem(11, itemCopy);
                        gui.setItem(15, item);
                        gui.setItem(13, item1);
                        ItemStack deny = new ItemStack(Material.RED_WOOL);
                        ItemMeta deny_meta = deny.getItemMeta();
                        deny_meta.setDisplayName(ChatColor.RED + "Отменить зачарование");
                        deny.setItemMeta(deny_meta);
                        gui.setItem(21, deny);
                        ItemStack accept = new ItemStack(Material.GREEN_WOOL);
                        ItemMeta accept_meta = accept.getItemMeta();
                        accept_meta.setDisplayName(ChatColor.GREEN + "Зачаровать");
                        accept.setItemMeta(accept_meta);
                        gui.setItem(23, accept);
                        player.openInventory(gui);
                        bool = 0;
                    } else{
                        Msg.send(player, "&4Вы не можете наложить это зачарование на предмет!");
                        bool = 0;
                        player.closeInventory();
                    }
                }
            } else if (event.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                player.closeInventory();
            }
        } else if(event.getView().getTitle().equalsIgnoreCase("Подтверждение зачарования") && event.getClickedInventory()!=null && event.getCurrentItem()!=null){
            if (event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                bool = 3;
                player.getInventory().addItem(player.getOpenInventory().getTopInventory().getItem(15));
                player.getInventory().removeItem(player.getOpenInventory().getTopInventory().getItem(13));
                player.closeInventory();
            } else if (event.getCurrentItem().getType().equals(Material.RED_WOOL)){
                player.closeInventory();
            }
        }
        if (event.getClickedInventory() != null & event.getCurrentItem() != null) {
            String name = event.getCurrentItem().getType().toString();
            if (!name.endsWith("_HELMET") && !name.endsWith("_CHESTPLATE") && !name.endsWith("_LEGGINGS") && !name.endsWith("_BOOTS") && !name.endsWith("_PICKAXE") && !name.endsWith("_AXE") && !name.endsWith("_SWORD") && event.getView().getTitle().equalsIgnoreCase("Зачарование")) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void inventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Зачарование") || event.getView().getTitle().equalsIgnoreCase("Подтверждение зачарования")){
            Player player = (Player) event.getPlayer();
            if (player.getOpenInventory().getTopInventory().getItem(11)!=null && bool == 0){
                player.getInventory().addItem(player.getOpenInventory().getTopInventory().getItem(11));
            }
        }
    }
}


