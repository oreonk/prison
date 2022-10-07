package com.oreonk;

import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class DatabaseCommand {
    String table = Prison.getPlugin(Prison.class).getConfig().getString("DB.table"); //Имя таблицы
    private Prison plugin;
    public DatabaseCommand(Prison plugin){
        this.plugin = plugin;
    }
    public void createTable(){
        PreparedStatement ps;
        try{
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS prison "
                    +"(NAME VARCHAR(100),UUID VARCHAR(100),LVL INT(4),MULTIPLIER VARCHAR(100),BOOST VARCHAR(100),FACTION VARCHAR(100),CELL_LVL INT(4),CELL_CASH VARCHAR(100),INTELLECT_STAT INT(4),AUTHORITY_STAT INT(4),RELIGION_STAT INT(4),PRIMARY KEY(NAME))");
            ps.executeUpdate();
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS prison_bst "
                    +"(UUID_BLOCK VARCHAR(100),UUID_MONEY VARCHAR(100),TIME_BLOCK INT(20),TIME_MONEY INT(20), NUMBER INT(1), PRIMARY KEY(NUMBER))");
            ps.executeUpdate();
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS prison_block_top "
                    +"(UUID VARCHAR(100), BLOCKS INT(100), PRIMARY KEY(UUID))");
            ps.executeUpdate();
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS prison_lvl_top "
                    +"(UUID VARCHAR(100), LVL INT(10), DATE DATE, PRIMARY KEY(UUID))");
            ps.executeUpdate();
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS prison_collection "
                    // MUSHROOM - Гриб, QUARTZ - Кусок кварца, STONE_DUST - каменная пыль, OAK_SAWDUST - Дубовые опилки, COAL - Карбонат, BARLEY - Ячмень, STALACTITE - Сталактит, MUSH - Мох, BEDROCK - Затвердевшая порода, DIAMOND_DUST - алмазная крошка, STICK - Веточка, SOUL - Душа грешника, EYE - Всевидящее око, TUNGSTEN - Осколок вользфрама
                    +"(UUID VARCHAR(100), MUSHROOM INT(2), QUARTZ INT(2), STONE_DUST INT(2), OAK_SAWDUST INT(2), COAL INT(2), BARLEY INT(2), STALACTITE INT(2), MUSH INT(2), BEDROCK INT(2), DIAMOND_DUST INT(2), STICK INT(2), SOUL INT(2), EYE INT(2), TUNGSTEN INT(2), PRIMARY KEY(UUID))");
            ps.executeUpdate();
            if (!idExists(1)) {
                ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO prison_bst (NUMBER,UUID_BLOCK,UUID_MONEY,TIME_BLOCK,TIME_MONEY) VALUES (?,?,?,?,?)");
                ps.setInt(1,1);
                ps.setString(2,"0");
                ps.setString(3,"0");
                ps.setInt(4,0);
                ps.setInt(5,0);
                ps.executeUpdate();
            }
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
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO " + table + " (NAME,UUID,LVL,BOOST,MULTIPLIER,FACTION,CELL_LVL,CELL_CASH,INTELLECT_STAT,AUTHORITY_STAT,RELIGION_STAT) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, player.getUniqueId().toString());
                ps2.setInt(3, 1);
                ps2.setString(4, "0.0");
                ps2.setString(5, multiplier);
                ps2.setString(6, "0");
                //ps2.setInt(7, 1);
                //ps2.setString(8, 0);
                //ps2.setInt(9, 0);
                //ps2.setInt(10, 0);
                //ps2.setInt(11, 0);
                ps2.executeUpdate();
            } if (!playerCollectionExists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO prison_collection (UUID,MUSHROOM,QUARTZ,STONE_DUST,OAK_SAWDUST,COAL,BARLEY,STALACTITE,MUSH,BEDROCK,DIAMOND_DUST,STICK,SOUL,EYE,TUNGSTEN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps2.setString(1, player.getUniqueId().toString());
                ps2.setInt(2, 0);
                ps2.setInt(3, 0);
                ps2.setInt(4, 0);
                ps2.setInt(5, 0);
                ps2.setInt(6, 0);
                ps2.setInt(7, 0);
                ps2.setInt(8, 0);
                ps2.setInt(9, 0);
                ps2.setInt(10, 0);
                ps2.setInt(11, 0);
                ps2.setInt(12, 0);
                ps2.setInt(13, 0);
                ps2.setInt(14, 0);
                ps2.setInt(15, 0);
                ps2.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getUUID(String name) {
        if (playerNameExists(name)) {
            try {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE NAME=?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("UUID");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "nope";
        }
        return "nope";
    }
    public boolean playerNameExists(String name){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE NAME=?");
            ps.setString(1, name);
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
    public Integer getCellLvl(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("CELL_LVL");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 1;
    }
     public Integer getAuthorityStat(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("AUTHORITY_STAT");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addAuthorityStat(Player player, int add) {
        try {
            UUID uuid = player.getUniqueId();
            int stat = getAuthorityStat(player);
            int finalStat = stat + add;
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET AUTHORITY_STAT=? WHERE UUID=?");
            ps.setInt(1, finalStat);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getReligionStat(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("RELIGION_STAT");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addReligionStat(Player player, int add) {
        try {
            UUID uuid = player.getUniqueId();
            int stat = getReligionStat(player);
            int finalStat = stat + add;
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET RELIGION_STAT=? WHERE UUID=?");
            ps.setInt(1, finalStat);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getIntellectStat(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("INTELLECT_STAT");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addIntellectStat(Player player, int add) {
        try {
            UUID uuid = player.getUniqueId();
            int stat = getIntellectStat(player);
            int finalStat = stat + add;
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET INTELLECT_STAT=? WHERE UUID=?");
            ps.setInt(1, finalStat);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateCellLvl(Player player, int level) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET CELL_LVL=? WHERE UUID=?");
            ps.setInt(1, level);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getCollectionLvl(Player player, String columnCapsName){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_collection WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(columnCapsName);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addCollectionLvl(Player player, String columnCapsName) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET "+ columnCapsName + "=? WHERE UUID=?");
            int lvl = getCollectionLvl(player, columnCapsName) + 1;
            ps.setInt(1, lvl);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Double getCellCash(Player player){
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Double.parseDouble(rs.getString("CELL_CASH"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0.0;
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
    public boolean playerCollectionExists(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_collection WHERE UUID=?");
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
    public boolean idExists(Integer id){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_bst WHERE NUMBER=?");
            ps.setInt(1, id);
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
    public boolean topExists(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top WHERE UUID=?");
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
    public boolean topLvlExists(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top WHERE UUID=?");
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
    public void setFaction(Player player, String faction) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE " + table + " SET FACTION=? WHERE UUID=?");
            ps.setString(1, faction);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getFaction(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("FACTION");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }
    public void nuke() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DROP TABLE prison_bst");
            ps.executeUpdate();
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("DROP TABLE prison");
            ps2.executeUpdate();
            PreparedStatement ps3 = plugin.SQL.getConnection().prepareStatement("DROP TABLE prison_block_top");
            ps3.executeUpdate();
            PreparedStatement ps4 = plugin.SQL.getConnection().prepareStatement("DROP TABLE prison_lvl_top");
            ps4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public Integer getBlockBstTime() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TIME_BLOCK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Integer getMoneyBstTime() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("TIME_MONEY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockBstUuid() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("UUID_BLOCK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }
    public String getMoneyBstUuid() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("UUID_MONEY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }
    public void clearMoneyBst(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE prison_bst SET TIME_MONEY=?, UUID_MONEY=? WHERE NUMBER=?");
            ps.setNull(1, Types.INTEGER);
            ps.setNull(2, Types.VARCHAR);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearBlockBst(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE prison_bst SET TIME_BLOCK=?, UUID_BLOCK=? WHERE NUMBER=?");
            ps.setInt(1, 0);
            ps.setNull(2, Types.VARCHAR);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertBlockBst(Integer time, UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE prison_bst SET TIME_BLOCK=?, UUID_BLOCK=? WHERE NUMBER=?");
            ps.setInt(1, time);
            ps.setString(2, uuid.toString());
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertMoneyBst(UUID uuid, Integer time){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE prison_bst SET TIME_MONEY=?, UUID_MONEY=? WHERE NUMBER=?");
            ps.setInt(1, time);
            ps.setString(2, uuid.toString());
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertFirstTop(UUID uuid){
        try {
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO prison_block_top (UUID,BLOCKS) VALUES (?,?)");
            ps2.setString(1, uuid.toString());
            ps2.setInt(2, 0);
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertFirstLvlTop(UUID uuid){
        try {
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO prison_lvl_top (UUID,LVL,DATE) VALUES (?,?,?)");
            ps2.setString(1, uuid.toString());
            ps2.setInt(2, 0);
            ps2.setDate(3, new Date(0L));
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBlockTop(UUID uuid, int blocks){
        try {
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("UPDATE prison_block_top SET BLOCKS=? WHERE UUID=?");
            ps2.setInt(1, blocks);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getBlockTopValue(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("BLOCKS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopOneName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopOne(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopTwoName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 2) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopTwo(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while(rs.getRow() < 2) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopThreeName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 3) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopThree(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while(rs.getRow() < 3) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopFourName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 4) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopFour() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 4) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopFiveName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 5) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopFive() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 5) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopSixName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 6) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopSix() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 6) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopSevenName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 7) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopSeven() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 7) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopEightName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 8) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopEight() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 8) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopNineName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 9) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopNine() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 9) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockTopTenName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 10) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTopTen() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 10) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getLvlTopOne() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                    int integer;
                    integer = rs.getInt(2);
                    return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopOneName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string;
                string = rs.getString(1);
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getLvlTopTwoName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 2) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopTwo() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 2) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopThreeName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 3) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopThree() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 3) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopFourName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 4) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopFour() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 4) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopFiveName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 5) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopFive() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 5) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopSixName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 6) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopSix() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 6) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopSevenName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 7) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopSeven() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 7) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopEightName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 8) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopEight() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 8) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopNineName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 9) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopNine() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 9) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopTenName(){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = null;
                while(rs.getRow() < 10) {
                    rs.next();
                    string = rs.getString(1);
                }
                return string;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTopTen() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = 0;
                while (rs.getRow() < 10) {
                    rs.next();
                    integer = rs.getInt(2);
                }
                return integer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateLVLTop(UUID uuid, int level, Date date) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE prison_lvl_top SET LVL=?,DATE=? WHERE UUID=?");
            ps.setInt(1, level);
            ps.setDate(2,date);
            ps.setString(3, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
