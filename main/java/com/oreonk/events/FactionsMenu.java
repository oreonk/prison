package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.List;

public class FactionsMenu implements Listener {
    @EventHandler
    public void FactionsMenu (InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().contains("Выбор фракции") || event.getView().getTitle().contains("Выбор белых") || event.getView().getTitle().contains("Выбор черных") || event.getView().getTitle().contains("Выбор азиатов")){
            event.setCancelled(true);
        }
        if (event.getClickedInventory()!=null&&event.getCurrentItem()!=null) {
            if (event.getView().getTitle().contains("Выбор фракции")){
                if (event.getCurrentItem().getType().equals(Material.WHITE_WOOL)){
                    Inventory gui = Bukkit.createInventory(player, 27, "Выбор белых");
                    ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
                    ItemStack deny = new ItemStack(Material.RED_WOOL);

                    ItemMeta confirm_meta = confirm.getItemMeta();
                    ItemMeta deny_meta = deny.getItemMeta();
                    confirm_meta.setDisplayName(ChatColor.GREEN + "Подтвердить");
                    deny_meta.setDisplayName(ChatColor.RED + "Отмена");
                    confirm.setItemMeta(confirm_meta);
                    deny.setItemMeta(deny_meta);

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack air = new ItemStack(Material.AIR);
                    gui.setItem(10, air);
                    gui.setItem(11, confirm);
                    gui.setItem(12, air);
                    gui.setItem(13, air);
                    gui.setItem(14, air);
                    gui.setItem(15, deny);
                    gui.setItem(16, air);
                    player.openInventory(gui);
                } else if(event.getCurrentItem().getType().equals(Material.COAL_BLOCK)){
                    Inventory gui = Bukkit.createInventory(player, 27, "Выбор черных");
                    ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
                    ItemStack deny = new ItemStack(Material.RED_WOOL);

                    ItemMeta confirm_meta = confirm.getItemMeta();
                    ItemMeta deny_meta = deny.getItemMeta();
                    confirm_meta.setDisplayName(ChatColor.GREEN + "Подтвердить");
                    deny_meta.setDisplayName(ChatColor.RED + "Отмена");
                    confirm.setItemMeta(confirm_meta);
                    deny.setItemMeta(deny_meta);

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack air = new ItemStack(Material.AIR);
                    gui.setItem(10, air);
                    gui.setItem(11, confirm);
                    gui.setItem(12, air);
                    gui.setItem(13, air);
                    gui.setItem(14, air);
                    gui.setItem(15, deny);
                    gui.setItem(16, air);
                    player.openInventory(gui);
                } else if(event.getCurrentItem().getType().equals(Material.GOLD_BLOCK)){
                    Inventory gui = Bukkit.createInventory(player, 27, "Выбор азиатов");
                    ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
                    ItemStack deny = new ItemStack(Material.RED_WOOL);

                    ItemMeta confirm_meta = confirm.getItemMeta();
                    ItemMeta deny_meta = deny.getItemMeta();
                    confirm_meta.setDisplayName(ChatColor.GREEN + "Подтвердить");
                    deny_meta.setDisplayName(ChatColor.RED + "Отмена");
                    confirm.setItemMeta(confirm_meta);
                    deny.setItemMeta(deny_meta);

                    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta filler_meta = filler.getItemMeta();
                    filler_meta.setDisplayName(" ");
                    filler.setItemMeta(filler_meta);
                    for (int slot = 0; slot < gui.getSize(); slot++)
                        gui.setItem(slot, filler);
                    ItemStack air = new ItemStack(Material.AIR);
                    gui.setItem(10, air);
                    gui.setItem(11, confirm);
                    gui.setItem(12, air);
                    gui.setItem(13, air);
                    gui.setItem(14, air);
                    gui.setItem(15, deny);
                    gui.setItem(16, air);
                    player.openInventory(gui);
                }
            }
            if (event.getView().getTitle().contains("Выбор фракции") || event.getView().getTitle().contains("Выбор белых") || event.getView().getTitle().contains("Выбор черных") || event.getView().getTitle().contains("Выбор азиатов")){
                if (event.getCurrentItem().getType().equals(Material.RED_WOOL)){
                    player.closeInventory();
                }
            }
            if (event.getView().getTitle().contains("Выбор белых") && event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                Prison.getInstance().getDatabase().setFaction(player, "white");
                Prison.getInstance().faction.put(player, "white");
                Msg.send(player, "Вы вступили в фракцию белых!");
                Permission perms = Prison.getPermissions();
                perms.playerAdd(player, "prison.white");
                perms.playerAdd(player, "chatty.chat.factionwhite");
                player.closeInventory();
                //Ачивка фракции
                if (!Prison.getInstance().FRACTION_ONE.contains(player.getUniqueId().toString())){
                    if (Prison.getInstance().getDatabase().getOverallAchievementLvl(player) >= 3) {
                        Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Да кто ты такой? " + ChatColor.GREEN + "проверьте его в /achievements");
                        List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("FRACTION_ONE");
                        list.add(player.getUniqueId().toString());
                        try {
                            Prison.getInstance().getAchievementsConfig().set("FRACTION_ONE", list);
                            Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            Prison.getInstance().FRACTION_ONE.add(player.getUniqueId().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (event.getView().getTitle().contains("Выбор черных") && event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                Prison.getInstance().getDatabase().setFaction(player, "black");
                Prison.getInstance().faction.put(player, "black");
                Msg.send(player, "Вы вступили в фракцию черных!");
                Permission perms = Prison.getPermissions();
                perms.playerAdd(player, "prison.black");
                perms.playerAdd(player, "chatty.chat.factionblack");
                player.closeInventory();
                //Ачивка фракции
                if (!Prison.getInstance().FRACTION_ONE.contains(player.getUniqueId().toString())){
                    if (Prison.getInstance().getDatabase().getOverallAchievementLvl(player) >= 3) {
                        Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Да кто ты такой? " + ChatColor.GREEN + "проверьте его в /achievements");
                        List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("FRACTION_ONE");
                        list.add(player.getUniqueId().toString());
                        try {
                            Prison.getInstance().getAchievementsConfig().set("FRACTION_ONE", list);
                            Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            Prison.getInstance().FRACTION_ONE.add(player.getUniqueId().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (event.getView().getTitle().contains("Выбор азиатов") && event.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                Prison.getInstance().getDatabase().setFaction(player, "asian");
                Prison.getInstance().faction.put(player, "asian");
                Msg.send(player, "Вы вступили в фракцию азиатов!");
                Permission perms = Prison.getPermissions();
                perms.playerAdd(player, "prison.asian");
                perms.playerAdd(player, "chatty.chat.factionasian");
                player.closeInventory();
                //Ачивка фракции
                if (!Prison.getInstance().FRACTION_ONE.contains(player.getUniqueId().toString())){
                    if (Prison.getInstance().getDatabase().getOverallAchievementLvl(player) >= 3) {
                        Msg.send(player, ChatColor.GREEN + "Вы получили достижение " + ChatColor.GOLD + "Да кто ты такой? " + ChatColor.GREEN + "проверьте его в /achievements");
                        List<String> list = Prison.getInstance().getAchievementsConfig().getStringList("FRACTION_ONE");
                        list.add(player.getUniqueId().toString());
                        try {
                            Prison.getInstance().getAchievementsConfig().set("FRACTION_ONE", list);
                            Prison.getInstance().getAchievementsConfig().save(Prison.getInstance().getAchievementsConfigFile());
                            Prison.getInstance().FRACTION_ONE.add(player.getUniqueId().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
