package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import ru.antarescraft.api.AntaresAPI;
import ru.antarescraft.api.object.ApiUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;


public class DonateMenus implements Listener {
    @EventHandler
    public void Menus (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().contains("")||event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера")||event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры")||event.getView().getTitle().equalsIgnoreCase("Покупка автоселла") || event.getView().getTitle().equalsIgnoreCase("Покупка ключей") || event.getView().getTitle().equalsIgnoreCase("Локальные бустеры")){
            event.setCancelled(true);
        }
        if (event.getClickedInventory()!=null&&event.getCurrentItem()!=null) {
            int antBalance = 0;
            int creditBalance = 0;
            ApiUser economy = null;
            if (event.getView().getTitle().contains("") || event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера") || event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") || event.getView().getTitle().equalsIgnoreCase("Покупка автоселла" ) || event.getView().getTitle().equalsIgnoreCase("Покупка ключей") ||event.getView().getTitle().equalsIgnoreCase("Локальные бустеры")) {
                try {
                    antBalance = AntaresAPI.getApiUser(player.getName()).getAntaresCoins();
                    creditBalance = AntaresAPI.getApiUser(player.getName()).getCreditCoins();
                    economy = AntaresAPI.getApiUser(player.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (event.getView().getTitle().contains("") && event.getCurrentItem().getType().equals(Material.DIAMOND)) {
                Double boost = Prison.getPlugin(Prison.class).bst.get(player);
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

            } else if (event.getView().getTitle().contains("") && event.getCurrentItem().getType().equals(Material.TRIPWIRE_HOOK)) {
                Inventory gui = Bukkit.createInventory(player, 27, "Покупка ключей");
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);
                ItemStack normalKey = new ItemStack(Material.TRIPWIRE_HOOK);
                ItemMeta normalKeyMeta = normalKey.getItemMeta();
                normalKeyMeta.setDisplayName("Купить обычный ключ");
                ArrayList<String> normalKeyLore = new ArrayList<>();
                normalKeyLore.add("Цена - 10 " + ChatColor.WHITE + "");
                normalKeyMeta.setLore(normalKeyLore);
                normalKey.setItemMeta(normalKeyMeta);
                gui.setItem(10, normalKey);

                ItemStack normalTenKeys = new ItemStack(Material.TRIPWIRE_HOOK);
                ItemMeta normalTenKeysMeta = normalTenKeys.getItemMeta();
                normalTenKeys.setAmount(10);
                normalTenKeysMeta.setDisplayName("Купить 10 обычных ключей");
                ArrayList<String> normalTenKeysLore = new ArrayList<>();
                normalTenKeysLore.add("Цена - 60 " + ChatColor.WHITE + "");
                normalTenKeysMeta.setLore(normalTenKeysLore);
                normalTenKeys.setItemMeta(normalTenKeysMeta);
                gui.setItem(11, normalTenKeys);

                ItemStack normalMoreKeys = new ItemStack(Material.TRIPWIRE_HOOK);
                ItemMeta normalMoreKeysMeta = normalMoreKeys.getItemMeta();
                normalMoreKeys.setAmount(32);
                normalMoreKeysMeta.setDisplayName("Купить 32 обычных ключа");
                ArrayList<String> normalMoreKeysLore = new ArrayList<>();
                normalMoreKeysLore.add("Цена - 150 " + ChatColor.WHITE + "");
                normalMoreKeysMeta.setLore(normalMoreKeysLore);
                normalMoreKeys.setItemMeta(normalMoreKeysMeta);
                gui.setItem(12, normalMoreKeys);

                gui.setItem(13, air);

                ItemStack rareKey = new ItemStack(Material.GHAST_TEAR);
                ItemMeta rareKeyMeta = rareKey.getItemMeta();
                rareKeyMeta.setDisplayName("Купить редкий ключ");
                ArrayList<String> rareKeyLore = new ArrayList<>();
                rareKeyLore.add("Цена - 30 " + ChatColor.WHITE + "");
                rareKeyMeta.setLore(rareKeyLore);
                rareKey.setItemMeta(rareKeyMeta);
                gui.setItem(14, rareKey);

                ItemStack heroicKey = new ItemStack(Material.NETHER_STAR);
                ItemMeta heroicKeyMeta = heroicKey.getItemMeta();
                heroicKeyMeta.setDisplayName("Купить героический ключ");
                ArrayList<String> heroicKeyLore = new ArrayList<>();
                heroicKeyLore.add("Цена - 60 " + ChatColor.WHITE + "");
                heroicKeyMeta.setLore(heroicKeyLore);
                heroicKey.setItemMeta(heroicKeyMeta);
                gui.setItem(15, heroicKey);

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
                first_meta.setDisplayName(ChatColor.YELLOW+"Бустер монет на 15 минут");
                ArrayList<String> first_lore = new ArrayList<>();
                first_lore.add(" ");
                first_lore.add("Цена - 50 " + ChatColor.WHITE + "");
                first_meta.setLore(first_lore);
                first.setItemMeta(first_meta);
                gui.setItem(10, first);

                ItemStack second = new ItemStack(Material.GOLD_INGOT);
                ItemMeta second_meta = second.getItemMeta();
                second_meta.setDisplayName(ChatColor.YELLOW+"Бустер монет на 30 минут");
                ArrayList<String> second_lore = new ArrayList<>();
                second_lore.add(" ");
                second_lore.add("Цена - 80 " + ChatColor.WHITE + "");
                second_meta.setLore(second_lore);
                second.setItemMeta(second_meta);
                gui.setItem(11, second);

                ItemStack third = new ItemStack(Material.DIAMOND);
                ItemMeta third_meta = third.getItemMeta();
                third_meta.setDisplayName(ChatColor.YELLOW+"Бустер монет на 45 минут");
                ArrayList<String> third_lore = new ArrayList<>();
                third_lore.add(" ");
                third_lore.add("Цена - 100 " + ChatColor.WHITE + "");
                third_meta.setLore(third_lore);
                third.setItemMeta(third_meta);
                gui.setItem(12, third);

                ItemStack fourth = new ItemStack(Material.DIRT);
                ItemMeta fourth_meta = fourth.getItemMeta();
                fourth_meta.setDisplayName(ChatColor.YELLOW+"Бустер блоков на 15 минут");
                ArrayList<String> fourth_lore = new ArrayList<>();
                fourth_lore.add(" ");
                fourth_lore.add("Цена - 50 " + ChatColor.WHITE + "");
                fourth_meta.setLore(fourth_lore);
                fourth.setItemMeta(fourth_meta);
                gui.setItem(14, fourth);

                ItemStack fifth = new ItemStack(Material.STONE);
                ItemMeta fifth_meta = fifth.getItemMeta();
                fifth_meta.setDisplayName(ChatColor.YELLOW+"Бустер блоков на 30 минут");
                ArrayList<String> fifth_lore = new ArrayList<>();
                fifth_lore.add(" ");
                fifth_lore.add("Цена - 80 " + ChatColor.WHITE + "");
                fifth_meta.setLore(fifth_lore);
                fifth.setItemMeta(fifth_meta);
                gui.setItem(15, fifth);

                ItemStack sixth = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta sixth_meta = sixth.getItemMeta();
                sixth_meta.setDisplayName(ChatColor.YELLOW+"Бустер блоков на 45 минут");
                ArrayList<String> sixth_lore = new ArrayList<>();
                sixth_lore.add(" ");
                sixth_lore.add("Цена - 100 " + ChatColor.WHITE + "");
                sixth_meta.setLore(sixth_lore);
                sixth.setItemMeta(sixth_meta);
                gui.setItem(16, sixth);

                gui.setItem(13, air);


                player.openInventory(gui);
            } else if (event.getView().getTitle().contains("") && event.getCurrentItem().getType().equals(Material.WRITABLE_BOOK)) {
                Inventory gui = Bukkit.createInventory(player, 27, "Локальные бустеры");
                ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta filler_meta = filler.getItemMeta();
                filler_meta.setDisplayName(" ");
                filler.setItemMeta(filler_meta);
                for (int slot = 0; slot < gui.getSize(); slot++)
                    gui.setItem(slot, filler);
                ItemStack air = new ItemStack(Material.AIR);

                ItemStack first = new ItemStack(Material.IRON_INGOT);
                ItemMeta first_meta = first.getItemMeta();
                first_meta.setDisplayName(ChatColor.YELLOW+"Бустер монет х1.5 на 15 минут");
                ArrayList<String> first_lore = new ArrayList<>();
                first_lore.add(" ");
                first_lore.add("Цена - 25 " + ChatColor.WHITE + "");
                first_meta.setLore(first_lore);
                first.setItemMeta(first_meta);
                gui.setItem(10, first);

                ItemStack second = new ItemStack(Material.IRON_BLOCK);
                ItemMeta second_meta = second.getItemMeta();
                second_meta.setDisplayName(ChatColor.YELLOW+"Бустер монет х2 на 15 минут");
                ArrayList<String> second_lore = new ArrayList<>();
                second_lore.add(" ");
                second_lore.add("Цена - 40 " + ChatColor.WHITE + "");
                second_meta.setLore(second_lore);
                second.setItemMeta(second_meta);
                gui.setItem(12, second);

                ItemStack third = new ItemStack(Material.DIRT);
                ItemMeta third_meta = third.getItemMeta();
                third_meta.setDisplayName(ChatColor.YELLOW+"Бустер блоков x1.5 на 15 минут");
                ArrayList<String> third_lore = new ArrayList<>();
                third_lore.add(" ");
                third_lore.add("Цена - 25 " + ChatColor.WHITE + "");
                third_meta.setLore(third_lore);
                third.setItemMeta(third_meta);
                gui.setItem(14, third);

                ItemStack fourth = new ItemStack(Material.BEDROCK);
                ItemMeta fourth_meta = fourth.getItemMeta();
                fourth_meta.setDisplayName(ChatColor.YELLOW+"Бустер блоков x2 на 15 минут");
                ArrayList<String> fourth_lore = new ArrayList<>();
                fourth_lore.add(" ");
                fourth_lore.add("Цена - 40 " + ChatColor.WHITE + "");
                fourth_meta.setLore(fourth_lore);
                fourth.setItemMeta(fourth_meta);
                gui.setItem(16, fourth);


                player.openInventory(gui);
            }
                if (event.getView().getTitle().equalsIgnoreCase("Покупка приватного бустера") && event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                    Double boost = Prison.getPlugin(Prison.class).bst.get(player);
                    int price = 150;
                    if (boost>2.0){
                        Msg.send(player, "&4У вас уже максимально возможный бустер!");
                    } else{
                        if (antBalance>=price) {
                            try {
                                economy.withdrawAntaresCoins(price);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            Prison.getPlugin(Prison.class).bst.remove(player);
                            Prison.getPlugin(Prison.class).bst.put(player, boost+0.1);
                            Prison.getPlugin(Prison.class).getDatabase().updateBoost(player, String.valueOf(boost+0.1));
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
                        int price = 250;
                        if (antBalance>=price){
                            try{
                                economy.withdrawAntaresCoins(price);
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                            Permission perms = Prison.getPermissions();
                            perms.playerAdd(player, "prison.autosell");
                            Msg.send(player, ChatColor.GREEN + "Автоселл успешно приобретён. Используйте /autosell on для включения и /autosell off для отключения.");
                            player.closeInventory();
                        }
                    }
            } else if (event.getView().getTitle().equalsIgnoreCase("Покупка ключей") && event.getCurrentItem().getType().equals(Material.TRIPWIRE_HOOK) && event.getCurrentItem().getAmount() == 1){
                        int price = 10;
                        if (antBalance>=price){
                            try{
                                economy.withdrawAntaresCoins(price);
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc give p CrateExample 1 " + player.getName());
                            Msg.send(player, ChatColor.GREEN + "Ключ успешно приобретён.");
                            player.closeInventory();
                        }
            } else if (event.getView().getTitle().equalsIgnoreCase("Покупка ключей") && event.getCurrentItem().getType().equals(Material.TRIPWIRE_HOOK) && event.getCurrentItem().getAmount() == 10){
                        int price = 150;
                        if (antBalance>=price){
                            try{
                                economy.withdrawAntaresCoins(price);
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc give p CrateExample 10 " + player.getName());
                            Msg.send(player, ChatColor.GREEN + "Ключ успешно приобретён.");
                            player.closeInventory();
                        }
            } else if (event.getView().getTitle().equalsIgnoreCase("Покупка ключей") && event.getCurrentItem().getType().equals(Material.TRIPWIRE_HOOK) && event.getCurrentItem().getAmount() == 32){
                        int price = 350;
                        if (antBalance>=price){
                            try{
                                economy.withdrawAntaresCoins(price);
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc give p CrateExample 32 " + player.getName());
                            Msg.send(player, ChatColor.GREEN + "Ключ успешно приобретён.");
                            player.closeInventory();
                        }
            } else if (event.getView().getTitle().equalsIgnoreCase("Покупка ключей") && event.getCurrentItem().getType().equals(Material.GHAST_TEAR)){
                        int price = 100;
                        if (antBalance>=price){
                            try{
                                economy.withdrawAntaresCoins(price);
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cubelets givekey " + player.getName() + " normal_id");
                            Msg.send(player, ChatColor.GREEN + "Ключ успешно приобретён.");
                            player.closeInventory();
                        }
            } else if (event.getView().getTitle().equalsIgnoreCase("Покупка ключей") && event.getCurrentItem().getType().equals(Material.NETHER_STAR)){
                        int price = 200;
                        if (antBalance>=price){
                            try{
                                economy.withdrawAntaresCoins(price);
                            } catch(IOException e){
                                e.printStackTrace();
                            }
                            Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "cubelets givekey " + player.getName() + " heroic_id");
                            Msg.send(player, ChatColor.GREEN + "Ключ успешно приобретён.");
                            player.closeInventory();
                        }
            } else if (event.getView().getTitle().equalsIgnoreCase("Локальные бустеры") && event.getCurrentItem().getType().equals(Material.IRON_INGOT)){
                    if (!Prison.getPlugin(Prison.class).privateBooster.containsKey(player.getUniqueId())) {
                        if (antBalance>=25) {
                            Prison.getPlugin(Prison.class).privateBooster.put(player.getUniqueId(), 15);
                            Prison.getInstance().privateBoosterMultiplier.put(player.getUniqueId(), 1);
                            try {
                                economy.withdrawAntaresCoins(25);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы успешно приобрели личный бустер денег х1.5 на 15 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    }
                else if (event.getView().getTitle().equalsIgnoreCase("Локальные бустеры") && event.getCurrentItem().getType().equals(Material.IRON_BLOCK)){
                    if (!Prison.getPlugin(Prison.class).privateBooster.containsKey(player.getUniqueId())) {
                        if (antBalance>=40) {
                            Prison.getPlugin(Prison.class).privateBooster.put(player.getUniqueId(), 15);
                            Prison.getInstance().privateBoosterMultiplier.put(player.getUniqueId(), 2);
                            try {
                                economy.withdrawAntaresCoins(40);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы успешно приобрели личный бустер денег х2 на 15 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    }
                else if (event.getView().getTitle().equalsIgnoreCase("Локальные бустеры") && event.getCurrentItem().getType().equals(Material.DIRT)){
                    if (!Prison.getPlugin(Prison.class).privateBlockBooster.containsKey(player.getUniqueId())) {
                        if (antBalance>=25) {
                            Prison.getPlugin(Prison.class).privateBlockBooster.put(player.getUniqueId(), 15);
                            Prison.getInstance().privateBlockBoosterMultiplier.put(player.getUniqueId(), 1);
                            try {
                                economy.withdrawAntaresCoins(25);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы успешно приобрели личный бустер блоков х1.5 на 15 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    }
                else if (event.getView().getTitle().equalsIgnoreCase("Локальные бустеры") && event.getCurrentItem().getType().equals(Material.BEDROCK)){
                    if (!Prison.getPlugin(Prison.class).privateBlockBooster.containsKey(player.getUniqueId())) {
                        if (antBalance>=25) {
                            Prison.getPlugin(Prison.class).privateBlockBooster.put(player.getUniqueId(), 15);
                            Prison.getInstance().privateBlockBoosterMultiplier.put(player.getUniqueId(), 2);
                            try {
                                economy.withdrawAntaresCoins(40);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            Msg.send(player, ChatColor.GREEN + "Вы успешно приобрели личный бустер блоков х2 на 15 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    }
                else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.IRON_INGOT)){
                    if (Prison.getPlugin(Prison.class).booster.isEmpty()) {
                        if (antBalance>=50) {
                            Prison.getPlugin(Prison.class).booster.put(player.getUniqueId(), 15);
                            Prison.getInstance().getDatabase().insertMoneyBst(player.getUniqueId(), 15);
                            try {
                                economy.withdrawAntaresCoins(50);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            new BukkitRunnable() {
                                public void run(){
                                Integer timer = Prison.getPlugin(Prison.class).booster.get(player.getUniqueId());
                                timer--;
                                    Prison.getPlugin(Prison .class).booster.replace(player.getUniqueId(),timer);
                                    if(timer<=0){
                                    Prison.getPlugin(Prison.class).booster.clear();
                                    if (!Prison.getPlugin(Prison.class).thx.isEmpty()) {
                                        Prison.getPlugin(Prison.class).thx.clear();
                                    }
                                    this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            int displayTime = 0;
                            String playerName = null ;
                                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                                    displayTime = entry.getValue();
                                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                                    } else {
                                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                    }
                                }
                            BossBar bossBar = Bukkit.createBossBar("Активен бустер монет игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                            bossBar.setVisible(true);
                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                            new BukkitRunnable() {
                                    public void run() {
                                        int time = 0;
                                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                                            time = entry.getValue();
                                            String player;
                                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                                player = Bukkit.getPlayer(entry.getKey()).getName();
                                            } else {
                                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                            }
                                            bossBar.setTitle("Активен бустер монет игрока " + player + ". Осталось " +  time + " минут");
                                        }
                                        if (time-1 == 0){
                                            this.cancel();
                                            Prison.getInstance().getDatabase().clearMoneyBst();
                                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.removePlayer(u.getPlayer()));
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер на 15 минут! Используйте /thx для того, что бы отблагодарить его.");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.DIRT)){
                    if (Prison.getPlugin(Prison.class).boosterBlocks.isEmpty()) {
                        if (antBalance>=50) {
                            Prison.getPlugin(Prison.class).boosterBlocks.put(player.getUniqueId(), 15);
                            Prison.getInstance().getDatabase().insertBlockBst(15, player.getUniqueId());
                            try {
                                economy.withdrawAntaresCoins(50);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            new BukkitRunnable() {
                                public void run(){
                                Integer timer = Prison.getPlugin(Prison.class).boosterBlocks.get(player.getUniqueId());
                                timer--;
                                    Prison.getPlugin(Prison .class).boosterBlocks.replace(player.getUniqueId(),timer);
                                    if(timer<=0){
                                    Prison.getPlugin(Prison.class).boosterBlocks.clear();
                                    this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            int displayTime = 0;
                            String playerName = null ;
                                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                                    displayTime = entry.getValue();
                                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                                    } else {
                                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                    }
                                }
                            BossBar bossBar = Bukkit.createBossBar("Активен бустер блоков игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                            bossBar.setVisible(true);
                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                            new BukkitRunnable() {
                                    public void run() {
                                        int time = 0;
                                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                                            time = entry.getValue();
                                            String player;
                                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                                player = Bukkit.getPlayer(entry.getKey()).getName();
                                            } else {
                                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                            }
                                            bossBar.setTitle("Активен бустер блоков игрока " + player + ". Осталось " +  time + " минут");
                                        }
                                        if (time-1 == 0){
                                            this.cancel();
                                            Prison.getInstance().getDatabase().clearBlockBst();
                                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.removePlayer(u.getPlayer()));
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер блоков на 15 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
                    if (Prison.getPlugin(Prison.class).booster.isEmpty()) {
                        if (antBalance >= 80) {
                            Prison.getPlugin(Prison.class).booster.put(player.getUniqueId(), 30);
                            Prison.getInstance().getDatabase().insertMoneyBst(player.getUniqueId(), 30);
                            try {
                                economy.withdrawAntaresCoins(80);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            new BukkitRunnable() {
                                public void run(){
                                Integer timer = Prison.getPlugin(Prison.class).booster.get(player.getUniqueId());
                                timer--;
                                    Prison.getPlugin(Prison .class).booster.replace(player.getUniqueId(),timer);
                                    if(timer<=0){
                                    Prison.getPlugin(Prison.class).booster.clear();
                                    if (!Prison.getPlugin(Prison.class).thx.isEmpty()) {
                                        Prison.getPlugin(Prison.class).thx.clear();
                                    }
                                    this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            int displayTime = 0;
                            String playerName = null ;
                                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                                    displayTime = entry.getValue();
                                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                                    } else {
                                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                    }
                                }
                            BossBar bossBar = Bukkit.createBossBar("Активен бустер монет игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                            bossBar.setVisible(true);
                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                            new BukkitRunnable() {
                                    public void run() {
                                        int time = 0;
                                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                                            time = entry.getValue();
                                            String player;
                                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                                player = Bukkit.getPlayer(entry.getKey()).getName();
                                            } else {
                                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                            }
                                            bossBar.setTitle("Активен бустер монет игрока " + player + ". Осталось " +  time + " минут");
                                        }
                                        if (time-1 == 0){
                                            this.cancel();
                                            Prison.getInstance().getDatabase().clearMoneyBst();
                                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.removePlayer(u.getPlayer()));
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер на 30 минут! Используйте /thx для того, что бы отблагодарить его.");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.STONE)){
                    if (Prison.getPlugin(Prison.class).boosterBlocks.isEmpty()) {
                        if (antBalance>=80) {
                            Prison.getPlugin(Prison.class).boosterBlocks.put(player.getUniqueId(), 30);
                            Prison.getInstance().getDatabase().insertBlockBst(30, player.getUniqueId());
                            try {
                                economy.withdrawAntaresCoins(80);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            new BukkitRunnable() {
                                public void run(){
                                Integer timer = Prison.getPlugin(Prison.class).boosterBlocks.get(player.getUniqueId());
                                timer--;
                                    Prison.getPlugin(Prison .class).boosterBlocks.replace(player.getUniqueId(),timer);
                                    if(timer<=0){
                                    Prison.getPlugin(Prison.class).boosterBlocks.clear();
                                    this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            int displayTime = 0;
                            String playerName = null ;
                                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                                    displayTime = entry.getValue();
                                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                                    } else {
                                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                    }
                                }
                            BossBar bossBar = Bukkit.createBossBar("Активен бустер блоков игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                            bossBar.setVisible(true);
                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                            new BukkitRunnable() {
                                    public void run() {
                                        int time = 0;
                                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                                            time = entry.getValue();
                                            String player;
                                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                                player = Bukkit.getPlayer(entry.getKey()).getName();
                                            } else {
                                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                            }
                                            bossBar.setTitle("Активен бустер блоков игрока " + player + ". Осталось " +  time + " минут");
                                        }
                                        if (time-1 == 0){
                                            this.cancel();
                                            Prison.getInstance().getDatabase().clearBlockBst();
                                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.removePlayer(u.getPlayer()));
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер блоков на 30 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                    } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.DIAMOND_BLOCK)){
                    if (Prison.getPlugin(Prison.class).boosterBlocks.isEmpty()) {
                        if (antBalance>=100) {
                            Prison.getPlugin(Prison.class).boosterBlocks.put(player.getUniqueId(), 45);
                            Prison.getInstance().getDatabase().insertBlockBst(45, player.getUniqueId());
                            try {
                                economy.withdrawAntaresCoins(100);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            new BukkitRunnable() {
                                public void run(){
                                Integer timer = Prison.getPlugin(Prison.class).boosterBlocks.get(player.getUniqueId());
                                timer--;
                                    Prison.getPlugin(Prison .class).boosterBlocks.replace(player.getUniqueId(),timer);
                                    if(timer<=0){
                                    Prison.getPlugin(Prison.class).boosterBlocks.clear();
                                    this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            int displayTime = 0;
                            String playerName = null ;
                                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                                    displayTime = entry.getValue();
                                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                                    } else {
                                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                    }
                                }
                            BossBar bossBar = Bukkit.createBossBar("Активен бустер блоков игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                            bossBar.setVisible(true);
                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                            new BukkitRunnable() {
                                    public void run() {
                                        int time = 0;
                                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                                            time = entry.getValue();
                                            String player;
                                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                                player = Bukkit.getPlayer(entry.getKey()).getName();
                                            } else {
                                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                            }
                                            bossBar.setTitle("Активен бустер блоков игрока " + player + ". Осталось " +  time + " минут");
                                        }
                                        if (time-1 == 0){
                                            this.cancel();
                                            Prison.getInstance().getDatabase().clearBlockBst();
                                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.removePlayer(u.getPlayer()));
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " активировал глобальный бустер блоков на 45 минут!");
                            player.closeInventory();
                        } else{
                            Msg.send(player, ChatColor.RED + "У вас недостаточно средств!");
                        }
                    } else {
                        Msg.send(player, ChatColor.RED + "Уже активен другой бустер!");
                    }
                } else if (event.getView().getTitle().equalsIgnoreCase("Глобальные бустеры") && event.getCurrentItem().getType().equals(Material.DIAMOND)){
                    if (Prison.getPlugin(Prison.class).booster.isEmpty()) {
                        if (antBalance>=100) {
                            Prison.getPlugin(Prison.class).booster.put(player.getUniqueId(), 45);
                            Prison.getInstance().getDatabase().insertMoneyBst(player.getUniqueId(), 45);
                            try {
                                economy.withdrawAntaresCoins(100);
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                            new BukkitRunnable() {
                                public void run(){
                                Integer timer = Prison.getPlugin(Prison.class).booster.get(player.getUniqueId());
                                timer--;
                                    Prison.getPlugin(Prison .class).booster.replace(player.getUniqueId(),timer);
                                    if(timer<=0){
                                        Prison.getPlugin(Prison.class).booster.clear();
                                        if (!Prison.getPlugin(Prison.class).thx.isEmpty()) {
                                            Prison.getPlugin(Prison.class).thx.clear();
                                        }
                                        this.cancel();
                                    }
                                }
                            }.runTaskTimer(Prison.getInstance(), 0, 1200);
                            int displayTime = 0;
                            String playerName = null ;
                                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                                    displayTime = entry.getValue();
                                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                                    } else {
                                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                    }
                                }
                            BossBar bossBar = Bukkit.createBossBar("Активен бустер монет игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                            bossBar.setVisible(true);
                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                            new BukkitRunnable() {
                                    public void run() {
                                        int time = 0;
                                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                                            time = entry.getValue();
                                            String player;
                                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                                player = Bukkit.getPlayer(entry.getKey()).getName();
                                            } else {
                                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                                            }
                                            bossBar.setTitle("Активен бустер монет игрока " + player + ". Осталось " +  time + " минут");
                                        }
                                        if (time-1 == 0){
                                            this.cancel();
                                            Prison.getInstance().getDatabase().clearMoneyBst();
                                            Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.removePlayer(u.getPlayer()));
                                        }
                                    }
                                }.runTaskTimer(Prison.getInstance(), 0, 1200);
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

