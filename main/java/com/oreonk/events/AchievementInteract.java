package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.List;

public class AchievementInteract implements Listener {
    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        if (event.getClickedInventory() != null && event.getCurrentItem() != null) {
            if (event.getView().getTitle().contains("Достижения")){
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();

                //Копай пока молодой
                if (event.getCurrentItem().getType().equals(Material.STONE_PICKAXE)) {
                    if (Prison.getInstance().DIG_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("DIG_ONE_REWARD").contains(player.getUniqueId().toString())){
                            PotionEffect effect = new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 0);
                            player.addPotionEffect(effect);
                            Msg.send(player, "Награда получена.");
                            List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("DIG_ONE_REWARD");
                            list.add(player.getUniqueId().toString());
                            try {
                                Prison.getInstance().getAchievementsConfig().set("DIG_ONE_REWARD", list);
                                Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //И куда это вставлять?
                else if (event.getCurrentItem().getType().equals(Material.GHAST_TEAR)) {
                    if (Prison.getInstance().KEY_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("KEY_ONE_REWARD").contains(player.getUniqueId().toString())){
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Normal").getValues(true));
                            player.getInventory().addItem(key);
                            Msg.send(player, "Награда получена.");
                            List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("KEY_ONE_REWARD");
                            list.add(player.getUniqueId().toString());
                            try {
                                Prison.getInstance().getAchievementsConfig().set("KEY_ONE_REWARD", list);
                                Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Машина
                else if (event.getCurrentItem().getType().equals(Material.IRON_SWORD)) {
                    if (Prison.getInstance().BOSS_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("BOSS_ONE_REWARD").contains(player.getUniqueId().toString())){
                            if (!Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())) {
                                Prison.getInstance().privateBlockBooster.put(player.getUniqueId(), 5);
                                Prison.getInstance().privateBlockBoosterMultiplier.put(player.getUniqueId(), 2);
                                Prison.getEconomy().depositPlayer(player, 30);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("BOSS_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("BOSS_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Msg.send(player, ChatColor.GRAY + "У вас уже есть активный бустер блоков, дождитесь его окончания!");
                            }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Барыга
                else if (event.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
                    if (Prison.getInstance().SELL_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("SELL_ONE_REWARD").contains(player.getUniqueId().toString())){
                                ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Normal").getValues(true));
                                player.getInventory().addItem(key);
                                Prison.getEconomy().depositPlayer(player, 50);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("SELL_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("SELL_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Владелец недвижимости
                else if (event.getCurrentItem().getType().equals(Material.BRICKS)) {
                    if (Prison.getInstance().HOME_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("HOME_ONE_REWARD").contains(player.getUniqueId().toString())){
                                Prison.getEconomy().depositPlayer(player, 75);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("HOME_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("HOME_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Искатель
                else if (event.getCurrentItem().getType().equals(Material.SPIDER_EYE)) {
                    if (Prison.getInstance().COLLECTION_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("COLLECTION_ONE_REWARD").contains(player.getUniqueId().toString())){
                                ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Normal").getValues(true));
                                key.setAmount(3);
                                player.getInventory().addItem(key);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("COLLECTION_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("COLLECTION_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Да кто ты такой?
                else if (event.getCurrentItem().getType().equals(Material.COAL_BLOCK)) {
                    if (Prison.getInstance().FRACTION_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("FRACTION_ONE_REWARD").contains(player.getUniqueId().toString())){
                                ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                                ItemMeta sword_meta = sword.getItemMeta();
                                sword_meta.setUnbreakable(true);
                                sword.setItemMeta(sword_meta);
                                player.getInventory().addItem(sword);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("FRACTION_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("FRACTION_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Охотник
                else if (event.getCurrentItem().getType().equals(Material.BOW)) {
                    if (Prison.getInstance().HUNTER_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("HUNTER_ONE_REWARD").contains(player.getUniqueId().toString())){
                            if (!Prison.getInstance().privateBooster.containsKey(player.getUniqueId())) {
                                Prison.getInstance().privateBooster.put(player.getUniqueId(), 5);
                                Prison.getInstance().privateBoosterMultiplier.put(player.getUniqueId(), 1);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("HUNTER_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("HUNTER_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Msg.send(player, ChatColor.GRAY + "У вас уже есть активный бустер денег, дождитесь его окончания!");
                            }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Бандит
                else if (event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)) {
                    if (Prison.getInstance().SOLO_BOSS_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("SOLO_BOSS_ONE_REWARD").contains(player.getUniqueId().toString())){
                            if (!Prison.getInstance().privateMobBooster.containsKey(player.getUniqueId())) {
                                Prison.getInstance().privateMobBooster.put(player.getUniqueId(), 10);
                                Prison.getInstance().privateMobMultiplier.put(player.getUniqueId(), 2);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("SOLO_BOSS_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("SOLO_BOSS_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Msg.send(player, ChatColor.GRAY + "У вас уже есть активный бустер мобов, дождитесь его окончания!");
                            }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Модник
                else if (event.getCurrentItem().getType().equals(Material.LEATHER_HELMET)) {
                    if (Prison.getInstance().ARMOR_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("ARMOR_ONE_REWARD").contains(player.getUniqueId().toString())){
                                Prison.getEconomy().depositPlayer(player, 300);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("ARMOR_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("ARMOR_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Пай-мальчик
                else if (event.getCurrentItem().getType().equals(Material.GOLD_BLOCK)) {
                    if (Prison.getInstance().BATTLEPASS_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("BATTLEPASS_ONE_REWARD").contains(player.getUniqueId().toString())){
                                ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                                player.getInventory().addItem(key);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("BATTLEPASS_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("BATTLEPASS_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Подземный житель
                else if (event.getCurrentItem().getType().equals(Material.DIRT)) {
                    if (Prison.getInstance().UNDERGROUND_ONE.contains(player.getUniqueId().toString())) {
                        if(!Prison.getInstance().getAchievementsConfig().getStringList("UNDERGROUND_ONE_REWARD").contains(player.getUniqueId().toString())) {
                            if (!Prison.getInstance().privateMobBooster.containsKey(player.getUniqueId())) {
                                Prison.getInstance().privateMobBooster.put(player.getUniqueId(), 10);
                                Prison.getInstance().privateMobMultiplier.put(player.getUniqueId(), 2);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("UNDERGROUND_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("UNDERGROUND_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else{
                                Msg.send(player, ChatColor.GRAY + "У вас уже есть активный бустер мобов, дождитесь его окончания!");
                            }
                        } else {
                            Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Святой
                else if (event.getCurrentItem().getType().equals(Material.BEACON)) {
                    if (Prison.getInstance().HOLY_ONE.contains(player.getUniqueId().toString())) {
                            if(!Prison.getInstance().getAchievementsConfig().getStringList("HOLY_ONE_REWARD").contains(player.getUniqueId().toString())) {
                                PotionEffect effect = new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 0);
                                player.addPotionEffect(effect);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("HOLY_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("HOLY_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else{
                                Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Вот это агрегат
                else if (event.getCurrentItem().getType().equals(Material.ANVIL)) {
                    if (Prison.getInstance().UPGRADE_ONE.contains(player.getUniqueId().toString())) {
                            if(!Prison.getInstance().getAchievementsConfig().getStringList("UPGRADE_ONE_REWARD").contains(player.getUniqueId().toString())) {
                                ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                                player.getInventory().addItem(key);
                                Msg.send(player, "Награда получена.");
                                List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("UPGRADE_ONE_REWARD");
                                list.add(player.getUniqueId().toString());
                                try {
                                    Prison.getInstance().getAchievementsConfig().set("UPGRADE_ONE_REWARD", list);
                                    Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else{
                                Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Садовник
                else if (event.getCurrentItem().getType().equals(Material.STONE_HOE)) {
                    if (Prison.getInstance().FARM_ONE.contains(player.getUniqueId().toString())) {
                            if(!Prison.getInstance().getAchievementsConfig().getStringList("FARM_ONE_REWARD").contains(player.getUniqueId().toString())) {
                                if (!Prison.getInstance().privateBlockBooster.containsKey(player.getUniqueId())) {
                                    Prison.getInstance().privateBlockBooster.put(player.getUniqueId(), 20);
                                    Prison.getInstance().privateBlockBoosterMultiplier.put(player.getUniqueId(), 2);
                                    Msg.send(player, "Награда получена.");
                                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("FARM_ONE_REWARD");
                                    list.add(player.getUniqueId().toString());
                                    try {
                                        Prison.getInstance().getAchievementsConfig().set("FARM_ONE_REWARD", list);
                                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Msg.send(player, ChatColor.GRAY + "У вас уже есть активный бустер блоков, дождитесь его окончания!");
                                }
                            } else {
                                Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Коллекционер
                else if (event.getCurrentItem().getType().equals(Material.FERMENTED_SPIDER_EYE)) {
                    if (Prison.getInstance().COLLECTION_TWO.contains(player.getUniqueId().toString())) {
                            if(!Prison.getInstance().getAchievementsConfig().getStringList("COLLECTION_TWO_REWARD").contains(player.getUniqueId().toString())) {
                                    PotionEffect effect = new PotionEffect(PotionEffectType.FAST_DIGGING, 24000, 0);
                                    player.addPotionEffect(effect);
                                    Msg.send(player, "Награда получена.");
                                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("COLLECTION_TWO_REWARD");
                                    list.add(player.getUniqueId().toString());
                                    try {
                                        Prison.getInstance().getAchievementsConfig().set("COLLECTION_TWO_REWARD", list);
                                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                            } else {
                                Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }

                //Отыдх? Это кто?
                else if (event.getCurrentItem().getType().equals(Material.NETHERITE_PICKAXE)) {
                    if (Prison.getInstance().SWEAT_ONE.contains(player.getUniqueId().toString())) {
                            if(!Prison.getInstance().getAchievementsConfig().getStringList("SWEAT_ONE_REWARD").contains(player.getUniqueId().toString())) {
                                    ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                                    player.getInventory().addItem(key);
                                    Msg.send(player, "Награда получена.");
                                    List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("SWEAT_ONE_REWARD");
                                    list.add(player.getUniqueId().toString());
                                    try {
                                        Prison.getInstance().getAchievementsConfig().set("SWEAT_ONE_REWARD", list);
                                        Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                            } else {
                                Msg.send(player, ChatColor.RED + "Вы уже получили награду за это достижение!");
                            }
                    } else {
                        Msg.send(player, ChatColor.RED + "Условия выполнения достижения не выполнены!");
                    }
                    player.closeInventory();
                }
            }
        }
    }
}
