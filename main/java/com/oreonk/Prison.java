package com.oreonk;

import com.oreonk.commands.*;
import com.oreonk.events.*;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Prison extends JavaPlugin {
    public  MySQL SQL;
    public  DatabaseCommand data;
    private File blocksConfigFile;
    private FileConfiguration blocksConfig;
    private File lvlConfigFile;
    private FileConfiguration lvlConfig;
    private File cellConfigFile;
    private FileConfiguration cellConfig;
    private static Prison instance;
    private static Economy econ = null;
    private static Permission perms = null;
    public HashMap<Player, Double> mltp = new HashMap<>() {};
    public HashMap<Player, Integer> religion = new HashMap<>() {};
    public HashMap<Player, Double> bst = new HashMap<>() {};
    public HashMap<Player, Integer> timer = new HashMap<>() {};
    public HashMap<Player, Integer> pvpTimer = new HashMap<>() {};
    public HashMap<UUID, Integer> booster = new HashMap<>() {}; //Глобал
    public HashMap<Player, Integer> thx = new HashMap<>() {};
    public HashMap<Player, Integer> lvl = new HashMap<>() {};
    public HashMap<Player, String> faction = new HashMap<>() {};
    public HashMap<Player, Double> minotaur = new HashMap<>() {};
    public HashMap<Player, Double> knight = new HashMap<>() {};
    public HashMap<Player, Double> ice = new HashMap<>() {};
    public HashMap<String, Integer> blocks = new HashMap<>() {};
    public HashMap<UUID, Integer> boosterBlocks = new HashMap<>() {};
    public HashMap<Player, Integer> tpTimer = new HashMap<>() {};
    public HashMap<UUID, Integer> privateBooster = new HashMap<>() {};
    public HashMap<UUID, Integer> privateBlockBooster = new HashMap<>() {};
    public HashMap<Player, Integer> hide = new HashMap<>() {};
    public HashMap<Player, Double> wither = new HashMap<>() {};
    public HashMap<Player, Double> spider = new HashMap<>() {};
    public HashMap<Player, Double> spirit = new HashMap<>() {};
    public HashMap<Player, Double> blaze = new HashMap<>() {};
    public HashMap<Player, Double> witch = new HashMap<>() {};
    public HashMap<Player, Double> slime = new HashMap<>() {};
    public HashMap<Player, Double> skeleton = new HashMap<>() {};
    @Override
    public void onEnable() {
        instance = this;
        createBlocksConfig();
        createLvlConfig();
        createCellConfig();
        saveDefaultConfig();
        loadBlocks();
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
        getServer().getPluginManager().registerEvents(new StatHud(), this);
        getServer().getPluginManager().registerEvents(new DBJoin(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new BlockSell(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new Enchant(), this);
        getServer().getPluginManager().registerEvents(new CraftCancel(), this);
        getServer().getPluginManager().registerEvents(new KeyDrop(), this);
        getServer().getPluginManager().registerEvents(new FactionsMenu(), this);
        getServer().getPluginManager().registerEvents(new MobSpawnDisable(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new CustomDrops(), this);
        getServer().getPluginManager().registerEvents(new RightClickCancel(), this);
        getServer().getPluginManager().registerEvents(new PremiumMines(), this);
        getServer().getPluginManager().registerEvents(new Drop(), this);
        getServer().getPluginManager().registerEvents(new BlockTop(), this);
        getServer().getPluginManager().registerEvents(new AxeDamageCancel(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new CellWorld(), this);
        getServer().getPluginManager().registerEvents(new ReligionBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new UniqueDrops(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakMobSpawn(), this);
        getServer().getPluginManager().registerEvents(new WoodEvent(), this);
        this.getCommand("hide").setExecutor(new Hide());
        this.getCommand("mine").setExecutor(new Mine());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("lvl").setExecutor(new Lvl());
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("rubbish").setExecutor(new Rubbish());
        this.getCommand("base").setExecutor(new Base());
        this.getCommand("upgrade").setExecutor(new Upgrade());
        this.getCommand("shop").setExecutor(new Donate());
        this.getCommand("thx").setExecutor(new Thx());
        this.getCommand("autosell").setExecutor(new AutoSell());
        this.getCommand("faction").setExecutor(new Factions());
        this.getCommand("adminbooster").setExecutor(new AdminBooster());
        this.getCommand("localboost").setExecutor(new LocalBoost());
        this.getCommand("adminlvl").setExecutor(new AdminLvl());
        this.getCommand("statwipe").setExecutor(new StatWipe());
        this.getCommand("cell").setExecutor(new Cell());
        this.getCommand("pay").setExecutor(new Pay());
        getServer().getPluginManager().registerEvents(new DonateMenus(), this);
        new Placeholders().register();
        new BukkitRunnable() {
            public void run() {
                Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "hd reload");
            }
        }.runTaskTimer(Prison.getInstance(), 0, 12000);
        if (!setupEconomy() ) {
            System.out.println("Нет плагина с экономикой");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (this.getDatabase().getBlockBstUuid() != null &&this.getDatabase().getBlockBstTime() != 0 && !this.getDatabase().getBlockBstUuid().equals("0")){
                boosterBlocks.put(UUID.fromString(this.getDatabase().getBlockBstUuid()), this.getDatabase().getBlockBstTime());
            }
        else if (this.getDatabase().getMoneyBstUuid() != null && this.getDatabase().getMoneyBstTime() != null && !this.getDatabase().getMoneyBstUuid().equals("0") && this.getDatabase().getMoneyBstTime() != 0){
                booster.put(UUID.fromString(this.getDatabase().getMoneyBstUuid()), this.getDatabase().getMoneyBstTime());
        }
        if (!boosterBlocks.isEmpty()) {
            int displayTime = 0;
            String playerName = null ;
                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                    displayTime = entry.getValue();
                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                    } else {
                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                    }
                }
            BossBar bossBar = Bukkit.createBossBar("Активен бустер блоков игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
            bossBar.setVisible(true);
            new BukkitRunnable() {
                    public void run() {
                        int time = 0;
                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                            time = entry.getValue();
                            String player;
                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                player = Bukkit.getPlayer(entry.getKey()).getName();
                            } else {
                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                            }
                            bossBar.setTitle("Активен бустер блоков игрока " + player + ". Осталось " +  time + " минут");
                        }
                        if (time <= 0){
                            this.cancel();
                            bossBar.removeAll();
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 1200, 1200);
            new BukkitRunnable() {
                public void run() {
                    int time = 0;
                    for (Map.Entry<UUID, Integer> entry : boosterBlocks.entrySet()) {
                        time = entry.getValue();
                    }
                    for (Map.Entry<UUID, Integer> entry : boosterBlocks.entrySet()) {
                        entry.setValue(time - 1);
                    }
                    if (time - 1 <= 0) {
                        Prison.getInstance().boosterBlocks.clear();
                        getDatabase().clearBlockBst();
                        this.cancel();
                    }
                }
            }.runTaskTimer(Prison.getInstance(), 1200, 1200);
        }
        if (!booster.isEmpty()) {
            int displayTime = 0;
            String playerName = null ;
                for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                    displayTime = entry.getValue();
                    if (Bukkit.getPlayer(entry.getKey()) != null) {
                        playerName = Bukkit.getPlayer(entry.getKey()).getName();
                    } else {
                        playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                    }
                }
            BossBar bossBar = Bukkit.createBossBar("Активен бустер монет игрока " + playerName + ". Осталось " + displayTime + " минут", BarColor.BLUE, BarStyle.SOLID);
            bossBar.setVisible(true);
            new BukkitRunnable() {
                    public void run() {
                        int time = 0;
                        for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                            time = entry.getValue();
                            String player;
                            if (Bukkit.getPlayer(entry.getKey()) != null) {
                                player = Bukkit.getPlayer(entry.getKey()).getName();
                            } else {
                                player = Bukkit.getOfflinePlayer(entry.getKey()).getName();
                            }
                            bossBar.setTitle("Активен бустер монет игрока " + player + ". Осталось " +  time + " минут");
                        }
                        if (time <= 0){
                            this.cancel();
                            bossBar.removeAll();
                        }
                    }
                }.runTaskTimer(Prison.getInstance(), 1200, 1200);
            new BukkitRunnable() {
                public void run() {
                    int time = 0;
                    for (Map.Entry<UUID, Integer> entry : booster.entrySet()) {
                        time = entry.getValue();
                    }
                    for (Map.Entry<UUID, Integer> entry : booster.entrySet()) {
                        entry.setValue(time - 1);
                    }
                    if (time - 1 <= 0) {
                        Prison.getInstance().booster.clear();
                        getDatabase().clearMoneyBst();
                        this.cancel();
                    }
                }
            }.runTaskTimer(Prison.getInstance(), 1200, 1200);
        }
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
        if (!boosterBlocks.isEmpty()) {
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).boosterBlocks.entrySet()) {
                this.getDatabase().insertBlockBst(entry.getValue(),entry.getKey());
            }
        }
        if (!booster.isEmpty()) {
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).booster.entrySet()) {
                this.getDatabase().insertMoneyBst(entry.getKey(), entry.getValue());
            }
        }
        SQL.disconnect();
    }
    public static Prison getInstance(){
        return instance;
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

    public FileConfiguration getCellConfig() {return this.cellConfig;}

    private void createCellConfig() {
        cellConfigFile = new File(getDataFolder(), "cell.yml");
        if (!cellConfigFile.exists()){
            cellConfigFile.getParentFile().mkdirs();
            saveResource("cell.yml", false);
        }
        cellConfig = new YamlConfiguration();
        try {
            cellConfig.load(cellConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    private void loadBlocks() {
        int size = getBlocksConfig().getConfigurationSection("blocks").getKeys(false).size();
        for(int i = 1; i <= size; i++) {
            String blockName = getBlocksConfig().getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(0);
            String priceString = getBlocksConfig().getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(1);
            int price = Integer.parseInt(priceString);
            blocks.put(blockName, price);
        }
    }
}
