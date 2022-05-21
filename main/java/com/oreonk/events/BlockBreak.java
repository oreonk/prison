package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            FileConfiguration config = TestPlug.getPlugin(TestPlug.class).getConfig();
            int size = config.getConfigurationSection("blocks").getKeys(false).size();
            String type = event.getBlock().getType().toString();
            for(int i = 1; i <= size; i++){
                String blockName = config.getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(0);
                if (type.equals(blockName)){
                    TestPlug plugin = TestPlug.getPlugin(TestPlug.class);
                    Double multipilier = 0.0;
                    try{
                        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
                        statement.setString(1, player.getUniqueId().toString());
                        ResultSet results = statement.executeQuery();
                        results.next();
                        multipilier = results.getDouble("MULTIPLIER");
                        statement.close();
                        results.close();
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                    String priceString = config.getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(1);
                    int price = Integer.parseInt(priceString);
                    Economy economy = TestPlug.getEconomy();
                    economy.depositPlayer(player, price * multipilier);
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
