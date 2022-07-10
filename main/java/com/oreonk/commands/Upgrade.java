package com.oreonk.commands;


import com.oreonk.CommandBase;
import com.oreonk.Msg;
import com.oreonk.TestPlug;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Upgrade {
    public Upgrade(TestPlug plugin){
        new CommandBase("upgrade", true){
            @Override
            public boolean onCommand(CommandSender sender, String [] arguments) {
                Player player = (Player) sender;
                ItemStack item = player.getInventory().getItemInMainHand();
                String material = player.getInventory().getItemInMainHand().getType().toString();
                Material mat = player.getInventory().getItemInMainHand().getType();
                ItemStack firstItem = item.clone();
                if (item.getType() != Material.AIR) {
                    if (item.getItemMeta().isUnbreakable()) {
                        Economy economy = TestPlug.getEconomy();
                        Inventory gui = Bukkit.createInventory(player, 27, "Апгрейд");
                        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
                        ItemStack cancel = new ItemStack(Material.RED_WOOL);
                        ItemStack stat = new ItemStack(Material.COBBLESTONE);

                        ItemMeta confirm_meta = confirm.getItemMeta();
                        confirm_meta.setDisplayName(ChatColor.GREEN + "Апгрейд");
                        ArrayList<String> confirm_lore = new ArrayList<>();
                        confirm_lore.add(ChatColor.GRAY + "Провести улучшение");
                        confirm_meta.setLore(confirm_lore);
                        confirm.setItemMeta(confirm_meta);

                        ItemMeta cancel_meta = cancel.getItemMeta();
                        cancel_meta.setDisplayName(ChatColor.RED + "Назад");
                        ArrayList<String> cancel_lore = new ArrayList<>();
                        cancel_lore.add(ChatColor.GRAY + "Закрыть меню");
                        cancel_meta.setLore(cancel_lore);
                        cancel.setItemMeta(cancel_meta);

                        ItemMeta stat_meta = stat.getItemMeta();
                        stat_meta.setDisplayName(ChatColor.YELLOW + "Нужно для улучшения:");
                        ArrayList<String> stat_lore = new ArrayList<>();


                        ItemMeta firstItem_meta = firstItem.getItemMeta();
                        firstItem.setItemMeta(firstItem_meta);

                        ItemStack secondItem = new ItemStack(Material.valueOf(material));
                        ItemMeta secondItem_meta = secondItem.getItemMeta();
                        ArrayList<String> upgrade = new ArrayList<>();
                        ArrayList<String> upgradeArmor = new ArrayList<>();
                        ArrayList<String> upgradeSwords = new ArrayList<>();
                        upgrade.add("WOODEN_PICKAXE");upgrade.add("STONE_PICKAXE");upgrade.add("IRON_PICKAXE");upgrade.add("DIAMOND_PICKAXE");upgrade.add("WOODEN_SHOVEL");upgrade.add("STONE_SHOVEL");upgrade.add("IRON_SHOVEL");upgrade.add("DIAMOND_SHOVEL");upgrade.add("WOODEN_AXE");upgrade.add("STONE_AXE");upgrade.add("IRON_AXE");upgrade.add("DIAMOND_AXE");
                        upgradeArmor.add("LEATHER_HELMET");upgradeArmor.add("CHAINMAIL_HELMET");upgradeArmor.add("IRON_HELMET");upgradeArmor.add("DIAMOND_HELMET");upgradeArmor.add("LEATHER_CHESTPLATE");upgradeArmor.add("CHAINMAIL_CHESTPLATE");upgradeArmor.add("IRON_CHESTPLATE");upgradeArmor.add("DIAMOND_CHESTPLATE");upgradeArmor.add("LEATHER_LEGGINGS");upgradeArmor.add("CHAINMAIL_LEGGINGS");upgradeArmor.add("IRON_LEGGINGS");upgradeArmor.add("DIAMOND_LEGGINGS");upgradeArmor.add("LEATHER_BOOTS");upgradeArmor.add("CHAINMAIL_BOOTS");upgradeArmor.add("IRON_BOOTS");upgradeArmor.add("DIAMOND_BOOTS");
                        upgradeSwords.add("WOODEN_SWORD");upgradeSwords.add("STONE_SWORD");upgradeSwords.add("IRON_SWORD");upgradeSwords.add("DIAMOND_SWORD");
                        if (upgrade.contains(material)) {
                            int eff = firstItem_meta.getEnchantLevel(Enchantment.DIG_SPEED);
                            if (eff > 0) {
                                if (eff == 5) {
                                        if (mat.equals(Material.DIAMOND_PICKAXE)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 5, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.DIAMOND_SHOVEL)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 5, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.DIAMOND_AXE)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 5, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else {
                                            int number = 0;
                                            int count = upgrade.size();
                                            for (int c = 0;c < count;c++){
                                                String pick = upgrade.get(c);
                                                if (material.equals(pick)){
                                                    number = c + 1;
                                                    break;
                                                }
                                            }
                                            String finalName = upgrade.get(number);
                                            secondItem.setType(Material.valueOf(finalName));
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        }
                                } else {
                                    eff++;
                                    secondItem_meta.addEnchant(Enchantment.DIG_SPEED, eff, false);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                }
                            } else {
                                secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 1, false);
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                            }
                        }else if(upgradeArmor.contains(material)){
                            int prot = firstItem_meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
                            if (prot > 0) {
                                if (prot == 5){
                                    if (mat.equals(Material.DIAMOND_HELMET)) {
                                        secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                        ArrayList<String> secondItem_lore = new ArrayList<>();
                                        secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                        secondItem_meta.setLore(secondItem_lore);
                                        secondItem_meta.setUnbreakable(true);
                                        secondItem.setItemMeta(secondItem_meta);
                                    } else if (mat.equals(Material.DIAMOND_CHESTPLATE)) {
                                        secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                        ArrayList<String> secondItem_lore = new ArrayList<>();
                                        secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                        secondItem_meta.setLore(secondItem_lore);
                                        secondItem_meta.setUnbreakable(true);
                                        secondItem.setItemMeta(secondItem_meta);
                                    } else if (mat.equals(Material.DIAMOND_LEGGINGS)) {
                                        secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                        ArrayList<String> secondItem_lore = new ArrayList<>();
                                        secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                        secondItem_meta.setLore(secondItem_lore);
                                        secondItem_meta.setUnbreakable(true);
                                        secondItem.setItemMeta(secondItem_meta);
                                    } else if (mat.equals(Material.DIAMOND_BOOTS)) {
                                        secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
                                        ArrayList<String> secondItem_lore = new ArrayList<>();
                                        secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                        secondItem_meta.setLore(secondItem_lore);
                                        secondItem_meta.setUnbreakable(true);
                                        secondItem.setItemMeta(secondItem_meta);
                                    } else {
                                            int number = 0;
                                            int count = upgradeArmor.size();
                                            for (int c = 0;c < count;c++){
                                                String sword = upgradeArmor.get(c);
                                                if (material.equals(sword)){
                                                    number = c + 1;
                                                    break;
                                                }
                                            }
                                            String finalName = upgradeArmor.get(number);
                                            secondItem.setType(Material.valueOf(finalName));
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                    }
                                } else{
                                    prot++;
                                    secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, prot, true);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                }
                            } else{
                                secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                            }
                        } else if(upgradeSwords.contains(material)){
                            int shr = firstItem_meta.getEnchantLevel(Enchantment.DAMAGE_ALL);
                            if (shr > 0) {
                                if (shr == 5){
                                    if (mat.equals(Material.DIAMOND_SWORD)) {
                                        secondItem_meta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
                                        ArrayList<String> secondItem_lore = new ArrayList<>();
                                        secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                        secondItem_meta.setLore(secondItem_lore);
                                        secondItem_meta.setUnbreakable(true);
                                        secondItem.setItemMeta(secondItem_meta);
                                    } else {
                                            int number = 0;
                                            int count = upgradeSwords.size();
                                            for (int c = 0;c < count;c++){
                                                String sword = upgradeSwords.get(c);
                                                if (material.equals(sword)){
                                                    number = c + 1;
                                                    break;
                                                }
                                            }
                                            String finalName = upgradeSwords.get(number);
                                            secondItem.setType(Material.valueOf(finalName));
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                    }
                                } else{
                                    shr++;
                                    secondItem_meta.addEnchant(Enchantment.DAMAGE_ALL, shr, false);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                }
                            } else{
                                secondItem_meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                            }
                        }
                            String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:DIRT,STONE,OBSIDIAN,GRANITE,DIORITE,ANDESITE,GOLD_BLOCK,IRON_BLOCK,DIAMOND_BLOCK,COAL_BLOCK,GRAVEL,SAND,NETHERRACK,OAK_LEAVES,BIRCH_LEAVES,SPRUCE_LEAVES,JUNGLE_LEAVES,DARK_OAK_LEAVES,OAK_LOG,BIRCH_LOG,SPRUCE_LOG,JUNGLE_LOG,DARK_OAK_LOG,GOLD_ORE,IRON_ORE,COAL_ORE,DIAMOND_ORE,REDSTONE_ORE,EMERALD_ORE,NETHER_QUARTZ_ORE,LAPIS_ORE,WHITE_CONCRETE,ORANGE_CONCRETE,MAGENTA_CONCRETE,LIGHT_BLUE_CONCRETE,YELLOW_CONCRETE,LIME_CONCRETE,PINK_CONCRETE,GRAY_CONCRETE,LIGHT_GRAY_CONCRETE,CYAN_CONCRETE,PURPLE_CONCRETE,BLUE_CONCRETE,BROWN_CONCRETE,GREEN_CONCRETE,RED_CONCRETE,BLACK_CONCRETE,PURPUR_BLOCK,PURPUR_PILLAR,END_STONE,END_STONE_BRICKS,%");
                            int ebal = (int) economy.getBalance(player);
                            String bal = String.valueOf(ebal);
                            String mater = secondItem.getType().toString();
                            String materr = firstItem.getType().toString();
                            int lvl = secondItem_meta.getEnchantLevel(Enchantment.DIG_SPEED) + secondItem_meta.getEnchantLevel(Enchantment.DAMAGE_ALL) + secondItem_meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
                            FileConfiguration config = plugin.getConfig();
                            String path = "Upgrade." + mater;
                            if (lvl == 0){
                                lvl = 6;
                                path = "Upgrade." + materr;
                            }
                            ArrayList<String> configs = (ArrayList<String>) config.getConfigurationSection(path).getStringList(String.valueOf(lvl));
                            stat_lore.add(ChatColor.GRAY + bal +"/" + configs.get(0) + ChatColor.WHITE + " ");
                            int cfgLenght = configs.size();
                            if (firstItem_meta.hasEnchant(Enchantment.DIG_SPEED) || secondItem_meta.hasEnchant(Enchantment.DIG_SPEED)) {
                                stat_lore.add(ChatColor.GRAY + st + "/" + configs.get(0) + " Блоков");
                                for (int i = 2; i < cfgLenght; i = i + 3){
                                    String whatToEarn = configs.get(i);
                                    String howMuch = configs.get(i+1);
                                    String earnName = configs.get(i+2);
                                    stat_lore.add(ChatColor.GRAY + PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:" + whatToEarn + "%") + "/" + howMuch + " " + earnName);
                            }
                            }else if (firstItem_meta.hasEnchant(Enchantment.DAMAGE_ALL) || secondItem_meta.hasEnchant(Enchantment.DAMAGE_ALL) || firstItem_meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL) || secondItem_meta.hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)){
                                for (int i = 1; i < cfgLenght; i = i + 3){
                                    String whatToEarn = configs.get(i);
                                    String howMuch = configs.get(i+1);
                                    String earnName = configs.get(i+2);
                                    stat_lore.add(ChatColor.GRAY + PlaceholderAPI.setPlaceholders(player, "%statistic_kill_entity:" + whatToEarn + "%") + "/" + howMuch + " " + earnName);
                            }
                            }
                            stat_meta.setLore(stat_lore);
                            stat.setItemMeta(stat_meta);


                        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                        ItemMeta filler_meta = filler.getItemMeta();
                        filler_meta.setDisplayName("");
                        filler.setItemMeta(filler_meta);
                        for (int slot = 0; slot < gui.getSize(); slot++)
                            gui.setItem(slot, filler);
                        ItemStack air = new ItemStack(Material.AIR);

                        gui.setItem(10, air);
                        gui.setItem(11, firstItem);
                        gui.setItem(12, air);
                        gui.setItem(13, stat);
                        gui.setItem(14, air);
                        gui.setItem(15, secondItem);
                        gui.setItem(16, air);
                        gui.setItem(21, cancel);
                        gui.setItem(23, confirm);
                        player.openInventory(gui);
                    } else {
                        Msg.send(player, "Вы не можете это улучшить..");
                    }
                } else{
                    Msg.send(player, "В ваших руках ничего нет!");
                }
                return true;
            }
            @Override
            public String getUsage() {
                return "/upgrade";
            }
        };
    }
}
