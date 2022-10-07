package com.oreonk.commands;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class Test implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
    if (sender instanceof Player) {
        Player player = (Player) sender;
        Msg.send(player, "Топ 1 лвл " + Prison.getInstance().getDatabase().getLvlTopOne() + "ник " + Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopOneName())).getName());
        Msg.send(player, "Топ 1 блоки " + Prison.getInstance().getDatabase().getBlockTopOne() + "ник " + Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopOneName())).getName());
        return true;
        }
        return true;
    }
}
