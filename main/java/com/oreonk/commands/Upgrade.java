package com.oreonk.commands;


import com.oreonk.Msg;
import com.oreonk.Prison;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Upgrade implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            Plugin plugin = Prison.getInstance();
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            String material = player.getInventory().getItemInMainHand().getType().toString();
            Material mat = player.getInventory().getItemInMainHand().getType();
            ItemStack firstItem = item.clone();
            if (item.getType() != Material.AIR) {
                if (item.getItemMeta().isUnbreakable()) {
                    Economy economy = Prison.getEconomy();
                    Inventory gui = Bukkit.createInventory(player, 27, "Улучшение предмета");
                    ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
                    ItemStack cancel = new ItemStack(Material.RED_WOOL);
                    ItemStack stat = new ItemStack(Material.ANVIL);

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

                    ItemStack secondItem = new ItemStack(Material.valueOf(material));
                    ItemMeta secondItem_meta = secondItem.getItemMeta();
                    ArrayList<String> upgrade = new ArrayList<>();
                    ArrayList<String> upgradeArmor = new ArrayList<>();
                    ArrayList<String> upgradeSwords = new ArrayList<>();
                    upgrade.add("WOODEN_PICKAXE");
                    upgrade.add("STONE_PICKAXE");
                    upgrade.add("IRON_PICKAXE");
                    upgrade.add("DIAMOND_PICKAXE");
                    upgrade.add("WOODEN_SHOVEL");
                    upgrade.add("STONE_SHOVEL");
                    upgrade.add("IRON_SHOVEL");
                    upgrade.add("DIAMOND_SHOVEL");
                    upgrade.add("WOODEN_AXE");
                    upgrade.add("STONE_AXE");
                    upgrade.add("IRON_AXE");
                    upgrade.add("DIAMOND_AXE");
                    upgrade.add("WOODEN_HOE");
                    upgradeArmor.add("LEATHER_HELMET");
                    upgradeArmor.add("CHAINMAIL_HELMET");
                    upgradeArmor.add("IRON_HELMET");
                    upgradeArmor.add("DIAMOND_HELMET");
                    upgradeArmor.add("LEATHER_CHESTPLATE");
                    upgradeArmor.add("CHAINMAIL_CHESTPLATE");
                    upgradeArmor.add("IRON_CHESTPLATE");
                    upgradeArmor.add("DIAMOND_CHESTPLATE");
                    upgradeArmor.add("LEATHER_LEGGINGS");
                    upgradeArmor.add("CHAINMAIL_LEGGINGS");
                    upgradeArmor.add("IRON_LEGGINGS");
                    upgradeArmor.add("DIAMOND_LEGGINGS");
                    upgradeArmor.add("LEATHER_BOOTS");
                    upgradeArmor.add("CHAINMAIL_BOOTS");
                    upgradeArmor.add("IRON_BOOTS");
                    upgradeArmor.add("DIAMOND_BOOTS");
                    upgradeSwords.add("WOODEN_SWORD");
                    upgradeSwords.add("STONE_SWORD");
                    upgradeSwords.add("IRON_SWORD");
                    upgradeSwords.add("DIAMOND_SWORD");
                    if (upgrade.contains(material)) {
                        int eff = firstItem.getEnchantmentLevel(Enchantment.DIG_SPEED);
                            if (mat.equals(Material.DIAMOND_PICKAXE) | mat.equals(Material.DIAMOND_SHOVEL) | mat.equals(Material.DIAMOND_AXE)) {
                                if (item.getItemMeta().getDisplayName().contains("Героическая")) {
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
                                        }
                                    }
                                } else if(item.getItemMeta().getDisplayName().contains("Редкая")){
                                    if (eff == 4) {
                                        if (mat.equals(Material.DIAMOND_PICKAXE)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 4, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.DIAMOND_SHOVEL)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 4, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.DIAMOND_AXE)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 4, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        }
                                    }
                                } else {
                                    if (eff == 3 && !item.getItemMeta().getDisplayName().contains("Редкая") && !item.getItemMeta().getDisplayName().contains("Героическая")) {
                                        if (mat.equals(Material.DIAMOND_PICKAXE)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 3, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.DIAMOND_SHOVEL)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 3, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.DIAMOND_AXE)) {
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 3, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        } else if (mat.equals(Material.WOODEN_HOE)){
                                            secondItem_meta.addEnchant(Enchantment.DIG_SPEED, 3, false);
                                            ArrayList<String> secondItem_lore = new ArrayList<>();
                                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                            secondItem_meta.setLore(secondItem_lore);
                                            secondItem_meta.setUnbreakable(true);
                                            secondItem.setItemMeta(secondItem_meta);
                                        }
                                    }
                                }
                            } else {
                                int number = 0;
                                int count = upgrade.size();
                                for (int c = 0; c < count; c++) {
                                    String pick = upgrade.get(c);
                                    if (material.equals(pick)) {
                                        number = c + 1;
                                        break;
                                    }
                                }
                                if (eff == 5 && item.getItemMeta().getDisplayName().contains("Героическая")) {
                                    //Переход на тир
                                    secondItem.setType(Material.valueOf(upgrade.get(number)));
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                    secondItem.addEnchantments(player.getInventory().getItemInMainHand().getEnchantments());
                                    secondItem.removeEnchantment(Enchantment.DIG_SPEED);
                                } else if (eff == 4 && item.getItemMeta().getDisplayName().contains("Редкая")){
                                    secondItem.setType(Material.valueOf(upgrade.get(number)));
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem_meta.setDisplayName("Редкая кирка");
                                    secondItem.setItemMeta(secondItem_meta);
                                    secondItem.addEnchantments(player.getInventory().getItemInMainHand().getEnchantments());
                                    secondItem.removeEnchantment(Enchantment.DIG_SPEED);
                                } else if(!item.getItemMeta().getDisplayName().contains("Редкая") && !item.getItemMeta().getDisplayName().contains("Героическая") && !item.getType().equals(Material.WOODEN_HOE)){
                                    if (eff == 3) {
                                        secondItem.setType(Material.valueOf(upgrade.get(number)));
                                        secondItem_meta.setUnbreakable(true);
                                        secondItem.setItemMeta(secondItem_meta);
                                        secondItem.addEnchantments(player.getInventory().getItemInMainHand().getEnchantments());
                                        secondItem.removeEnchantment(Enchantment.DIG_SPEED);
                                    }
                                }
                                if (eff >=1 && eff < 5 && item.getItemMeta().getDisplayName().contains("Героическая")){
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem_meta.setDisplayName("Героическая кирка");
                                    secondItem.setItemMeta(secondItem_meta);
                                    secondItem.addEnchantments(player.getInventory().getItemInMainHand().getEnchantments());
                                    secondItem.addEnchantment(Enchantment.DIG_SPEED, eff+1);
                                } else if (eff >=1 && eff < 4 && item.getItemMeta().getDisplayName().contains("Редкая")){
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem_meta.setDisplayName("Редкая кирка");
                                    secondItem.setItemMeta(secondItem_meta);
                                    secondItem.addEnchantments(player.getInventory().getItemInMainHand().getEnchantments());
                                    secondItem.addEnchantment(Enchantment.DIG_SPEED, eff+1);
                                }
                                    else if (eff >= 1 && eff < 3) {
                                    //Эффективность после 1
                                    secondItem_meta.setUnbreakable(true);
                                    if (firstItem_meta.getDisplayName() != null && (firstItem_meta.getDisplayName().contains("Героическая") || firstItem_meta.getDisplayName().contains("Редкая"))){
                                        secondItem_meta.setDisplayName(firstItem_meta.getDisplayName());
                                    }
                                    secondItem.setItemMeta(secondItem_meta);
                                    secondItem.addEnchantments(player.getInventory().getItemInMainHand().getEnchantments());
                                    secondItem.addEnchantment(Enchantment.DIG_SPEED, eff+1);
                                } else if (eff == 0){
                                    //Эффективность 1
                                    secondItem_meta.setUnbreakable(true);
                                    if (firstItem_meta.getDisplayName() != null && (firstItem_meta.getDisplayName().contains("Героическая") || firstItem_meta.getDisplayName().contains("Редкая"))){
                                        secondItem_meta.setDisplayName(firstItem_meta.getDisplayName());
                                    }
                                    secondItem.setItemMeta(secondItem_meta);
                                    secondItem.addUnsafeEnchantments(firstItem.getEnchantments());
                                    secondItem.addEnchantment(Enchantment.DIG_SPEED, 1);
                                }
                            }
                    } else if (upgradeArmor.contains(material)) {
                        int prot = firstItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
                        if (mat.equals(Material.DIAMOND_HELMET) || mat.equals(Material.DIAMOND_CHESTPLATE) || mat.equals(Material.DIAMOND_LEGGINGS) || mat.equals(Material.DIAMOND_BOOTS)) {
                            if (prot == 4) {
                                if (mat.equals(Material.DIAMOND_HELMET)) {
                                    secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                    ArrayList<String> secondItem_lore = new ArrayList<>();
                                    secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                    secondItem_meta.setLore(secondItem_lore);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                } else if (mat.equals(Material.DIAMOND_CHESTPLATE)) {
                                    secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                    ArrayList<String> secondItem_lore = new ArrayList<>();
                                    secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                    secondItem_meta.setLore(secondItem_lore);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                } else if (mat.equals(Material.DIAMOND_LEGGINGS)) {
                                    secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                    ArrayList<String> secondItem_lore = new ArrayList<>();
                                    secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                    secondItem_meta.setLore(secondItem_lore);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                } else if (mat.equals(Material.DIAMOND_BOOTS)) {
                                    secondItem_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
                                    ArrayList<String> secondItem_lore = new ArrayList<>();
                                    secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                                    secondItem_meta.setLore(secondItem_lore);
                                    secondItem_meta.setUnbreakable(true);
                                    secondItem.setItemMeta(secondItem_meta);
                                }
                            }
                        } else {
                            int number = 0;
                            int count = upgradeArmor.size();
                            for (int c = 0; c < count; c++) {
                                String armor = upgradeArmor.get(c);
                                if (material.equals(armor)) {
                                    number = c + 1;
                                    break;
                                }
                            }
                            if (prot == 4) {
                                String finalName = upgradeArmor.get(number);
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                                secondItem.setType(Material.valueOf(finalName));
                                secondItem.addEnchantments(firstItem.getEnchantments());
                                secondItem.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                            } else if (prot >= 1) {
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                                secondItem.addEnchantments(firstItem.getEnchantments());
                                secondItem.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, prot+1);
                            } else if (prot == 0) {
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                                secondItem.addEnchantments(firstItem.getEnchantments());
                                secondItem.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                            }
                        }
                    }
                    else if (upgradeSwords.contains(material)) {
                        int shr = firstItem.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
                        if (mat.equals(Material.DIAMOND_SWORD) && shr == 5) {
                            secondItem_meta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
                            ArrayList<String> secondItem_lore = new ArrayList<>();
                            secondItem_lore.add(ChatColor.GREEN + "Максимальное улучшение!");
                            secondItem_meta.setLore(secondItem_lore);
                            secondItem_meta.setUnbreakable(true);
                            secondItem.setItemMeta(secondItem_meta);
                        } else {
                            int number = 0;
                            int count = upgradeSwords.size();
                            for (int c = 0; c < count; c++) {
                                String sword = upgradeSwords.get(c);
                                if (material.equals(sword)) {
                                    number = c + 1;
                                    break;
                                }
                            }
                            if (shr == 5) {
                                String finalName = upgradeSwords.get(number);
                                secondItem.setType(Material.valueOf(finalName));
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                                secondItem.addEnchantments(firstItem.getEnchantments());
                                secondItem.removeEnchantment(Enchantment.DAMAGE_ALL);
                            } else if (shr >= 1) {
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                                secondItem.addEnchantments(firstItem.getEnchantments());
                                secondItem.addEnchantment(Enchantment.DAMAGE_ALL, shr+1);
                            } else if (shr == 0) {
                                secondItem_meta.setUnbreakable(true);
                                secondItem.setItemMeta(secondItem_meta);
                                secondItem.addEnchantments(firstItem.getEnchantments());
                                secondItem.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                            }
                        }
                    }
                    int ebal = (int) economy.getBalance(player);
                    String bal = String.valueOf(ebal);
                    String materr = firstItem.getType().toString();
                    int lvl = secondItem.getEnchantmentLevel(Enchantment.DIG_SPEED) + secondItem.getEnchantmentLevel(Enchantment.DAMAGE_ALL) + secondItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
                    FileConfiguration config = plugin.getConfig();
                    String path = "Upgrade." + materr;
                    if (lvl == 0 & !firstItem.getType().toString().contains("DIAMOND")) {
                        lvl = 6;
                        path = "Upgrade." + materr;
                    }
                    Msg.send(player, "Проверка " + path);
                    ArrayList<String> configs = (ArrayList<String>) config.getConfigurationSection(path).getStringList(String.valueOf(lvl));
                    int cfgLenght = configs.size();
                    if (firstItem.containsEnchantment(Enchantment.DIG_SPEED) || secondItem.containsEnchantment(Enchantment.DIG_SPEED)) {
                        stat_lore.add(ChatColor.GRAY + bal + "/" + configs.get(0) + ChatColor.WHITE + " ");
                        for (int i = 1; i < cfgLenght; i = i + 3) {
                            String whatToEarn = configs.get(i);
                            String howMuch = configs.get(i + 1);
                            String earnName = configs.get(i + 2);
                            if (!PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:" + whatToEarn + "%").contains("Invalid")) {
                                stat_lore.add(ChatColor.GRAY + PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:" + whatToEarn + "%") + "/" + howMuch + " " + earnName);
                            } else {
                                stat_lore.add(ChatColor.GRAY + PlaceholderAPI.setPlaceholders(player, "%statistic_kill_entity:" + whatToEarn + "%") + "/" + howMuch + " " + earnName);
                            }
                        }
                    } else if (firstItem.containsEnchantment(Enchantment.DAMAGE_ALL) || secondItem.containsEnchantment(Enchantment.DAMAGE_ALL) || firstItem.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) || secondItem.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                        stat_lore.add(ChatColor.GRAY + bal + "/" + configs.get(0) + ChatColor.WHITE + " ");
                        for (int i = 1; i < cfgLenght; i = i + 3) {
                            String whatToEarn = configs.get(i);
                            String howMuch = configs.get(i + 1);
                            String earnName = configs.get(i + 2);
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
            } else {
                Msg.send(player, "В ваших руках ничего нет!");
            }
            return true;
        }
        return true;
    }
}