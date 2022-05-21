package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class ClickEvent implements Listener {
    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getCurrentItem()!=null) {
            Player player = (Player) event.getWhoClicked();
            Plugin plugin = TestPlug.getPlugin(TestPlug.class);
            ArrayList<String> types = new ArrayList<>();
            types.add("BARREL");
            types.add("BEACON");
            types.add("BREWING");
            types.add("CRAFTING");
            types.add("DISPENSER");
            types.add("DROPPER");
            types.add("ENCHANTING");
            types.add("FURNACE");
            types.add("GRINDSTONE");
            types.add("HOPPER");
            types.add("SHULKER_BOX");
            types.add("WORKBENCH");
            String type = event.getClickedInventory().getType().toString();
            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
            String itemMatreial = player.getInventory().getItemInMainHand().getType().toString();
            if (types.contains(type)||event.getView().getTitle().equalsIgnoreCase("Апгрейд")||event.getView().getTitle().equalsIgnoreCase("Повышение уровня")) {
                    if (event.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                            FileConfiguration config = plugin.getConfig();
                            String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:DIRT,STONE,OBSIDIAN,GRANITE,DIORITE,ANDESITE,GOLD_BLOCK,IRON_BLOCK,DIAMOND_BLOCK,COAL_BLOCK,GRAVEL,SAND,NETHERRACK,OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,JUNGLE_LEAVES,DARK_OAK_LEAVES,OAK_LOG,BIRCH_LOG,SPRUCE_LOG,JUNGLE_LOG,DARK_OAK_LOG,GOLD_ORE,IRON_ORE,COAL_ORE,DIAMOND_ORE,REDSTONE_ORE,EMERALD_ORE,NETHER_QUARTZ_ORE,LAPIS_ORE,WHITE_CONCRETE,ORANGE_CONCRETE,MAGENTA_CONCRETE,LIGHT_BLUE_CONCRETE,YELLOW_CONCRETE,LIME_CONCRETE,PINK_CONCRETE,GRAY_CONCRETE,LIGHT_GRAY_CONCRETE,CYAN_CONCRETE,PURPLE_CONCRETE,BLUE_CONCRETE,BROWN_CONCRETE,GREEN_CONCRETE,RED_CONCRETE,BLACK_CONCRETE,%");
                            int blocks = Integer.parseInt(st);
                            int count = config.getConfigurationSection("Upgrade").getKeys(false).size();
                            Set<String> key = config.getConfigurationSection("Upgrade").getKeys(false);
                            ArrayList<String> keys = new ArrayList<>(key);
                            for (int c = 0; c < count; c++) {
                                if (keys.get(c).equals(itemMatreial)) {
                                    String path = "Upgrade." + keys.get(c);
                                    for (int d = 1; d <= 6; d++) {
                                        ItemStack item = event.getClickedInventory().getItem(15);
                                        ItemMeta metaSecond = item.getItemMeta();
                                        String number = String.valueOf(d);
                                        if (meta.getEnchantLevel(Enchantment.DIG_SPEED) == 5 || meta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 5 || meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 5){
                                            number = "6";
                                        }
                                        ArrayList<String> configs = (ArrayList<String>) config.getConfigurationSection(path).getStringList(number);
                                        Economy economy = TestPlug.getEconomy();
                                        if (metaSecond.getEnchantLevel(Enchantment.DIG_SPEED) == d || meta.getEnchantLevel(Enchantment.DIG_SPEED) == 5){
                                            int cycle = 0;
                                            int balance = (int) economy.getBalance(player);
                                            int block = Integer.parseInt(configs.get(0));
                                            int money = Integer.parseInt(configs.get(1));
                                            int cfgLength = configs.size();
                                            for (int configBlocks = 2; configBlocks <= cfgLength; configBlocks = configBlocks + 3) {
                                                String configName = configs.get(configBlocks);
                                                String placeholderName = "%statistic_mine_block:" + configName + "%";
                                                int blockToCheck = Integer.parseInt(configs.get(configBlocks+1));
                                                int placeholder = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, placeholderName));
                                                if (blocks >= block & balance >= money & placeholder >= blockToCheck) {
                                                    cycle++;
                                                }else{
                                                    Msg.send(player, "Вы не выполнили все условия!");
                                                    player.closeInventory();
                                                    Msg.send(player, "Bruh" + cycle);
                                                    break;
                                                }
                                                    if (cycle == (cfgLength-3)/2){
                                                    economy.withdrawPlayer(player, money);
                                                    player.getInventory().getItemInMainHand().setAmount(0);
                                                    player.getInventory().addItem(item);
                                                    player.closeInventory();
                                                    Msg.send(player, "&6" + money + ":common_money: &7было снято с вашего счёта.");
                                                }
                                            }
                                            break;
                                        }else if (meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 5 || metaSecond.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == d || meta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 5 || metaSecond.getEnchantLevel(Enchantment.DAMAGE_ALL) == d) {
                                            int balance = (int) economy.getBalance(player);
                                            int money = Integer.parseInt(configs.get(0));
                                            int cfgLength = configs.size();
                                            int cycles = 0;
                                            for (int configMobs = 1; configMobs <= cfgLength; configMobs = configMobs + 3) {
                                                String configName = configs.get(configMobs);
                                                String placeholderName = "%statistic_kill_entity:" + configName + "%";
                                                int mobsToCheck = Integer.parseInt(configs.get(configMobs+1));
                                                int placeholder = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, placeholderName));
                                                if (balance >= money & placeholder >= mobsToCheck) {
                                                    cycles++;
                                                }else {
                                                        player.closeInventory();
                                                        Msg.send(player, "Вы не выполнили все условия!");
                                                        break;
                                                    }
                                                if (cycles == (cfgLength - 2) / 2) {
                                                        economy.withdrawPlayer(player, money);
                                                        player.getInventory().getItemInMainHand().setAmount(0);
                                                        player.getInventory().addItem(item);
                                                        player.closeInventory();
                                                        Msg.send(player, "&6" + money + ":common_money: &7было снято с вашего счёта.");
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                    }else if (event.getCurrentItem().getType().equals(Material.RED_WOOL)&&event.getAction() != InventoryAction.HOTBAR_MOVE_AND_READD && event.getAction() != InventoryAction.HOTBAR_SWAP){
                        player.closeInventory();
                    }else if (event.getCurrentItem().getType().equals(Material.RED_CONCRETE)&&event.getAction() != InventoryAction.HOTBAR_MOVE_AND_READD && event.getAction() != InventoryAction.HOTBAR_SWAP) {
                        player.closeInventory();
                    }else if (event.getCurrentItem().getType().equals(Material.GREEN_CONCRETE)&&event.getAction() != InventoryAction.HOTBAR_MOVE_AND_READD && event.getAction() != InventoryAction.HOTBAR_SWAP) {
                        TestPlug plug = TestPlug.getPlugin(TestPlug.class);
                        int lvl = 0;
                        try{
                            PreparedStatement statement = plug.getConnection().prepareStatement("SELECT * FROM " + plug.table + " WHERE UUID=?");
                            statement.setString(1, player.getUniqueId().toString());
                            ResultSet results = statement.executeQuery();
                            results.next();
                            lvl = results.getInt("LVL");
                        }   catch (SQLException e){
                                 e.printStackTrace();
                        }
                        FileConfiguration config = plugin.getConfig();
                        player.closeInventory();
                        int maxLlvl = config.getConfigurationSection("Lvl").getKeys(false).size() + 1;
                        ArrayList<String> configs = (ArrayList<String>) config.getConfigurationSection("Lvl").getStringList(String.valueOf(lvl+1));
                        Economy economy = TestPlug.getEconomy();
                        int balance = (int) economy.getBalance(player);
                        String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:DIRT,STONE,OBSIDIAN,GRANITE,DIORITE,ANDESITE,GOLD_BLOCK,IRON_BLOCK,DIAMOND_BLOCK,COAL_BLOCK,GRAVEL,SAND,NETHERRACK,OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,JUNGLE_LEAVES,DARK_OAK_LEAVES,OAK_LOG,BIRCH_LOG,SPRUCE_LOG,JUNGLE_LOG,DARK_OAK_LOG,GOLD_ORE,IRON_ORE,COAL_ORE,DIAMOND_ORE,REDSTONE_ORE,EMERALD_ORE,NETHER_QUARTZ_ORE,LAPIS_ORE,WHITE_CONCRETE,ORANGE_CONCRETE,MAGENTA_CONCRETE,LIGHT_BLUE_CONCRETE,YELLOW_CONCRETE,LIME_CONCRETE,PINK_CONCRETE,GRAY_CONCRETE,LIGHT_GRAY_CONCRETE,CYAN_CONCRETE,PURPLE_CONCRETE,BLUE_CONCRETE,BROWN_CONCRETE,GREEN_CONCRETE,RED_CONCRETE,BLACK_CONCRETE,%");
                        if (lvl == maxLlvl){
                            Msg.send(player, "&2Вы достигли максимального уровня!");
                        } else if (balance >= Integer.parseInt(configs.get(1)) & Integer.parseInt(st) >= Integer.parseInt(configs.get(0))){
                            lvl = lvl + 1;
                            try{
                                PreparedStatement statement = plug.getConnection().prepareStatement("UPDATE " + plug.table + " SET LVL=? WHERE UUID=?");
                                statement.setDouble(1, lvl);
                                statement.setString(2, player.getUniqueId().toString());
                                statement.executeUpdate();
                                statement.close();
                                Msg.send(player, "&2Вы достигли " + lvl + " уровня" );
                                economy.withdrawPlayer(player, Integer.parseInt(configs.get(1)));
                                Msg.send(player, "&6" + Integer.parseInt(configs.get(1)) + " &f:common_money: было снято с вашего счёта." );
                            } catch (SQLException e){
                                e.printStackTrace();
                            }
                        } else{
                            Msg.send(player, "&4Условия повышения уровня не выполнены!");
                        }
                    }
                    event.setCancelled(true);
            }
        }
    }
}