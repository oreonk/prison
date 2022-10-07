package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

public class CustomDrops implements Listener {
    @EventHandler
    public void customDrops(EntityDamageByEntityEvent event) {
        if (event.getEntity().getCustomName() != null) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (event.getEntity().getCustomName().contains("Минотавр")) {
                    if (!Prison.getInstance().minotaur.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().minotaur.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().minotaur.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().minotaur.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Ледяной гигант")) {
                    if (!Prison.getInstance().ice.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().ice.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().ice.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().ice.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Тёмный рыцарь")) {
                    if (!Prison.getInstance().knight.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().knight.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().knight.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().knight.replace(player, finalDamage);
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
                } else if (event.getEntity().getCustomName().contains("Паук")) {
                    if (!Prison.getInstance().spider.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().spider.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().spider.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().spider.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Лесной дух")) {
                    if (!Prison.getInstance().spirit.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().spirit.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().spirit.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().spirit.replace(player, finalDamage);
                    }
                } else if (event.getEntity().getCustomName().contains("Блейз")) {
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
                else if (event.getEntity().getCustomName().contains("Слизень")) {
                    if (!Prison.getInstance().slime.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().slime.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().slime.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().slime.replace(player, finalDamage);
                    }
                }
                else if (event.getEntity().getCustomName().contains("Скелет")) {
                    if (!Prison.getInstance().skeleton.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().skeleton.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().skeleton.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().skeleton.replace(player, finalDamage);
                    }
                }
                else if (event.getEntity().getCustomName().contains("Тёмный конь")) {
                    if (!Prison.getInstance().knight.containsKey(player)) {
                        Double damage = event.getFinalDamage();
                        Prison.getInstance().knight.put(player, damage);
                    } else {
                        Double damage = Prison.getInstance().knight.get(player);
                        Double finalDamage = damage + event.getFinalDamage();
                        Prison.getInstance().knight.replace(player, finalDamage);
                    }
                }
            }
        }
    }
    @EventHandler
    public void customDrops(EntityDeathEvent event) {
        if (event.getEntity().getCustomName() != null) {
            if (event.getEntity().getCustomName().contains("Минотавр")) {
                if (!Prison.getInstance().minotaur.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).minotaur.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().minotaur.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().minotaur.size() == 1){
                            stringPrice = decimalFormat.format(10000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 10000 * (damage / 1000);
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 10) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 6) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Минотавра");
                    }
                    Prison.getInstance().minotaur.clear();
                }
            } else if (event.getEntity().getCustomName().contains("Ледяной гигант")) {
                if (!Prison.getInstance().ice.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).ice.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().ice.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().ice.size() == 1){
                            stringPrice = decimalFormat.format(50000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 50000 * (damage / 1500);
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
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Ледяного гиганта ");
                    }
                    Prison.getInstance().ice.clear();
                }
                } else if (event.getEntity().getCustomName().contains("Лесной дух")) {
                if (!Prison.getInstance().spirit.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).spirit.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().spirit.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().spirit.size() == 1){
                            stringPrice = decimalFormat.format(40000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 4500 * (damage / 1000);
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
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Лесного духа ");
                    }
                    Prison.getInstance().spirit.clear();
                }
                } else if (event.getEntity().getCustomName().contains("Блейз")) {
                if (!Prison.getInstance().blaze.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).blaze.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().blaze.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().blaze.size() == 1){
                            stringPrice = decimalFormat.format(6000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 6000 * (damage / 800);
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
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Блейза ");
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
                            stringPrice = decimalFormat.format(60000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            int pay = 6000 / Prison.getInstance().witch.size();
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
                } else if (event.getEntity().getCustomName().contains("Паук")) {
                if (!Prison.getInstance().spider.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).spider.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().spider.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().spider.size() == 1){
                            stringPrice = decimalFormat.format(5000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 5000 * (damage / 600);
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
                        if (percent < 4) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве паука ");
                    }
                    Prison.getInstance().spider.clear();
                }
                } else if (event.getEntity().getCustomName().contains("Слизень")) {
                if (!Prison.getInstance().slime.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).slime.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().slime.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().slime.size() == 1){
                            stringPrice = decimalFormat.format(15000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 15000 * (damage / 1000);
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 12) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 8) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве слайма ");
                    }
                    Prison.getInstance().slime.clear();
                }
                }else if (event.getEntity().getCustomName().contains("Скелет")) {
                if (!Prison.getInstance().skeleton.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).skeleton.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().skeleton.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().skeleton.size() == 1){
                            stringPrice = decimalFormat.format(25000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 25000 * (damage / 1000);
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 14) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 9) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве скелета ");
                    }
                    Prison.getInstance().skeleton.clear();
                }
                }else if (event.getEntity().getCustomName().contains("Иссушитель")) {
                if (!Prison.getInstance().wither.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).wither.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().wither.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().wither.size() == 1){
                            stringPrice = decimalFormat.format(70000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 6000 * (damage / 1000);
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
                }
            } else if (event.getEntity().getCustomName().contains("Тёмный конь")) {
                if (!Prison.getInstance().knight.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).knight.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().knight.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice ;
                        if (Prison.getInstance().knight.size() == 1){
                            stringPrice = decimalFormat.format(50000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 5000 * (damage / 600);
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
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве коня Тёмного рыцаря");
                    }
                    Prison.getInstance().knight.clear();
                }
            } else if (event.getEntity().getCustomName().contains("Тёмный рыцарь")) {
                if (!Prison.getInstance().knight.isEmpty()) {
                    for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).knight.entrySet()) {
                        Player player = entry.getKey();
                        Double damage = Prison.getInstance().knight.get(player);
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        String stringPrice;
                        double finalPrice;
                        if (Prison.getInstance().knight.size() == 1){
                            stringPrice = decimalFormat.format(50000);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        } else {
                            Double pay = 7000 * (damage / 900);
                            stringPrice = decimalFormat.format(pay);
                            finalPrice = Double.parseDouble(stringPrice);
                            Prison.getEconomy().depositPlayer(player, finalPrice);
                        }
                        Random r = new Random();
                        int percent = r.nextInt(100);
                        if (percent < 20) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Rare").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили редкий ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        if (percent < 10) {
                            ItemStack key = ItemStack.deserialize(Prison.getInstance().getConfig().getConfigurationSection("Heroic").getValues(true));
                            Msg.send(player, ChatColor.GREEN + "Вы получили героический ключ, за убийство босса");
                            player.getInventory().addItem(key);
                        }
                        Msg.send(player, "&aВы получили " + stringPrice + " &f &aза участие в убийстве Тёмного рыцаря");
                    }
                    Prison.getInstance().knight.clear();
                }
            } else if (event.getEntity().getCustomName().contains("Слайм")){
                if (event.getEntity().getKiller() != null) {
                    Player player = event.getEntity().getKiller();
                    Random random = new Random();
                    int amount = random.nextInt(15);
                    Prison.getEconomy().depositPlayer(player, amount);
                    Msg.send(player, "Вы получили " + amount + " &f");
                }
            }
        }
    }
}
