package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;



public class BlockBreak implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void blockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        Collection<ItemStack> drops = event.getBlock().getDrops(pickaxe);
        if (!player.hasPermission("prison.autosell.on")) {
            for (ItemStack stack : drops) {
                player.getInventory().addItem(stack);
            }
        } else{
            Double globalBoost = 0.0;
            if (!TestPlug.getPlugin(TestPlug.class).booster.isEmpty()){
                globalBoost = 1.0;
            }
            Double multiplier = TestPlug.getPlugin(TestPlug.class).mltp.get(player);
            Double boost = TestPlug.getPlugin(TestPlug.class).bst.get(player);
            FileConfiguration config = TestPlug.getPlugin(TestPlug.class).getConfig();
            int size = config.getConfigurationSection("blocks").getKeys(false).size();
            String type = event.getBlock().getType().toString();
            for(int i = 1; i <= size; i++){
                String blockName = config.getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(0);
                if (type.equals(blockName)){
                    String priceString = config.getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(1);
                    int price = Integer.parseInt(priceString);
                    Economy economy = TestPlug.getEconomy();
                    economy.depositPlayer(player, price * (multiplier+boost+globalBoost));
                    break;
                }
            }
        }
        if (!player.getCanPickupItems()) {
            Msg.send(player, "&4Ваш инвентарь полон!");
            event.setCancelled(true);
        }
        player.incrementStatistic(Statistic.MINE_BLOCK, event.getBlock().getType(), 1);
        event.setDropItems(false);
    }
}
