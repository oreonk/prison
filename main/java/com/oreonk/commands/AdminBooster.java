package com.oreonk.commands;

import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class AdminBooster implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("adminbooster")) {
                Player player = (Player) sender;
                Prison.getInstance().boosterBlocks.replace(player.getUniqueId(), 45);
                new BukkitRunnable() {
                    public void run() {
                        int time = 0;
                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                            time = entry.getValue();
                        }
                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                            entry.setValue(time - 1);
                        }
                        if (time - 1 == 0) {
                            Prison.getInstance().boosterBlocks.clear();
                            Prison.getInstance().getDatabase().clearBlockBst();
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 0, 1200);
                int displayTime = 0;
                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                    displayTime = entry.getValue();
                }
                BossBar bossBar = Bukkit.createBossBar("Активен администаторский бустер блоков. Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
                bossBar.setVisible(true);
                Bukkit.getServer().getOnlinePlayers().forEach(u -> bossBar.addPlayer(u.getPlayer()));
                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                    Prison.getInstance().getDatabase().insertBlockBst(entry.getValue(), entry.getKey());
                }
                new BukkitRunnable() {
                    public void run() {
                        int time = 0;
                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                            time = entry.getValue();
                            bossBar.setTitle("Активен администаторский бустер блоков. Осталось " + time + " минут");
                        }
                        if (time-1 == 0){
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 1200, 1200);
            }
            return true;
        }
        return true;
    }
}
