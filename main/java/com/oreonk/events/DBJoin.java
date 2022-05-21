package com.oreonk.events;



import com.oreonk.TestPlug;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;

public class DBJoin implements Listener {
    TestPlug plugin = TestPlug.getPlugin(TestPlug.class);
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        try {
            plugin.mysqlSetup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Player player = event.getPlayer();
        createPlayer(player.getUniqueId(), player);
        try {
            plugin.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @EventHandler
        public void update(PlayerJoinEvent event){
        try {
            plugin.mysqlSetup();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Double multiplier = 0.0;
        try{
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            multiplier = results.getDouble("MULTIPLIER");
        } catch (SQLException e){
            e.printStackTrace();
        }
        if (player.hasPermission("rank.shulker")){
            update_Multiplier(player.getUniqueId(), 1.5);
        } else if (player.hasPermission("rank.magma")) {
            update_Multiplier(player.getUniqueId(), 1.4);
        } else if (player.hasPermission("rank.emerald")) {
            update_Multiplier(player.getUniqueId(), 1.3);
        } else if  (player.hasPermission("rank.diamond")) {
            update_Multiplier(player.getUniqueId(), 1.2);
        } else if (player.hasPermission("rank.gold")) {
            update_Multiplier(player.getUniqueId(), 1.1);
        }
        if (player.hasPermission("prison.privateboost")){
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            for (double i = 0.1; i <= 2.0; i = i + 0.1){
                String boostNumber = decimalFormat.format(i);
                if (player.hasPermission("prison.privateboost." + boostNumber)){
                    if (player.hasPermission("rank.shulker")){
                        update_Multiplier(player.getUniqueId(), (double) Math.round((i+1.5)*10)/10);
                    } else if (player.hasPermission("rank.magma")) {
                        update_Multiplier(player.getUniqueId(), (double) Math.round((i+1.4)*10)/10);
                    } else if (player.hasPermission("rank.emerald")) {
                        update_Multiplier(player.getUniqueId(), (double) Math.round((i+1.3)*10)/10);
                    } else if  (player.hasPermission("rank.diamond")) {
                        update_Multiplier(player.getUniqueId(), (double) Math.round((i+1.2)*10)/10);
                    } else if (player.hasPermission("rank.gold")) {
                        update_Multiplier(player.getUniqueId(), (double) Math.round((i+1.1)*10)/10);
                    } else {
                        update_Multiplier(player.getUniqueId(), (double) Math.round((i+1.0)*10)/10);
                    }
                    break;
                }
            }
        }
        try {
            plugin.getConnection().close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean playerExists(UUID uuid) {
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            if (results.next()){
                //Если найдено подключение
                return true;
            }
            System.out.println(ChatColor.RED + "Игрока нет в БД!");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void createPlayer(final UUID uuid, Player player){
        try{
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if(!playerExists(uuid)){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table + " (UUID,PLAYER,LVL,MULTIPLIER) VALUE (?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, player.getName());
                insert.setInt(3, 1);
                insert.setDouble(4, 1.0);
                insert.executeUpdate();
                insert.close();
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void update_Multiplier(UUID uuid, double multiplier){
        try{
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table + " SET MULTIPLIER=? WHERE UUID=?");
            statement.setDouble(1, multiplier);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
