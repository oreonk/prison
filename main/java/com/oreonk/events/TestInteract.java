package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestInteract implements Listener {
    @EventHandler
    public void menuInteract (InventoryClickEvent event){
        if (event.getView().getTitle().contains("Тест") && event.getCurrentItem() != null){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getType().equals(Material.LEATHER_HELMET)){
                double stat = Prison.getInstance().authorityStat.get(player);
                Prison.getInstance().authorityStat.replace(player, stat+10);
            } else if (event.getCurrentItem().getType().equals(Material.LEATHER_BOOTS)){
                double stat = Prison.getInstance().authorityStat.get(player);
                Prison.getInstance().authorityStat.replace(player, stat-10);
            } else if (event.getCurrentItem().getType().equals(Material.IRON_HELMET)){
                int stat = Prison.getInstance().religion.get(player);
                Prison.getInstance().religion.replace(player, stat+10);
            } else if (event.getCurrentItem().getType().equals(Material.IRON_BOOTS)){
                int stat = Prison.getInstance().religion.get(player);
                Prison.getInstance().religion.replace(player, stat-10);
            } else if (event.getCurrentItem().getType().equals(Material.GOLDEN_HELMET)){
                int stat = Prison.getInstance().lvl.get(player);
                Prison.getInstance().lvl.replace(player, stat+1);
            } else if (event.getCurrentItem().getType().equals(Material.GOLDEN_BOOTS)){
                int stat = Prison.getInstance().lvl.get(player);
                Prison.getInstance().lvl.replace(player, stat-1);
            } else if (event.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
                Prison.getEconomy().depositPlayer(player, 100);
            }else if (event.getCurrentItem().getType().equals(Material.CLOCK)){
                Prison.getInstance().blessing.remove(player.getUniqueId());
            }else if (event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)){
                ItemStack stack = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta stack_meta = stack.getItemMeta();
                stack_meta.setUnbreakable(true);
                stack_meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                stack.setItemMeta(stack_meta);
                player.getInventory().addItem(stack);
            } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)){
                ItemStack stack = new ItemStack(Material.DIAMOND_HELMET);
                ItemMeta stack_meta = stack.getItemMeta();
                stack_meta.setUnbreakable(true);
                stack_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                stack.setItemMeta(stack_meta);
                player.getInventory().addItem(stack);
            } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_CHESTPLATE)){
                ItemStack stack = new ItemStack(Material.DIAMOND_CHESTPLATE);
                ItemMeta stack_meta = stack.getItemMeta();
                stack_meta.setUnbreakable(true);
                stack_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                stack.setItemMeta(stack_meta);
                player.getInventory().addItem(stack);
            } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_LEGGINGS)){
                ItemStack stack = new ItemStack(Material.DIAMOND_LEGGINGS);
                ItemMeta stack_meta = stack.getItemMeta();
                stack_meta.setUnbreakable(true);
                stack_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                stack.setItemMeta(stack_meta);
                player.getInventory().addItem(stack);
            } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_BOOTS)){
                ItemStack stack = new ItemStack(Material.DIAMOND_BOOTS);
                ItemMeta stack_meta = stack.getItemMeta();
                stack_meta.setUnbreakable(true);
                stack_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
                stack.setItemMeta(stack_meta);
                player.getInventory().addItem(stack);
            } else if (event.getCurrentItem().getType().equals(Material.DIAMOND_PICKAXE)){
                ItemStack stack = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta stack_meta = stack.getItemMeta();
                stack_meta.setUnbreakable(true);
                stack_meta.addEnchant(Enchantment.DIG_SPEED, 3, true);
                stack.setItemMeta(stack_meta);
                player.getInventory().addItem(stack);
            } else if (event.getCurrentItem().getType().equals(Material.NETHERITE_HELMET)){
                int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                int newCellLvl = cellLvl+1;
                Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
            } else if (event.getCurrentItem().getType().equals(Material.NETHERITE_BOOTS)){
                int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                int newCellLvl = cellLvl-1;
                Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
            }
        }
    }
}
