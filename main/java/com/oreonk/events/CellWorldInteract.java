package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class CellWorldInteract implements Listener {
    @EventHandler
    public void menuInteract (InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            if (event.getView().getTitle().contains("Улучшение начальной камеры") || event.getView().getTitle().contains("Улучшение средней камеры") || event.getView().getTitle().contains("Улучшение большой камеры") || event.getView().getTitle().contains("Улучшение небольшой камеры")) {
                event.setCancelled(true);
            }
            //Апгрейды
            if (event.getView().getTitle().contains("Улучшение начальной камеры") && event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
                Player player = (Player) event.getWhoClicked();
                int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                double price = Double.parseDouble(config.get(0));
                int balance = (int) Prison.getEconomy().getBalance(player);
                int lvl = Integer.parseInt(config.get(2));
                if (cellLvl < 4 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl) {
                    int newCellLvl = cellLvl + 1;
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                    });
                    Msg.send(player, ChatColor.GREEN + "Камера успешно улучшена!");
                    player.closeInventory();
                } else if (cellLvl >= 4) {
                    Msg.send(player, ChatColor.RED + "Улучшите камеру в новом месте. Используйте /cell для телепортации");
                    player.closeInventory();
                } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl) {
                    Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
                    player.closeInventory();
                }
            } else if (event.getView().getTitle().contains("Улучшение небольшой камеры") && event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
                Player player = (Player) event.getWhoClicked();
                int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                double price = Double.parseDouble(config.get(0));
                int balance = (int) Prison.getEconomy().getBalance(player);
                int lvl = Integer.parseInt(config.get(2));
                if (cellLvl < 7 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl && cellLvl >= 4) {
                    int newCellLvl = cellLvl + 1;
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                    });
                    Msg.send(player, ChatColor.GREEN + "Камера успешно улучшена!");
                    player.closeInventory();
                } else if (cellLvl >= 7) {
                    Msg.send(player, ChatColor.RED + "Улучшите камеру в новом месте. Используйте /cell для телепортации");
                    player.closeInventory();
                } else if (cellLvl < 4) {
                    Msg.send(player, ChatColor.RED + "Вас не должно тут быть...Используйте /cell для телепортации");
                    player.closeInventory();
                } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl) {
                    Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
                    player.closeInventory();
                }
            } else if (event.getView().getTitle().contains("Улучшение средней камеры") && event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
                Player player = (Player) event.getWhoClicked();
                int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                double price = Double.parseDouble(config.get(0));
                int balance = (int) Prison.getEconomy().getBalance(player);
                int lvl = Integer.parseInt(config.get(2));
                if (cellLvl < 13 && balance >= price && Prison.getInstance().lvl.get(player) >= lvl && cellLvl >= 7) {
                    int newCellLvl = cellLvl + 1;
                    Bukkit.getScheduler().runTaskAsynchronously(Prison.getInstance(), () -> {
                        Prison.getInstance().getDatabase().updateCellLvl(player, newCellLvl);
                    });
                    Msg.send(player, ChatColor.GREEN + "Камера успешно улучшена!");
                    player.closeInventory();
                } else if (cellLvl >= 13) {
                    Msg.send(player, ChatColor.RED + "Улучшите камеру в новом месте. Используйте /cell для телепортации");
                    player.closeInventory();
                } else if (cellLvl < 7) {
                    Msg.send(player, ChatColor.RED + "Вас не должно тут быть...Используйте /cell для телепортации");
                    player.closeInventory();
                } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl) {
                    Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
                    player.closeInventory();
                }
            } else if (event.getView().getTitle().contains("Улучшение большой камеры") && event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
                Player player = (Player) event.getWhoClicked();
                int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                double price = Double.parseDouble(config.get(0));
                int balance = (int) Prison.getEconomy().getBalance(player);
                int lvl = Integer.parseInt(config.get(2));
                if (cellLvl >= 13) {
                    Msg.send(player, ChatColor.DARK_GREEN + "Камера максимальна.");
                    player.closeInventory();
                } else if (cellLvl < 13) {
                    Msg.send(player, ChatColor.RED + "Вас не должно тут быть...Используйте /cell для телепортации");
                    player.closeInventory();
                } else if (balance < price || Prison.getInstance().lvl.get(player) < lvl) {
                    Msg.send(player, ChatColor.RED + "Вы не выполнили все условия!");
                    player.closeInventory();
                }
            }
            //Стрелка
//            if (event.getCurrentItem().getType().equals(Material.IRON_SWORD) && event.getCurrentItem() != null && (event.getCurrentItem().getItemMeta().getDisplayName().contains("Устроить стрелку"))) {
//                Player player = (Player) event.getWhoClicked();
//                if (Prison.getInstance().getDatabase().getCellLvl(player) < 3) {
//                    Msg.send(player, ChatColor.RED + "Стрелки доступны с 3 уровня!");
//                    player.closeInventory();
//                } else if (Prison.getInstance().getDatabase().getCellLvl(player) < 5 && Prison.getInstance().getDatabase().getCellLvl(player) >= 3) {
//                    if (Prison.getEconomy().getBalance(player) >= 30) {
//                        //if (!Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())) {
//                        if (Prison.getInstance().ezkiel_arena_one.size() <= 7) {
//                            for (int i = 0; i <= 6; i++) {
//                                if (!Prison.getInstance().ezkiel_arena_one.containsValue(i)) {
//                                    Prison.getInstance().ezkiel_arena_one.put(player.getUniqueId(), i);
//                                    break;
//                                }
//                            }
//                            //Разные координаты арен
//                            Location location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 0) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на первую арену первым");
//                            } else if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 1) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на первую арену вторым");
//                            } else if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 2) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на первую арену третьим");
//                            } else if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 3) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на первую арену четвертым");
//                            } else if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 4) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 5) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_one.get(player.getUniqueId()) == 6) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            }
//                            player.teleport(location);
//                            player.closeInventory();
//                            Prison.getEconomy().withdrawPlayer(player, 30);
//                            Prison.getInstance().ezkiel_cd.put(player.getUniqueId(), 1);
//                            Msg.send(player, ChatColor.GOLD + "Убейте босса и получите награду...");
//                            Msg.send(player, ChatColor.RED + "Следующая стрелка будет доступна спустя сутки!");
//                        } else {
//                            Msg.send(player, ChatColor.RED + "Все арены заполнены!");
//                            player.closeInventory();
//                        }
//                    } else {
//                        Msg.send(player, ChatColor.RED + "Стрелка будет доступна на следующие сутки");
//                        player.closeInventory();
//                    }
//                    //} else {
//                    //    Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
//                    //    player.closeInventory();
//                    //}
//                } else if (Prison.getInstance().getDatabase().getCellLvl(player) < 7 && Prison.getInstance().getDatabase().getCellLvl(player) >= 5) {
//                    if (Prison.getEconomy().getBalance(player) >= 100) {
//                        //if (!Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())) {
//                        if (Prison.getInstance().ezkiel_arena_two.size() <= 7) {
//                            for (int i = 0; i <= 6; i++) {
//                                if (!Prison.getInstance().ezkiel_arena_two.containsValue(i)) {
//                                    Prison.getInstance().ezkiel_arena_two.put(player.getUniqueId(), i);
//                                    break;
//                                }
//                            }
//                            Location location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            //Разные координаты арен
//                            if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 0) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на вторую арену первым");
//                            } else if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 1) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на вторую арену вторым");
//                            } else if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 2) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на вторую арену третьим");
//                            } else if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 3) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на вторую арену четвертым");
//                            } else if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 4) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 5) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_two.get(player.getUniqueId()) == 6) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            }
//                            player.teleport(location);
//                            Prison.getEconomy().withdrawPlayer(player, 100);
//                            player.closeInventory();
//                            Prison.getInstance().ezkiel_cd.put(player.getUniqueId(), 1);
//                            Msg.send(player, ChatColor.GOLD + "Убейте босса и получите награду...");
//                            Msg.send(player, ChatColor.RED + "Следующая стрелка будет доступна спустя сутки!");
//                        } else {
//                            Msg.send(player, ChatColor.RED + "Все арены заполнены!");
//                            player.closeInventory();
//                        }
//                    } else {
//                        Msg.send(player, ChatColor.RED + "Стрелка будет доступна на следующие сутки");
//                        player.closeInventory();
//                    }
//                    //} else {
//                    //    Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
//                    //    player.closeInventory();
//                    //}
//                } else if (Prison.getInstance().getDatabase().getCellLvl(player) < 11 && Prison.getInstance().getDatabase().getCellLvl(player) >= 7) {
//                    if (Prison.getEconomy().getBalance(player) >= 250) {
//                        if (!Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())) {
//                            if (Prison.getInstance().ezkiel_arena_three.size() <= 7) {
//                                for (int i = 0; i <= 6; i++) {
//                                    if (!Prison.getInstance().ezkiel_arena_three.containsValue(i)) {
//                                        Prison.getInstance().ezkiel_arena_three.put(player.getUniqueId(), i);
//                                        break;
//                                    }
//                                }
//                                Location location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 0) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                    Msg.send(player, "Тп на третью арену первым");
//                                } else if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 1) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                    Msg.send(player, "Тп на третью арену вторым");
//                                } else if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 2) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                    Msg.send(player, "Тп на третью арену третьим");
//                                } else if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 3) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                    Msg.send(player, "Тп на третью арену четвертым");
//                                } else if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 4) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                } else if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 5) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                } else if (Prison.getInstance().ezkiel_arena_three.get(player.getUniqueId()) == 6) {
//                                    location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                }
//                                player.teleport(location);
//                                Prison.getEconomy().withdrawPlayer(player, 250);
//                                player.closeInventory();
//                                Prison.getInstance().ezkiel_cd.put(player.getUniqueId(), 1);
//                                Msg.send(player, ChatColor.GOLD + "Убейте босса и получите награду...");
//                                Msg.send(player, ChatColor.RED + "Следующая стрелка будет доступна спустя сутки!");
//                            } else {
//                                Msg.send(player, ChatColor.RED + "Все арены заполнены!");
//                                player.closeInventory();
//                            }
//                        } else {
//                            Msg.send(player, ChatColor.RED + "Стрелка будет доступна на следующие сутки");
//                            player.closeInventory();
//                        }
//                    } else {
//                        Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
//                        player.closeInventory();
//                    }
//                } else if (Prison.getInstance().getDatabase().getCellLvl(player) < 13 && Prison.getInstance().getDatabase().getCellLvl(player) >= 11) {
//                    if (Prison.getEconomy().getBalance(player) >= 1000) {
//                        //if (!Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())) {
//                        if (Prison.getInstance().ezkiel_arena_four.size() <= 7) {
//                            for (int i = 0; i <= 6; i++) {
//                                if (!Prison.getInstance().ezkiel_arena_four.containsValue(i)) {
//                                    Prison.getInstance().ezkiel_arena_four.put(player.getUniqueId(), i);
//                                    break;
//                                }
//                            }
//                            Location location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 0) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на четвертую арену первым");
//                            } else if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 1) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на четвертую арену вторым");
//                            } else if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 2) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на четвертую арену третьим");
//                            } else if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 3) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на четвертую арену чевертым");
//                            } else if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 4) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 5) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_four.get(player.getUniqueId()) == 6) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            }
//                            player.teleport(location);
//                            player.closeInventory();
//                            Prison.getEconomy().withdrawPlayer(player, 1000);
//                            Prison.getInstance().ezkiel_cd.put(player.getUniqueId(), 1);
//                            Msg.send(player, ChatColor.GOLD + "Убейте босса и получите награду...");
//                            Msg.send(player, ChatColor.RED + "Следующая стрелка будет доступна спустя сутки!");
//                        } else {
//                            Msg.send(player, ChatColor.RED + "Все арены заполнены!");
//                            player.closeInventory();
//                        }
//                        //} else {
//                        //    Msg.send(player, ChatColor.RED + "Стрелка будет доступна на следующие сутки");
//                        //    player.closeInventory();
//                        //}
//                    } else {
//                        Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
//                        player.closeInventory();
//                    }
//                } else if (Prison.getInstance().getDatabase().getCellLvl(player) >= 13) {
//                    if (Prison.getEconomy().getBalance(player) >= 1500) {
//                        //if (!Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())) {
//                        if (Prison.getInstance().ezkiel_arena_five.size() <= 7) {
//                            for (int i = 0; i <= 6; i++) {
//                                if (!Prison.getInstance().ezkiel_arena_five.containsValue(i)) {
//                                    Prison.getInstance().ezkiel_arena_five.put(player.getUniqueId(), i);
//                                    break;
//                                }
//                            }
//                            Location location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 0) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на пятую арену первым");
//                            } else if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 1) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на пятую арену вторым");
//                            } else if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 2) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                                Msg.send(player, "Тп на пятую арену третьим");
//                            } else if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 3) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 4) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 5) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            } else if (Prison.getInstance().ezkiel_arena_five.get(player.getUniqueId()) == 6) {
//                                location = new Location(Bukkit.getWorld("boss"), -47, -59, 42);
//                            }
//                            player.teleport(location);
//                            player.closeInventory();
//                            Prison.getEconomy().withdrawPlayer(player, 1500);
//                            Prison.getInstance().ezkiel_cd.put(player.getUniqueId(), 1);
//                            Msg.send(player, ChatColor.GOLD + "Убейте босса и получите награду...");
//                            Msg.send(player, ChatColor.RED + "Следующая стрелка будет доступна спустя сутки!");
//                        } else {
//                            Msg.send(player, ChatColor.RED + "Все арены заполнены!");
//                            player.closeInventory();
//                        }
//                        //} else {
//                        //    Msg.send(player, ChatColor.RED + "Стрелка будет доступна на следующие сутки");
//                        //    player.closeInventory();
//                        //}
//                    } else {
//                        Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
//                        player.closeInventory();
//                    }
//                }
//            }
        }
    }
}
