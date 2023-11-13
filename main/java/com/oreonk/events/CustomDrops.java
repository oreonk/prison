package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class CustomDrops implements Listener {
    @EventHandler
    public void customDrops(EntityDamageByEntityEvent event) {
        if (event.getEntity().getCustomName() != null) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (event.getEntity().getCustomName().contains("Шахтёр")) {
                    if (!Prison.getInstance().miner.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().miner.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().miner.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().miner.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Ледяное чудовище")) {
                    if (!Prison.getInstance().ice.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().ice.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().ice.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().ice.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Злой фермер")) {
                    if (!Prison.getInstance().farmer.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().farmer.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().farmer.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().farmer.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Иссушитель")) {
                    if (!Prison.getInstance().wither.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().wither.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().wither.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().wither.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Воришка")) {
                    if (!Prison.getInstance().thief.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().thief.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().thief.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().thief.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Хранитель леса")) {
                    if (!Prison.getInstance().spirit.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().spirit.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().spirit.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().spirit.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Страж сатаны")) {
                    if (!Prison.getInstance().blaze.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().blaze.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().blaze.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().blaze.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Ведьма")) {
                    if (!Prison.getInstance().witch.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().witch.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().witch.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().witch.replace(player, finalDamage);
                    }
                }
                //Фишка с воровством денег
            }
        } else if (event.getEntity() instanceof Player && event.getDamager().getCustomName() != null && event.getDamager().getCustomName().contains("Воришка")){
            Player player = (Player) event.getEntity();
            if (!Prison.getInstance().thief_yoink.containsKey(player.getUniqueId())){
                if(Prison.getEconomy().getBalance(player) >= 5.0){
                    Prison.getInstance().thief_yoink.put(player.getUniqueId(), 5.0);
                    Msg.send(player, ChatColor.RED + "Воришка украл у вас 5 " + ChatColor.WHITE + "" );
                } else if (Prison.getEconomy().getBalance(player) == 0){
                    Prison.getInstance().thief_yoink.put(player.getUniqueId(), 0.0);
                    Msg.send(player, "Воришке нечего у вас красть..." );
                } else if (Prison.getEconomy().getBalance(player) < 5.0 && Prison.getEconomy().getBalance(player) > 0){
                    double amount = Prison.getEconomy().getBalance(player);
                    Prison.getInstance().thief_yoink.put(player.getUniqueId(), amount);
                    Msg.send(player, ChatColor.RED + "Воришка украл у вас " + amount + ChatColor.WHITE + " " );
                }
            }
        }
    }
    @EventHandler
    public void customDrops(EntityDeathEvent event) {
        if (event.getEntity().getCustomName() != null) {
             if (event.getEntity().getCustomName().contains("Ледяное чудовище")) {
                if (!Prison.getInstance().ice.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).ice.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().ice.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().ice.size() == 1){
                            stringPrice = decimalFormat.format(1500);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 3000 * (damage / 800);
                            if (pay > 1500){
                                pay = 1500.0;
                            } else if (pay < 300){
                                pay = 300.0;
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 16) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 7) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Ледяного чудовища ");
                    }
                    Prison.getInstance().ice.clear();
                }
             } else if (event.getEntity().getCustomName().contains("Хранитель леса")) {
                if (!Prison.getInstance().spirit.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).spirit.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().spirit.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().spirit.size() == 1){
                            stringPrice = decimalFormat.format(8750);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                            Random r = new Random();
                            int percent = r.nextInt(100);
                            if (percent < 25) {
                                ItemStack ra = new ItemStack(Material.GLOWSTONE_DUST);
                                ItemMeta ra_meta = ra.getItemMeta();
                                ra_meta.setDisplayName(ChatColor.DARK_RED + "Ра");
                                ra.setItemMeta(ra_meta);
                                player.getInventory().addItem(ra);
                                Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                            }
                        } else {
                            ItemStack ra = new ItemStack(Material.GLOWSTONE_DUST);
                            ItemMeta ra_meta = ra.getItemMeta();
                            ra_meta.setDisplayName(ChatColor.DARK_RED + "Ра");
                            ra.setItemMeta(ra_meta);
                            Double pay = 17500 * (damage / 1000);
                            if (pay > 8750){
                                pay = 8750.0;
                            } else if(pay < 1750){
                                pay = 1750.0;
                            }
                            if (damage/1000 >= 0.3){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 25) {
                                    player.getInventory().addItem(ra);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else if (damage/1000 >= 0.15){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 15) {
                                    player.getInventory().addItem(ra);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else {
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 10) {
                                    player.getInventory().addItem(ra);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 16) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 7) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Хранителя леса ");
                    }
                    Prison.getInstance().spirit.clear();
                }
                } else if (event.getEntity().getCustomName().contains("Страж сатаны")) {
                if (!Prison.getInstance().blaze.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).blaze.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().blaze.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().blaze.size() == 1){
                            stringPrice = decimalFormat.format(12500);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                            Random r = new Random();
                            int percent = r.nextInt(100);
                            if (percent < 25) {
                                ItemStack soul_normal = new ItemStack(Material.PHANTOM_MEMBRANE);
                                ItemMeta soul_normal_meta = soul_normal.getItemMeta();
                                soul_normal_meta.setDisplayName(ChatColor.AQUA + "Душа");
                                soul_normal.setItemMeta(soul_normal_meta);
                                player.getInventory().addItem(soul_normal);
                                Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                            }
                        } else {
                            ItemStack soul_normal = new ItemStack(Material.PHANTOM_MEMBRANE);
                            ItemMeta soul_normal_meta = soul_normal.getItemMeta();
                            soul_normal_meta.setDisplayName(ChatColor.AQUA + "Душа");
                            soul_normal.setItemMeta(soul_normal_meta);
                            Double pay = 25000 * (damage / 2500);
                            if (pay>12500){
                                pay = 12500.0;
                            } else if (pay < 1750){
                                pay = 1750.0;
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                            if (damage/2500 >= 0.3){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 15) {
                                    player.getInventory().addItem(soul_normal);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else if (damage/2500 >= 0.15){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 10) {
                                    player.getInventory().addItem(soul_normal);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else {
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 5) {
                                    player.getInventory().addItem(soul_normal);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            }
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 16) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 7) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Стража сатаны ");
                    }
                    Prison.getInstance().blaze.clear();
                }
                } else if (event.getEntity().getCustomName().contains("Ведьма")) {
                if (!Prison.getInstance().witch.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).witch.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().witch.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().witch.size() == 1){
                            stringPrice = decimalFormat.format(5000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                            Random r = new Random();
                            int percent = r.nextInt(100);
                            if (percent < 30) {
                                ItemStack potion = new ItemStack(Material.SPLASH_POTION);
                                ItemMeta potion_meta = potion.getItemMeta();
                                potion_meta.setDisplayName(ChatColor.DARK_PURPLE + "Эликсир");
                                potion.setItemMeta(potion_meta);
                                player.getInventory().addItem(potion);
                                Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                            }
                        } else {
                            ItemStack potion = new ItemStack(Material.SPLASH_POTION);
                            ItemMeta potion_meta = potion.getItemMeta();
                            potion_meta.setDisplayName(ChatColor.DARK_PURPLE + "Эликсир");
                            potion.setItemMeta(potion_meta);
                            Double pay = 10000 * (damage / 1000);
                            if (pay>5000){
                                pay = 5000.0;
                            } else if (pay < 1000){
                                pay = 1000.0;
                            }
                            if (damage/1000 >= 0.3){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 30) {
                                    player.getInventory().addItem(potion);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else if (damage/1000 >= 0.15){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 20) {
                                    player.getInventory().addItem(potion);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else {
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 15) {
                                    player.getInventory().addItem(potion);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 18) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 10) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Ведьмы ");
                    }
                    Prison.getInstance().witch.clear();
                }
             } else if (event.getEntity().getCustomName().contains("Иссушитель")) {
                if (!Prison.getInstance().wither.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).wither.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().wither.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().wither.size() == 1){
                            stringPrice = decimalFormat.format(25000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 50000 * (damage / 1000);
                            if (pay>25000){
                                pay = 25000.0;
                            } else if (pay < 2500){
                                pay = 2500.0;
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 16) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 7) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Иссушителя ");
                    }
                    Prison.getInstance().wither.clear();
                } else if (event.getEntity().getCustomName().contains("Шахтёр")) {
                if (!Prison.getInstance().miner.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).miner.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().miner.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().miner.size() == 1){
                            stringPrice = decimalFormat.format(250);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 500 * (damage / 200);
                            if (pay>250){
                                pay = 250.0;
                            } else if (pay < 50){
                                pay = 50.0;
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 5) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 1) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Шахтёра ");
                    }
                    Prison.getInstance().miner.clear();
                }
                } else if (event.getEntity().getCustomName().contains("Злой фермер")) {
                if (!Prison.getInstance().farmer.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).farmer.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().farmer.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().farmer.size() == 1){
                            stringPrice = decimalFormat.format(1000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                            Random r = new Random();
                            int percent = r.nextInt(100);
                            if (percent < 50) {
                                ItemStack hoe = new ItemStack(Material.STONE_HOE);
                                ItemMeta hoe_meta = hoe.getItemMeta();
                                hoe_meta.setDisplayName(ChatColor.YELLOW + "Тяпка");
                                hoe.setItemMeta(hoe_meta);
                                player.getInventory().addItem(hoe);
                                Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                            }
                        } else {
                            ItemStack hoe = new ItemStack(Material.STONE_HOE);
                            ItemMeta hoe_meta = hoe.getItemMeta();
                            hoe_meta.setDisplayName(ChatColor.YELLOW + "Тяпка");
                            hoe.setItemMeta(hoe_meta);
                            Double pay = 2000 * (damage / 500);
                            if (pay>1000){
                                pay = 1000.0;
                            } else if (pay < 200){
                                pay = 200.0;
                            }
                            if (damage/500 >= 0.3){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 50) {
                                    player.getInventory().addItem(hoe);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else if (damage/500 >= 0.15){
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 40) {
                                    player.getInventory().addItem(hoe);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            } else {
                                Random r = new Random();
                                int percent = r.nextInt(100);
                                if (percent < 30) {
                                    player.getInventory().addItem(hoe);
                                    Msg.send(player, ChatColor.GREEN + "Вы получили коллекционный предмет!");
                                }
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 8) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 3) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Злого фермера ");
                    }
                    Prison.getInstance().farmer.clear();
                }
                }else if (event.getEntity().getCustomName().contains("Воришка")) {
                if (!Prison.getInstance().thief.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).thief.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().thief.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().thief.size() == 1){
                            stringPrice = decimalFormat.format(7500);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 15000 * (damage / 1500);
                            if (pay>7500){
                                pay = 7500.0;
                            } else if (pay < 1500){
                                pay = 1500.0;
                            }
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 7) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 3) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Воришки ");
                    }
                    Prison.getInstance().thief.clear();
                    for (Map.Entry<UUID, Double> entry : Prison.getInstance().thief_yoink.entrySet()) {
                        Prison.getEconomy().depositPlayer(Bukkit.getPlayer(entry.getKey()), entry.getValue());
                    }
                    Prison.getInstance().thief_yoink.clear();
                }
                }
            } else if (event.getEntity().getCustomName().contains("Опоссум")){
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    Random random = new Random();
                    double amount = (random.nextDouble() * (0.1-0.05))+0.05;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String parseAmount = decimalFormat.format(amount);
                    Prison.getEconomy().depositPlayer(player, amount);
                    Msg.send(player, "Вы получили " + parseAmount + " &f");
                    int drops = random.nextInt(2);
                    ItemStack stack = new ItemStack(Material.ROTTEN_FLESH);
                    stack.setAmount(drops);
                    player.getInventory().addItem(stack);
                }
            } else if (event.getEntity().getCustomName().contains("Зомби")){
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    Random random = new Random();
                    double amount = (random.nextDouble() * (0.5-0.3))+0.3;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String parseAmount = decimalFormat.format(amount);
                    Prison.getEconomy().depositPlayer(player, amount);
                    Msg.send(player, "Вы получили " + parseAmount + " &f");
                    int drops = (random.nextInt() * (3-1))+1;
                    ItemStack stack = new ItemStack(Material.ROTTEN_FLESH);
                    stack.setAmount(drops);
                    player.getInventory().addItem(stack);
                }
            } else if (event.getEntity().getCustomName().contains("Тарантул")){
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    Random random = new Random();
                    double amount = (random.nextDouble() * (0.4-0.2))+0.2;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String parseAmount = decimalFormat.format(amount);
                    Prison.getEconomy().depositPlayer(player, amount);
                    Msg.send(player, "Вы получили " + parseAmount + " &f");
                    int drops = random.nextInt(2);
                    ItemStack stack = new ItemStack(Material.STRING);
                    stack.setAmount(drops);
                    player.getInventory().addItem(stack);
                }
            } else if (event.getEntity().getCustomName().contains("Ожившая сопля")){
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    Random random = new Random();
                    int drops = (random.nextInt() * (3-1))+1;
                    ItemStack stack = new ItemStack(Material.SLIME_BALL);
                    stack.setAmount(drops);
                    player.getInventory().addItem(stack);
                }
            } else if (event.getEntity().getCustomName().contains("Вампир")){
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    Random random = new Random();
                    double amount = (random.nextDouble() * (5.0-3.0))+3.0;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String parseAmount = decimalFormat.format(amount);
                    Prison.getEconomy().depositPlayer(player, amount);
                    Msg.send(player, "Вы получили " + parseAmount + " &f");
                    int drops = (random.nextInt() * (5-3))+3;
                    ItemStack stack = new ItemStack(Material.ROTTEN_FLESH);
                    stack.setAmount(drops);
                    player.getInventory().addItem(stack);
                }
            }
        }
    }
}
