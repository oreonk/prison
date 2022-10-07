package com.oreonk.events;

import com.oreonk.Msg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class UniqueDrops implements Listener {
    @EventHandler
    public void blockBreak(BlockBreakEvent event){
        //Выборка блоков и шанс
        Random r = new Random();
        int percent = r.nextInt(100000);
        if (event.getBlock().getType().equals(Material.COARSE_DIRT) && percent < 100){
            dropMushroom(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.RED_SAND) && percent < 80){
            dropQuartz(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.STONE) && percent < 30){
            dropDust(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.OAK_WOOD) && percent < 50){
            dropSawdust(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.COAL_BLOCK) && percent < 1000){
            dropCoal(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.HAY_BLOCK) && percent < 40){
            dropBarley(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.CLAY) && percent < 30){
            dropStalactite(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.MOSSY_COBBLESTONE) && percent < 30){
            dropMush(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.RED_SANDSTONE) && percent < 20){
            dropBedrock(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.DIAMOND_ORE) && percent < 70){
            dropDiamondDust(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.ACACIA_LOG) && percent < 20){
            dropStick(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.SOUL_SAND) && percent < 20){
            dropSoul(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.EMERALD_ORE) && percent < 60){
            dropEye(event.getPlayer());
        } else if (event.getBlock().getType().equals(Material.PURPUR_BLOCK) && percent < 15){
            dropTungsten(event.getPlayer());
        }
    }
    //Дропы самих предметов
    private void dropMushroom(Player player){
        ItemStack mush = new ItemStack(Material.BROWN_MUSHROOM);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&6Гриб");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный гриб!");
    }
    private void dropQuartz(Player player){
        ItemStack mush = new ItemStack(Material.GHAST_TEAR);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&9Кусок кварца");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный кварц!");
    }
    private void dropDust(Player player){
        ItemStack mush = new ItemStack(Material.GUNPOWDER);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&7Каменная пыль");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционную каменную пыль!");
    }
    private void dropSawdust(Player player){
        ItemStack mush = new ItemStack(Material.PUMPKIN_SEEDS);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&eДубовые опилки");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционные опилки!");
    }
    private void dropCoal(Player player){
        ItemStack mush = new ItemStack(Material.COAL);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&8Карбонат");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный карбонат!");
    }
    private void dropBarley(Player player){
        ItemStack mush = new ItemStack(Material.WHEAT);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&8Ячмень");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный ячмень!");
    }
    private void dropStalactite(Player player){
        ItemStack mush = new ItemStack(Material.IRON_NUGGET);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&dСталактит");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный сталактит!");
    }
    private void dropMush(Player player){
        ItemStack mush = new ItemStack(Material.GREEN_DYE);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&aМох");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный мох!");
    }
    private void dropBedrock(Player player){
        ItemStack mush = new ItemStack(Material.BRICK);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&cЗатвердевшая порода");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционную затвердевшую породу!");
    }
    private void dropDiamondDust(Player player){
        ItemStack mush = new ItemStack(Material.CYAN_DYE);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&bАлмазная крошка");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционную алмазную крошку!");
    }
    private void dropStick(Player player){
        ItemStack mush = new ItemStack(Material.ACACIA_SAPLING);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&eВеточка");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционную ветку!");
    }
    private void dropSoul(Player player){
        ItemStack mush = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&4Душа грешника");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли душу грешника!");
    }
    private void dropEye(Player player){
        ItemStack mush = new ItemStack(Material.ENDER_EYE);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&dВсевидящее око");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли всевидящее око!");
    }
    private void dropTungsten(Player player){
        ItemStack mush = new ItemStack(Material.SHULKER_SHELL);
        ItemMeta mush_meta = mush.getItemMeta();
        mush_meta.setDisplayName("&5Осколок вольфрама");
        mush.setItemMeta(mush_meta);
        if (player.getCanPickupItems()) {
            player.getInventory().addItem(mush);
        } else {
            Bukkit.getWorld(player.getUniqueId()).dropItemNaturally(player.getLocation(), mush);
        }
        Msg.send(player, ChatColor.GREEN + "Вы нашли коллекционный вольфрам!");
    }
    private void bossDropMiner(Entity entity){
        Location location = entity.getLocation();
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Сердце");
        Bukkit.getWorld("world").dropItemNaturally(location, item);
    }
    private void bossDropFarmer(Entity entity){
        Location location = entity.getLocation();
        ItemStack item = new ItemStack(Material.STONE_HOE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Тяпка");
        Bukkit.getWorld("world").dropItemNaturally(location, item);
    }
    private void bossDropWitch(Entity entity){
        Location location = entity.getLocation();
        ItemStack item = new ItemStack(Material.SPLASH_POTION);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "Эликсир");
        Bukkit.getWorld("world").dropItemNaturally(location, item);
    }
    private void bossDropForest(Entity entity){
        Location location = entity.getLocation();
        ItemStack item = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.DARK_RED + "Ра");
        Bukkit.getWorld("world").dropItemNaturally(location, item);
    }
    private void bossDropSatan(Entity entity){
        Location location = entity.getLocation();
        ItemStack item = new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Душа");
        Bukkit.getWorld("world").dropItemNaturally(location, item);
    }
    //Отмена ПКМ
    @EventHandler
    public void interactionCancel(PlayerInteractEvent event){
        if ((event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Веточка") || event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Веточка") || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Всевидящее око") || event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Всевидящее око") || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Сердце") || event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Сердце") || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Тяпка") || event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Тяпка") || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Эликсир") || event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Эликсир")) && (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR))){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void deathDrop(EntityDeathEvent event){
        if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains("Шахтёр")) {
            Random r = new Random();
            int percent = r.nextInt(100);
            if (percent < 90) {
                bossDropMiner(event.getEntity());
            }
        } else if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains("фермер")) {
            Random r = new Random();
            int percent = r.nextInt(100);
            if (percent < 90) {
                bossDropFarmer(event.getEntity());
            }
        } else if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains("Ведьма")) {
            Random r = new Random();
            int percent = r.nextInt(100);
            if (percent < 70) {
                bossDropWitch(event.getEntity());
            }
        } else if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains("Хранитель леса")) {
            Random r = new Random();
            int percent = r.nextInt(100);
            if (percent < 70) {
                bossDropForest(event.getEntity());
            }
        } else if (event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains("сатаны")) {
            Random r = new Random();
            int percent = r.nextInt(100);
            if (percent < 50) {
                bossDropSatan(event.getEntity());
            }
        }
    }
}
