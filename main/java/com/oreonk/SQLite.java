package com.oreonk;

import com.oreonk.Prison;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class SQLite extends DatabaseCommand{
    String dbname;
    public SQLite(Prison instance){
        super(instance);
        dbname = "prison";
    }

    //Множители бустеров: если стоит 0 - множитель х1.5, если 1 - множитель х2
    public String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS prison (" +
            "`NAME` VARCHAR NOT NULL," +
            "`UUID` VARCHAR NOT NULL," +
            "`LVL` INT NOT NULL," +
            "`MULTIPLIER` VARCHAR NOT NULL," +
            "`BOOST` VARCHAR NOT NULL," +
            "`FACTION` VARCHAR NOT NULL," +
            "`CELL_LVL` INT NOT NULL," +
            "`CELL_CASH` VARCHAR NOT NULL," +
            "`INTELLECT_STAT` INT NOT NULL," +
            "`AUTHORITY_STAT` INT NOT NULL," +
            "`RELIGION_STAT` INT NOT NULL," +
            "`CELL_FARM` INT NOT NULL," +
            "`BLOCK_BOOST_MULTIPLIER` INT NOT NULL," +
            "`BLOCK_BOOST_TIME` INT NOT NULL," +
            "`MONEY_BOOST_MULTIPLIER` INT NOT NULL," +
            "`MONEY_BOOST_TIME` INT NOT NULL," +
            "`MOB_BOOST_MULTIPLIER` INT NOT NULL," +
            "`MOB_BOOST_TIME` INT NOT NULL," +
            "`PLUS_MULTIPLIER` VARCHAR NOT NULL," +
            "`COMMISSION` INT NOT NULL," +
            "PRIMARY KEY (`NAME`)" +
            ");";


    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}
