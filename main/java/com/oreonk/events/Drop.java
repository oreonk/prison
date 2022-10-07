package com.oreonk.events;


import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Drop implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void itemDrop(PlayerDeathEvent event){
        event.setKeepInventory(true);
        Player player = event.getEntity();
        event.getDrops().removeIf(itemStack -> itemStack.getType().toString().contains("CHESTPLATE") || itemStack.getType().toString().contains("BOOTS") || itemStack.getType().toString().contains("LEGGINGS") || itemStack.getType().toString().contains("PICKAXE") || itemStack.getType().toString().contains("HELMET") || itemStack.getType().toString().contains("AXE") || itemStack.getType().toString().contains("SWORD") || itemStack.getType().toString().contains("LEGGINGS") || itemStack.getType().toString().contains("SHOVEL") || itemStack.getType().toString().contains("SHEARS"));
        for (ItemStack itemStack : event.getDrops()){
            if (!(itemStack.getType().toString().contains("CHESTPLATE") || itemStack.getType().toString().contains("BOOTS") || itemStack.getType().toString().contains("LEGGINGS") || itemStack.getType().toString().contains("PICKAXE") || itemStack.getType().toString().contains("HELMET") || itemStack.getType().toString().contains("AXE") || itemStack.getType().toString().contains("SWORD") || itemStack.getType().toString().contains("LEGGINGS") || itemStack.getType().toString().contains("SHOVEL") || itemStack.getType().toString().contains("SHEARS"))){
                player.getInventory().remove(itemStack);
            }
        }
        ItemStack itemStack = player.getInventory().getItemInOffHand();
        if (!(itemStack.getType().toString().contains("CHESTPLATE") || itemStack.getType().toString().contains("BOOTS") || itemStack.getType().toString().contains("LEGGINGS") || itemStack.getType().toString().contains("PICKAXE") || itemStack.getType().toString().contains("HELMET") || itemStack.getType().toString().contains("AXE") || itemStack.getType().toString().contains("SWORD") || itemStack.getType().toString().contains("LEGGINGS") || itemStack.getType().toString().contains("SHOVEL") || itemStack.getType().toString().contains("SHEARS") )){
            player.getInventory().setItemInOffHand(null);
        }
        int amount = Prison.getInstance().lvl.get(player);
        if (Prison.getEconomy().getBalance(player) >= amount * 10) {
            Prison.getEconomy().withdrawPlayer(player, amount * 10);
            Msg.send(player, ChatColor.RED + "Вы потеряли " + amount * 10 + " &f");
            Prison.getEconomy().depositPlayer(player.getKiller(), amount * 10);
            if (player.getKiller() != null) {
                Msg.send(player.getKiller(), ChatColor.GREEN + "Вы получили " + amount * 10 + " &f " + ChatColor.GREEN + "за убийство игрока " + player.getName());
            }
        }
    }
}

