package com.oreonk.commands;

import com.oreonk.CommandBase;
import com.oreonk.TestPlug;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lvl {
    public Lvl(TestPlug plugin){
        new CommandBase("lvl",true){
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments) {
                try{
                    plugin.mysqlSetup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Player player = (Player) sender;
                Economy economy = TestPlug.getEconomy();
                Inventory gui = Bukkit.createInventory(player, 27, "Повышение уровня");
                        ItemStack confirm = new ItemStack(Material.GREEN_CONCRETE);
                        ItemStack cancel = new ItemStack(Material.RED_CONCRETE);
                        ItemStack stat = new ItemStack(Material.COBBLESTONE);

                        ItemMeta confirm_meta = confirm.getItemMeta();
                        confirm_meta.setDisplayName(ChatColor.GREEN + "Повысить уровень");
                        confirm.setItemMeta(confirm_meta);

                        ItemMeta cancel_meta = cancel.getItemMeta();
                        cancel_meta.setDisplayName(ChatColor.RED + "Назад");
                        cancel.setItemMeta(cancel_meta);

                        ItemMeta stat_meta = stat.getItemMeta();
                        ArrayList<String> stat_lore = new ArrayList<>();
                        FileConfiguration config = plugin.getConfig();
                        int level = 0;
                        int balance = (int) economy.getBalance(player);
                        String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:DIRT,STONE,OBSIDIAN,GRANITE,DIORITE,ANDESITE,GOLD_BLOCK,IRON_BLOCK,DIAMOND_BLOCK,COAL_BLOCK,GRAVEL,SAND,NETHERRACK,OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,JUNGLE_LEAVES,DARK_OAK_LEAVES,OAK_LOG,BIRCH_LOG,SPRUCE_LOG,JUNGLE_LOG,DARK_OAK_LOG,GOLD_ORE,IRON_ORE,COAL_ORE,DIAMOND_ORE,REDSTONE_ORE,EMERALD_ORE,NETHER_QUARTZ_ORE,LAPIS_ORE,WHITE_CONCRETE,ORANGE_CONCRETE,MAGENTA_CONCRETE,LIGHT_BLUE_CONCRETE,YELLOW_CONCRETE,LIME_CONCRETE,PINK_CONCRETE,GRAY_CONCRETE,LIGHT_GRAY_CONCRETE,CYAN_CONCRETE,PURPLE_CONCRETE,BLUE_CONCRETE,BROWN_CONCRETE,GREEN_CONCRETE,RED_CONCRETE,BLACK_CONCRETE,%");
                        try{
                            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
                            statement.setString(1, player.getUniqueId().toString());
                            ResultSet results = statement.executeQuery();
                            results.next();
                            level = results.getInt("LVL");
                        }   catch (SQLException e){
                                 e.printStackTrace();
                        }
                        try{
                            plugin.getConnection().close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                int maxLlvl = config.getConfigurationSection("Lvl").getKeys(false).size() + 1;
                        if (level == maxLlvl){
                            stat_meta.setDisplayName(ChatColor.GREEN + "Максимальный уровень!");
                            stat.setItemMeta(stat_meta);
                        } else {
                            String key = String.valueOf(level + 1);
                            ArrayList<String> configs = (ArrayList<String>) config.getConfigurationSection("Lvl").getStringList(key);
                            stat_meta.setDisplayName(ChatColor.YELLOW + "Повышение до " + key + " уровня");
                            stat_lore.add(ChatColor.GRAY + String.valueOf(balance) + " / " + configs.get(1) + ChatColor.WHITE + " :common_money:");
                            stat_lore.add(ChatColor.GRAY + st + " / " + configs.get(0) + "Блоков");
                            stat_meta.setLore(stat_lore);
                            stat.setItemMeta(stat_meta);
                        }
                        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                        ItemMeta filler_meta = filler.getItemMeta();
                        filler_meta.setDisplayName("");
                        filler.setItemMeta(filler_meta);
                        for (int slot = 0; slot < gui.getSize(); slot++)
                            gui.setItem(slot, filler);
                        ItemStack air = new ItemStack(Material.AIR);

                        gui.setItem(10, air);
                        gui.setItem(11, cancel);
                        gui.setItem(12, air);
                        gui.setItem(13, stat);
                        gui.setItem(14, air);
                        gui.setItem(15, confirm);
                        gui.setItem(16, air);
                        player.openInventory(gui);
                return true;
            }

            @Override
            public String getUsage() {
                return "/lvl";
            }


        };
    }
}
