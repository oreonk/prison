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
                        Msg.send(player, "С обычного мира в камеры. Неуязвимость и спрятать игроков");
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
                        Msg.send(player, "С камер в обычный мир. Убрать неуязвимость и показать игроков");
                        Bukkit.getOnlinePlayers().forEach(p -> {
                            p.showPlayer(Prison.getInstance(), player);
                            player.showPlayer(Prison.getInstance(), p);
                        });
                        player.setInvulnerable(false);
                    }
                }
            }.runTaskLater(Prison.getInstance(), 10);
        } else if (event.getFrom().getName().equalsIgnoreCase("boss")){
            new BukkitRunnable(){
                @Override
                public void run() {
                    if (player.getWorld().getName().equalsIgnoreCase("cells")) {
                        Msg.send(player, "С босса в камеры. Неуязвимость и спрятать игрков");
                        Bukkit.getOnlinePlayers().forEach(p -> {
                            p.hidePlayer(Prison.getInstance(), player);
                            player.showPlayer(Prison.getInstance(), p);
                        });
                        player.setInvulnerable(true);
                    }
                }
            }.runTaskLater(Prison.getInstance(), 10);
        } else if (event.getFrom().getName().equalsIgnoreCase("cells")){
            new BukkitRunnable(){
                @Override
                public void run() {
                    if (player.getWorld().getName().equalsIgnoreCase("boss")) {
                        Msg.send(player, "С камер в босса. Убрать неуязвимость и показать игроков");
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
            Location location = new Location(Bukkit.getWorld("cells"), -29, -59,-19);
            Location locationTwo = new Location(Bukkit.getWorld("cells"), -18, -59,-19);
            Location locationThree = new Location(Bukkit.getWorld("cells"), -5, -59,-18);
            Location locationFour = new Location(Bukkit.getWorld("cells"), 8, -59,-17);
            if (Math.signum(location.getX()) == -1.0){
                location.subtract(1.0,0,0);
            }
            if (Math.signum(location.getZ()) == -1.0){
                location.subtract(0,0,1.0);
            }
            if (Math.signum(locationTwo.getX()) == -1.0){
                locationTwo.subtract(1.0,0,0);
            }
            if (Math.signum(locationTwo.getZ()) == -1.0){
                locationTwo.subtract(0,0,1.0);
            }
            if (Math.signum(locationThree.getX()) == -1.0){
                locationThree.subtract(1.0,0,0);
            }
            if (Math.signum(locationThree.getZ()) == -1.0){
                locationThree.subtract(0,0,1.0);
            }
            if (Math.signum(locationFour.getX()) == -1.0){
                locationFour.subtract(1.0,0,0);
            }
            if (Math.signum(locationFour.getZ()) == -1.0){
                locationFour.subtract(0,0,1.0);
            }
            //Первый ГУИ апа камеры
            if (Math.floor(event.getRightClicked().getLocation().getX()) == location.getX() && event.getRightClicked().getLocation().getY() == location.getY() && Math.floor(event.getRightClicked().getLocation().getZ()) == location.getZ()) {
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
                    int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                    ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                    double price = Double.parseDouble(config.get(0));
                    double priceFight = Double.parseDouble(config.get(1));
                    int balance = (int) Prison.getEconomy().getBalance(player);
                    fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                    fightLore.add("");
                    fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + priceFight);
                    fightLore.add("");
                    if (cellLvl < 3){
                        fightLore.add(ChatColor.GRAY + "Недоступно");
                    } else if (cellLvl == 3 || cellLvl == 4){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌" + ChatColor.GRAY + "▌▌▌▌");
                    } else if (cellLvl >= 5 && cellLvl < 7){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌" + ChatColor.GRAY + "▌▌▌");
                    } else if (cellLvl >= 7 && cellLvl < 11){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌" + ChatColor.GRAY + "▌▌");
                    } else if (cellLvl >= 11 && cellLvl <= 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌" + ChatColor.GRAY + "▌");
                    } else if (cellLvl == 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌▌");
                    }
                    fightLore.add("");
                    if (balance >= priceFight) {
                        fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                    } else {
                        fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                    }
                    if (Prison.getInstance().getDatabase().getCellLvl(player) <= 3) {
                        int lvl = Integer.parseInt(config.get(2));
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + "/" + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        if (Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())){
                            fightLore.add("");
                            fightLore.add(ChatColor.RED + "Стрелка доступна на следующие сутки!");
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
                    fight.setItemMeta(fightMeta);
                    gui.setItem(15, fight);
                    player.openInventory(gui);
                //Второй ГУИ апа камеры
            } else if (Math.floor(event.getRightClicked().getLocation().getX()) == locationTwo.getX() && event.getRightClicked().getLocation().getY() == locationTwo.getY() && Math.floor(event.getRightClicked().getLocation().getZ()) == locationTwo.getZ()) {
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
                    int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                    ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                    double price = Double.parseDouble(config.get(0));
                    double priceFight = Double.parseDouble(config.get(1));
                    int balance = (int) Prison.getEconomy().getBalance(player);
                    fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                    fightLore.add("");
                    fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + priceFight);
                    fightLore.add("");
                    if (cellLvl < 3){
                        fightLore.add(ChatColor.GRAY + "Недоступно");
                    } else if (cellLvl == 3 || cellLvl == 4){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌" + ChatColor.GRAY + "▌▌▌▌");
                    } else if (cellLvl >= 5 && cellLvl < 7){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌" + ChatColor.GRAY + "▌▌▌");
                    } else if (cellLvl >= 7 && cellLvl < 11){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌" + ChatColor.GRAY + "▌▌");
                    } else if (cellLvl >= 11 && cellLvl <= 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌" + ChatColor.GRAY + "▌");
                    } else if (cellLvl == 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌▌");
                    }
                    fightLore.add("");
                    if (balance >= priceFight) {
                        fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                    } else {
                        fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                    }
                    if (Prison.getInstance().getDatabase().getCellLvl(player) < 7 && Prison.getInstance().getDatabase().getCellLvl(player) > 3) {
                        int lvl = Integer.parseInt(config.get(2));
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + "/" + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        if (Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())){
                            fightLore.add("");
                            fightLore.add(ChatColor.RED + "Стрелка доступна на следующие сутки!");
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
                        fight.setItemMeta(fightMeta);
                        gui.setItem(15, fight);
                        player.openInventory(gui);
                //Третий ГУИ апа камеры
            } else if (Math.floor(event.getRightClicked().getLocation().getX()) == locationThree.getX() && event.getRightClicked().getLocation().getY() == locationThree.getY() && Math.floor(event.getRightClicked().getLocation().getZ()) == locationThree.getZ()) {
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
                    int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                    ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                    double price = Double.parseDouble(config.get(0));
                    double priceFight = Double.parseDouble(config.get(1));
                    int balance = (int) Prison.getEconomy().getBalance(player);
                    fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                    fightLore.add("");
                    fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + priceFight);
                    fightLore.add("");
                    if (cellLvl < 3){
                        fightLore.add(ChatColor.GRAY + "Недоступно");
                    } else if (cellLvl == 3 || cellLvl == 4){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌" + ChatColor.GRAY + "▌▌▌▌");
                    } else if (cellLvl >= 5 && cellLvl < 7){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌" + ChatColor.GRAY + "▌▌▌");
                    } else if (cellLvl >= 7 && cellLvl < 11){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌" + ChatColor.GRAY + "▌▌");
                    } else if (cellLvl >= 11 && cellLvl <= 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌" + ChatColor.GRAY + "▌");
                    } else if (cellLvl == 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌▌");
                    }
                    fightLore.add("");
                    if (balance >= priceFight) {
                        fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                    } else {
                        fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                    }
                    if (Prison.getInstance().getDatabase().getCellLvl(player) < 13 && Prison.getInstance().getDatabase().getCellLvl(player) > 6) {
                        int lvl = Integer.parseInt(config.get(2));
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + " / " + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        if (Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())){
                            fightLore.add("");
                            fightLore.add(ChatColor.RED + "Стрелка доступна на следующие сутки!");
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
                        fight.setItemMeta(fightMeta);
                        gui.setItem(15, fight);
                        player.openInventory(gui);
            }
            //Четвертый ГУИ апа камеры
            else if (Math.floor(event.getRightClicked().getLocation().getX()) == locationFour.getX() && event.getRightClicked().getLocation().getY() == locationFour.getY() && Math.floor(event.getRightClicked().getLocation().getZ()) == locationFour.getZ()) {
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
                    int cellLvl = Prison.getInstance().getDatabase().getCellLvl(player);
                    ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getCellConfig().getConfigurationSection("Upgrade").getStringList(String.valueOf(cellLvl + 1));
                    double price = Double.parseDouble(config.get(0));
                    double priceFight = Double.parseDouble(config.get(1));
                    int balance = (int) Prison.getEconomy().getBalance(player);
                    fightLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                    fightLore.add("");
                    fightLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + priceFight);
                    fightLore.add("");
                    if (cellLvl < 3){
                        fightLore.add(ChatColor.GRAY + "Недоступно");
                    } else if (cellLvl == 3 || cellLvl == 4){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌" + ChatColor.GRAY + "▌▌▌▌");
                    } else if (cellLvl >= 5 && cellLvl < 7){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌" + ChatColor.GRAY + "▌▌▌");
                    } else if (cellLvl >= 7 && cellLvl < 11){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌" + ChatColor.GRAY + "▌▌");
                    } else if (cellLvl >= 11 && cellLvl <= 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌" + ChatColor.GRAY + "▌");
                    } else if (cellLvl == 13){
                        fightLore.add(ChatColor.GRAY + "Сложность: " + ChatColor.BOLD + "" + ChatColor.RED + "▌▌▌▌▌");
                    }
                    fightLore.add("");
                    if (balance >= priceFight) {
                        fightLore.add(ChatColor.GREEN + "Нажмите, для начала стрелки");
                    } else {
                        fightLore.add(ChatColor.RED + "Вам не хватает средств!");
                    }
                    if (Prison.getInstance().getDatabase().getCellLvl(player) >= 13) {
                        int lvl = Integer.parseInt(config.get(2));
                        upgradeLore.add(ChatColor.GRAY + "Уровень камеры: " + ChatColor.GREEN + cellLvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Денег: " + ChatColor.GREEN + balance + ChatColor.WHITE + "/" + ChatColor.GREEN + price);
                        upgradeLore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GREEN + Prison.getInstance().lvl.get(player) + ChatColor.WHITE + " / " + ChatColor.GREEN + lvl);
                        upgradeLore.add("");
                        upgradeLore.add(ChatColor.GRAY + "Улучшайте свою камеру для получения приемуществ");
                        upgradeLore.add(ChatColor.GRAY + "и прогресса на режиме!");
                        upgradeLore.add("");

                        if (Prison.getInstance().ezkiel_cd.containsKey(player.getUniqueId())){
                            fightLore.add("");
                            fightLore.add(ChatColor.RED + "Стрелка доступна на следующие сутки!");
                        }
                        if (balance >= price && Prison.getInstance().lvl.get(player) >= lvl) {
                            upgradeLore.add(ChatColor.GREEN + "Максимальный уровень камеры.");
                        } else {
                            upgradeLore.add(ChatColor.RED + "Условия улучшения не выполнены!");
                        }
                    } else {
                        upgradeLore.add("Вас не должно тут быть.");
                    }
                        upgradeMeta.setLore(upgradeLore);
                        upgrade.setItemMeta(upgradeMeta);
                        gui.setItem(11, upgrade);
                        fightMeta.setLore(fightLore);
                        fight.setItemMeta(fightMeta);
                        gui.setItem(15, fight);
                        player.openInventory(gui);
            }
        }
    }
}
