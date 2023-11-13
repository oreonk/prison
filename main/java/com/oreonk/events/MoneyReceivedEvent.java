package com.oreonk.events;

import com.djrapitops.vaultevents.events.economy.PlayerDepositEvent;
import com.oreonk.Msg;
import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.util.List;

public class MoneyReceivedEvent implements Listener {
    @EventHandler
    public void moneyEvent(PlayerDepositEvent event){
        if (Prison.getInstance().SELL_ONE.contains(event.getOfflinePlayer().getUniqueId().toString()) || Prison.getInstance().SELL_ONE.contains(event.getOfflinePlayer().getPlayer().getUniqueId().toString())){
            Player player = null;
            OfflinePlayer offlinePlayer = null;
            if (!event.getOfflinePlayer().isOnline()){
                player = (Player) event.getOfflinePlayer();
            } else {
                offlinePlayer = event.getOfflinePlayer();
            }
            if (player != null) {
                double amount = Double.parseDouble(PlaceholderAPI.setPlaceholders(player, "%auctionhouse_pstat_moneyearned_" + player.getName() + "%"));
                if (amount > 0) {
                    Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Барыга " + ChatColor.GREEN + "проверьте его в /achievements");
                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("SELL_ONE");
                    list.add(player.getUniqueId().toString());
                    try {
                        Prison.getInstance().getAchievementsConfig().set("SELL_ONE", list);
                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                        Prison.getInstance().SELL_ONE.add(player.getUniqueId().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (offlinePlayer != null){
                double amount = Double.parseDouble(PlaceholderAPI.setPlaceholders(offlinePlayer, "%auctionhouse_pstat_moneyearned_" + offlinePlayer.getName() + "%"));
                if (amount > 0){
                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("SELL_ONE");
                    list.add(offlinePlayer.getUniqueId().toString());
                    try {
                        Prison.getInstance().getAchievementsConfig().set("SELL_ONE", list);
                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                        Prison.getInstance().SELL_ONE.add(offlinePlayer.getUniqueId().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
