package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.TestPlug;
import me.davidml16.acubelets.api.CubeletsAPI;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Map;


public class DonateMenus implements Listener {
    @EventHandler
    public void Menus (InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().contains("")||event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера")||event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры")||event.getView().getTitle().equalsIgnoreCase("Покупка автоселла")){
            event.setCancelled(true);
        }
        if (event.getClickedInventory()!=null&&event.getCurrentItem()!=null) {
            GemsEconomyAPI economy = new GemsEconomyAPI();
            if (event.getView().getTitle().contains("") && event.getCurrentItem().getType().equals(Material.DIAMOND)) {
                Double boost = TestPlug.getPlugin(TestPlug.class).bst.get(player);
                Inventory gui = Bukkit.createInventory(player, 27, "Покупка приватного бустера");
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);
                ItemStack deny = new ItemStack(Material.RED_WOOL);
                ItemMeta deny_meta = deny.getItemMeta();
                deny_meta.setDisplayName(ChatColor.RED + "Отмена");
                deny.setItemMeta(deny_meta);
                gui.setItem(21, deny);
                ItemStack accept = new ItemStack(Material.GREEN_WOOL);
                ItemMeta accept_meta = accept.getItemMeta();
                accept_meta.setDisplayName(ChatColor.GREEN + "Подтвердить");
                accept.setItemMeta(accept_meta);
                gui.setItem(23, accept);

                ItemStack info = new ItemStack(Material.GOLD_BLOCK);
                ItemMeta info_meta = info.getItemMeta();
                ArrayList<String> info_lore = new ArrayList<>();
                accept_meta.setDisplayName(ChatColor.YELLOW + "Информация");
                info_lore.add(" ");
                info_lore.add("Ваш нынешний бустер " + boost);
                info_meta.setLore(info_lore);
                info.setItemMeta(info_meta);
                gui.setItem(13, info);

                gui.setItem(10, air);
                gui.setItem(11, air);
                gui.setItem(12, air);
                gui.setItem(14, air);
                gui.setItem(15, air);
                gui.setItem(16, air);

                player.openInventory(gui);
            } else if (event.getView().getTitle().contains("") && event.getCurrentItem().getType().equals(Material.GOLDEN_PICKAXE)) {
                Inventory gui = Bukkit.createInventory(player, 27, "Покупка автоселла");
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);
                ItemStack deny = new ItemStack(Material.RED_WOOL);
                ItemMeta deny_meta = deny.getItemMeta();
                deny_meta.setDisplayName(ChatColor.RED + "Отмена");
                deny.setItemMeta(deny_meta);
                gui.setItem(21, deny);
                ItemStack accept = new ItemStack(Material.GREEN_WOOL);
                ItemMeta accept_meta = accept.getItemMeta();
                accept_meta.setDisplayName(ChatColor.GREEN + "Подтвердить");
                accept.setItemMeta(accept_meta);
                gui.setItem(23, accept);
                gui.setItem(13, air);
                gui.setItem(10, air);
                gui.setItem(11, air);
                gui.setItem(12, air);
                gui.setItem(14, air);
                gui.setItem(15, air);
                gui.setItem(16, air);

                player.openInventory(gui);

            } else if (event.getView().getTitle().contains("") && event.getCurrentItem().getType().equals(Material.EXPERIENCE_BOTTLE)) {
                Inventory gui = Bukkit.createInventory(player, 27, "Глобальные бустеры");
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);

                ItemStack first = new ItemStack(Material.IRON_INGOT);
                ItemMeta first_meta = first.getItemMeta();
                first_meta.setDisplayName(ChatColor.YELLOW+"Бустер на 15 минут");
                ArrayList<String> first_lore = new ArrayList<>();
                first_lore.add(" ");
                first_lore.add("Цена - 100 " + ChatColor.WHITE + "");
                first_meta.setLore(first_lore);
                first.setItemMeta(first_meta);
                gui.setItem(10, first);

                ItemStack second = new ItemStack(Material.GOLD_INGOT);
                ItemMeta second_meta = second.getItemMeta();
                second_meta.setDisplayName(ChatColor.YELLOW+"Бустер на 30 минут");
                ArrayList<String> second_lore = new ArrayList<>();
                second_lore.add(" ");
                second_lore.add("Цена - 180 " + ChatColor.WHITE + "");
                second_meta.setLore(second_lore);
                second.setItemMeta(second_meta);
                gui.setItem(13, second);

                ItemStack third = new ItemStack(Material.DIAMOND);
                ItemMeta third_meta = third.getItemMeta();
                third_meta.setDisplayName(ChatColor.YELLOW+"Бустер на 45 минут");
                ArrayList<String> third_lore = new ArrayList<>();
                third_lore.add(" ");
                third_lore.add("Цена - 250 " + ChatColor.WHITE + "");
                third_meta.setLore(third_lore);
                third.setItemMeta(third_meta);
                gui.setItem(16, third);

                gui.setItem(12, air);
                gui.setItem(15, air);
                gui.setItem(11, air);
                gui.setItem(14, air);

                player.openInventory(gui);
            }
                if (event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера") && event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                    Double boost = TestPlug.getPlugin(TestPlug.class).bst.get(player);
                    double price = 150.0;
                    if (boost>2.0){
                        Msg.send(player, "&4У вас уже максимально возможный бустер!");
                    } else{
                        Double balance = economy.getBalance(player.getUniqueId(), economy.getCurrency("honey"));
                        if (balance>=price) {
                            economy.withdraw(player.getUniqueId(), price,economy.getCurrency("honey"));
                            TestPlug.getPlugin(TestPlug.class).bst.remove(player);
                            TestPlug.getPlugin(TestPlug.class).bst.put(player, boost+0.1);
                            TestPlug.getPlugin(TestPlug.class).getDatabase().updateBoost(player, TestPlug.getPlugin(TestPlug.class).bst.get(player).toString());
                            Msg.send(player, "&aПокупка успешна!");
                            player.closeInventory();
                        } else {
                            Msg.send(player, "&4У вас недостаточно средств!");
                        }
                    }
                } else if (event.getView().getTitle().equalsIgnoreCase("Покупка автоселла") && event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                    if (player.hasPermission("prison.autosell")){
                        Msg.send(player, "У вас уже есть автоселл!");
                    } else {
                        double price = 250.0;
                        Double balance = economy.getBalance(player.getUniqueId(), economy.getCurrency("honey"));
                        if (balance>=price){
                            economy.withdraw(player.getUniqueId(), price,economy.getCurrency("honey"));
                            Permission perms = TestPlug.getPermissions();
                            perms.playerAdd(player, "prison.autosell");
                            Msg.send(player, ChatColor.GREEN + "Автоселл успешно приобретён. Используйте /autosell on для включения и /autosell off для отключения.");
                            player.closeInventory();
                        }
                    }
            } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.IRON_INGOT)){
                    if (TestPlug.getPlugin(TestPlug.class).booster.isEmpty()) {
                        if (economy.getBalance(player.getUniqueId(), economy.getCurrency("honey"))>=100) {
                            TestPlug.getPlugin(TestPlug.class).booster.put(player, 15);
                            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(TestPlug.getInstance(), () -> {
                                    Integer timer = TestPlug.getPlugin(TestPlug.class).booster.get(player);
                                    timer--;
                                    TestPlug.getPlugin(TestPlug.class).booster.replace(player, timer);
                                    if (timer<=0){
                                        Player finalPlayer = null;
                                        for (Map.Entry<Player, Integer> entry : TestPlug.getPlugin(TestPlug.class).booster.entrySet()){
                                            finalPlayer = entry.getKey();
                                        }
                                        TestPlug.getPlugin(TestPlug.class).booster.clear();
                                        if (!TestPlug.getPlugin(TestPlug.class).thx.isEmpty()) {
                                            TestPlug.getPlugin(TestPlug.class).thx.clear();
                                        }
                                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Бустер игрока " + ChatColor.WHITE + finalPlayer.getName() + ChatColor.YELLOW + " прекратил действовать.");
                                    }
                            }, 1200, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер на 15 минут! Используйте /thx для того, что бы отблагодарить его.");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
                    if (TestPlug.getPlugin(TestPlug.class).booster.isEmpty()) {
                        if (economy.getBalance(player.getUniqueId(), economy.getCurrency("honey"))>=180) {
                            TestPlug.getPlugin(TestPlug.class).booster.put(player, 30);
                            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(TestPlug.getInstance(), () -> {
                                    Integer timer = TestPlug.getPlugin(TestPlug.class).booster.get(player);
                                    timer--;
                                    TestPlug.getPlugin(TestPlug.class).booster.replace(player, timer);
                                    if (timer<=0){
                                        Player finalPlayer = null;
                                        for (Map.Entry<Player, Integer> entry : TestPlug.getPlugin(TestPlug.class).booster.entrySet()){
                                            finalPlayer = entry.getKey();
                                        }
                                        TestPlug.getPlugin(TestPlug.class).booster.clear();
                                        if (!TestPlug.getPlugin(TestPlug.class).thx.isEmpty()) {
                                            TestPlug.getPlugin(TestPlug.class).thx.clear();
                                        }
                                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Бустер игрока " + ChatColor.WHITE + finalPlayer.getName() + ChatColor.YELLOW + " прекратил действовать.");
                                    }
                            }, 1200, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер на 30 минут! Используйте /thx для того, что бы отблагодарить его.");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.DIAMOND)){
                    if (TestPlug.getPlugin(TestPlug.class).booster.isEmpty()) {
                        if (economy.getBalance(player.getUniqueId(), economy.getCurrency("honey"))>=250) {
                            TestPlug.getPlugin(TestPlug.class).booster.put(player, 45);
                            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(TestPlug.getInstance(), () -> {
                                    Integer timer = TestPlug.getPlugin(TestPlug.class).booster.get(player);
                                    timer--;
                                    TestPlug.getPlugin(TestPlug.class).booster.replace(player, timer);
                                    if (timer<=0){
                                        Player finalPlayer = null;
                                        for (Map.Entry<Player, Integer> entry : TestPlug.getPlugin(TestPlug.class).booster.entrySet()){
                                            finalPlayer = entry.getKey();
                                        }
                                        if (!TestPlug.getPlugin(TestPlug.class).thx.isEmpty()) {
                                            TestPlug.getPlugin(TestPlug.class).thx.clear();
                                        }
                                        TestPlug.getPlugin(TestPlug.class).booster.clear();
                                        Bukkit.broadcastMessage(ChatColor.YELLOW + "Бустер игрока " + ChatColor.WHITE + finalPlayer.getName() + ChatColor.YELLOW + " прекратил действовать.");
                                    }
                            }, 1200, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер на 45 минут! Используйте /thx для того, что бы отблагодарить его.");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                }
                else if (event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера") || event.getView().getTitle().equalsIgnoreCase("Покупка автоселла") || event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры")){
                    if (event.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                        player.closeInventory();
                    }
                }
            }
        }
    }

