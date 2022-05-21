package com.oreonk;

import com.oreonk.commands.Lvl;
import com.oreonk.commands.Upgrade;
import com.oreonk.events.*;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestPlug extends JavaPlugin {
    private Connection connection;
    public String host, database, username, password, table;
    public int port;
    private static TestPlug instance;
    private static Economy econ = null;
    @Override
    public void onEnable() {
        instance = this;
        System.out.println("Prison by oreonk kekw");
        saveDefaultConfig();
        mysqlSetup();
        getServer().getPluginManager().registerEvents(new DBJoin(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        new Upgrade(this);
        new Lvl(this);
        getServer().getPluginManager().registerEvents(new BlockSell(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new Enchant(), this);
        if (!setupEconomy() ) {
            System.out.println("Нет плагина с экономикой");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }
    public void mysqlSetup(){
        host = this.getConfig().getString("DB.host");
        port = this.getConfig().getInt("DB.port");
        database = this.getConfig().getString("DB.database");
        username = this.getConfig().getString("DB.username");
        password = this.getConfig().getString("DB.password");
        table = this.getConfig().getString("DB.table");

        try{
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException throwables){
            throwables.getStackTrace();
        }
    }
    public Connection getConnection(){
        return  connection;
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
    @Override
    public void onDisable() {
        System.out.println("[Prison] The world is going dark...goodbye");
    }
    public static TestPlug getInstance(){
        return instance;
    }
}
