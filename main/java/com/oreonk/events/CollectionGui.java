package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CollectionGui implements Listener {
    @EventHandler
    public void guiInteract(PlayerInteractEvent event){
        //Попробовать оптимизировать? (постоянный вызов при интеракте)
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("cells") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Location locationOne = new Location(Bukkit.getWorld("cells"), 4, 100,0);
            Location locationTwo = new Location(Bukkit.getWorld("cells"), 5, 100,0);
            Location locationThree = new Location(Bukkit.getWorld("cells"), 6, 100,0);
            if (event.getClickedBlock().getLocation().equals(locationOne) || event.getClickedBlock().getLocation().equals(locationTwo) || event.getClickedBlock().getLocation().equals(locationThree)){
                createGUI(event.getPlayer());
            }
        }
    }
    @EventHandler
    public void inGuiInteract(InventoryClickEvent event){
        if (event.getView().getTitle().contains("Коллекции")){
            Player player = (Player) event.getWhoClicked();
            Economy economy = Prison.getEconomy();
            if (event.getCurrentItem().getType().equals(Material.ARROW)){
                player.closeInventory();
            } else if (event.getCurrentItem().getType().equals(Material.BROWN_MUSHROOM)){
                ItemStack mush = new ItemStack(Material.BROWN_MUSHROOM);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&6Гриб");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "MUSHROOM") <= 3){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "MUSHROOM");
                            economy.depositPlayer(player, 5);
                            Msg.send(player, ChatColor.GREEN + "5 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 1);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 1 единицу интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "MUSHROOM");
                            economy.depositPlayer(player, 5);
                            Msg.send(player, ChatColor.GREEN + "5 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.GHAST_TEAR)){
                ItemStack mush = new ItemStack(Material.GHAST_TEAR);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&9Кусок кварца");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "QUARTZ") <= 2){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "QUARTZ");
                            economy.depositPlayer(player, 20);
                            Msg.send(player, ChatColor.GREEN + "20 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 2);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 2 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "QUARTZ");
                            economy.depositPlayer(player, 5);
                            Msg.send(player, ChatColor.GREEN + "5 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.GUNPOWDER)){
                ItemStack mush = new ItemStack(Material.GUNPOWDER);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&7Каменная пыль");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "STONE_DUST") <= 2){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "STONE_DUST");
                            economy.depositPlayer(player, 50);
                            Msg.send(player, ChatColor.GREEN + "50 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 2);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 2 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "STONE_DUST");
                            economy.depositPlayer(player, 15);
                            Msg.send(player, ChatColor.GREEN + "15 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.PUMPKIN_SEEDS)){
                ItemStack mush = new ItemStack(Material.PUMPKIN_SEEDS);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&eДубовые опилки");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "OAK_SAWDUST") <= 2){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "OAK_SAWDUST");
                            economy.depositPlayer(player, 300);
                            Msg.send(player, ChatColor.GREEN + "300 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 2);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 2 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "OAK_SAWDUST");
                            economy.depositPlayer(player, 50);
                            Msg.send(player, ChatColor.GREEN + "50 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.COAL)){
                ItemStack mush = new ItemStack(Material.COAL);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&8Карбонат");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "COAL") <= 2){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "COAL");
                            economy.depositPlayer(player, 400);
                            Msg.send(player, ChatColor.GREEN + "300 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 3);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 3 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "COAL");
                            economy.depositPlayer(player, 75);
                            Msg.send(player, ChatColor.GREEN + "75 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.WHEAT)){
                ItemStack mush = new ItemStack(Material.WHEAT);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&8Ячмень");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "BARLEY") <= 2){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "BARLEY");
                            economy.depositPlayer(player, 500);
                            Msg.send(player, ChatColor.GREEN + "500 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 3);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 3 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "BARLEY");
                            economy.depositPlayer(player, 100);
                            Msg.send(player, ChatColor.GREEN + "100 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.IRON_NUGGET)){
                ItemStack mush = new ItemStack(Material.IRON_NUGGET);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&dСталактит");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "STALACTITE") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "STALACTITE");
                            economy.depositPlayer(player, 900);
                            Msg.send(player, ChatColor.GREEN + "900 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 4);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 4 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "STALACTITE");
                            economy.depositPlayer(player, 200);
                            Msg.send(player, ChatColor.GREEN + "200 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.GREEN_DYE)){
                ItemStack mush = new ItemStack(Material.GREEN_DYE);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&aМох");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "MUSH") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "MUSH");
                            economy.depositPlayer(player, 1400);
                            Msg.send(player, ChatColor.GREEN + "1400 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 4);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 4 единицы интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "MUSH");
                            economy.depositPlayer(player, 250);
                            Msg.send(player, ChatColor.GREEN + "250 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.BRICK)){
                ItemStack mush = new ItemStack(Material.BRICK);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&cЗатвердевшая порода");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "BEDROCK") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "BEDROCK");
                            economy.depositPlayer(player, 3000);
                            Msg.send(player, ChatColor.GREEN + "3000 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 5);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 5 единиц интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "BEDROCK");
                            economy.depositPlayer(player, 300);
                            Msg.send(player, ChatColor.GREEN + "300 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.CYAN_DYE)){
                ItemStack mush = new ItemStack(Material.CYAN_DYE);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&bАлмазная крошка");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "DIAMOND_DUST") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "DIAMOND_DUST");
                            economy.depositPlayer(player, 7000);
                            Msg.send(player, ChatColor.GREEN + "7000 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 5);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 5 единиц интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "DIAMOND_DUST");
                            economy.depositPlayer(player, 1000);
                            Msg.send(player, ChatColor.GREEN + "1000 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.ACACIA_SAPLING)){
                ItemStack mush = new ItemStack(Material.ACACIA_SAPLING);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&eВеточка");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "STICK") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "STICK");
                            economy.depositPlayer(player, 30000);
                            Msg.send(player, ChatColor.GREEN + "30000 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 6);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 6 единиц интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "STICK");
                            economy.depositPlayer(player, 2000);
                            Msg.send(player, ChatColor.GREEN + "2000 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.FIRE_CHARGE)){
                ItemStack mush = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&4Душа грешника");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "SOUL") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "SOUL");
                            economy.depositPlayer(player, 70000);
                            Msg.send(player, ChatColor.GREEN + "70000 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 6);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 6 единиц интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "SOUL");
                            economy.depositPlayer(player, 4000);
                            Msg.send(player, ChatColor.GREEN + "4000 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.ENDER_EYE)){
                ItemStack mush = new ItemStack(Material.ENDER_EYE);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&dВсевидящее око");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "EYE") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "EYE");
                            economy.depositPlayer(player, 150000);
                            Msg.send(player, ChatColor.GREEN + "150000 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 7);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 7 единиц интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "EYE");
                            economy.depositPlayer(player, 25000);
                            Msg.send(player, ChatColor.GREEN + "25000 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            } else if (event.getCurrentItem().getType().equals(Material.SHULKER_SHELL)){
                ItemStack mush = new ItemStack(Material.SHULKER_SHELL);
                ItemMeta mush_meta = mush.getItemMeta();
                mush_meta.setDisplayName("&5Осколок вольфрама");
                mush.setItemMeta(mush_meta);
                if (player.getInventory().contains(mush)) {
                    player.getInventory().remove(mush);
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        if (Prison.getInstance().getDatabase().getCollectionLvl(player, "TUNGSTEN") <= 1){
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "TUNGSTEN");
                            economy.depositPlayer(player, 300000);
                            Msg.send(player, ChatColor.GREEN + "300000 было зачисленно на ваш счёт");
                            if (Prison.getInstance().getDatabase().getIntellectStat(player) < 100 ) {
                                Prison.getInstance().getDatabase().addIntellectStat(player, 7);
                                Msg.send(player, ChatColor.GREEN + "Вы так же получили 7 единиц интеллекта. Всего: " + Prison.getInstance().getDatabase().getIntellectStat(player));
                            } else {
                                Msg.send(player, ChatColor.GREEN + "Вы достигли максимального значения интелекта!");
                            }
                        } else {
                            Prison.getInstance().getDatabase().addCollectionLvl(player, "TUNGSTEN");
                            economy.depositPlayer(player, 40000);
                            Msg.send(player, ChatColor.GREEN + "40000 было зачисленно на ваш счёт");
                        }
                    });
                } else {
                    Msg.send(player, ChatColor.RED + "У вас нет этого предмета!");
                }
            }
        }
    }
    private void createGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, "Коллекции");

        ItemStack close = new ItemStack(Material.ARROW);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.GRAY + "Закрыть меню");
        close.setItemMeta(close_meta);
        gui.setItem(45, close);

        ItemStack info = new ItemStack(Material.NETHER_STAR);
        ItemMeta info_meta = info.getItemMeta();
        info_meta.setDisplayName(ChatColor.GRAY + "Информация о коллекциях");

        ArrayList<String> info_lore = new ArrayList<>();
        info_lore.add(ChatColor.GRAY + "Вы можете отдавать бесконечное количество");
        info_lore.add(ChatColor.GRAY + "коллекционных предметов, для получения");
        info_lore.add(ChatColor.GRAY + "денег, однако, количество интеллекта, ");
        info_lore.add(ChatColor.GRAY + "с одного предмета ограничено.");
        info_lore.add(ChatColor.GRAY + "");
        info_lore.add(ChatColor.GRAY + "Основная характеристика, получаемая");
        info_lore.add(ChatColor.GRAY + "за сбор коллекций - интелект. Больше");
        info_lore.add(ChatColor.GRAY + "интеллекта - выше бустер.");
        info_lore.add(ChatColor.GRAY + "");
        info_lore.add(ChatColor.GRAY + "20-39 - бустер 1.1; 40-59 - 1.15");
        info_lore.add(ChatColor.GRAY + "60-79 - бустер 1.2; 80-99 - 1.25");
        info_lore.add(ChatColor.GRAY + "100 - бустер 1.3");
        info_lore.add(ChatColor.GRAY + "");
        info_lore.add(ChatColor.GRAY + "За каждые 20 очков загорится одна ячейка");
        //Ячейки
        int intellect = Prison.getInstance().getDatabase().getIntellectStat(player);
        if (intellect >= 20 && intellect < 40) {
            info_lore.add(ChatColor.GRAY + "Ваш интеллект -" + ChatColor.GREEN + ChatColor.BOLD + "▌"+ ChatColor.RED + ChatColor.BOLD + "▌▌▌▌");
        } else if (intellect >= 40 && intellect < 60){
            info_lore.add(ChatColor.GRAY + "Ваш интеллект -" + ChatColor.GREEN + ChatColor.BOLD + "▌▌"+ ChatColor.RED + ChatColor.BOLD + "▌▌▌");
        } else if (intellect >= 60 && intellect < 80){
            info_lore.add(ChatColor.GRAY + "Ваш интеллект -" + ChatColor.GREEN + ChatColor.BOLD + "▌▌▌"+ ChatColor.RED + ChatColor.BOLD + "▌▌");
        } else if (intellect >= 80 && intellect < 100){
            info_lore.add(ChatColor.GRAY + "Ваш интеллект -" + ChatColor.GREEN + ChatColor.BOLD + "▌▌▌▌"+ ChatColor.RED + ChatColor.BOLD + "▌");
        } else if (intellect == 100){
            info_lore.add(ChatColor.GRAY + "Ваш интеллект -" + ChatColor.GREEN + ChatColor.BOLD + "▌▌▌▌▌");
        } else {
            info_lore.add(ChatColor.GRAY + "Ваш интеллект -" + ChatColor.RED + ChatColor.BOLD + "▌▌▌▌▌");
        }
        gui.setItem(49,info);

        ItemStack mushroom = new ItemStack(Material.BROWN_MUSHROOM);
        ItemMeta mushroom_meta = mushroom.getItemMeta();
        mushroom_meta.setDisplayName("&6Гриб");

        ArrayList<String> mushroom_lore = new ArrayList<>();
        mushroom_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 5");
        mushroom_lore.add(ChatColor.GRAY + "и 1 единицы интелекта (максимум - 3).");
        mushroom_meta.setLore(mushroom_lore);

        mushroom.setItemMeta(mushroom_meta);
        gui.setItem(10,mushroom);

        ItemStack quartz = new ItemStack(Material.GHAST_TEAR);
        ItemMeta quartz_meta = quartz.getItemMeta();
        quartz_meta.setDisplayName("&9Кусок кварца");

        ArrayList<String> quartz_lore = new ArrayList<>();
        quartz_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 20");
        quartz_lore.add(ChatColor.GRAY + "и 2 единиц интелекта (максимум - 4).");
        quartz_lore.add(ChatColor.GRAY + "после достижения максимума даёт 5.");
        quartz_meta.setLore(quartz_lore);

        quartz.setItemMeta(quartz_meta);
        gui.setItem(11,quartz);

        ItemStack dust = new ItemStack(Material.GUNPOWDER);
        ItemMeta dust_meta = dust.getItemMeta();
        dust_meta.setDisplayName("&7Каменная пыль");

        ArrayList<String> dust_lore = new ArrayList<>();
        dust_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 50");
        dust_lore.add(ChatColor.GRAY + "и 2 единиц интелекта (максимум - 4).");
        dust_lore.add(ChatColor.GRAY + "после достижения максимума даёт 15.");
        dust_meta.setLore(dust_lore);

        dust.setItemMeta(dust_meta);
        gui.setItem(12,dust);

        ItemStack sawdust = new ItemStack(Material.PUMPKIN_SEEDS);
        ItemMeta sawdust_meta = sawdust.getItemMeta();
        sawdust_meta.setDisplayName("&eДубовые опилки");

        ArrayList<String> sawdust_lore = new ArrayList<>();
        sawdust_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 300");
        sawdust_lore.add(ChatColor.GRAY + "и 3 единиц интелекта (максимум - 6).");
        sawdust_lore.add(ChatColor.GRAY + "после достижения максимума даёт 50.");
        sawdust_meta.setLore(sawdust_lore);

        sawdust.setItemMeta(sawdust_meta);
        gui.setItem(13,sawdust);

        ItemStack coal = new ItemStack(Material.COAL);
        ItemMeta coal_meta = coal.getItemMeta();
        coal_meta.setDisplayName("&8Карбонат");

        ArrayList<String> coal_lore = new ArrayList<>();
        coal_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 400");
        coal_lore.add(ChatColor.GRAY + "и 3 единиц интелекта (максимум - 6).");
        coal_lore.add(ChatColor.GRAY + "после достижения максимума даёт 75.");
        coal_meta.setLore(coal_lore);

        coal.setItemMeta(coal_meta);
        gui.setItem(14,coal);

        ItemStack barley = new ItemStack(Material.WHEAT);
        ItemMeta barley_meta = barley.getItemMeta();
        barley_meta.setDisplayName("&8Ячмень");

        ArrayList<String> barley_lore = new ArrayList<>();
        barley_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 500");
        barley_lore.add(ChatColor.GRAY + "и 3 единиц интелекта (максимум - 6).");
        barley_lore.add(ChatColor.GRAY + "после достижения максимума даёт 100.");
        barley_meta.setLore(barley_lore);

        barley.setItemMeta(barley_meta);
        gui.setItem(15,barley);

        ItemStack stalactite = new ItemStack(Material.IRON_NUGGET);
        ItemMeta stalactite_meta = stalactite.getItemMeta();
        stalactite_meta.setDisplayName("&dСталактит");

        ArrayList<String> stalactite_lore = new ArrayList<>();
        stalactite_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 900");
        stalactite_lore.add(ChatColor.GRAY + "и 4 единиц интелекта (максимум - 4).");
        stalactite_lore.add(ChatColor.GRAY + "после достижения максимума даёт 200.");
        stalactite_meta.setLore(stalactite_lore);

        stalactite.setItemMeta(stalactite_meta);
        gui.setItem(16,stalactite);

        ItemStack mush = new ItemStack(Material.GREEN_DYE);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&aМох");

        ArrayList<String> mush_lore = new ArrayList<>();
        mush_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 1400");
        mush_lore.add(ChatColor.GRAY + "и 4 единиц интелекта (максимум - 4).");
        mush_lore.add(ChatColor.GRAY + "после достижения максимума даёт 250.");
        mush_meta.setLore(mush_lore);

        mush.setItemMeta(mush_meta);
        gui.setItem(19,mush);

        ItemStack bedrock = new ItemStack(Material.BRICK);
        ItemMeta bedrock_meta = bedrock.getItemMeta();
        bedrock_meta.setDisplayName("&cЗатвердевшая порода");

        ArrayList<String> bedrock_lore = new ArrayList<>();
        bedrock_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 3000");
        bedrock_lore.add(ChatColor.GRAY + "и 5 единиц интелекта (максимум - 5).");
        bedrock_lore.add(ChatColor.GRAY + "после достижения максимума даёт 300.");
        bedrock_meta.setLore(bedrock_lore);

        bedrock.setItemMeta(bedrock_meta);
        gui.setItem(20,bedrock);

        ItemStack diamondDust = new ItemStack(Material.CYAN_DYE);
        ItemMeta diamondDust_meta = diamondDust.getItemMeta();
        diamondDust_meta.setDisplayName("&bАлмазная крошка");

        ArrayList<String> diamondDust_lore = new ArrayList<>();
        diamondDust_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 7000");
        diamondDust_lore.add(ChatColor.GRAY + "и 5 единиц интелекта (максимум - 5).");
        diamondDust_lore.add(ChatColor.GRAY + "после достижения максимума даёт 1000.");
        diamondDust_meta.setLore(diamondDust_lore);

        diamondDust.setItemMeta(diamondDust_meta);
        gui.setItem(21,diamondDust);

        ItemStack stick = new ItemStack(Material.ACACIA_SAPLING);
        ItemMeta stick_meta = stick.getItemMeta();
        stick_meta.setDisplayName("&eВеточка");

        ArrayList<String> stick_lore = new ArrayList<>();
        stick_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 30000");
        stick_lore.add(ChatColor.GRAY + "и 6 единиц интелекта (максимум - 6).");
        stick_lore.add(ChatColor.GRAY + "после достижения максимума даёт 2000.");
        stick_meta.setLore(stick_lore);

        stick.setItemMeta(stick_meta);
        gui.setItem(22,stick);

        ItemStack soul = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta soul_meta = soul.getItemMeta();
        soul_meta.setDisplayName("&4Душа грешника");

        ArrayList<String> soul_lore = new ArrayList<>();
        soul_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 70000");
        soul_lore.add(ChatColor.GRAY + "и 6 единиц интелекта (максимум - 6).");
        soul_lore.add(ChatColor.GRAY + "после достижения максимума даёт 4000.");
        soul_meta.setLore(soul_lore);

        soul.setItemMeta(soul_meta);
        gui.setItem(23,soul);

        ItemStack eye = new ItemStack(Material.ENDER_EYE);
        ItemMeta eye_meta = eye.getItemMeta();
        eye_meta.setDisplayName("&dВсевидящее око");

        ArrayList<String> eye_lore = new ArrayList<>();
        eye_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 150000");
        eye_lore.add(ChatColor.GRAY + "и 7 единиц интелекта (максимум - 7).");
        eye_lore.add(ChatColor.GRAY + "после достижения максимума даёт 25000.");
        eye_meta.setLore(eye_lore);

        eye.setItemMeta(eye_meta);
        gui.setItem(24,eye);

        ItemStack tungsten = new ItemStack(Material.SHULKER_SHELL);
        ItemMeta tungsten_meta = tungsten.getItemMeta();
        tungsten_meta.setDisplayName("&dОсколок вольфрама");

        ArrayList<String> tungsten_lore = new ArrayList<>();
        tungsten_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 300000");
        tungsten_lore.add(ChatColor.GRAY + "и 7 единиц интелекта (максимум - 7).");
        tungsten_lore.add(ChatColor.GRAY + "после достижения максимума даёт 40000.");
        tungsten_meta.setLore(tungsten_lore);

        tungsten.setItemMeta(tungsten_meta);
        gui.setItem(25,tungsten);

        ItemStack heart = new ItemStack(Material.RED_DYE);
        ItemMeta heart_meta = heart.getItemMeta();
        heart_meta.setDisplayName("&cСердце");

        ArrayList<String> heart_lore = new ArrayList<>();
        heart_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения трёх ключей");
        heart_lore.add(ChatColor.GRAY + "и 1 единицы интелекта (максимум - 3).");
        heart_lore.add(ChatColor.GRAY + "после достижения максимума даёт один ключ.");
        heart_meta.setLore(heart_lore);

        heart.setItemMeta(heart_meta);
        gui.setItem(37,heart);

        ItemStack hoe = new ItemStack(Material.STONE_HOE);
        ItemMeta hoe_meta = hoe.getItemMeta();
        hoe_meta.setDisplayName(ChatColor.YELLOW + "Тяпка");

        ArrayList<String> hoe_lore = new ArrayList<>();
        hoe_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 2 единиц");
        hoe_lore.add(ChatColor.GRAY + "интелекта, с максимумом в 6 единиц.");
        hoe_meta.setLore(hoe_lore);

        hoe.setItemMeta(hoe_meta);
        gui.setItem(38,hoe);

        ItemStack potion = new ItemStack(Material.SPLASH_POTION);
        ItemMeta potion_meta = potion.getItemMeta();
        potion_meta.setDisplayName(ChatColor.DARK_PURPLE + "Эликсир");

        ArrayList<String> potion_lore = new ArrayList<>();
        potion_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 3 единиц");
        potion_lore.add(ChatColor.GRAY + "интелекта, с максимумом в 9 единиц.");
        potion_meta.setLore(potion_lore);

        potion.setItemMeta(potion_meta);
        gui.setItem(39,potion);

        ItemStack ra = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta ra_meta = ra.getItemMeta();
        ra_meta.setDisplayName(ChatColor.DARK_RED + "Ра");

        ArrayList<String> ra_lore = new ArrayList<>();
        ra_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения двух ключей");
        ra_lore.add(ChatColor.GRAY + "и 1 единицы интелекта (максимум - 3).");
        ra_lore.add(ChatColor.GRAY + "после достижения максимума даёт по 100.");
        ra_meta.setLore(ra_lore);

        ra.setItemMeta(ra_meta);
        gui.setItem(40,ra);

        ItemStack soul_normal = new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemMeta soul_normal_meta = soul_normal.getItemMeta();
        soul_normal_meta.setDisplayName(ChatColor.AQUA + "Душа");

        ArrayList<String> soul_normal_lore = new ArrayList<>();
        soul_normal_lore.add(ChatColor.GRAY + "Сдайте предмет, для получения 3 единиц");
        soul_normal_lore.add(ChatColor.GRAY + "интелекта, с максимумом в 9 единиц.");
        soul_normal_meta.setLore(soul_normal_lore);

        soul_normal.setItemMeta(soul_normal_meta);
        gui.setItem(41,soul_normal);
    }
}
