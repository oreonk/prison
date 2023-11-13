package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class ChurchInteract implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        if (event.getHand().equals(EquipmentSlot.HAND) && event.getRightClicked().getCustomName().equalsIgnoreCase("Священник")){
            createGUI(event.getPlayer());
        }
    }
    private void createGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 27, "Религия");

        ItemStack donate = new ItemStack(Material.BEACON);
        ItemMeta donate_meta = donate.getItemMeta();
        donate_meta.setDisplayName("Пожертвование");
        ArrayList<String> donate_lore = new ArrayList<>();
        donate_lore.add("");
        if (Prison.getInstance().religion.get(player) < 20){
            donate_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GRAY + "▌▌▌▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 20 && Prison.getInstance().religion.get(player) < 40){
            donate_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 40 && Prison.getInstance().religion.get(player) < 60){
            donate_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 60 && Prison.getInstance().religion.get(player) < 80){
            donate_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 80 && Prison.getInstance().religion.get(player) < 100){
            donate_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
        } else if (Prison.getInstance().religion.get(player) >= 100){
            donate_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌▌▌");
        }
        donate_lore.add("");
        donate_lore.add(ChatColor.GOLD + "Необходимо:");
        if (Prison.getInstance().religion.get(player) < 10) {
            donate_lore.add(ChatColor.GOLD + "25 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 10 слизи");
        } else if (Prison.getInstance().religion.get(player) >= 10 && Prison.getInstance().religion.get(player) < 20) {
            donate_lore.add(ChatColor.GOLD + "50 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 20 слизи");
        } else if (Prison.getInstance().religion.get(player) >= 20 && Prison.getInstance().religion.get(player) < 30) {
            donate_lore.add(ChatColor.GOLD + "125 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 50 нитей");
        } else if (Prison.getInstance().religion.get(player) >= 30 && Prison.getInstance().religion.get(player) < 40) {
            donate_lore.add(ChatColor.GOLD + "250 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 100 нитей");
        } else if (Prison.getInstance().religion.get(player) >= 40 && Prison.getInstance().religion.get(player) < 50) {
            donate_lore.add(ChatColor.GOLD + "400 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 50 гнилой плоти");
        } else if (Prison.getInstance().religion.get(player) >= 50 && Prison.getInstance().religion.get(player) < 60) {
            donate_lore.add(ChatColor.GOLD + "1000 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 80 гнилой плоти");
        } else if (Prison.getInstance().religion.get(player) >= 60 && Prison.getInstance().religion.get(player) < 70) {
            donate_lore.add(ChatColor.GOLD + "1500 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 150 гнилой плоти");
        } else if (Prison.getInstance().religion.get(player) >= 70 && Prison.getInstance().religion.get(player) < 80) {
            donate_lore.add(ChatColor.GOLD + "4000 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 300 гнилой плоти");
        } else if (Prison.getInstance().religion.get(player) >= 80 && Prison.getInstance().religion.get(player) < 90) {
            donate_lore.add(ChatColor.GOLD + "9000 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 500 гнилой плоти");
        } else if (Prison.getInstance().religion.get(player) >= 90 && Prison.getInstance().religion.get(player) < 100) {
            donate_lore.add(ChatColor.GOLD + "20000 " + ChatColor.WHITE + " " + ChatColor.GOLD + "и 800 гнилой плоти");
        } else if (Prison.getInstance().religion.get(player) >= 100) {
            donate_lore.add(ChatColor.GREEN + "Максимальный уровень религии");
            donate_lore.add("");
            donate_lore.add(ChatColor.GRAY + "Если у вас всё ещё есть задание");
            donate_lore.add(ChatColor.GRAY + "на пожертвование - вы можете");
            donate_lore.add(ChatColor.GRAY + "нажать на этот пункт меню");
        }
        donate_meta.setLore(donate_lore);
        donate.setItemMeta(donate_meta);
        gui.setItem(11, donate);

        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta info_meta = info.getItemMeta();
        info_meta.setDisplayName("Что это такое?");
        ArrayList<String> info_lore = new ArrayList<>();
        info_lore.add("");
        info_lore.add(ChatColor.WHITE + "Пожертвования необходимы для получения");
        info_lore.add(ChatColor.WHITE + "религии, необходимой для бонусов при");
        info_lore.add(ChatColor.WHITE + "копании шахты и увеличения длительности");
        info_lore.add(ChatColor.GOLD + "благословения" + ChatColor.WHITE + ".");
        info_lore.add("");
        info_lore.add(ChatColor.GOLD + "Благословение" + ChatColor.WHITE + " даёт спешку один");
        info_lore.add(ChatColor.WHITE + "раз в день, длительность  которой зависит");
        info_lore.add(ChatColor.WHITE + "от уровня вашей религии.");
        info_meta.setLore(info_lore);
        info.setItemMeta(info_meta);
        gui.setItem(13, info);

        ItemStack bless = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta bless_meta = bless.getItemMeta();
        bless_meta.setDisplayName("Благословение");
        ArrayList<String> bless_lore = new ArrayList<>();
        bless_lore.add("");
        bless_lore.add(ChatColor.WHITE + "Получите эффект спешки, длительность");
        bless_lore.add(ChatColor.WHITE + "которого зависит от уровня вашей");
        bless_lore.add(ChatColor.WHITE + "религии");
        bless_lore.add("");
        bless_lore.add(ChatColor.GOLD + "Религия: " + Prison.getInstance().religion.get(player) + "/100");
        bless_meta.setLore(bless_lore);
        bless.setItemMeta(bless_meta);
        gui.setItem(15, bless);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName("Закрыть меню");
        close.setItemMeta(close_meta);
        gui.setItem(22,close);

        player.openInventory(gui);
    }
    @EventHandler
    public void menuInteract (InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            if (event.getView().getTitle().contains("Религия")) {
                event.setCancelled(true);
            }
            if (event.getView().getTitle().equals("Религия")) {
                if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
                    event.getWhoClicked().closeInventory();
                }
                if (event.getCurrentItem().getType().equals(Material.BEACON)) {
                    Player player = (Player) event.getWhoClicked();
                    if (Prison.getInstance().religion.get(player) < 10) {
                        ItemStack slime = new ItemStack(Material.SLIME_BALL);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(slime, 10) && Prison.getEconomy().getBalance(player) >= 25) {
                            Prison.getEconomy().withdrawPlayer(player, 25);
                            int count = 10;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.SLIME_BALL)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 10 && Prison.getInstance().religion.get(player) < 20) {
                        //donate_lore.add(ChatColor.GOLD + "50 и 20 слизи");
                        ItemStack slime = new ItemStack(Material.SLIME_BALL);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(slime, 20) && Prison.getEconomy().getBalance(player) >= 50) {
                            Prison.getEconomy().withdrawPlayer(player, 50);
                            int count = 20;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.SLIME_BALL)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 20 && Prison.getInstance().religion.get(player) < 30) {
                        //donate_lore.add(ChatColor.GOLD + "125 и 50 нитей");
                        ItemStack string = new ItemStack(Material.STRING);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(string, 50) && Prison.getEconomy().getBalance(player) >= 125) {
                            Prison.getEconomy().withdrawPlayer(player, 125);
                            int count = 50;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.STRING)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 30 && Prison.getInstance().religion.get(player) < 40) {
                        //donate_lore.add(ChatColor.GOLD + "250 и 100 нитей");
                        ItemStack string = new ItemStack(Material.STRING);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(string, 100) && Prison.getEconomy().getBalance(player) >= 250) {
                            Prison.getEconomy().withdrawPlayer(player, 250);
                            int count = 100;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.STRING)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 40 && Prison.getInstance().religion.get(player) < 50) {
                        //donate_lore.add(ChatColor.GOLD + "400 и 50 гнилой плоти");
                        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(flesh, 50) && Prison.getEconomy().getBalance(player) >= 400) {
                            Prison.getEconomy().withdrawPlayer(player, 400);
                            int count = 50;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.ROTTEN_FLESH)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 50 && Prison.getInstance().religion.get(player) < 60) {
                        //donate_lore.add(ChatColor.GOLD + "1000 и 80 гнилой плоти");
                        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(flesh, 80) && Prison.getEconomy().getBalance(player) >= 1000) {
                            Prison.getEconomy().withdrawPlayer(player, 1000);
                            int count = 80;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.ROTTEN_FLESH)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 60 && Prison.getInstance().religion.get(player) < 70) {
                        //donate_lore.add(ChatColor.GOLD + "1500 и 150 гнилой плоти");
                        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(flesh, 150) && Prison.getEconomy().getBalance(player) >= 1500) {
                            Prison.getEconomy().withdrawPlayer(player, 1500);
                            int count = 150;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.ROTTEN_FLESH)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 70 && Prison.getInstance().religion.get(player) < 80) {
                        //donate_lore.add(ChatColor.GOLD + "4000 и 300 гнилой плоти");
                        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(flesh, 300) && Prison.getEconomy().getBalance(player) >= 4000) {
                            Prison.getEconomy().withdrawPlayer(player, 4000);
                            int count = 300;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.ROTTEN_FLESH)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 80 && Prison.getInstance().religion.get(player) < 90) {
                        //donate_lore.add(ChatColor.GOLD + "9000 и 500 гнилой плоти");
                        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(flesh, 500) && Prison.getEconomy().getBalance(player) >= 9000) {
                            Prison.getEconomy().withdrawPlayer(player, 9000);
                            int count = 500;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.ROTTEN_FLESH)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 90 && Prison.getInstance().religion.get(player) < 100) {
                        //donate_lore.add(ChatColor.GOLD + "20000 и 800 гнилой плоти");
                        ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                        player.closeInventory(); //Может вызвать проблемы
                        if (player.getInventory().containsAtLeast(flesh, 800) && Prison.getEconomy().getBalance(player) >= 20000) {
                            Prison.getEconomy().withdrawPlayer(player, 20000);
                            int count = 800;
                            for (ItemStack stack : player.getInventory().getContents()) {
                                if (stack != null && stack.getType().equals(Material.ROTTEN_FLESH)) {
                                    if (count <= 0) {
                                        break;
                                    }
                                    if (stack.getAmount() >= count) {
                                        int amount = stack.getAmount();
                                        stack.setAmount(amount - count);
                                        count = 0;
                                    } else {
                                        int amount = stack.getAmount();
                                        count = count - amount;
                                        player.getInventory().remove(stack);
                                    }
                                }
                            }
                            int religion = Prison.getInstance().religion.get(player);
                            Prison.getInstance().religion.put(player, religion + 10);
                            //Закинуть в счётчик пожертвования для квеста
                            if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы совершили пожертвование");
                        } else {
                            Msg.send(player, ChatColor.RED + "Не выполнены требования для пожертвования");
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 100) {
                        //donate_lore.add(ChatColor.GREEN + "Максимальный уровень религии");
                        Msg.send(player, ChatColor.GREEN + "Вы достигли максимального уровня религии!");
                        //Закинуть в счётчик пожертвования для квеста
                        if (!Prison.getInstance().church_counter.containsKey(player.getUniqueId())){
                                Prison.getInstance().church_counter.put(player.getUniqueId(), 1);}
                        }
                }
                if (event.getCurrentItem().getType().equals(Material.ENCHANTED_BOOK)) {
                    Player player = (Player) event.getWhoClicked();
                    if (Prison.getInstance().religion.get(player) < 10) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 12000, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 10 && Prison.getInstance().religion.get(player) < 20) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 14400, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 20 && Prison.getInstance().religion.get(player) < 30) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 16800, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 30 && Prison.getInstance().religion.get(player) < 40) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 19200, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 40 && Prison.getInstance().religion.get(player) < 50) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 21600, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 50 && Prison.getInstance().religion.get(player) < 60) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 24000, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 60 && Prison.getInstance().religion.get(player) < 70) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 26400, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 70 && Prison.getInstance().religion.get(player) < 80) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 28800, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 80 && Prison.getInstance().religion.get(player) < 90) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 31200, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 90 && Prison.getInstance().religion.get(player) < 100) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 36000, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    } else if (Prison.getInstance().religion.get(player) >= 100) {
                        if (!Prison.getInstance().blessing.containsKey(player.getUniqueId())) {
                            player.closeInventory();
                            Prison.getInstance().blessing.put(player.getUniqueId(), 1);
                            PotionEffect pot = new PotionEffect(PotionEffectType.FAST_DIGGING, 42000, 0);
                            player.addPotionEffect(pot);
                        } else {
                            Msg.send(player, ChatColor.RED + "Сегодня вы уже получали благословение");
                            player.closeInventory();
                        }
                    }
                }
            }
        }
    }
}
