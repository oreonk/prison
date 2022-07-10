package com.oreonk;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseCommand {
    String table = TestPlug.getPlugin(TestPlug.class).getConfig().getString("DB.table"); //Имя таблицы
    private TestPlug plugin;
    public DatabaseCommand(TestPlug plugin){
        this.plugin = plugin;
    }
    public void createTable(){
        PreparedStatement ps;
        try{
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS prison "
                    +"(NAME VARCHAR(100),UUID VARCHAR(100),LVL INT(4),MULTIPLIER VARCHAR(100),BOOST VARCHAR(100),PRIMARY KEY(NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createPlayer(Player player, String multiplier){
        try{
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (!playerExists(uuid)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO " + table + " (NAME,UUID,LVL,BOOST,MULTIPLIER) VALUES (?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, player.getUniqueId().toString());
                ps2.setInt(3, 1);
                ps2.setString(4, "0.0");
                ps2.setString(5, multiplier);
                ps2.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Double getMultiplier(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Double.parseDouble(rs.getString("MULTIPLIER"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 1.0;
    }
    public Double getBoost(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Double.parseDouble(rs.getString("BOOST"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0.0;
    }
    public int getLVL(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("LVL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateLVL(Player player, int level) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET LVL=? WHERE UUID=?");
            ps.setInt(1, level);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean playerExists(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
        public void updateMultiplier(UUID uuid, String multiplier){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if(playerExists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("REPLACE INTO " + table + " (MULTIPLIER) VALUES(?)");
                ps2.setString(1, multiplier);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateBoost(Player player, String boost) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET LVL=? WHERE UUID=?");
            ps.setString(1, boost);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void nuke() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DROP TABLE " + table);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
