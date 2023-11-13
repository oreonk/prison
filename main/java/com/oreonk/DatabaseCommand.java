package com.oreonk;

import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public abstract class DatabaseCommand {
    Prison plugin;
    Connection connection;
    String table = Prison.getPlugin(Prison.class).getConfig().getString("DB.table"); //Имя таблицы
    public DatabaseCommand(Prison instance){
        plugin = instance;
    }
    public abstract Connection getSQLConnection();
    public abstract void load();
    public void initialize(){
        connection = getSQLConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID = ?");
            ResultSet rs = ps.executeQuery();
            close(ps,rs);
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    }
    public void createTable(){
        PreparedStatement ps;
        Connection connection;
        try{
            connection = getSQLConnection();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS prison_bst "
                    +"(UUID_BLOCK VARCHAR(100),UUID_MONEY VARCHAR(100),TIME_BLOCK INT(20),TIME_MONEY INT(20), NUMBER INT(1), PRIMARY KEY(NUMBER))");
            ps.executeUpdate();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS prison_block_top "
                    +"(UUID VARCHAR(100), BLOCKS INT(100), PRIMARY KEY(UUID))");
            ps.executeUpdate();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS prison_lvl_top "
                    +"(UUID VARCHAR(100), LVL INT(10), DATE DATE, PRIMARY KEY(UUID))");
            ps.executeUpdate();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS prison_collection "
                    // MUSHROOM - Гриб, QUARTZ - Кусок кварца, STONE_DUST - каменная пыль, OAK_SAWDUST - Дубовые опилки, COAL - Карбонат, BARLEY - Ячмень, STALACTITE - Сталактит, MUSH - Мох, BEDROCK - Затвердевшая порода, DIAMOND_DUST - алмазная крошка, STICK - Веточка, SOUL - Душа грешника, EYE - Всевидящее око, TUNGSTEN - Осколок вользфрама, HEART - Сердце, HOE - тяпка, POTION - Эликсир, RA - Ра, SOUL_BOSS - Душа (с босса)
                    +"(UUID VARCHAR(100), MUSHROOM INT(2), QUARTZ INT(2), STONE_DUST INT(2), OAK_SAWDUST INT(2), COAL INT(2), BARLEY INT(2), STALACTITE INT(2), MUSH INT(2), BEDROCK INT(2), DIAMOND_DUST INT(2), STICK INT(2), SOUL INT(2), EYE INT(2), TUNGSTEN INT(2), HEART INT(2), HOE INT(2), POTION INT(2), RA INT(2), SOUL_BOSS INT(2), PRIMARY KEY(UUID))");
            ps.executeUpdate();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS prison_achievements "
                    // DIG_ONE - "Копай пока молодой",KEY_ONE - "И куда это вставлять?",BOSS_ONE - "Машина",SELL_ONE - "Барыга",HOME_ONE - "Владелец недвижимости", COLLECTION_ONE - "Искатель", FRACTION_ONE - "Да кто ты такой?", HUNTER_ONE - "Охотник", SOLO_BOSS_ONE - "Бандит", ARMOR_ONE - "Модник", BATTLEPASS_ONE - "Пай-мальчик", UNDERGROUND_ONE - "Подземный житель", HOLY_ONE - "Святой", UPGRADE_ONE - "Вот это агрегат!", FARM_ONE - "Садовник", COLLECTION_TWO - "Коллекционер", SWEAT_ONE - "Отдых? Это кто?"
                    +"(UUID VARCHAR(100), DIG_ONE INT(2), KEY_ONE INT(2), BOSS_ONE INT(2), SELL_ONE INT(2), HOME_ONE INT(2), COLLECTION_ONE INT(2), FRACTION_ONE INT(2), HUNTER_ONE INT(2), SOLO_BOSS_ONE INT(2), ARMOR_ONE INT(2), BATTLEPASS_ONE INT(2), UNDERGROUND_ONE INT(2), HOLY_ONE INT(2), UPGRADE_ONE INT(2), FARM_ONE INT(2), COLLECTION_TWO INT(2), SWEAT_ONE INT(2), PRIMARY KEY(UUID))");
            ps.executeUpdate();
            ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS prison_authority "
                    +"(UUID VARCHAR(100), SECURITY_ONE INT(20), SECURITY_TWO INT(20), SECURITY_THREE INT(20), SECURITY_FOUR INT(20), SECURITY_FIVE INT(20), SLIME INT(20), SPIDER INT(20), SILVERFISH INT(20), ZOMBIE INT(20), VAMPIRE INT(20), PRIMARY KEY(UUID))");
            ps.executeUpdate();
            if (!idExists(1)) {
                ps = connection.prepareStatement("INSERT INTO prison_bst (NUMBER,UUID_BLOCK,UUID_MONEY,TIME_BLOCK,TIME_MONEY) VALUES (?,?,?,?,?)");
                ps.setInt(1,1);
                ps.setString(2,"0");
                ps.setString(3,"0");
                ps.setInt(4,0);
                ps.setInt(5,0);
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createPlayer(Player player, String multiplier){
        Connection connection;
        try{
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (!playerExists(uuid)) {
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO " + table + " (NAME,UUID,LVL,BOOST,MULTIPLIER,FACTION,CELL_LVL,CELL_CASH,INTELLECT_STAT,AUTHORITY_STAT,RELIGION_STAT,CELL_FARM,BLOCK_BOOST_MULTIPLIER,BLOCK_BOOST_TIME,MONEY_BOOST_MULTIPLIER,MONEY_BOOST_TIME,PLUS_MULTIPLIER,COMMISSION,MOB_BOOST_MULTIPLIER,MOB_BOOST_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, player.getUniqueId().toString());
                ps2.setInt(3, 1);
                ps2.setString(4, "0.0");
                ps2.setString(5, multiplier);
                ps2.setString(6, "0");
                ps2.setInt(7, 1);
                ps2.setString(8, "0.0");
                ps2.setInt(9, 0);
                ps2.setInt(10, 0);
                ps2.setInt(11, 0);
                ps2.setInt(12, 0);
                ps2.setInt(13, 0);
                ps2.setInt(14, 0);
                ps2.setInt(15, 0);
                ps2.setInt(16, 0);
                ps2.setString(17, "0.0");
                ps2.setInt(18, 0);
                ps2.setInt(19, 0);
                ps2.setInt(20, 0);
                ps2.executeUpdate();
                ps2.close();
            } if (!playerCollectionExists(uuid)){
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO prison_collection (UUID,MUSHROOM,QUARTZ,STONE_DUST,OAK_SAWDUST,COAL,BARLEY,STALACTITE,MUSH,BEDROCK,DIAMOND_DUST,STICK,SOUL,EYE,TUNGSTEN) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                ps2.close();
            } if (!playerAchievementExists(uuid)){
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO prison_achievements (UUID,DIG_ONE,KEY_ONE,BOSS_ONE,SELL_ONE,HOME_ONE,COLLECTION_ONE,FRACTION_ONE,HUNTER_ONE,SOLO_BOSS_ONE,ARMOR_ONE,BATTLEPASS_ONE,UNDERGROUND_ONE,HOLY_ONE,UPGRADE_ONE,FARM_ONE,COLLECTION_TWO,SWEAT_ONE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                ps2.setInt(16, 0);
                ps2.setInt(17, 0);
                ps2.setInt(18, 0);
                ps2.executeUpdate();
                ps2.close();
            } if (!playerAuthorityExists(uuid)){
                PreparedStatement ps2 = connection.prepareStatement("INSERT INTO prison_authority (UUID,SECURITY_ONE,SECURITY_TWO,SECURITY_THREE,SECURITY_FOUR,SECURITY_FIVE,SLIME,SPIDER,SILVERFISH,ZOMBIE,VAMPIRE) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
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
                ps2.executeUpdate();
                ps2.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String getUUID(String name) {
        Connection connection;
        if (playerNameExists(name)) {
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE NAME=?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String string = rs.getString("UUID");
                    rs.close();
                    ps.close();
                    return string;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "nope";
        }
        return "nope";
    }
    public void sendLocalMoneyBoosterInfo(UUID uuid){
        try{
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_TIME=? WHERE UUID=?");
            ps.setInt(1, Prison.getInstance().privateBooster.get(uuid));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_MULTIPLIER=? WHERE UUID=?");
            ps2.setInt(1, Prison.getInstance().privateBoosterMultiplier.get(uuid));
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void sendLocalMobBoosterInfo(UUID uuid){
        try{
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_TIME=? WHERE UUID=?");
            ps.setInt(1, Prison.getInstance().privateMobBooster.get(uuid));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_MULTIPLIER=? WHERE UUID=?");
            ps2.setInt(1, Prison.getInstance().privateMobMultiplier.get(uuid));
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void sendLocalBlockBoosterInfo(UUID uuid){
        try{
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_TIME=? WHERE UUID=?");
            ps.setInt(1, Prison.getInstance().privateBlockBooster.get(uuid));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_MULTIPLIER=? WHERE UUID=?");
            ps2.setInt(1, Prison.getInstance().privateBlockBoosterMultiplier.get(uuid));
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearLocalMobBoosterInfo(UUID uuid){
        try{
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_TIME=? WHERE UUID=?");
            ps.setInt(1, 0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_MULTIPLIER=? WHERE UUID=?");
            ps2.setInt(1, 0);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearLocalBlockBoosterInfo(UUID uuid){
        try{
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_TIME=? WHERE UUID=?");
            ps.setInt(1, 0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_MULTIPLIER=? WHERE UUID=?");
            ps2.setInt(1, 0);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearLocalMoneyBoosterInfo(UUID uuid){
        try{
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_TIME=? WHERE UUID=?");
            ps.setInt(1, 0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_MULTIPLIER=? WHERE UUID=?");
            ps2.setInt(1, 0);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getLocalMoneyBoosterTime(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int integer = rs.getInt("MONEY_BOOST_TIME");
                    rs.close();
                    ps.close();
                    return integer;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }
    public Double getPlusMultiplier(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    double db = Double.parseDouble(rs.getString("PLUS_MULTIPLIER"));
                    rs.close();
                    ps.close();
                    return db;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0.0;
    }
    public void updatePlusMultiplier(UUID uuid, String multiplier) {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET PLUS_MULTIPLIER=? WHERE UUID=?");
            ps.setString(1, multiplier);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getLocalMoneyBoosterMultiplier(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int integer = rs.getInt("MONEY_BOOST_MULTIPLIER");
                    rs.close();
                    ps.close();
                    return integer;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }
    public Integer getLocalBlockBoosterMultiplier(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int integer = rs.getInt("BLOCK_BOOST_MULTIPLIER");
                    rs.close();
                    ps.close();
                    return integer;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }
    public Integer getLocalBlockBoosterTime(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int integer = rs.getInt("BLOCK_BOOST_TIME");
                    rs.close();
                    ps.close();
                    return integer;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }
    public Integer getLocalMobBoosterMultiplier(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int integer = rs.getInt("MOB_BOOST_MULTIPLIER");
                    rs.close();
                    ps.close();
                    return integer;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }
    public Integer getLocalMobBoosterTime(UUID uuid) {
        Connection connection;
            try {
                connection = getSQLConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
                ps.setString(1, uuid.toString());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int integer = rs.getInt("MOB_BOOST_TIME");
                    rs.close();
                    ps.close();
                    return integer;
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }
    public void setAchievement(UUID uuid, String capsAchievementName) {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_achievements SET " + capsAchievementName +"=? WHERE UUID=?");
            ps.setInt(1, 1);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Boolean getAchievement(UUID uuid, String capsAchievementName){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_achievements WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt(capsAchievementName);
                if(integer == 1){
                    rs.close();
                    ps.close();
                    return true;
                } else {
                    rs.close();
                    ps.close();
                    return false;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public Integer getOverallAchievementLvl(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_collection WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("MUSHROOM") + rs.getInt("QUARTZ") + rs.getInt("STONE_DUST") + rs.getInt("OAK_SAWDUST") + rs.getInt("COAL") + rs.getInt("BARLEY") + rs.getInt("STALACTITE") + rs.getInt("MUSH") + rs.getInt("HEART") + rs.getInt("HOE") + rs.getInt("POTION") + rs.getInt("RA");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public boolean playerNameExists(String name){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE NAME=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public Integer getCellLvl(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("CELL_LVL");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 1;
    }
     public Integer getAuthorityStat(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("AUTHORITY_STAT");
                rs.close();
                ps.close();
                return integer;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void setAuthorityStat(Player player, int set) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET AUTHORITY_STAT=? WHERE UUID=?");
            ps.setInt(1, set);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addCellCash(int add) {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET CELL_CASH = CELL_CASH + " + add + " WHERE CELL_FARM=?");
            ps.setInt(1, 1);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getReligionStat(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("RELIGION_STAT");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void setReligionStat(Player player, int set) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET RELIGION_STAT=? WHERE UUID=?");
            ps.setInt(1, set);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getIntellectStat(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("INTELLECT_STAT");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addIntellectStat(Player player, int add) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            int stat = getIntellectStat(player);
            int finalStat = stat + add;
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET INTELLECT_STAT=? WHERE UUID=?");
            ps.setInt(1, finalStat);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateCellLvl(Player player, int level) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET CELL_LVL=? WHERE UUID=?");
            ps.setInt(1, level);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getCollectionLvl(Player player, String columnCapsName){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_collection WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt(columnCapsName);
                ps.close();
                rs.close();
                return integer;
            }
            ps.close();
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addCollectionLvl(Player player, String columnCapsName) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET "+ columnCapsName + "=? WHERE UUID=?");
            int lvl = getCollectionLvl(player, columnCapsName) + 1;
            ps.setInt(1, lvl);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Double getCellCash(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double dbl = Double.parseDouble(rs.getString("CELL_CASH"));
                ps.close();
                rs.close();
                return dbl;
            }
            ps.close();
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0.0;
    }
    public Double getMultiplier(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double dbl = Double.parseDouble(rs.getString("MULTIPLIER"));
                rs.close();
                ps.close();
                return dbl;
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 1.0;
    }
    public Double getBoost(Player player){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double dbl = Double.parseDouble(rs.getString("BOOST"));
                rs.close();
                ps.close();
                return dbl;
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0.0;
    }
    public int getLVL(Player player) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("LVL");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateLVL(Player player, int level) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET LVL=? WHERE UUID=?");
            ps.setInt(1, level);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean playerExists(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean farmPurchased(){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE CELL_FARM=?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean playerCollectionExists(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_collection WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean playerAchievementExists(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_achievements WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean playerAuthorityExists(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_authority WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean idExists(Integer id){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst WHERE NUMBER=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean topExists(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean topLvlExists(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_lvl_top WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                return true;
            }
            rs.close();
            ps.close();
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    //Может не работать
        public void updateMultiplier(UUID uuid, String multiplier){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            if(playerExists(uuid)){
                PreparedStatement ps2 = connection.prepareStatement("REPLACE INTO " + table + " (MULTIPLIER) VALUES(?)");
                ps2.setString(1, multiplier);
                ps2.close();
            }
            rs.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateBoost(Player player, String boost) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET LVL=? WHERE UUID=?");
            ps.setString(1, boost);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setCommission(Player player, int commission) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET COMMISSION=? WHERE UUID=?");
            ps.setInt(1, commission);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getCommission(Player player) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int digit = rs.getInt("COMMISSION");
                rs.close();
                ps.close();
                return digit;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void setFaction(Player player, String faction) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET FACTION=? WHERE UUID=?");
            ps.setString(1, faction);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getFaction(Player player) {
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = rs.getString("FACTION");
                rs.close();
                ps.close();
                return string;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }
    public void nuke() {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("DROP TABLE prison_bst");
            ps.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("DROP TABLE prison");
            ps2.executeUpdate();
            PreparedStatement ps3 = connection.prepareStatement("DROP TABLE prison_block_top");
            ps3.executeUpdate();
            PreparedStatement ps4 = connection.prepareStatement("DROP TABLE prison_lvl_top");
            ps4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public Integer getBlockBstTime() {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("TIME_BLOCK");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Integer getMoneyBstTime() {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("TIME_MONEY");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getBlockBstUuid() {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = rs.getString("UUID_BLOCK");
                rs.close();
                ps.close();
                return string;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }
    public String getMoneyBstUuid() {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String string = rs.getString("UUID_MONEY");
                rs.close();
                ps.close();
                return string;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }
    public void clearMoneyBst(){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_MONEY=?, UUID_MONEY=? WHERE NUMBER=?");
            ps.setNull(1, Types.INTEGER);
            ps.setNull(2, Types.VARCHAR);
            ps.setInt(3, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearBlockBst(){
       Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_BLOCK=?, UUID_BLOCK=? WHERE NUMBER=?");
            ps.setInt(1, 0);
            ps.setNull(2, Types.VARCHAR);
            ps.setInt(3, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertBlockBst(Integer time, UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_BLOCK=?, UUID_BLOCK=? WHERE NUMBER=?");
            ps.setInt(1, time);
            ps.setString(2, uuid.toString());
            ps.setInt(3, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertMoneyBst(UUID uuid, Integer time){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_MONEY=?, UUID_MONEY=? WHERE NUMBER=?");
            ps.setInt(1, time);
            ps.setString(2, uuid.toString());
            ps.setInt(3, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertFirstTop(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO prison_block_top (UUID,BLOCKS) VALUES (?,?)");
            ps2.setString(1, uuid.toString());
            ps2.setInt(2, 0);
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertFirstLvlTop(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO prison_lvl_top (UUID,LVL,DATE) VALUES (?,?,?)");
            ps2.setString(1, uuid.toString());
            ps2.setInt(2, 0);
            ps2.setDate(3, new Date(0L));
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBlockTop(UUID uuid, int blocks){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps2 = connection.prepareStatement("UPDATE prison_block_top SET BLOCKS=? WHERE UUID=?");
            ps2.setInt(1, blocks);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getBlockTopValue(UUID uuid){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt("BLOCKS");
                rs.close();
                ps.close();
                return integer;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getBlockTopName(int number){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (number == 1){
                    return rs.getString(1);
                } else {
                    String string = "";
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()){
                            return "";
                        }
                        string = rs.getString(1);
                    }
                    return string;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getBlockTop(int number){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (number == 1) {
                    return rs.getInt(2);
                } else {
                    int integer = 0;
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()){
                            return 0;
                        }
                        integer = rs.getInt(2);
                    }
                    return integer;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getLvlTopName(int number){
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (number == 1) {
                    return rs.getString(1);
                } else {
                    String string = "";
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()){
                            return "";
                        }
                        string = rs.getString(1);
                    }
                    return string;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getLvlTop(int number) {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC,DATE ASC");
            ResultSet rs = ps.executeQuery();
            if (number == 1) {
                return rs.getInt(2);
            } else {
                if (rs.next()) {
                    int integer = 0;
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()){
                            return 0;
                        }
                        integer = rs.getInt(2);
                    }
                    return integer;
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateLVLTop(UUID uuid, int level, Date date) {
        Connection connection;
        try {
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_lvl_top SET LVL=?,DATE=? WHERE UUID=?");
            ps.setInt(1, level);
            ps.setDate(2,date);
            ps.setString(3, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getAuthorityMobsAmount(Player player, String capsMobID){
        Connection connection;
        try {
            connection = getSQLConnection();
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_authority WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int integer = rs.getInt(capsMobID);
                ps.close();
                rs.close();
                return integer;
            }
            ps.close();
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void setAuthorityMobsAmount(Player player, String capsMobID, Integer amount) {
        Connection connection;
        try {
            UUID uuid = player.getUniqueId();
            connection = getSQLConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE prison_authority SET " + capsMobID +"=? WHERE UUID=?");
            ps.setInt(1, amount);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close(PreparedStatement ps, ResultSet rs){
        try{
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}
