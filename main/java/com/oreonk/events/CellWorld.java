package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class CellWorld implements Listener {
    //Спрятать игроков и дать годмод при телепорте
    @EventHandler
    public void onTeleport(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        if (event.getFrom().getName().equalsIgnoreCase("world")){
            new BukkitRunnable(){
                @Override
                public void run() {
                    if (player.getWorld().getName().equalsIgnoreCase("cells")) {
                        player.getWorld().getPlayers().forEach(p -> {
                            p.hidePlayer(Prison.getInstance(), player);
                            player.hidePlayer(Prison.getInstance(), p);
                        });
                        player.setInvulnerable(true);
                    }
                }
            }.runTaskLater(Prison.getInstance(), 10);
        //Показать игроков и убрать годмод
        } else if (event.getFrom().getName().equalsIgnoreCase("cells")){
            new BukkitRunnable(){
                @Override
                public void run() {
                    if (player.getWorld().getName().equalsIgnoreCase("world")) {
                        Bukkit.getOnlinePlayers().forEach(p -> {
                            p.showPlayer(Prison.getInstance(), player);
                            player.showPlayer(Prison.getInstance(), p);
                        });
                        player.setInvulnerable(false);
                    }
                }
            }.runTaskLater(Prison.getInstance(), 10);
        }
    }
    //Спрятать игроков и дать годмод при логине
    @EventHandler
    public void onLogin(PlayerJoinEvent event){
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("cells")){
            Player player = event.getPlayer();
            player.getWorld().getPlayers().forEach(p -> {
                p.hidePlayer(Prison.getInstance(), player);
                player.hidePlayer(Prison.getInstance(), p);
            });
            player.setInvulnerable(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){
        //Пкм по нужному нпс в мире камер
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("cells") && event.getHand().equals(EquipmentSlot.HAND) && event.getRightClicked().getCustomName().equalsIgnoreCase("Сокамерник")){
            Location location = new Location(Bukkit.getWorld("cells"), 0, 100,0);
            Location locationTwo = new Location(Bukkit.getWorld("cells"), 1, 100,0);
            Location locationThree = new Location(Bukkit.getWorld("cells"), 2, 100,0);
            Location locationFour = new Location(Bukkit.getWorld("cells"), 3, 100,0);
            //Первый ГУИ апа камеры
            if (event.getRightClicked().getLocation().equals(location)){
                    Player player = event.getPlayer();
                    Inventory gui = Bukkit.createInventory(player, 27, "Улучшение начальной камеры");

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack upgrade = new ItemStack(Material.EXPERIENCE_BOTTLE);
                    ItemMeta upgradeMeta = upgrade.getItemMeta();
                    upgradeMeta.setDisplayName(ChatColor.YELLOW + "Улучшить камеру");
                    ArrayList<String> upgradeLore = new ArrayList<>();

                    ItemStack fight = new ItemStack(Material.IRON_SWORD);
                    ItemMeta fightMeta = fight.getItemMeta();
                    fightMeta.setDisplayName(ChatColor.RED + "Устроить стрелку");
                    ArrayList<String> fightLore = new ArrayList<>();
                    if (Prison.getInstance().getDatabase().getCellLvl(player) < 5) {
                        int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                        ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                        double price = Double.parseDouble(config.get(0));
                        double priceFight = Double.parseDouble(config.get(1));
                        int lvl = Integer.parseInt(config.get(2));
                        int balance = (int) Prison.getEconomy().getBalance(player);
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + priceFight);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Нужна для получения: ");
                        fightLore.add(ChatColor.WHITE + "Авторитет: НЕ ЗАБУДЬ СДЕЛАТЬ ИКОНКИ");
                        fightLore.add("");
                        if (balance >= priceFight) {
                            fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                        } else {
                            fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                        }
                        if (balance >= price && Prison.getInstance().lvl.get(player) >= lvl) {
                            upgradeLore.add(ChatColor.GREEN + "Нажмите для улучшения камеры");
                        } else {
                            upgradeLore.add(ChatColor.RED + "Условия улучшения не выполнены!");
                        }
                    } else {
                        upgradeLore.add("Максимальный уровень камеры. В том случае,");
                        upgradeLore.add("если вы тут застряли - используйте /cell");
                        upgradeLore.add("для перехода в камеру следующего уровня.");
                    }
                    upgradeMeta.setLore(upgradeLore);
                    upgrade.setItemMeta(upgradeMeta);
                    gui.setItem(11, upgrade);
                    fightMeta.setLore(fightLore);
                    fight.setItemMeta(upgradeMeta);
                    gui.setItem(15, fight);
                    player.openInventory(gui);
                //Второй ГУИ апа камеры
            } else if (event.getRightClicked().getLocation().equals(locationTwo)){
                Player player = event.getPlayer();
                Inventory gui = Bukkit.createInventory(player, 27, "Улучшение небольшой камеры");

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack upgrade = new ItemStack(Material.EXPERIENCE_BOTTLE);
                    ItemMeta upgradeMeta = upgrade.getItemMeta();
                    upgradeMeta.setDisplayName(ChatColor.YELLOW + "Улучшить камеру");
                    ArrayList<String> upgradeLore = new ArrayList<>();

                    ItemStack fight = new ItemStack(Material.IRON_SWORD);
                    ItemMeta fightMeta = fight.getItemMeta();
                    fightMeta.setDisplayName(ChatColor.RED + "Устроить стрелку");
                    ArrayList<String> fightLore = new ArrayList<>();
                    if (Prison.getInstance().getDatabase().getCellLvl(player) < 8 && Prison.getInstance().getDatabase().getCellLvl(player) >= 4) {
                        int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                        ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl+1));
                        double price = Double.parseDouble(config.get(0));
                        double priceFight = Double.parseDouble(config.get(1));
                        int lvl = Integer.parseInt(config.get(2));
                        int balance = (int) Prison.getEconomy().getBalance(player);
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + priceFight);
                        fightLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Нужна для получения: ");
                        fightLore.add(ChatColor.WHITE + "Авторитет: НЕ ЗАБУДЬ СДЕЛАТЬ ИКОНКИ");
                        fightLore.add("");
                        if (balance >= priceFight) {
                            fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                        } else {
                            fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                        }
                        if (balance >= price && Prison.getInstance().lvl.get(player) >= lvl) {
                            upgradeLore.add(ChatColor.GREEN + "Нажмите для улучшения камеры");
                        } else {
                            upgradeLore.add(ChatColor.RED + "Условия улучшения не выполнены!");
                        }
                    } else {
                        upgradeLore.add("Максимальный уровень камеры. В том случае,");
                        upgradeLore.add("если вы тут застряли - используйте /cell");
                        upgradeLore.add("для перехода в камеру следующего уровня.");
                    }
                        upgradeMeta.setLore(upgradeLore);
                        upgrade.setItemMeta(upgradeMeta);
                        gui.setItem(11, upgrade);
                        fightMeta.setLore(fightLore);
                        fight.setItemMeta(upgradeMeta);
                        gui.setItem(15, fight);
                        player.openInventory(gui);
                //Третий ГУИ апа камеры
            } else if (event.getRightClicked().getLocation().equals(locationThree)){
                Player player = event.getPlayer();
                Inventory gui = Bukkit.createInventory(player, 27, "Улучшение средней камеры");

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack upgrade = new ItemStack(Material.EXPERIENCE_BOTTLE);
                    ItemMeta upgradeMeta = upgrade.getItemMeta();
                    upgradeMeta.setDisplayName(ChatColor.YELLOW + "Улучшить камеру");
                    ArrayList<String> upgradeLore = new ArrayList<>();

                    ItemStack fight = new ItemStack(Material.IRON_SWORD);
                    ItemMeta fightMeta = fight.getItemMeta();
                    fightMeta.setDisplayName(ChatColor.RED + "Устроить стрелку");
                    ArrayList<String> fightLore = new ArrayList<>();
                    if ((Prison.getInstance().getDatabase().getCellLvl(player) < 14 && Prison.getInstance().getDatabase().getCellLvl(player) >= 7)) {
                        int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                        ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl+1));
                        double price = Double.parseDouble(config.get(0));
                        double priceFight = Double.parseDouble(config.get(1));
                        int lvl = Integer.parseInt(config.get(2));
                        int balance = (int) Prison.getEconomy().getBalance(player);
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + priceFight);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Нужна для получения: ");
                        fightLore.add(ChatColor.WHITE + "Авторитет: НЕ ЗАБУДЬ СДЕЛАТЬ ИКОНКИ");
                        fightLore.add("");
                        if (balance >= priceFight) {
                            fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                        } else {
                            fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                        }
                        if (balance >= price && Prison.getInstance().lvl.get(player) >= lvl) {
                            upgradeLore.add(ChatColor.GREEN + "Нажмите для улучшения камеры");
                        } else {
                            upgradeLore.add(ChatColor.RED + "Условия улучшения не выполнены!");
                        }
                    } else {
                        upgradeLore.add("Максимальный уровень камеры. В том случае,");
                        upgradeLore.add("если вы тут застряли - используйте /cell");
                        upgradeLore.add("для перехода в камеру следующего уровня.");
                    }
                        upgradeMeta.setLore(upgradeLore);
                        upgrade.setItemMeta(upgradeMeta);
                        gui.setItem(11, upgrade);
                        fightMeta.setLore(fightLore);
                        fight.setItemMeta(upgradeMeta);
                        gui.setItem(15, fight);
                        player.openInventory(gui);
            }
            //Четвертый ГУИ апа камеры
            else if (event.getRightClicked().getLocation().equals(locationFour)){
                Player player = event.getPlayer();
                Inventory gui = Bukkit.createInventory(player, 27, "Улучшение большой камеры");

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack upgrade = new ItemStack(Material.EXPERIENCE_BOTTLE);
                    ItemMeta upgradeMeta = upgrade.getItemMeta();
                    upgradeMeta.setDisplayName(ChatColor.YELLOW + "Улучшить камеру");
                    ArrayList<String> upgradeLore = new ArrayList<>();

                    ItemStack fight = new ItemStack(Material.IRON_SWORD);
                    ItemMeta fightMeta = fight.getItemMeta();
                    fightMeta.setDisplayName(ChatColor.RED + "Устроить стрелку");
                    ArrayList<String> fightLore = new ArrayList<>();
                    if (Prison.getInstance().getDatabase().getCellLvl(player) > 13) {
                        int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                        ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl+1));
                        double price = Double.parseDouble(config.get(0));
                        double priceFight = Double.parseDouble(config.get(1));
                        int lvl = Integer.parseInt(config.get(2));
                        int balance = (int) Prison.getEconomy().getBalance(player);
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + priceFight);
                        fightLore.add("");
                        fightLore.add(ChatColor.GRAY + "Нужна для получения: ");
                        fightLore.add(ChatColor.WHITE + "Авторитет: НЕ ЗАБУДЬ СДЕЛАТЬ ИКОНКИ");
                        fightLore.add("");
                        if (balance >= priceFight) {
                            fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                        } else {
                            fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                        }
                        if (balance >= price && Prison.getInstance().lvl.get(player) >= lvl) {
                            upgradeLore.add(ChatColor.GREEN + "Нажмите для улучшения камеры");
                        } else {
                            upgradeLore.add(ChatColor.RED + "Условия улучшения не выполнены!");
                        }
                    } else {
                        upgradeLore.add("Максимальный уровень камеры. В том случае,");
                        upgradeLore.add("если вы тут застряли - используйте /cell");
                        upgradeLore.add("для перехода в камеру следующего уровня.");
                    }
                        upgradeMeta.setLore(upgradeLore);
                        upgrade.setItemMeta(upgradeMeta);
                        gui.setItem(11, upgrade);
                        fightMeta.setLore(fightLore);
                        fight.setItemMeta(upgradeMeta);
                        gui.setItem(15, fight);
                        player.openInventory(gui);
            }
        }
    }
}
