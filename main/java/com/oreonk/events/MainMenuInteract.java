package com.oreonk.events;

import com.oreonk.Prison;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class MainMenuInteract implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (event.getCurrentItem() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getView().getTitle().equalsIgnoreCase("Основное меню") || event.getView().getTitle().equalsIgnoreCase("Информация об игроке")) {
                event.setCancelled(true);
            }
            if (event.getView().getTitle().equalsIgnoreCase("Основное меню")){
                if (event.getCurrentItem().hasItemMeta()) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Информация об игроке")) {
                        createSubGUI(player);
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Закрыть меню")){
                        player.closeInventory();
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Личная камера")){
                        player.performCommand("cell");
                        player.closeInventory();
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Боевой пропуск")){
                        player.closeInventory();
                        player.performCommand("battlepass");
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Донат")){
                        player.closeInventory();
                        player.performCommand("donate");
                    }
                }
            } else if (event.getView().getTitle().equalsIgnoreCase("Информация об игроке")){
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Закрыть меню")){
                        player.closeInventory();
                }
            }
        }

    }
    private void createSubGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 27, "Информация об игроке");
        ItemStack authority = new ItemStack(Material.IRON_SWORD);
        ItemMeta authority_meta = authority.getItemMeta();
        authority_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> authority_lore = new ArrayList<>();
        authority_meta.setDisplayName("Авторитет");
        authority_lore.add("");
        authority_lore.add(ChatColor.WHITE + "Авторитет необходим для доступа к");
        authority_lore.add(ChatColor.WHITE + "большинству шахт. Добывается он");
        authority_lore.add(ChatColor.WHITE + "путём убийства охранников и других");
        authority_lore.add(ChatColor.WHITE + "мобов на шахтах.");
        authority_lore.add("");
        if (Prison.getInstance().authorityStat.get(player) < 20){
            authority_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GRAY + "▌▌▌▌▌");
        } else if (Prison.getInstance().authorityStat.get(player) >= 20 && Prison.getInstance().authorityStat.get(player) < 40){
            authority_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
        } else if (Prison.getInstance().authorityStat.get(player) >= 40 && Prison.getInstance().authorityStat.get(player) < 60){
            authority_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
        } else if (Prison.getInstance().authorityStat.get(player) >= 60 && Prison.getInstance().authorityStat.get(player) < 80){
            authority_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
        } else if (Prison.getInstance().authorityStat.get(player) >= 80 && Prison.getInstance().authorityStat.get(player) < 100){
            authority_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
        } else if (Prison.getInstance().authorityStat.get(player) >= 100){
            authority_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌▌▌▌");
        }
        authority_lore.add(ChatColor.WHITE + "В цифрах: " + Prison.getInstance().authorityStat.get(player) + " (ячейка загорается каждые 20 очков)");
        authority_meta.setLore(authority_lore);
        authority.setItemMeta(authority_meta);
        gui.setItem(13, authority);

        ItemStack religion = new ItemStack(Material.BEACON);
        ItemMeta religion_meta = religion.getItemMeta();
        religion_meta.setDisplayName("Религия");
        ArrayList<String> religion_lore = new ArrayList<>();
        religion_lore.add("");
        religion_lore.add(ChatColor.WHITE + "Религия даёт различные бонусы при");
        religion_lore.add(ChatColor.WHITE + "копании шахты. Бонус улучшается");
        religion_lore.add(ChatColor.WHITE + "каждые 20 очков. Получить религию");
        religion_lore.add(ChatColor.WHITE + "можно путём пожертвований в церковь");
        religion_lore.add(ChatColor.WHITE + "на спавне.");
        religion_lore.add("");
        if (Prison.getInstance().religion.get(player) < 20){
            religion_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GRAY + "▌▌▌▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 20 && Prison.getInstance().religion.get(player) < 40){
            religion_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 40 && Prison.getInstance().religion.get(player) < 60){
            religion_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 60 && Prison.getInstance().religion.get(player) < 80){
            religion_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
        } else if (Prison.getInstance().religion.get(player) >= 80 && Prison.getInstance().religion.get(player) < 100){
            religion_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
        } else if (Prison.getInstance().religion.get(player) >= 100){
            religion_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌▌▌");
        }
        religion_lore.add(ChatColor.WHITE + "В цифрах: " + Prison.getInstance().religion.get(player) + " (ячейка загорается каждые 20 очков)");
        religion_meta.setLore(religion_lore);
        religion.setItemMeta(religion_meta);
        gui.setItem(12, religion);

        ItemStack intellect = new ItemStack(Material.BOOK);
        ItemMeta intellect_meta = intellect.getItemMeta();
        intellect_meta.setDisplayName("Интеллект");
        ArrayList<String> intellect_lore = new ArrayList<>();
        intellect_lore.add("");
        intellect_lore.add(ChatColor.WHITE + "Интеллект даёт небольшой бустер");
        intellect_lore.add(ChatColor.WHITE + "денег за каждые 20 очков. Для");
        intellect_lore.add(ChatColor.WHITE + "получения интеллекта нужно");
        intellect_lore.add(ChatColor.WHITE + "собирать коллекции в камере.");
        intellect_lore.add("");
        if (Prison.getInstance().intellect.get(player) < 20){
            intellect_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GRAY + "▌▌▌▌▌");
        } else if (Prison.getInstance().intellect.get(player) >= 20 && Prison.getInstance().intellect.get(player) < 40){
            intellect_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
        } else if (Prison.getInstance().intellect.get(player) >= 40 && Prison.getInstance().intellect.get(player) < 60){
            intellect_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
        } else if (Prison.getInstance().intellect.get(player) >= 60 && Prison.getInstance().intellect.get(player) < 80){
            intellect_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
        } else if (Prison.getInstance().intellect.get(player) >= 80 && Prison.getInstance().intellect.get(player) < 100){
            intellect_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
        } else if (Prison.getInstance().intellect.get(player) >= 100){
            intellect_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌▌▌▌");
        }
        intellect_lore.add(ChatColor.WHITE + "В цифрах: " + Prison.getInstance().intellect.get(player) + " (ячейка загорается каждые 20 очков)");
        if (Prison.getInstance().intellect.get(player) < 20) {
            intellect_lore.add(ChatColor.WHITE + "Прибавка множителя: 0");
        } else if (Prison.getInstance().intellect.get(player) >= 20 && Prison.getInstance().intellect.get(player) < 40) {
            intellect_lore.add(ChatColor.WHITE + "Прибавка множителя: 0.1");
        } else if (Prison.getInstance().intellect.get(player) >= 40 && Prison.getInstance().intellect.get(player) < 60){
            intellect_lore.add(ChatColor.WHITE + "Прибавка множителя: 0.15");
        } else if (Prison.getInstance().intellect.get(player) >= 60 && Prison.getInstance().intellect.get(player) < 80){
            intellect_lore.add(ChatColor.WHITE + "Прибавка множителя: 0.2");
        } else if (Prison.getInstance().intellect.get(player) >= 80 && Prison.getInstance().intellect.get(player) < 100){
            intellect_lore.add(ChatColor.WHITE + "Прибавка множителя: 0.25");
        } else if (Prison.getInstance().intellect.get(player) >= 100) {
            intellect_lore.add(ChatColor.WHITE + "Прибавка множителя: 0.3");
        }
        intellect_meta.setLore(intellect_lore);
        intellect.setItemMeta(intellect_meta);
        gui.setItem(14, intellect);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName("Игрок " + player.getName());
        head.setItemMeta(skullMeta);
        gui.setItem(4, head);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.RED + "Закрыть меню");
        close.setItemMeta(close_meta);
        gui.setItem(22, close);

        player.openInventory(gui);
    }
}
