package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BlockSell implements Listener {
    Plugin plugin = TestPlug.getPlugin(TestPlug.class);


    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteraction(PlayerInteractEvent event) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                int amount;
                int finalPrice = 0;
                Economy economy = TestPlug.getEconomy();
                Player player = event.getPlayer();
                int count = plugin.getConfig().getConfigurationSection("blocks").getKeys(false).size();
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    Block block = event.getClickedBlock();
                    if (block.getType() == Material.OAK_WALL_SIGN) {
                        PlayerInventory inventory = player.getInventory();
                        for (int c = 1; c <= count; c++) {
                            String blockNumber = String.valueOf(c);
                            String name = plugin.getConfig().getConfigurationSection("blocks").getStringList(blockNumber).get(0);
                            String pr = plugin.getConfig().getConfigurationSection("blocks").getStringList(blockNumber).get(1);
                            Integer price = Integer.valueOf(pr);
                            amount = 0;
                            for (ItemStack stack : inventory.all(Material.valueOf(name)).values()) {
                                if (stack != null && stack.getType() == Material.valueOf(name)) {
                                    amount = amount + stack.getAmount();
                                    player.getInventory().remove(Material.valueOf(name));
                                }
                            }
                            TestPlug plug = TestPlug.getPlugin(TestPlug.class);
                            double multipilier = 0.0;
                            try{
                                PreparedStatement statement = plug.getConnection().prepareStatement("SELECT * FROM " + plug.table + " WHERE UUID=?");
                                statement.setString(1, player.getUniqueId().toString());
                                ResultSet results = statement.executeQuery();
                                results.next();
                                multipilier = results.getDouble("MULTIPLIER");
                                statement.close();
                                results.close();
                            } catch (SQLException e){
                                 e.printStackTrace();
                            }
                            finalPrice = (int) (finalPrice + amount * price * multipilier);
                        }
                        if (finalPrice > 0) {
                            EconomyResponse response = economy.depositPlayer(player, finalPrice);
                            Msg.send(player, "&6" + finalPrice + "&fcommon_money" + " &aБыло зачислено на ваш счёт!");
                        } else{
                            Msg.send(player, "&4У вас нет ничего для продажи!");
                        }
                    }
                }
            }
        }, 4L);
    }
}


