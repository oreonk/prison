package com.oreonk;

import com.oreonk.commands.*;
import com.oreonk.events.*;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TestPlug extends JavaPlugin {
    public  MySQL SQL;
    public  DatabaseCommand data;
    private File blocksConfigFile;
    private FileConfiguration blocksConfig;
    private File lvlConfigFile;
    private FileConfiguration lvlConfig;
    private static TestPlug instance;
    private static Economy econ = null;
    private static Permission perms = null;
    public HashMap<Player, Double> mltp = new HashMap<>() {};
    public HashMap<Player, Double> bst = new HashMap<>() {};
    public HashMap<Player, Integer> booster = new HashMap<>() {};
    public HashMap<Player, Integer> thx = new HashMap<>() {};
    public HashMap<Player, Integer> lvl = new HashMap<>() {};
    @Override
    public void onEnable() {
        instance = this;
        createBlocksConfig();
        createLvlConfig();
        if (this.getConfig().getConfigurationSection("data") != null) {
            getBooster();
        }
        System.out.println("Prison by oreonk kekw");
        setupPermissions();
        this.SQL = new MySQL();
        this.data = new DatabaseCommand(this);
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if (SQL.isConnected()){
            Bukkit.getLogger().info("[PRISON]Подключение найдено!");
            data.createTable();
        }
        getServer().getPluginManager().registerEvents(new DBJoin(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new BlockSell(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new Enchant(), this);
        getServer().getPluginManager().registerEvents(new CraftCancel(), this);
        this.getCommand("lvl").setExecutor(new Lvl());
        this.getCommand("upgrade").setExecutor(new Upgrade());
        this.getCommand("shop").setExecutor(new Donate());
        this.getCommand("thx").setExecutor(new Thx());
        this.getCommand("autosell").setExecutor(new AutoSell());
        getServer().getPluginManager().registerEvents(new DonateMenus(), this);
        new Placeholders().register();
        if (!setupEconomy() ) {
            System.out.println("Нет плагина с экономикой");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
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
    private boolean setupPermissions(){
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
    public static Permission getPermissions(){ return perms; }
    public DatabaseCommand getDatabase(){ return this.data; }
    @Override
    public void onDisable() {
        System.out.println("[Prison] The world is going dark...goodbye");
        if (this.getConfig().getConfigurationSection("data") != null) {
            saveBooster();
        }
        SQL.disconnect();
    }
    public static TestPlug getInstance(){
        return instance;
    }

    public void saveBooster(){
        if (!this.booster.isEmpty()){
            for (Map.Entry<Player,Integer> entry : this.booster.entrySet()){
                this.getConfig().set("data." + entry.getKey().getUniqueId(), entry.getValue());
            }
            this.saveConfig();
        }
    }
    public void getBooster(){
        if (!this.getConfig().getConfigurationSection("data").getKeys(true).isEmpty()) {
            Set<String> uuid = this.getConfig().getConfigurationSection("data").getKeys(true);
            Player player = Bukkit.getPlayer(UUID.fromString(uuid.toString()));
            Integer time = Integer.parseInt(this.getConfig().getConfigurationSection("data").getKeys(false).toString());
            this.booster.put(player, time);
            this.getConfig().set("data", null);
        }
    }
    public FileConfiguration getBlocksConfig(){
        return this.blocksConfig;
    }

    private void createBlocksConfig(){
        blocksConfigFile = new File(getDataFolder(), "blocks.yml");
        if (!blocksConfigFile.exists()){
            blocksConfigFile.getParentFile().mkdirs();
            saveResource("blocks.yml", false);
        }
        blocksConfig = new YamlConfiguration();
        try {
            blocksConfig.load(blocksConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getLvlConfig(){
        return this.lvlConfig;
    }

    private void createLvlConfig() {
        lvlConfigFile = new File(getDataFolder(), "lvl.yml");
        if (!lvlConfigFile.exists()){
            lvlConfigFile.getParentFile().mkdirs();
            saveResource("lvl.yml", false);
        }
        lvlConfig = new YamlConfiguration();
        try {
            lvlConfig.load(lvlConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
