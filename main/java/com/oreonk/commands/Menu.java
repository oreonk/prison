package com.oreonk.commands;

import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Menu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 45, "Основное меню"); //22

            ItemStack player_info = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta player_info_meta = (SkullMeta) player_info.getItemMeta();
            player_info_meta.setDisplayName("Информация об игроке");
            player_info_meta.setOwningPlayer(player);
            ArrayList<String> player_info_lore = new ArrayList<>();
            player_info_lore.add("");
            player_info_lore.add(ChatColor.WHITE + "Игрок " + player.getName());
            player_info_lore.add("");
            if (Prison.getInstance().authorityStat.get(player) < 20){
                player_info_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GRAY + "▌▌▌▌▌");
            } else if (Prison.getInstance().authorityStat.get(player) >= 20 && Prison.getInstance().authorityStat.get(player) < 40){
                player_info_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
            } else if (Prison.getInstance().authorityStat.get(player) >= 40 && Prison.getInstance().authorityStat.get(player) < 60){
                player_info_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
            } else if (Prison.getInstance().authorityStat.get(player) >= 60 && Prison.getInstance().authorityStat.get(player) < 80){
                player_info_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
            } else if (Prison.getInstance().authorityStat.get(player) >= 80 && Prison.getInstance().authorityStat.get(player) < 100){
                player_info_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
            } else if (Prison.getInstance().authorityStat.get(player) >= 100){
                player_info_lore.add(ChatColor.WHITE + "Авторитет: " + ChatColor.GREEN + "▌▌▌▌▌");
            }
            if (Prison.getInstance().religion.get(player) < 20){
                player_info_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GRAY + "▌▌▌▌▌");
            } else if (Prison.getInstance().religion.get(player) >= 20 && Prison.getInstance().religion.get(player) < 40){
                player_info_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
            } else if (Prison.getInstance().religion.get(player) >= 40 && Prison.getInstance().religion.get(player) < 60){
                player_info_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
            } else if (Prison.getInstance().religion.get(player) >= 60 && Prison.getInstance().religion.get(player) < 80){
                player_info_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
            } else if (Prison.getInstance().religion.get(player) >= 80 && Prison.getInstance().religion.get(player) < 100){
                player_info_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
            } else if (Prison.getInstance().religion.get(player) >= 100){
                player_info_lore.add(ChatColor.WHITE + "Религия: " + ChatColor.GREEN + "▌▌▌▌▌");
            }
            if (Prison.getInstance().intellect.get(player) < 20){
                player_info_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GRAY + "▌▌▌▌▌");
            } else if (Prison.getInstance().intellect.get(player) >= 20 && Prison.getInstance().intellect.get(player) < 40){
                player_info_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌" + ChatColor.GRAY + "▌▌▌▌");
            } else if (Prison.getInstance().intellect.get(player) >= 40 && Prison.getInstance().intellect.get(player) < 60){
                player_info_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌" + ChatColor.GRAY + "▌▌▌");
            } else if (Prison.getInstance().intellect.get(player) >= 60 && Prison.getInstance().intellect.get(player) < 80){
                player_info_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌▌" + ChatColor.GRAY + "▌▌");
            } else if (Prison.getInstance().intellect.get(player) >= 80 && Prison.getInstance().intellect.get(player) < 100){
                player_info_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌▌▌" + ChatColor.GRAY + "▌");
            } else if (Prison.getInstance().intellect.get(player) >= 100){
                player_info_lore.add(ChatColor.WHITE + "Интеллект: " + ChatColor.GREEN + "▌▌▌▌▌");
            }
            player_info_lore.add("");
            player_info_lore.add(ChatColor.WHITE + "Уровень: " + Prison.getInstance().lvl.get(player));
            player_info_lore.add(ChatColor.WHITE + "Блоков: " + Prison.getInstance().getMinedBlocks(player));
            player_info_lore.add("");
            player_info_lore.add(ChatColor.GOLD + "Нажмите на этот пункт меню для");
            player_info_lore.add(ChatColor.GOLD + "более подробной информации.");
            player_info_meta.setLore(player_info_lore);
            player_info.setItemMeta(player_info_meta);
            gui.setItem(22, player_info);

            ItemStack mines = new ItemStack(Material.STONE_PICKAXE);
            ItemMeta mines_meta = mines.getItemMeta();
            mines_meta.setDisplayName("Шахты");
            ArrayList<String> mines_lore = new ArrayList<>();
            mines_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            mines_lore.add("");
            mines_lore.add(ChatColor.WHITE + "Для доступа к шахтам нужен уровень,");
            mines_lore.add(ChatColor.WHITE + "а для некоторых, так же, авторитет.");
            mines_lore.add("");
            mines_lore.add(ChatColor.GOLD + "Для доступа в меню шахт используйте");
            mines_lore.add(ChatColor.GOLD + "команду /mine.");
            mines_lore.add("");
            mines_lore.add(ChatColor.GRAY + "Телепортация возможна также, введя");
            mines_lore.add(ChatColor.GRAY + "после /mine уровень шахты (например,");
            mines_lore.add(ChatColor.GRAY + "/mine 2 телепортирует в земляную шахту).");
            mines_meta.setLore(mines_lore);
            mines.setItemMeta(mines_meta);
            gui.setItem(20, mines);

            ItemStack commands = new ItemStack(Material.WRITABLE_BOOK);
            ItemMeta commands_meta = commands.getItemMeta();
            commands_meta.setDisplayName("Основные комманды");
            ArrayList<String> commands_lore = new ArrayList<>();
            commands_lore.add("");
            commands_lore.add(ChatColor.WHITE + "/lvl или /level - повышение уровня.");
            commands_lore.add("");
            commands_lore.add(ChatColor.WHITE + "/up или /upgrade - улучшение");
            commands_lore.add(ChatColor.WHITE + "инструмента или брони в ваших");
            commands_lore.add(ChatColor.WHITE + "руках.");
            commands_lore.add("");
            commands_lore.add(ChatColor.WHITE + "/faction - выбор фракции (с 5");
            commands_lore.add(ChatColor.WHITE + "уровня). После выбора станет");
            commands_lore.add(ChatColor.WHITE + "доступна /base , для перемещения");
            commands_lore.add(ChatColor.WHITE + "на базу.");
            commands_lore.add("");
            commands_lore.add(ChatColor.WHITE + "/rubbish или /rubb - мусорка");
            commands_lore.add("");
            commands_lore.add(ChatColor.WHITE + "/hide on - спрятать игроков");
            commands_lore.add(ChatColor.WHITE + "/hide off - показать");
            commands_meta.setLore(commands_lore);
            commands.setItemMeta(commands_meta);
            gui.setItem(14,commands);

            ItemStack cell = new ItemStack(Material.STONE_BRICKS);
            ItemMeta cell_meta = cell.getItemMeta();
            cell_meta.setDisplayName("Личная камера");
            ArrayList<String> cell_lore = new ArrayList<>();
            cell_lore.add("");
            cell_lore.add(ChatColor.WHITE + "Личная камера доступна с 3");
            cell_lore.add(ChatColor.WHITE + "уровня. Улучшение камеры");
            cell_lore.add(ChatColor.WHITE + "открывает новый функционал,");
            cell_lore.add(ChatColor.WHITE + "такой как: коллекции, личная");
            cell_lore.add(ChatColor.WHITE + "ферма, подвал и уникальный босс.");
            cell_lore.add("");
            cell_lore.add(ChatColor.WHITE + "За коллекции вы получите различные");
            cell_lore.add(ChatColor.WHITE + "награды и увеличение множителя продажи;");
            cell_lore.add(ChatColor.WHITE + "за убийство босса вы получите денежную");
            cell_lore.add(ChatColor.WHITE + "награду и звёзды, а ферма пассивно");
            cell_lore.add(ChatColor.WHITE + "генерирует деньги.");
            cell_lore.add("");
            cell_lore.add(ChatColor.GOLD + "Для телепортации используйте команду");
            cell_lore.add(ChatColor.GOLD + "/cell , или нажмите на этот пункт меню.");
            cell_meta.setLore(cell_lore);
            cell.setItemMeta(cell_meta);
            gui.setItem(12,cell);

            ItemStack keys = new ItemStack(Material.CHEST);
            ItemMeta keys_meta = keys.getItemMeta();
            keys_meta.setDisplayName("Кейсы");
            ArrayList<String> keys_lore = new ArrayList<>();
            keys_lore.add("");
            keys_lore.add(ChatColor.WHITE + "Обычные ключи, получаемые при");
            keys_lore.add(ChatColor.WHITE + "добывании блоков можно открыть");
            keys_lore.add(ChatColor.WHITE + "в любом месте, нажав правой");
            keys_lore.add(ChatColor.WHITE + "кнопкой мыши по земле.");
            keys_lore.add("");
            keys_lore.add(ChatColor.WHITE + "Другие типы кейсов открываются");
            keys_lore.add(ChatColor.WHITE + "на спавне. Они могут выпасть с");
            keys_lore.add(ChatColor.WHITE + "небольшим шансом, при убийстве");
            keys_lore.add(ChatColor.WHITE + "боссов, ещё их можно купить за");
            keys_lore.add(ChatColor.WHITE + "звёзды и в донат магазине.");
            keys_meta.setLore(keys_lore);
            keys.setItemMeta(keys_meta);
            gui.setItem(30, keys);

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta close_meta = close.getItemMeta();
            close_meta.setDisplayName(ChatColor.RED + "Закрыть меню");
            close.setItemMeta(close_meta);
            gui.setItem(40, close);

            ItemStack enchants = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta enchants_meta = enchants.getItemMeta();
            enchants_meta.setDisplayName("Зачарования");
            ArrayList<String> enchants_lore = new ArrayList<>();
            enchants_lore.add("");
            enchants_lore.add(ChatColor.WHITE + "Зачарования можно получить с");
            enchants_lore.add(ChatColor.WHITE + "кейсов, для зачарования вещей");
            enchants_lore.add(ChatColor.WHITE + "нажмите правой кнопкой мыши с");
            enchants_lore.add(ChatColor.WHITE + "книгой в руке");
            enchants_lore.add("");
            enchants_lore.add(ChatColor.GOLD + "Для получения информации о");
            enchants_lore.add(ChatColor.GOLD + "зачарованиях, поговорите с");
            enchants_lore.add(ChatColor.GOLD + "чародеем - он находится на");
            enchants_lore.add(ChatColor.GOLD + "спавне, там же можно открыть");
            enchants_lore.add(ChatColor.GOLD + "кейсы.");
            enchants_meta.setLore(enchants_lore);
            enchants.setItemMeta(enchants_meta);
            gui.setItem(24, enchants);

            ItemStack battlepass = new ItemStack(Material.BOOKSHELF);
            ItemMeta battlepass_meta = battlepass.getItemMeta();
            battlepass_meta.setDisplayName("Боевой пропуск");
            ArrayList<String> battlepass_lore = new ArrayList<>();
            battlepass_lore.add("");
            battlepass_lore.add(ChatColor.WHITE + "Боевой пропуск делится на два");
            battlepass_lore.add(ChatColor.WHITE + "типа -" + ChatColor.GOLD + " обычный и премиум" + ChatColor.WHITE + ". Для");
            battlepass_lore.add(ChatColor.WHITE + "каждого из них выдаются разные");
            battlepass_lore.add(ChatColor.WHITE + "награды. Премиум пропуск можно");
            battlepass_lore.add(ChatColor.WHITE + "купить в донат магазине.");
            battlepass_lore.add("");
            battlepass_lore.add(ChatColor.WHITE + "Для увеличения уровня пропуска");
            battlepass_lore.add(ChatColor.WHITE + "нужно проходить различные задания,");
            battlepass_lore.add(ChatColor.WHITE + "которые разделяются на два вида:");
            battlepass_lore.add(ChatColor.WHITE + "недельные (открываются каждую");
            battlepass_lore.add(ChatColor.WHITE + "неделю, от начала вайпа); и");
            battlepass_lore.add(ChatColor.WHITE + "ежедневные, которые обновляются");
            battlepass_lore.add(ChatColor.WHITE + "каждый день.");
            battlepass_lore.add("");
            battlepass_lore.add(ChatColor.GOLD + "Для открытия меню используйте");
            battlepass_lore.add(ChatColor.GOLD + "/battlepass или нажмите на");
            battlepass_lore.add(ChatColor.GOLD + "этот пункт меню.");
            battlepass_meta.setLore(battlepass_lore);
            battlepass.setItemMeta(battlepass_meta);
            gui.setItem(32, battlepass);

            ItemStack donate = new ItemStack(Material.EMERALD);
            ItemMeta donate_meta = donate.getItemMeta();
            donate_meta.setDisplayName("Донат");
            ArrayList<String> donate_lore = new ArrayList<>();
            donate_lore.add("");
            donate_lore.add(ChatColor.WHITE + "Команда отвечающая за донат -");
            donate_lore.add(ChatColor.GOLD + "/donate" + ChatColor.WHITE + " или " + ChatColor.GOLD +  "/shop" + ChatColor.WHITE + ".");
            donate_lore.add("");
            donate_lore.add(ChatColor.GOLD + "Для доступа в меню, также можно");
            donate_lore.add(ChatColor.GOLD + "нажать на этот пункт меню.");
            donate_meta.setLore(donate_lore);
            donate.setItemMeta(donate_meta);
            gui.setItem(4,donate);

            player.openInventory(gui);
            return true;
        } else {
            return false;
        }
    }
}
