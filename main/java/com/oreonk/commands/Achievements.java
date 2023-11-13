package com.oreonk.commands;

import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Achievements implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        Player player = (Player) sender;
        if (arguments.length>0) {
            return false;
        }
            Inventory gui = Bukkit.createInventory(player, 54, "Достижения");
            //Копай пока молодой
            ItemStack dig_one = new ItemStack(Material.STONE_PICKAXE);
            ItemMeta dig_one_meta = dig_one.getItemMeta();
            ArrayList<String> dig_one_lore = new ArrayList<>();
            dig_one_meta.setDisplayName("Копай пока молодой");
            dig_one_lore.add(ChatColor.GRAY + "");
            dig_one_lore.add(ChatColor.GRAY + "Накопайте 1000 блоков");
            dig_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            //Выполнена ли ачивка
            if (Prison.getInstance().DIG_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("DIG_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    dig_one_lore.add(ChatColor.GRAY + "");
                    dig_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    dig_one_lore.add(ChatColor.GREEN + "получения эффекта спешки на 5 минут.");
                } else {
                    dig_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    dig_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    dig_one_lore.add(ChatColor.GRAY + "");
                    dig_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                dig_one_lore.add(ChatColor.GRAY + "");
                dig_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            dig_one_meta.setLore(dig_one_lore);
            dig_one.setItemMeta(dig_one_meta);
            gui.setItem(0, dig_one);

            //И куда это вставлять?
            ItemStack key_one = new ItemStack(Material.GHAST_TEAR);
            ItemMeta key_one_meta = key_one.getItemMeta();
            ArrayList<String> key_one_lore = new ArrayList<>();
            key_one_meta.setDisplayName("И куда это вставлять?");
            key_one_lore.add(ChatColor.GRAY + "");
            key_one_lore.add(ChatColor.GRAY + "Найдите ключ");
            //Выполнена ли ачивка
            if (Prison.getInstance().KEY_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("KEY_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    key_one_lore.add(ChatColor.GRAY + "");
                    key_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    key_one_lore.add(ChatColor.GREEN + "получения ключа.");
                } else {
                    key_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    key_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    key_one_lore.add(ChatColor.GRAY + "");
                    key_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                key_one_lore.add(ChatColor.GRAY + "");
                key_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            key_one_meta.setLore(key_one_lore);
            key_one.setItemMeta(key_one_meta);
            gui.setItem(1, key_one);

            //Машина
            ItemStack boss_one = new ItemStack(Material.IRON_SWORD);
            ItemMeta boss_one_meta = boss_one.getItemMeta();
            ArrayList<String> boss_one_lore = new ArrayList<>();
            boss_one_meta.setDisplayName("Машина");
            boss_one_lore.add(ChatColor.GRAY + "");
            boss_one_lore.add(ChatColor.GRAY + "Нанесите последний удар по боссу");
            boss_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            //Выполнена ли ачивка
            if (Prison.getInstance().BOSS_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("BOSS_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    boss_one_lore.add(ChatColor.GRAY + "");
                    boss_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    boss_one_lore.add(ChatColor.GREEN + "получения 30 и бустера блоков х2");
                    boss_one_lore.add(ChatColor.GREEN + "на 5 минут.");
                } else {
                    boss_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    boss_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    boss_one_lore.add(ChatColor.GRAY + "");
                    boss_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                boss_one_lore.add(ChatColor.GRAY + "");
                boss_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            boss_one_meta.setLore(boss_one_lore);
            boss_one.setItemMeta(boss_one_meta);
            gui.setItem(2, boss_one);

            //Барыга
            ItemStack sell_one = new ItemStack(Material.GOLD_INGOT);
            ItemMeta sell_one_meta = sell_one.getItemMeta();
            ArrayList<String> sell_one_lore = new ArrayList<>();
            sell_one_meta.setDisplayName("Барыга");
            sell_one_lore.add(ChatColor.GRAY + "");
            sell_one_lore.add(ChatColor.GRAY + "Продайте предмет на аукционе");
            //Выполнена ли ачивка
            if (Prison.getInstance().SELL_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("SELL_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    sell_one_lore.add(ChatColor.GRAY + "");
                    sell_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    sell_one_lore.add(ChatColor.GREEN + "получения 50 и ключа.");
                } else {
                    sell_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    sell_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    sell_one_lore.add(ChatColor.GRAY + "");
                    sell_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                sell_one_lore.add(ChatColor.GRAY + "");
                sell_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            sell_one_meta.setLore(sell_one_lore);
            sell_one.setItemMeta(sell_one_meta);
            gui.setItem(3, sell_one);

            //Владелец недвижимости
            ItemStack home_one = new ItemStack(Material.BRICKS);
            ItemMeta home_one_meta = home_one.getItemMeta();
            ArrayList<String> home_one_lore = new ArrayList<>();
            home_one_meta.setDisplayName("Владелец недвижимости");
            home_one_lore.add(ChatColor.GRAY + "");
            home_one_lore.add(ChatColor.GRAY + "Телепортируйтесь в личную камеру");
            //Выполнена ли ачивка
            if (Prison.getInstance().HOME_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("HOME_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    home_one_lore.add(ChatColor.GRAY + "");
                    home_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    home_one_lore.add(ChatColor.GREEN + "получения 75.");
                } else {
                    home_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    home_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    home_one_lore.add(ChatColor.GRAY + "");
                    home_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                home_one_lore.add(ChatColor.GRAY + "");
                home_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            home_one_meta.setLore(home_one_lore);
            home_one.setItemMeta(home_one_meta);
            gui.setItem(4, home_one);

            //Искатель
            ItemStack collection_one = new ItemStack(Material.SPIDER_EYE);
            ItemMeta collection_one_meta = collection_one.getItemMeta();
            ArrayList<String> collection_one_lore = new ArrayList<>();
            collection_one_meta.setDisplayName("Искатель");
            collection_one_lore.add(ChatColor.GRAY + "");
            collection_one_lore.add(ChatColor.GRAY + "Сдайте 3 коллекционных предмета");
            //Выполнена ли ачивка
            if (Prison.getInstance().COLLECTION_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("COLLECTION_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    collection_one_lore.add(ChatColor.GRAY + "");
                    collection_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    collection_one_lore.add(ChatColor.GREEN + "получения 3 ключей.");
                } else {
                    collection_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    collection_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    collection_one_lore.add(ChatColor.GRAY + "");
                    collection_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                collection_one_lore.add(ChatColor.GRAY + "");
                collection_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            collection_one_meta.setLore(collection_one_lore);
            collection_one.setItemMeta(collection_one_meta);
            gui.setItem(5, collection_one);

            //Да кто ты такой?
            ItemStack fraction_one = new ItemStack(Material.COAL_BLOCK);
            ItemMeta fraction_one_meta = fraction_one.getItemMeta();
            ArrayList<String> fraction_one_lore = new ArrayList<>();
            fraction_one_meta.setDisplayName("Да кто ты такой?");
            fraction_one_lore.add(ChatColor.GRAY + "");
            fraction_one_lore.add(ChatColor.GRAY + "Вступите во фракцию");
            //Выполнена ли ачивка
            if (Prison.getInstance().FRACTION_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("FRACTION_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    fraction_one_lore.add(ChatColor.GRAY + "");
                    fraction_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    fraction_one_lore.add(ChatColor.GREEN + "получения деревянного меча.");
                } else {
                    fraction_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    fraction_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    fraction_one_lore.add(ChatColor.GRAY + "");
                    fraction_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                fraction_one_lore.add(ChatColor.GRAY + "");
                fraction_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            fraction_one_meta.setLore(fraction_one_lore);
            fraction_one.setItemMeta(fraction_one_meta);
            gui.setItem(6, fraction_one);

            //Охотник
            ItemStack hunter_one = new ItemStack(Material.BOW);
            ItemMeta hunter_one_meta = hunter_one.getItemMeta();
            ArrayList<String> hunter_one_lore = new ArrayList<>();
            hunter_one_meta.setDisplayName("Охотник");
            hunter_one_lore.add(ChatColor.GRAY + "");
            hunter_one_lore.add(ChatColor.GRAY + "Убейте 25 опоссумов");
            //Выполнена ли ачивка
            if (Prison.getInstance().HUNTER_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("HUNTER_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    hunter_one_lore.add(ChatColor.GRAY + "");
                    hunter_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    hunter_one_lore.add(ChatColor.GREEN + "получения бустера денег х1.5.");
                    hunter_one_lore.add(ChatColor.GREEN + "на 5 минут.");
                } else {
                    hunter_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    hunter_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    hunter_one_lore.add(ChatColor.GRAY + "");
                    hunter_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                hunter_one_lore.add(ChatColor.GRAY + "");
                hunter_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            hunter_one_meta.setLore(hunter_one_lore);
            hunter_one.setItemMeta(hunter_one_meta);
            gui.setItem(7, hunter_one);

            //Бандит
            ItemStack solo_boss_one = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta solo_boss_one_meta = solo_boss_one.getItemMeta();
            ArrayList<String> solo_boss_one_lore = new ArrayList<>();
            solo_boss_one_meta.setDisplayName("Бандит");
            solo_boss_one_lore.add(ChatColor.GRAY + "");
            solo_boss_one_lore.add(ChatColor.GRAY + "Организуйте стрелку");
            solo_boss_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            //Выполнена ли ачивка
            if (Prison.getInstance().SOLO_BOSS_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("SOLO_BOSS_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    solo_boss_one_lore.add(ChatColor.GRAY + "");
                    solo_boss_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    solo_boss_one_lore.add(ChatColor.GREEN + "получения бустера мобов х2.");
                    solo_boss_one_lore.add(ChatColor.GREEN + "на 10 минут.");
                } else {
                    solo_boss_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    solo_boss_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    solo_boss_one_lore.add(ChatColor.GRAY + "");
                    solo_boss_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                solo_boss_one_lore.add(ChatColor.GRAY + "");
                solo_boss_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            solo_boss_one_meta.setLore(solo_boss_one_lore);
            solo_boss_one.setItemMeta(solo_boss_one_meta);
            gui.setItem(8, solo_boss_one);

            //Модник
            ItemStack armor_one = new ItemStack(Material.LEATHER_HELMET);
            ItemMeta armor_one_meta = armor_one.getItemMeta();
            ArrayList<String> armor_one_lore = new ArrayList<>();
            armor_one_meta.setDisplayName("Модник");
            armor_one_lore.add(ChatColor.GRAY + "");
            armor_one_lore.add(ChatColor.GRAY + "Наденьте полный сет брони");
            armor_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            //Выполнена ли ачивка
            if (Prison.getInstance().ARMOR_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("ARMOR_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    armor_one_lore.add(ChatColor.GRAY + "");
                    armor_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    armor_one_lore.add(ChatColor.GREEN + "получения 300 .");
                } else {
                    armor_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    armor_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    armor_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    armor_one_lore.add(ChatColor.GRAY + "");
                    armor_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                armor_one_lore.add(ChatColor.GRAY + "");
                armor_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            armor_one_meta.setLore(armor_one_lore);
            armor_one.setItemMeta(armor_one_meta);
            gui.setItem(9, armor_one);

            //Пай-мальчик
            ItemStack battlepass_one = new ItemStack(Material.GOLD_BLOCK);
            ItemMeta battlepass_one_meta = battlepass_one.getItemMeta();
            ArrayList<String> battlepass_one_lore = new ArrayList<>();
            battlepass_one_meta.setDisplayName("Пай-мальчик");
            battlepass_one_lore.add(ChatColor.GRAY + "");
            battlepass_one_lore.add(ChatColor.GRAY + "Выполните задание баттлпасса");
            //Выполнена ли ачивка
            if (Prison.getInstance().BATTLEPASS_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("BATTLEPASS_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    battlepass_one_lore.add(ChatColor.GRAY + "");
                    battlepass_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    battlepass_one_lore.add(ChatColor.GREEN + "получения древнего ключа.");
                } else {
                    battlepass_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    battlepass_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    battlepass_one_lore.add(ChatColor.GRAY + "");
                    battlepass_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                battlepass_one_lore.add(ChatColor.GRAY + "");
                battlepass_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            battlepass_one_meta.setLore(battlepass_one_lore);
            battlepass_one.setItemMeta(battlepass_one_meta);
            gui.setItem(10, battlepass_one);

            //Подземный житель
            ItemStack underground_one = new ItemStack(Material.DIRT);
            ItemMeta underground_one_meta = underground_one.getItemMeta();
            ArrayList<String> underground_one_lore = new ArrayList<>();
            underground_one_meta.setDisplayName("Подземный житель");
            underground_one_lore.add(ChatColor.GRAY + "");
            underground_one_lore.add(ChatColor.GRAY + "Спуститесь в подвал");
            //Выполнена ли ачивка
            if (Prison.getInstance().UNDERGROUND_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("UNDERGROUND_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    underground_one_lore.add(ChatColor.GRAY + "");
                    underground_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    underground_one_lore.add(ChatColor.GREEN + "получения бустера мобов х2 на 10 минут.");
                } else {
                    underground_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    underground_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    underground_one_lore.add(ChatColor.GRAY + "");
                    underground_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                underground_one_lore.add(ChatColor.GRAY + "");
                underground_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            underground_one_meta.setLore(underground_one_lore);
            underground_one.setItemMeta(underground_one_meta);
            gui.setItem(11, underground_one);

            //Святой
            ItemStack holy_one = new ItemStack(Material.BEACON);
            ItemMeta holy_one_meta = holy_one.getItemMeta();
            ArrayList<String> holy_one_lore = new ArrayList<>();
            holy_one_meta.setDisplayName("Святой");
            holy_one_lore.add(ChatColor.GRAY + "");
            holy_one_lore.add(ChatColor.GRAY + "Сделайте единоразовое пожертвование церкви размером");
            holy_one_lore.add(ChatColor.GRAY + "в 1000");
            //Выполнена ли ачивка
            if (Prison.getInstance().HOLY_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("HOLY_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    holy_one_lore.add(ChatColor.GRAY + "");
                    holy_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    holy_one_lore.add(ChatColor.GREEN + "получения эффекта спешки 1 на");
                    holy_one_lore.add(ChatColor.GREEN + "10 минут.");
                } else {
                    holy_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    holy_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    holy_one_lore.add(ChatColor.GRAY + "");
                    holy_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                holy_one_lore.add(ChatColor.GRAY + "");
                holy_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            holy_one_meta.setLore(holy_one_lore);
            holy_one.setItemMeta(holy_one_meta);
            gui.setItem(12, holy_one);

            //Вот это агрегат!
            ItemStack upgrade_one = new ItemStack(Material.ANVIL);
            ItemMeta upgrade_one_meta = upgrade_one.getItemMeta();
            ArrayList<String> upgrade_one_lore = new ArrayList<>();
            upgrade_one_meta.setDisplayName("Вот это агрегат!");
            upgrade_one_lore.add(ChatColor.GRAY + "");
            upgrade_one_lore.add(ChatColor.GRAY + "Улучшите лопату до уровня железной");
            upgrade_one_lore.add(ChatColor.GRAY + "эффективность 3");
            //Выполнена ли ачивка
            if (Prison.getInstance().UPGRADE_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("UPGRADE_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    upgrade_one_lore.add(ChatColor.GRAY + "");
                    upgrade_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    upgrade_one_lore.add(ChatColor.GREEN + "получения древнего ключа.");
                } else {
                    upgrade_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    upgrade_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    upgrade_one_lore.add(ChatColor.GRAY + "");
                    upgrade_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                upgrade_one_lore.add(ChatColor.GRAY + "");
                upgrade_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            upgrade_one_meta.setLore(upgrade_one_lore);
            upgrade_one.setItemMeta(upgrade_one_meta);
            gui.setItem(13, upgrade_one);

            //Садовник
            ItemStack farm_one = new ItemStack(Material.STONE_HOE);
            ItemMeta farm_one_meta = farm_one.getItemMeta();
            ArrayList<String> farm_one_lore = new ArrayList<>();
            farm_one_meta.setDisplayName("Садовник");
            farm_one_lore.add(ChatColor.GRAY + "");
            farm_one_lore.add(ChatColor.GRAY + "Купите ферму в личной камере");
            farm_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            //Выполнена ли ачивка
            if (Prison.getInstance().FARM_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("FARM_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    farm_one_lore.add(ChatColor.GRAY + "");
                    farm_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    farm_one_lore.add(ChatColor.GREEN + "получения бустера блоков х2");
                    farm_one_lore.add(ChatColor.GREEN + "на 20 минут.");
                } else {
                    farm_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    farm_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    farm_one_lore.add(ChatColor.GRAY + "");
                    farm_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                farm_one_lore.add(ChatColor.GRAY + "");
                farm_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            farm_one_meta.setLore(farm_one_lore);
            farm_one.setItemMeta(farm_one_meta);
            gui.setItem(14, farm_one);

            //Коллекционер
            ItemStack collection_two = new ItemStack(Material.FERMENTED_SPIDER_EYE);
            ItemMeta collection_two_meta = collection_two.getItemMeta();
            ArrayList<String> collection_two_lore = new ArrayList<>();
            collection_two_meta.setDisplayName("Коллекционер");
            collection_two_lore.add(ChatColor.GRAY + "");
            collection_two_lore.add(ChatColor.GRAY + "Соберите полную коллекцию в личной камере");
            //Выполнена ли ачивка
            if (Prison.getInstance().COLLECTION_TWO.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("COLLECTION_TWO_REWARD").contains(player.getUniqueId().toString())) {
                    collection_two_lore.add(ChatColor.GRAY + "");
                    collection_two_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    collection_two_lore.add(ChatColor.GREEN + "эффекта спешки на 20 минут");
                } else {
                    collection_two_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    collection_two_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    collection_two_lore.add(ChatColor.GRAY + "");
                    collection_two_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                collection_two_lore.add(ChatColor.GRAY + "");
                collection_two_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            collection_two_meta.setLore(collection_two_lore);
            collection_two.setItemMeta(collection_two_meta);
            gui.setItem(15, collection_two);

            //Отдых?Это кто?
            ItemStack sweat_one = new ItemStack(Material.NETHERITE_PICKAXE);
            ItemMeta sweat_one_meta = sweat_one.getItemMeta();
            ArrayList<String> sweat_one_lore = new ArrayList<>();
            sweat_one_meta.setDisplayName("Отдых?Это кто?");
            sweat_one_lore.add(ChatColor.GRAY + "");
            sweat_one_lore.add(ChatColor.GRAY + "За 24 часа накопайте 300000 блоков");
            sweat_one_lore.add(ChatColor.GRAY + "без учёта бустеров");
            sweat_one_lore.add(ChatColor.GRAY + "");
            sweat_one_lore.add(ChatColor.GRAY + "Ваш прогресс (обновляется каждые 10 блоков:");
            sweat_one_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            if (Prison.getInstance().SWEAT_ONE_COUNT.containsKey(player)) {
                sweat_one_lore.add(ChatColor.GOLD + Prison.getInstance().SWEAT_ONE_COUNT.get(player).toString() + ChatColor.GRAY + "/" + ChatColor.GOLD + "300000");
            } else {
                sweat_one_lore.add(ChatColor.GOLD + "0" + ChatColor.GRAY + " / " + ChatColor.GOLD + "300000");
            }
            //Выполнена ли ачивка
            if (Prison.getInstance().SWEAT_ONE.contains(player.getUniqueId().toString())) {
                //Проверка на наличие ЮЮИД в листе собраных наград
                if (!Prison.getInstance().getAchievementsConfig().getStringList("SWEAT_ONE_REWARD").contains(player.getUniqueId().toString())) {
                    sweat_one_lore.add(ChatColor.GRAY + "");
                    sweat_one_lore.add(ChatColor.GREEN + "Достижение выполнено! Нажмите для");
                    sweat_one_lore.add(ChatColor.GREEN + "получения трёх древних ключей.");
                } else {
                    sweat_one_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                    sweat_one_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    sweat_one_lore.add(ChatColor.GRAY + "");
                    sweat_one_lore.add(ChatColor.GREEN + "Награда за достижение получена.");
                }
            } else {
                sweat_one_lore.add(ChatColor.GRAY + "");
                sweat_one_lore.add(ChatColor.RED + "Условия выполнения не выполнены!");
            }
            sweat_one_meta.setLore(sweat_one_lore);
            sweat_one.setItemMeta(sweat_one_meta);
            gui.setItem(16, sweat_one);


            //Закрыть
            ItemStack close = new ItemStack(Material.RED_WOOL);
            ItemMeta close_meta = close.getItemMeta();
            close_meta.setDisplayName(ChatColor.WHITE + "Закрыть меню");
            close.setItemMeta(close_meta);
            gui.setItem(49, close);
            player.openInventory(gui);
            return true;
        }
}

