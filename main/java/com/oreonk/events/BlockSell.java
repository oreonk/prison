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

public class BlockSell implements Listener {
    Plugin plugin = TestPlug.getPlugin(TestPlug.class);
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteraction(PlayerInteractEvent event) {
            int count = plugin.getConfig().getConfigurationSection("blocks").getKeys(false).size();
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                Block block = event.getClickedBlock();
                if (block.getType() == Material.OAK_WALL_SIGN) {
                    Economy economy = TestPlug.getEconomy();
                    Player player = event.getPlayer();
                    double globalBoost = 0.0;
                    if (!TestPlug.getPlugin(TestPlug.class).booster.isEmpty()){
                        globalBoost = 1.0;
                    }
                    int amount;
                    int finalPrice = 0;
                    Double multiplier = TestPlug.getPlugin(TestPlug.class).mltp.get(player);
                    Double boost = TestPlug.getPlugin(TestPlug.class).bst.get(player);
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
                        finalPrice = (int) (finalPrice + amount * price * (multiplier+boost+globalBoost));
                    }
                    if (finalPrice > 0) {
                        EconomyResponse response = economy.depositPlayer(player, finalPrice);
                        Double finalMultiplier = multiplier+boost+globalBoost;
                        Msg.send(player, "&6" + finalPrice + " &f" + " &a Было зачислено на ваш счёт! Множитель x" + finalMultiplier);
                    } else {
                        Msg.send(player, "&4У вас нет ничего для продажи!");
                    }
                }
            }
    }
}


