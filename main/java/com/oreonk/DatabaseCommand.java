package com.oreonk;

import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public abstract class DatabaseCommand {
    protected Prison plugin;
    protected Connection connection;
    protected String table;

    public DatabaseCommand(Prison instance) {
        this.plugin = instance;
        this.table = Prison.getPlugin(Prison.class).getConfig().getString("DB.table");
    }

    public abstract Connection getSQLConnection();
    public abstract void load();

    public void initialize() {
        connection = getSQLConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID = ?");
             ResultSet rs = ps.executeQuery()) {
            // Проверка соединения
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retrieve connection", ex);
        }
    }

    public void createTable() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS prison_bst " +
                             "(UUID_BLOCK VARCHAR(100), UUID_MONEY VARCHAR(100), TIME_BLOCK INT(20), TIME_MONEY INT(20), NUMBER INT(1), PRIMARY KEY(NUMBER))");
             PreparedStatement ps2 = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS prison_block_top " +
                             "(UUID VARCHAR(100), BLOCKS INT(100), PRIMARY KEY(UUID))");
             PreparedStatement ps3 = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS prison_lvl_top " +
                             "(UUID VARCHAR(100), LVL INT(10), DATE DATE, PRIMARY KEY(UUID))");
             PreparedStatement ps4 = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS prison_collection " +
                             "(UUID VARCHAR(100), MUSHROOM INT(2), QUARTZ INT(2), STONE_DUST INT(2), OAK_SAWDUST INT(2), COAL INT(2), BARLEY INT(2), STALACTITE INT(2), MUSH INT(2), BEDROCK INT(2), DIAMOND_DUST INT(2), STICK INT(2), SOUL INT(2), EYE INT(2), TUNGSTEN INT(2), HEART INT(2), HOE INT(2), POTION INT(2), RA INT(2), SOUL_BOSS INT(2), PRIMARY KEY(UUID))");
             PreparedStatement ps5 = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS prison_achievements " +
                             "(UUID VARCHAR(100), DIG_ONE INT(2), KEY_ONE INT(2), BOSS_ONE INT(2), SELL_ONE INT(2), HOME_ONE INT(2), COLLECTION_ONE INT(2), FRACTION_ONE INT(2), HUNTER_ONE INT(2), SOLO_BOSS_ONE INT(2), ARMOR_ONE INT(2), BATTLEPASS_ONE INT(2), UNDERGROUND_ONE INT(2), HOLY_ONE INT(2), UPGRADE_ONE INT(2), FARM_ONE INT(2), COLLECTION_TWO INT(2), SWEAT_ONE INT(2), PRIMARY KEY(UUID))");
             PreparedStatement ps6 = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS prison_authority " +
                             "(UUID VARCHAR(100), SECURITY_ONE INT(20), SECURITY_TWO INT(20), SECURITY_THREE INT(20), SECURITY_FOUR INT(20), SECURITY_FIVE INT(20), SLIME INT(20), SPIDER INT(20), SILVERFISH INT(20), ZOMBIE INT(20), VAMPIRE INT(20), PRIMARY KEY(UUID))")) {

            ps.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();
            ps6.executeUpdate();

            if (!idExists(1)) {
                try (PreparedStatement ps7 = connection.prepareStatement(
                        "INSERT INTO prison_bst (NUMBER, UUID_BLOCK, UUID_MONEY, TIME_BLOCK, TIME_MONEY) VALUES (?, ?, ?, ?, ?)")) {
                    ps7.setInt(1, 1);
                    ps7.setString(2, "0");
                    ps7.setString(3, "0");
                    ps7.setInt(4, 0);
                    ps7.setInt(5, 0);
                    ps7.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player, String multiplier) {
        try (Connection connection = getSQLConnection()) {
            UUID uuid = player.getUniqueId();
            if (!playerExists(uuid)) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO " + table + " (NAME, UUID, LVL, BOOST, MULTIPLIER, FACTION, CELL_LVL, CELL_CASH, INTELLECT_STAT, AUTHORITY_STAT, RELIGION_STAT, CELL_FARM, BLOCK_BOOST_MULTIPLIER, BLOCK_BOOST_TIME, MONEY_BOOST_MULTIPLIER, MONEY_BOOST_TIME, PLUS_MULTIPLIER, COMMISSION, MOB_BOOST_MULTIPLIER, MOB_BOOST_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    setPlayerValues(ps, player, multiplier);
                    ps.executeUpdate();
                }
            }
            if (!playerCollectionExists(uuid)) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO prison_collection (UUID, MUSHROOM, QUARTZ, STONE_DUST, OAK_SAWDUST, COAL, BARLEY, STALACTITE, MUSH, BEDROCK, DIAMOND_DUST, STICK, SOUL, EYE, TUNGSTEN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    setCollectionValues(ps, uuid);
                    ps.executeUpdate();
                }
            }
            if (!playerAchievementExists(uuid)) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO prison_achievements (UUID, DIG_ONE, KEY_ONE, BOSS_ONE, SELL_ONE, HOME_ONE, COLLECTION_ONE, FRACTION_ONE, HUNTER_ONE, SOLO_BOSS_ONE, ARMOR_ONE, BATTLEPASS_ONE, UNDERGROUND_ONE, HOLY_ONE, UPGRADE_ONE, FARM_ONE, COLLECTION_TWO, SWEAT_ONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    setAchievementValues(ps, uuid);
                    ps.executeUpdate();
                }
            }
            if (!playerAuthorityExists(uuid)) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO prison_authority (UUID, SECURITY_ONE, SECURITY_TWO, SECURITY_THREE, SECURITY_FOUR, SECURITY_FIVE, SLIME, SPIDER, SILVERFISH, ZOMBIE, VAMPIRE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    setAuthorityValues(ps, uuid);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setPlayerValues(PreparedStatement ps, Player player, String multiplier) throws SQLException {
        ps.setString(1, player.getName());
        ps.setString(2, player.getUniqueId().toString());
        ps.setInt(3, 1);
        ps.setString(4, "0.0");
        ps.setString(5, multiplier);
        ps.setString(6, "0");
        ps.setInt(7, 1);
        ps.setString(8, "0.0");
        ps.setInt(9, 0);
        ps.setInt(10, 0);
        ps.setInt(11, 0);
        ps.setInt(12, 0);
        ps.setInt(13, 0);
        ps.setInt(14, 0);
        ps.setInt(15, 0);
        ps.setInt(16, 0);
        ps.setString(17, "0.0");
        ps.setInt(18, 0);
        ps.setInt(19, 0);
        ps.setInt(20, 0);
    }

    private void setCollectionValues(PreparedStatement ps, UUID uuid) throws SQLException {
        ps.setString(1, uuid.toString());
        for (int i = 2; i <= 15; i++) {
            ps.setInt(i, 0);
        }
    }

    private void setAchievementValues(PreparedStatement ps, UUID uuid) throws SQLException {
        ps.setString(1, uuid.toString());
        for (int i = 2; i <= 18; i++) {
            ps.setInt(i, 0);
        }
    }

    private void setAuthorityValues(PreparedStatement ps, UUID uuid) throws SQLException {
        ps.setString(1, uuid.toString());
        for (int i = 2; i <= 11; i++) {
            ps.setInt(i, 0);
        }
    }

    public String getUUID(String name) {
        if (playerNameExists(name)) {
            try (Connection connection = getSQLConnection();
                 PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE NAME=?")) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("UUID");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "nope";
    }

    public void sendLocalMoneyBoosterInfo(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_TIME=? WHERE UUID=?");
             PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_MULTIPLIER=? WHERE UUID=?")) {
            ps.setInt(1, Prison.getInstance().privateBooster.get(uuid));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps2.setInt(1, Prison.getInstance().privateBoosterMultiplier.get(uuid));
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendLocalMobBoosterInfo(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_TIME=? WHERE UUID=?");
             PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_MULTIPLIER=? WHERE UUID=?")) {
            ps.setInt(1, Prison.getInstance().privateMobBooster.get(uuid));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps2.setInt(1, Prison.getInstance().privateMobMultiplier.get(uuid));
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendLocalBlockBoosterInfo(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_TIME=? WHERE UUID=?");
             PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_MULTIPLIER=? WHERE UUID=?")) {
            ps.setInt(1, Prison.getInstance().privateBlockBooster.get(uuid));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps2.setInt(1, Prison.getInstance().privateBlockBoosterMultiplier.get(uuid));
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearLocalMobBoosterInfo(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_TIME=? WHERE UUID=?");
             PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MOB_BOOST_MULTIPLIER=? WHERE UUID=?")) {
            ps.setInt(1, 0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps2.setInt(1, 0);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearLocalBlockBoosterInfo(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_TIME=? WHERE UUID=?");
             PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET BLOCK_BOOST_MULTIPLIER=? WHERE UUID=?")) {
            ps.setInt(1, 0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps2.setInt(1, 0);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearLocalMoneyBoosterInfo(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_TIME=? WHERE UUID=?");
             PreparedStatement ps2 = connection.prepareStatement("UPDATE " + table + " SET MONEY_BOOST_MULTIPLIER=? WHERE UUID=?")) {
            ps.setInt(1, 0);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            ps2.setInt(1, 0);
            ps2.setString(2, uuid.toString());
            ps2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getLocalMoneyBoosterTime(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MONEY_BOOST_TIME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Double getPlusMultiplier(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Double.parseDouble(rs.getString("PLUS_MULTIPLIER"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void updatePlusMultiplier(UUID uuid, String multiplier) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET PLUS_MULTIPLIER=? WHERE UUID=?")) {
            ps.setString(1, multiplier);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getLocalMoneyBoosterMultiplier(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MONEY_BOOST_MULTIPLIER");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getLocalBlockBoosterMultiplier(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("BLOCK_BOOST_MULTIPLIER");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getLocalBlockBoosterTime(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("BLOCK_BOOST_TIME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getLocalMobBoosterMultiplier(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MOB_BOOST_MULTIPLIER");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getLocalMobBoosterTime(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MOB_BOOST_TIME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setAchievement(UUID uuid, String capsAchievementName) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_achievements SET " + capsAchievementName + "=? WHERE UUID=?")) {
            ps.setInt(1, 1);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean getAchievement(UUID uuid, String capsAchievementName) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_achievements WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(capsAchievementName) == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer getOverallAchievementLvl(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_collection WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MUSHROOM") + rs.getInt("QUARTZ") + rs.getInt("STONE_DUST") + rs.getInt("OAK_SAWDUST") + rs.getInt("COAL") + rs.getInt("BARLEY") + rs.getInt("STALACTITE") + rs.getInt("MUSH") + rs.getInt("HEART") + rs.getInt("HOE") + rs.getInt("POTION") + rs.getInt("RA");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean playerNameExists(String name) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE NAME=?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer getCellLvl(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("CELL_LVL");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public Integer getAuthorityStat(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("AUTHORITY_STAT");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setAuthorityStat(Player player, int set) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET AUTHORITY_STAT=? WHERE UUID=?")) {
            ps.setInt(1, set);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCellCash(int add) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET CELL_CASH = CELL_CASH + " + add + " WHERE CELL_FARM=?")) {
            ps.setInt(1, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getReligionStat(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("RELIGION_STAT");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setReligionStat(Player player, int set) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET RELIGION_STAT=? WHERE UUID=?")) {
            ps.setInt(1, set);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getIntellectStat(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("INTELLECT_STAT");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addIntellectStat(Player player, int add) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET INTELLECT_STAT=? WHERE UUID=?")) {
            int stat = getIntellectStat(player);
            int finalStat = stat + add;
            ps.setInt(1, finalStat);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCellLvl(Player player, int level) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET CELL_LVL=? WHERE UUID=?")) {
            ps.setInt(1, level);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCollectionLvl(Player player, String columnCapsName) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_collection WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(columnCapsName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addCollectionLvl(Player player, String columnCapsName) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET " + columnCapsName + "=? WHERE UUID=?")) {
            int lvl = getCollectionLvl(player, columnCapsName) + 1;
            ps.setInt(1, lvl);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double getCellCash(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Double.parseDouble(rs.getString("CELL_CASH"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Double getMultiplier(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Double.parseDouble(rs.getString("MULTIPLIER"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    public Double getBoost(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Double.parseDouble(rs.getString("BOOST"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public int getLVL(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("LVL");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateLVL(Player player, int level) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET LVL=? WHERE UUID=?")) {
            ps.setInt(1, level);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean farmPurchased() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE CELL_FARM=?")) {
            ps.setInt(1, 1);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean playerCollectionExists(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_collection WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean playerAchievementExists(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_achievements WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean playerAuthorityExists(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_authority WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean idExists(Integer id) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst WHERE NUMBER=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean topExists(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean topLvlExists(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_lvl_top WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateMultiplier(UUID uuid, String multiplier) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && playerExists(uuid)) {
                    try (PreparedStatement ps2 = connection.prepareStatement("REPLACE INTO " + table + " (MULTIPLIER) VALUES(?)")) {
                        ps2.setString(1, multiplier);
                        ps2.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBoost(Player player, String boost) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET LVL=? WHERE UUID=?")) {
            ps.setString(1, boost);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCommission(Player player, int commission) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET COMMISSION=? WHERE UUID=?")) {
            ps.setInt(1, commission);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCommission(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("COMMISSION");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setFaction(Player player, String faction) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE " + table + " SET FACTION=? WHERE UUID=?")) {
            ps.setString(1, faction);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFaction(Player player) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("FACTION");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }

    public void nuke() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("DROP TABLE prison_bst");
             PreparedStatement ps2 = connection.prepareStatement("DROP TABLE prison");
             PreparedStatement ps3 = connection.prepareStatement("DROP TABLE prison_block_top");
             PreparedStatement ps4 = connection.prepareStatement("DROP TABLE prison_lvl_top")) {
            ps.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getBlockBstTime() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("TIME_BLOCK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getMoneyBstTime() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("TIME_MONEY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getBlockBstUuid() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("UUID_BLOCK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }

    public String getMoneyBstUuid() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_bst");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("UUID_MONEY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "nope";
    }

    public void clearMoneyBst() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_MONEY=?, UUID_MONEY=? WHERE NUMBER=?")) {
            ps.setNull(1, Types.INTEGER);
            ps.setNull(2, Types.VARCHAR);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearBlockBst() {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_BLOCK=?, UUID_BLOCK=? WHERE NUMBER=?")) {
            ps.setInt(1, 0);
            ps.setNull(2, Types.VARCHAR);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBlockBst(Integer time, UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_BLOCK=?, UUID_BLOCK=? WHERE NUMBER=?")) {
            ps.setInt(1, time);
            ps.setString(2, uuid.toString());
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMoneyBst(UUID uuid, Integer time) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_bst SET TIME_MONEY=?, UUID_MONEY=? WHERE NUMBER=?")) {
            ps.setInt(1, time);
            ps.setString(2, uuid.toString());
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFirstTop(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO prison_block_top (UUID, BLOCKS) VALUES (?, ?)")) {
            ps.setString(1, uuid.toString());
            ps.setInt(2, 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFirstLvlTop(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO prison_lvl_top (UUID, LVL, DATE) VALUES (?, ?, ?)")) {
            ps.setString(1, uuid.toString());
            ps.setInt(2, 0);
            ps.setDate(3, new Date(0L));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBlockTop(UUID uuid, int blocks) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_block_top SET BLOCKS=? WHERE UUID=?")) {
            ps.setInt(1, blocks);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBlockTopValue(UUID uuid) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top WHERE UUID=?")) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("BLOCKS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getBlockTopName(int number) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                if (number == 1) {
                    return rs.getString(1);
                } else {
                    String string = "";
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()) {
                            return "";
                        }
                        string = rs.getString(1);
                    }
                    return string;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getBlockTop(int number) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_block_top ORDER BY BLOCKS DESC");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                if (number == 1) {
                    return rs.getInt(2);
                } else {
                    int integer = 0;
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()) {
                            return 0;
                        }
                        integer = rs.getInt(2);
                    }
                    return integer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getLvlTopName(int number) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC, DATE ASC");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                if (number == 1) {
                    return rs.getString(1);
                } else {
                    String string = "";
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()) {
                            return "";
                        }
                        string = rs.getString(1);
                    }
                    return string;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getLvlTop(int number) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_lvl_top ORDER BY LVL DESC, DATE ASC");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                if (number == 1) {
                    return rs.getInt(2);
                } else {
                    int integer = 0;
                    while (rs.getRow() < number) {
                        rs.next();
                        if (rs.isClosed()) {
                            return 0;
                        }
                        integer = rs.getInt(2);
                    }
                    return integer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateLVLTop(UUID uuid, int level, Date date) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_lvl_top SET LVL=?, DATE=? WHERE UUID=?")) {
            ps.setInt(1, level);
            ps.setDate(2, date);
            ps.setString(3, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAuthorityMobsAmount(Player player, String capsMobID) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM prison_authority WHERE UUID=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(capsMobID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setAuthorityMobsAmount(Player player, String capsMobID, Integer amount) {
        try (Connection connection = getSQLConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE prison_authority SET " + capsMobID + "=? WHERE UUID=?")) {
            ps.setInt(1, amount);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}