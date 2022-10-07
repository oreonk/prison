package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class ClickEvent implements Listener {
    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getCurrentItem() != null) {
            Player player = (Player) event.getWhoClicked();
            Plugin plugin = Prison.getPlugin(Prison.class);
            ArrayList<String> types = new ArrayList<>();
            types.add("BARREL");types.add("BEACON");types.add("BREWING");types.add("CRAFTING");types.add("DISPENSER");types.add("DROPPER");types.add("ENCHANTING");types.add("FURNACE");types.add("GRINDSTONE");types.add("HOPPER");types.add("SHULKER_BOX");types.add("WORKBENCH");
            String type = event.getClickedInventory().getType().toString();
            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
            String itemMatreial = player.getInventory().getItemInMainHand().getType().toString();
            if (event.getView().getTitle().equalsIgnoreCase("Улучшение предмета")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {
                    if (player.getOpenInventory().getTopInventory().getItem(11).getType().toString().contains("DIAMOND") && player.getOpenInventory().getTopInventory().getItem(11).getEnchantments().containsValue(5)){
                        Msg.send(player, "Этот предмет уже максимально улучшен!");
                        player.closeInventory();
                    }
                    else {
                        FileConfiguration config = plugin.getConfig();
                        String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:DIRT,STONE,OBSIDIAN,GRANITE,DIORITE,ANDESITE,GOLD_BLOCK,IRON_BLOCK,DIAMOND_BLOCK,COAL_BLOCK,GRAVEL,SAND,NETHERRACK,OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,JUNGLE_LEAVES,DARK_OAK_LEAVES,OAK_WOOD,BIRCH_LOG,SPRUCE_LOG,JUNGLE_LOG,DARK_OAK_WOOD,STRIPPED_JUNGLE_WOOD,STRIPPED_OAK_WOOD,STRIPPED_DARK_OAK_WOOD,STRIPPED_SPRUCE_WOOD,GOLD_ORE,IRON_ORE,COAL_ORE,DIAMOND_ORE,REDSTONE_ORE,EMERALD_ORE,NETHER_QUARTZ_ORE,LAPIS_ORE,WHITE_CONCRETE,ORANGE_CONCRETE,MAGENTA_CONCRETE,LIGHT_BLUE_CONCRETE,YELLOW_CONCRETE,LIME_CONCRETE,PINK_CONCRETE,GRAY_CONCRETE,LIGHT_GRAY_CONCRETE,CYAN_CONCRETE,PURPLE_CONCRETE,BLUE_CONCRETE,BROWN_CONCRETE,GREEN_CONCRETE,RED_CONCRETE,BLACK_CONCRETE,PURPUR_BLOCK,PURPUR_PILLAR,END_STONE,END_STONE_BRICKS,NETHER_BRICKS,RED_NETHER_BRICKS,NETHER_WART_BLOCK,STONE_BRICKS,MOSSY_STONE_BRICKS,CRACKED_STONE_BRICKS,CHISELED_STONE_BRICKS,BRICKS,SANDSTONE,CHISELED_SANDSTONE,CUT_SANDSTONE,RED_SANDSTONE,CYAN_GLAZED_TERRACOTTA,PRISMARINE,PRISMARINE_BRICKS,DARK_PRISMARINE,SEA_LANTERN,WHITE_WOOL,ORANGE_WOOL,GRAY_WOOL,COBWEB,ICE,BLUE_ICE,PACKED_ICE,PINK_GLAZED_TERRACOTTA,RED_GLAZED_TERRACOTTA,GRAY_GLAZED_TERRACOTTA,SNOW_BLOCK,DEEPSLATE_BRICKS,POLISHED_BLACKSTONE_BRICKS,CRACKED_DEEPSLATE_BRICKS,BASALT,DEEPSLATE,COBBLED_DEEPSLATE,GLIDED_BLACKSTONE,CRYING_OBSIDIAN%");
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
                                    if (meta.getDisplayName().contains("Редкая")) {
                                        if (meta.getEnchantLevel(Enchantment.DIG_SPEED) == 4) {
                                            number = "6";
                                        }
                                    } else if(meta.getDisplayName().contains("Героическая")){
                                        if (meta.getEnchantLevel(Enchantment.DIG_SPEED) == 5) {
                                            number = "6";
                                        }
                                    } else if (meta.getEnchantLevel(Enchantment.DIG_SPEED) == 3 || meta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 5 || meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4){
                                        number = "6";
                                    }
                                    ArrayList<String> configs = (ArrayList<String>) config.getConfigurationSection(path).getStringList(number);
                                    if (Integer.parseInt(number) != 6) {
                                        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.DAMAGE_ALL) || item.containsEnchantment(Enchantment.DAMAGE_ALL)) {
                                            int lvl = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL) + 1;
                                            configs = (ArrayList<String>) config.getConfigurationSection(path).getStringList(String.valueOf(lvl));
                                        } else if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) || item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)){
                                            int lvl = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) + 1;
                                            configs = (ArrayList<String>) config.getConfigurationSection(path).getStringList(String.valueOf(lvl));
                                        }
                                    }
                                    Economy economy = Prison.getEconomy();
                                    if (metaSecond.hasEnchant(Enchantment.DIG_SPEED) || meta.hasEnchant(Enchantment.DIG_SPEED)) {
                                        if (metaSecond.getEnchantLevel(Enchantment.DIG_SPEED) == Integer.parseInt(number) || meta.getEnchantLevel(Enchantment.DIG_SPEED) == d-1){
                                            int cycle = 0;
                                            int balance = (int) economy.getBalance(player);
                                            int block = Integer.parseInt(configs.get(0));
                                            int money = Integer.parseInt(configs.get(1));
                                            int cfgLength = configs.size();
                                            for (int configBlocks = 2; configBlocks < cfgLength; configBlocks = configBlocks + 3) {
                                                String configName = configs.get(configBlocks);
                                                String placeholderName = "%statistic_mine_block:" + configName + "%";
                                                int blockToCheck = Integer.parseInt(configs.get(configBlocks + 1));
                                                int placeholder = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, placeholderName));
                                                if (blocks >= block & balance >= money & placeholder >= blockToCheck) {
                                                    cycle++;
                                                } else {
                                                    Msg.send(player, "Вы не выполнили все условия!");
                                                    player.closeInventory();
                                                    break;
                                                }
                                                if (cycle == (cfgLength - 3) / 2) {
                                                    economy.withdrawPlayer(player, money);
                                                    player.getInventory().getItemInMainHand().setAmount(0);
                                                    player.getInventory().addItem(item);
                                                    player.closeInventory();
                                                    Msg.send(player, "&6" + money + " &f &7было снято с вашего счёта.");
                                                }
                                            }
                                            break;
                                        }
                                    } else if (meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL) || metaSecond.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL) || meta.hasEnchant(Enchantment.DAMAGE_ALL) || metaSecond.hasEnchant(Enchantment.DAMAGE_ALL)) {
                                        if (metaSecond.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == Integer.parseInt(number) || meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == d - 1 || metaSecond.getEnchantLevel(Enchantment.DAMAGE_ALL) == Integer.parseInt(number) || meta.getEnchantLevel(Enchantment.DAMAGE_ALL) == d - 1) {
                                            int balance = (int) economy.getBalance(player);
                                            int money = Integer.parseInt(configs.get(0));
                                            int cfgLength = configs.size();
                                            int cycles = 0;
                                                String configName = configs.get(1);
                                                String placeholderName = "%statistic_kill_entity:" + configName + "%";
                                                int mobsToCheck = Integer.parseInt(configs.get(2));
                                                int placeholder = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, placeholderName));
                                                if (balance >= money & placeholder >= mobsToCheck) {
                                                    cycles++;
                                                } else {
                                                    player.closeInventory();
                                                    Msg.send(player, "Вы не выполнили все условия!");
                                                    break;
                                                }
                                                if (cycles == (cfgLength - 2) / 2) {
                                                    economy.withdrawPlayer(player, money);
                                                    player.getInventory().getItemInMainHand().setAmount(0);
                                                    player.getInventory().addItem(item);
                                                    player.closeInventory();
                                                    Msg.send(player, "&6" + money + " &f &7было снято с вашего счёта.");
                                                }
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                } else if (event.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                    player.closeInventory();
                }
            } else if (event.getView().getTitle().equalsIgnoreCase("Повышение уровня")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getType().equals(Material.RED_CONCRETE)) {
                    player.closeInventory();
                } else if (event.getCurrentItem().getType().equals(Material.GREEN_CONCRETE)) {
                    Prison plug = Prison.getPlugin(Prison.class);
                    int lvl = plug.lvl.get(player);
                    int maxLVL = Prison.getPlugin(Prison.class).getLvlConfig().getConfigurationSection("Lvl").getKeys(false).size();
                    if (lvl < maxLVL) {
                        ArrayList<String> configs = (ArrayList<String>) Prison.getPlugin(Prison.class).getLvlConfig().getConfigurationSection("Lvl").getStringList(String.valueOf(lvl + 1));
                        Economy economy = Prison.getEconomy();
                        int balance = (int) economy.getBalance(player);
                        String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:DIRT,STONE,OBSIDIAN,GRANITE,DIORITE,ANDESITE,GOLD_BLOCK,IRON_BLOCK,DIAMOND_BLOCK,COAL_BLOCK,GRAVEL,SAND,NETHERRACK,OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,JUNGLE_LEAVES,DARK_OAK_LEAVES,OAK_WOOD,BIRCH_LOG,SPRUCE_LOG,JUNGLE_LOG,DARK_OAK_WOOD,STRIPPED_JUNGLE_WOOD,STRIPPED_OAK_WOOD,STRIPPED_DARK_OAK_WOOD,STRIPPED_SPRUCE_WOOD,GOLD_ORE,IRON_ORE,COAL_ORE,DIAMOND_ORE,REDSTONE_ORE,EMERALD_ORE,NETHER_QUARTZ_ORE,LAPIS_ORE,WHITE_CONCRETE,ORANGE_CONCRETE,MAGENTA_CONCRETE,LIGHT_BLUE_CONCRETE,YELLOW_CONCRETE,LIME_CONCRETE,PINK_CONCRETE,GRAY_CONCRETE,LIGHT_GRAY_CONCRETE,CYAN_CONCRETE,PURPLE_CONCRETE,BLUE_CONCRETE,BROWN_CONCRETE,GREEN_CONCRETE,RED_CONCRETE,BLACK_CONCRETE,PURPUR_BLOCK,PURPUR_PILLAR,END_STONE,END_STONE_BRICKS,NETHER_BRICKS,RED_NETHER_BRICKS,NETHER_WART_BLOCK,STONE_BRICKS,MOSSY_STONE_BRICKS,CRACKED_STONE_BRICKS,CHISELED_STONE_BRICKS,BRICKS,SANDSTONE,CHISELED_SANDSTONE,CUT_SANDSTONE,RED_SANDSTONE,CYAN_GLAZED_TERRACOTTA,PRISMARINE,PRISMARINE_BRICKS,DARK_PRISMARINE,SEA_LANTERN,WHITE_WOOL,ORANGE_WOOL,GRAY_WOOL,COBWEB,ICE,BLUE_ICE,PACKED_ICE,PINK_GLAZED_TERRACOTTA,RED_GLAZED_TERRACOTTA,GRAY_GLAZED_TERRACOTTA,SNOW_BLOCK,DEEPSLATE_BRICKS,POLISHED_BLACKSTONE_BRICKS,CRACKED_DEEPSLATE_BRICKS,BASALT,DEEPSLATE,COBBLED_DEEPSLATE,GLIDED_BLACKSTONE,CRYING_OBSIDIAN%");
                        if (balance >= Integer.parseInt(configs.get(1)) & Integer.parseInt(st) >= Integer.parseInt(configs.get(0))) {
                            lvl = lvl + 1;
                            economy.withdrawPlayer(player, Integer.parseInt(configs.get(1)));
                            int finalLvl = lvl;
                            Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                                plug.getDatabase().updateLVL(player, finalLvl);
                            });
                            plug.lvl.replace(player, finalLvl);
                            Msg.send(player, "&2Вы достигли " + lvl + " уровня");
                            Msg.send(player, "&6" + Integer.parseInt(configs.get(1)) + " &f &7было снято с вашего счёта.");
                            Date now = new Date();
                            java.sql.Date finalDate = new java.sql.Date(now.getTime());
                            Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                                plug.getDatabase().updateLVLTop(player.getUniqueId(), finalLvl, finalDate);
                            });
                            player.closeInventory();
                        } else {
                            Msg.send(player, "&4Условия повышения уровня не выполнены!");
                            player.closeInventory();
                        }
                    } else if (lvl == maxLVL) {
                        Msg.send(player, "&2Вы достигли максимального уровня!");
                        player.closeInventory();
                    }
                }
                if (types.contains(type)) {
                    event.setCancelled(true);
                }
            }
        }
        String name = event.getCursor().getType().toString();
        if (event.getCurrentItem()!=null){
            if (event.getAction() == InventoryAction.HOTBAR_SWAP||event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD|| event.isShiftClick()) {
                if (event.getView().getTitle().equalsIgnoreCase("Зачарование") || event.getView().getTitle().equalsIgnoreCase("Повышение уровня") || event.getView().getTitle().equalsIgnoreCase("Улучшение предмета") || event.getView().getTitle().equalsIgnoreCase("Подтверждение зачарования") || event.getView().getTitle().contains("") || event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера") || event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") || event.getView().getTitle().equalsIgnoreCase("Покупка автоселла") || event.getView().getTitle().equalsIgnoreCase("Выбор фракции") || event.getView().getTitle().equalsIgnoreCase("Выбор белых")|| event.getView().getTitle().equalsIgnoreCase("Выбор черных")|| event.getView().getTitle().equalsIgnoreCase("Выбор азиатов") || event.getView().getTitle().equalsIgnoreCase("Зачарование")) {
                    event.setCancelled(true);
                }
            }
        } else if (event.getCurrentItem()==null&&!name.endsWith("_HELMET") && !name.endsWith("_CHESTPLATE") && !name.endsWith("_LEGGINGS") && !name.endsWith("_BOOTS") && !name.endsWith("_PICKAXE") && !name.endsWith("_AXE") && !name.endsWith("_SWORD")){
            event.setCancelled(true);
        } if (event.getView().getTitle().equalsIgnoreCase("Подтверждение зачарования")){
            event.setCancelled(true);
        }
    }
}
