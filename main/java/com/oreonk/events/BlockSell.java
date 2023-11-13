package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.text.DecimalFormat;
import java.util.Map;

public class BlockSell implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteraction(PlayerInteractEvent event) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK && (event.getClickedBlock().getType() == Material.OAK_WALL_SIGN || event.getClickedBlock().getType() == Material.OAK_SIGN)) {
                    Economy economy = Prison.getEconomy();
                    Player player = event.getPlayer();
                    double globalBoost = 0.0;
                    if (!Prison.getPlugin(Prison.class).booster.isEmpty()){
                        globalBoost = 1.0;
                    }
                    int amount;
                    double finalPrice = 0.0;
                    double finalPriceCount;
                    String finalPriceString = null;
                    Double multiplier = Prison.getPlugin(Prison.class).mltp.get(player);
                    Double boost = Prison.getPlugin(Prison.class).bst.get(player);
                    double privateBoost = 0.0;
                    if (Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
                        if (Prison.getInstance().privateBoosterMultiplier.get(player.getUniqueId()) == 2){
                            privateBoost = 1.0;
                        } else if (Prison.getInstance().privateBoosterMultiplier.get(player.getUniqueId()) == 1){
                            privateBoost = 0.5;
                        }
                    }
                    Double plusMultiplier = Prison.getInstance().plusMultiplier.get(player);
                    PlayerInventory inventory = player.getInventory();
                    for (Map.Entry<String , Double> entry : Prison.getPlugin(Prison.class).blocks.entrySet()){
                        String name = entry.getKey();
                        double price = entry.getValue();
                        amount = 0;
                        for (ItemStack stack : inventory.all(Material.valueOf(name)).values()) {
                            if (stack != null && stack.getType() == Material.valueOf(name)) {
                                amount = amount + stack.getAmount();
                                player.getInventory().remove(Material.valueOf(name));
                            }
                        }
                        finalPriceCount = (finalPrice + amount * price * (multiplier+boost+globalBoost+privateBoost+plusMultiplier));
                        DecimalFormat decimalFormat = new DecimalFormat("##################0.###");
                        finalPriceString = decimalFormat.format(finalPriceCount).replace("," , ".");
                        finalPrice = Double.parseDouble(finalPriceString);

                    }
                    if (finalPrice > 0) {
                        economy.depositPlayer(player, finalPrice);
                        double finalMultiplier = multiplier+boost+globalBoost+privateBoost+plusMultiplier;
                        DecimalFormat decimalFormat = new DecimalFormat("######0.###");
                        String finalMultiplierString = decimalFormat.format(finalMultiplier).replace("," , ".");
                        Msg.send(player, "&6" + finalPriceString + " &f" + " &a Было зачислено на ваш счёт! Множитель x" + finalMultiplierString);
                    } else {
                        Msg.send(player, "&4У вас нет ничего для продажи!");
                    }
            }
    }
}


