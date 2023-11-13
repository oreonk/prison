package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class BlockBreak implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void blockBreak(BlockBreakEvent event){
        //Автоселл и статистика
        Player player = event.getPlayer();
        ItemStack pickaxe = player.getInventory().getItemInMainHand();
        Collection<ItemStack> drops = event.getBlock().getDrops(pickaxe);
        if (!player.hasPermission("prison.autosell.on")) {
            if (!event.getBlock().getType().toString().contains("ICE") && !event.getBlock().getType().toString().contains("VINE") && !event.getBlock().getType().toString().contains("LEAVES")) {
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
                Double privateBoost = 0.0;
                if (Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
                    if (Prison.getInstance().privateBoosterMultiplier.get(player.getUniqueId()) == 2){
                            privateBoost = 1.0;
                        } else if (Prison.getInstance().privateBoosterMultiplier.get(player.getUniqueId()) == 1){
                            privateBoost = 0.5;
                        }
                }
                Double plusMultiplier = Prison.getInstance().plusMultiplier.get(player);
                if (!event.getBlock().getType().toString().contains("ICE") && !event.getBlock().getType().toString().contains("VINE") && !event.getBlock().getType().toString().contains("LEAVES")) {
                    for (ItemStack stack : drops) {
                        Economy economy = Prison.getEconomy();
                        int amount = stack.getAmount();
                        double price = Prison.getInstance().blocks.get(stack.getType().toString());
                        economy.depositPlayer(player, price * (multiplier + boost + globalBoost + privateBoost + plusMultiplier) * amount);
                    }
                }else {
                Economy economy = Prison.getEconomy();
                double price = Prison.getInstance().blocks.get(event.getBlock().getType().toString());
                economy.depositPlayer(player, price * (multiplier + boost + globalBoost + privateBoost + plusMultiplier));
            }
            }
        }
        if (!player.getCanPickupItems()) {
            Msg.send(player, "&4Ваш инвентарь полон!");
            event.setCancelled(true);
        }
        if (Prison.getInstance().blocks.containsKey(event.getBlock().getType().toString())) {
            if (Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId()) && Prison.getInstance().privateBlockBoosterMultiplier.get(player.getUniqueId()) == 1) {
                if (!Prison.getInstance().blockCounter.containsKey(player)) {
                    Prison.getInstance().blockCounter.put(player, 2);
                } else {
                    int counter = Prison.getInstance().blockCounter.get(player);
                    if (counter < 2) {
                        Prison.getInstance().blockCounter.replace(player, counter + 1);
                    } else {
                        Prison.getInstance().blockCounter.replace(player, 1);
                        if (Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId()) && Prison.getInstance().privateBlockBoosterMultiplier.get(player.getUniqueId()) == 1 && Prison.getInstance().boosterBlocks.isEmpty()) {
                            player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 3);
                        } else if (Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId()) && Prison.getInstance().privateBlockBoosterMultiplier.get(player.getUniqueId()) == 1 && !Prison.getInstance().boosterBlocks.isEmpty()) {
                            player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 5);
                        }
                    }
                }
            } else {
                if (!Prison.getInstance().boosterBlocks.isEmpty() && Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId()) && Prison.getInstance().privateBlockBoosterMultiplier.get(player.getUniqueId()) == 2) {
                    player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 3);
                } else if (!Prison.getInstance().boosterBlocks.isEmpty() && !Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())) {
                    player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 2);
                } else if (Prison.getInstance().boosterBlocks.isEmpty() && Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId()) && Prison.getInstance().privateBlockBoosterMultiplier.get(player.getUniqueId()) == 2) {
                    player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 2);
                } else {
                    player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 1);
                }
            }
        }
        event.setDropItems(false);

        //Ачивка на 1000 блоков
        int blocks = Prison.getInstance().getMinedBlocks(player);
        if (blocks == 1000){
            if (!Prison.getInstance().DIG_ONE.contains(player.getUniqueId().toString())){
                Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Копай пока молодой " + ChatColor.GREEN + "проверьте его в /achievements");
                try {
                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("DIG_ONE");
                    list.add(player.getUniqueId().toString());
                    Prison.getInstance().getAchievementsConfig().set("DIG_ONE", list);
                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                    Prison.getInstance().DIG_ONE.add(player.getUniqueId().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
