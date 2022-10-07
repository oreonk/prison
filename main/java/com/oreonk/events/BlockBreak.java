package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class BlockBreak implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void blockBreak(BlockBreakEvent event){
        //Автоселл и статистика
        Player player = event.getPlayer();
        ItemStack pickaxe = player.getInventory().getItemInMainHand();
        Collection<ItemStack> drops = event.getBlock().getDrops(pickaxe);
        if (!player.hasPermission("prison.autosell.on")) {
            if (!event.getBlock().getType().toString().contains("ICE")) {
                for (ItemStack stack : drops) {
                    player.getInventory().addItem(stack);
                }
            } else {
                ItemStack stack = new ItemStack(event.getBlock().getType());
                player.getInventory().addItem(stack);
            }
        } else {
            if (Prison.getInstance().blocks.containsKey(event.getBlock().getType().toString())) {
                Double globalBoost = 0.0;
                if (!Prison.getPlugin(Prison.class).booster.isEmpty()) {
                    globalBoost = 1.0;
                }
                Double multiplier = Prison.getPlugin(Prison.class).mltp.get(player);
                Double boost = Prison.getPlugin(Prison.class).bst.get(player);
                int privateBoost = 0;
                if (Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
                    privateBoost = 1;
                }
                if (!event.getBlock().getType().toString().contains("ICE")) {
                    for (ItemStack stack : drops) {
                        Economy economy = Prison.getEconomy();
                        int amount = stack.getAmount();
                        int price = Prison.getInstance().blocks.get(stack.getType().toString());
                        economy.depositPlayer(player, price * (multiplier + boost + globalBoost + privateBoost) * amount);
                    }
                }else {
                Economy economy = Prison.getEconomy();
                int price = Prison.getInstance().blocks.get(event.getBlock().getType().toString());
                economy.depositPlayer(player, price * (multiplier + boost + globalBoost + privateBoost));
            }
            }
        }
        if (!player.getCanPickupItems()) {
            Msg.send(player, "&4Ваш инвентарь полон!");
            event.setCancelled(true);
        }
        if (!Prison.getInstance().boosterBlocks.isEmpty() && Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())){
            player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 3);
        }
        else if (!Prison.getInstance().boosterBlocks.isEmpty() && !Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())) {
            player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 2);
        } else if (Prison.getInstance().boosterBlocks.isEmpty() && Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())){
            player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 2);
        }
        else {
            player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 1);
        }
        event.setDropItems(false);
    }
}
