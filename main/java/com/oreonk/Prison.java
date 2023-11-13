package com.oreonk;

import com.oreonk.commands.*;
import com.oreonk.events.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

public class Prison extends JavaPlugin {
    private DatabaseCommand db;
    private File blocksConfigFile;
    private FileConfiguration blocksConfig;
    private File lvlConfigFile;
    private FileConfiguration lvlConfig;
    private File cellConfigFile;
    private FileConfiguration cellConfig;
    private File achievementsConfigFile;
    private FileConfiguration achievementsConfig;
    private static Prison instance;
    private static Economy econ = null;
    private static Permission perms = null;
    public HashMap<Player, Double> mltp = new HashMap<>() {};
    public HashMap<Player, Double> bst = new HashMap<>() {};
    public HashMap<Player, Integer> timer = new HashMap<>() {};
    public HashMap<Player, Integer> pvpTimer = new HashMap<>() {};
    public HashMap<UUID, Integer> booster = new HashMap<>() {}; //Глобал
    public HashMap<Player, Integer> thx = new HashMap<>() {};
    public HashMap<Player, Integer> lvl = new HashMap<>() {};
    public HashMap<Player, String> faction = new HashMap<>() {};
    public HashMap<String, Double> blocks = new HashMap<>() {};
    public HashMap<UUID, Integer> boosterBlocks = new HashMap<>() {};
    public HashMap<Player, Integer> tpTimer = new HashMap<>() {};
    public HashMap<Player, Integer> hide = new HashMap<>() {};

    //Биссы
    public HashMap<Player, Double> ice = new HashMap<>() {};
    public HashMap<Player, Double> wither = new HashMap<>() {};
    public HashMap<Player, Double> spirit = new HashMap<>() {};
    public HashMap<Player, Double> blaze = new HashMap<>() {};
    public HashMap<Player, Double> witch = new HashMap<>() {};
    public HashMap<Player, Double> miner = new HashMap<>() {};
    public HashMap<Player, Double> farmer = new HashMap<>() {};
    public HashMap<Player, Double> thief = new HashMap<>() {};
    //Фишка босса с воровством денег
    public HashMap<UUID, Double> thief_yoink = new HashMap<>() {};
    //кд в 24 часа от спешки у священника
    public HashMap<UUID, Integer> blessing = new HashMap<>() {};
    public ArrayList<String> blockTopUUIDS = new ArrayList<>() {};
    public ArrayList<String> lvlTopUUIDS = new ArrayList<>() {};
    public ArrayList<Integer> blockTopValues = new ArrayList<>() {};
    public ArrayList<Integer> lvlTopValues = new ArrayList<>() {};
    public List<String> DIG_ONE = new ArrayList<>();
    public List<String> KEY_ONE = new ArrayList<>();
    public List<String> BOSS_ONE = new ArrayList<>();
    public List<String> SELL_ONE = new ArrayList<>();
    public List<String> HOME_ONE = new ArrayList<>();
    public List<String> COLLECTION_ONE = new ArrayList<>();
    public List<String> FRACTION_ONE = new ArrayList<>();
    public List<String> HUNTER_ONE = new ArrayList<>();
    public List<String> SOLO_BOSS_ONE = new ArrayList<>();
    public List<String> ARMOR_ONE = new ArrayList<>();
    public List<String> BATTLEPASS_ONE = new ArrayList<>();
    public List<String> UNDERGROUND_ONE = new ArrayList<>();
    public List<String> HOLY_ONE = new ArrayList<>();
    public List<String> UPGRADE_ONE = new ArrayList<>();
    public List<String> FARM_ONE = new ArrayList<>();
    public List<String> COLLECTION_TWO = new ArrayList<>();
    public List<String> SWEAT_ONE = new ArrayList<>();
    public HashMap<Player, Integer> SWEAT_ONE_COUNT = new HashMap<>() {};

    //Вечная прибавка бустера от внутриигровой параши (БП) ((НЕ СТАТЫ)) (((ОБНОВЛЯЕТСЯ КОМАНДОЙ)))
    public HashMap<Player, Double> plusMultiplier = new HashMap<>() {};

    //Локальные временные бустеры
    public HashMap<UUID, Integer> privateBooster = new HashMap<>() {};
    public HashMap<UUID, Integer> privateBlockBooster = new HashMap<>() {};
    // if 1 = 1.5 , if 2 = 2
    public HashMap<UUID, Integer> privateBoosterMultiplier = new HashMap<>() {};
    public HashMap<UUID, Integer> privateBlockBoosterMultiplier = new HashMap<>() {};

    //Счётчик блоков для х1.5 бустера блоков
    public HashMap<Player, Integer> blockCounter = new HashMap<>() {};

    //Локальные временные бустеры мобов
    public HashMap<UUID, Integer> privateMobBooster = new HashMap<>() {};
    public HashMap<UUID, Integer> privateMobMultiplier = new HashMap<>() {};

    //Статы
    //АВТОРИТЕТ НУЖНО АПДЕЙТИТЬ
    public HashMap<Player, Double> authorityStat = new HashMap<>() {};
    public HashMap<Player, Integer> religion = new HashMap<>() {};
    public HashMap<Player, Integer> intellect = new HashMap<>() {};

    //Стрелки
    public HashMap<UUID, Integer> ezkiel_cd = new HashMap<>() {};
    public HashMap<UUID, Integer> ezkiel_arena_one = new HashMap<>() {};
    public HashMap<UUID, Integer> ezkiel_arena_two = new HashMap<>() {};
    public HashMap<UUID, Integer> ezkiel_arena_three = new HashMap<>() {};
    public HashMap<UUID, Integer> ezkiel_arena_four = new HashMap<>() {};
    public HashMap<UUID, Integer> ezkiel_arena_five = new HashMap<>() {};

    //Счётчик кейсов для ежедневного квеста
    public HashMap<UUID, Integer> cases_counter = new HashMap<>() {};

    //Счётчик пожертвований для ежедневного квеста
    public HashMap<UUID, Integer> church_counter = new HashMap<>() {};

    //Счётчик выпадений коллекционных предметов с блоков
    public HashMap<UUID, Integer> collection_counter = new HashMap<>() {};
    @Override
    public void onEnable() {
        instance = this;
        createBlocksConfig();
        createLvlConfig();
        createCellConfig();
        createAchievementConfig();
        saveDefaultConfig();
        loadBlocks();

        //Загрузка листов для ачивок
        DIG_ONE.addAll(achievementsConfig.getStringList("DIG_ONE"));
        KEY_ONE.addAll(achievementsConfig.getStringList("KEY_ONE"));
        BOSS_ONE.addAll(achievementsConfig.getStringList("BOSS_ONE"));
        SELL_ONE.addAll(achievementsConfig.getStringList("SELL_ONE"));
        HOME_ONE.addAll(achievementsConfig.getStringList("HOME_ONE"));
        COLLECTION_ONE.addAll(achievementsConfig.getStringList("COLLECTION_ONE"));
        FRACTION_ONE.addAll(achievementsConfig.getStringList("FRACTION_ONE"));
        HUNTER_ONE.addAll(achievementsConfig.getStringList("HUNTER_ONE"));
        SOLO_BOSS_ONE.addAll(achievementsConfig.getStringList("SOLO_BOSS_ONE"));
        ARMOR_ONE.addAll(achievementsConfig.getStringList("ARMOR_ONE"));
        BATTLEPASS_ONE.addAll(achievementsConfig.getStringList("BATTLEPASS_ONE"));
        UNDERGROUND_ONE.addAll(achievementsConfig.getStringList("UNDERGROUND_ONE"));
        HOLY_ONE.addAll(achievementsConfig.getStringList("HOLY_ONE"));
        UPGRADE_ONE.addAll(achievementsConfig.getStringList("UPGRADE_ONE"));
        FARM_ONE.addAll(achievementsConfig.getStringList("FARM_ONE"));
        COLLECTION_TWO.addAll(achievementsConfig.getStringList("COLLECTION_TWO"));
        SWEAT_ONE.addAll(achievementsConfig.getStringList("SWEAT_ONE"));

        System.out.println("Prison by oreonk kekw");
        setupPermissions();
        this.db = new SQLite(this);
        this.db.load();
        this.db.createTable();
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
        getServer().getPluginManager().registerEvents(new CellWorldInteract(), this);
        getServer().getPluginManager().registerEvents(new ReligionBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new UniqueDrops(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakMobSpawn(), this);
        getServer().getPluginManager().registerEvents(new WoodEvent(), this);
        getServer().getPluginManager().registerEvents(new MobKillAchievement(), this);
        getServer().getPluginManager().registerEvents(new MoneyReceivedEvent(), this);
        getServer().getPluginManager().registerEvents(new BattlePassAchievement(), this);
        getServer().getPluginManager().registerEvents(new AchievementInteract(), this);
        getServer().getPluginManager().registerEvents(new MobKillBooster(), this);
        getServer().getPluginManager().registerEvents(new AuthorityMobCounter(), this);
        getServer().getPluginManager().registerEvents(new AuctionCommission(), this);
        getServer().getPluginManager().registerEvents(new BossWorldInteract(), this);
        getServer().getPluginManager().registerEvents(new CollectionGui(), this);
        getServer().getPluginManager().registerEvents(new TestInteract(), this);
        getServer().getPluginManager().registerEvents(new MainMenuInteract(), this);
        getServer().getPluginManager().registerEvents(new ChurchInteract(), this);
        getServer().getPluginManager().registerEvents(new CasesCounter(), this);
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
        this.getCommand("achievements").setExecutor(new Achievements());
        this.getCommand("adminmoney").setExecutor(new AdminMoney());
        this.getCommand("balance").setExecutor(new Balance());
        this.getCommand("localblockboost").setExecutor(new LocalBlockBooster());
        this.getCommand("plml").setExecutor(new Plml());
        this.getCommand("admintakemoney").setExecutor(new AdminTakeMoney());
        this.getCommand("admincommissionadd").setExecutor(new AdminCommissionAdd());
        this.getCommand("localmobboost").setExecutor(new LocalMobBoost());
        this.getCommand("menu").setExecutor(new Menu());
        this.getCommand("moneylvlpercent").setExecutor(new MoneyLvlPercent());
        this.getCommand("givestars").setExecutor(new GiveStars());
        getServer().getPluginManager().registerEvents(new DonateMenus(), this);
        new Placeholders().register();
        new BukkitRunnable() {
            public void run() {
                if (!blockTopUUIDS.isEmpty()){
                    blockTopUUIDS.clear();
                }
                if (!blockTopValues.isEmpty()){
                    blockTopValues.clear();
                }
                if (!lvlTopUUIDS.isEmpty()){
                    lvlTopUUIDS.clear();
                }
                if (!lvlTopValues.isEmpty()){
                    lvlTopValues.clear();
                }
                DatabaseCommand database = getDatabase();
                int a = 1;
                int b = 1;
                int c = 1;
                int d = 1;
                while (a <= 10) {
                    lvlTopUUIDS.add(database.getLvlTopName(a));
                    a ++;
                }
                while (b <= 10){
                    blockTopUUIDS.add(database.getBlockTopName(b));
                    b++;
                }
                while (c <= 10){
                    blockTopValues.add(database.getBlockTop(c));
                    c++;
                }
                while (d <= 10){
                    lvlTopValues.add(database.getLvlTop(d));
                    d++;
                }
                Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "hd reload");
            }
        }.runTaskTimer(Prison.getInstance(), 0, 12000);
        if (!setupEconomy() ) {
            System.out.println("Нет плагина с экономикой");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (this.getDatabase().getBlockBstUuid() != null &&this.getDatabase().getBlockBstTime() != 0 && !this.getDatabase().getBlockBstUuid().equals("0") && this.getDatabase().getBlockBstTime() != null){
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

            //Очистка массива для ачивки на 300к блоков + рестарт
            //new BukkitRunnable() {
            //        public void run() {
            //            long currentTime = System.currentTimeMillis();
            //            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
            //            Date date = new Date(currentTime);
            //            String time = simpleDateFormat.format(date);
            //            getLogger().log(Level.INFO, "Время " + time);
            //            if (time.equals("00:00")){
            //                SWEAT_ONE_COUNT.clear();
            //                ezkiel_cd.clear();
            //                Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
            //                new BukkitRunnable() {
            //                    @Override
            //                    public void run() {
            //                        Prison.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
            //                    }
            //                }.runTaskLater(Prison.getInstance(), 60);
            //           }
            //        }
            //}.runTaskTimer(Prison.getInstance(), 0, 1200);

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
        new BukkitRunnable(){
            public void run(){
                if (getDatabase().farmPurchased()){
                    getDatabase().addCellCash(20);
                }
            }
        }.runTaskTimer(Prison.getInstance(),0,12000);

        //Таймер убавления времени личного бустера
        new BukkitRunnable(){
            public void run(){
                if (!privateBooster.isEmpty()){
                    for (Map.Entry<UUID, Integer> entry : privateBooster.entrySet()) {
                        int time = (entry.getValue())-1;
                        if (time > 0){
                            entry.setValue(time);
                        } else {
                            privateBooster.remove(entry.getKey());
                            privateBoosterMultiplier.remove(entry.getKey());
                            getDatabase().clearLocalMoneyBoosterInfo(entry.getKey());
                            if (Bukkit.getPlayer(entry.getKey()).isOnline()) {
                                Msg.send(Bukkit.getPlayer(entry.getKey()), ChatColor.RED + "Ваш локальный бустер денег закончился!");
                            }
                        }
                    }
                }
                if (!privateBlockBooster.isEmpty()){
                    for (Map.Entry<UUID, Integer> entry : privateBlockBooster.entrySet()) {
                        int time = (entry.getValue())-1;
                        if (time > 0){
                            entry.setValue(time);
                        } else {
                            privateBlockBooster.remove(entry.getKey());
                            privateBlockBoosterMultiplier.remove(entry.getKey());
                            getDatabase().clearLocalBlockBoosterInfo(entry.getKey());
                            if (Bukkit.getPlayer(entry.getKey()).isOnline()) {
                                Msg.send(Bukkit.getPlayer(entry.getKey()), ChatColor.RED + "Ваш локальный бустер блоков закончился!");
                            }
                        }
                    }
                }
                if (!privateMobBooster.isEmpty()){
                    for (Map.Entry<UUID, Integer> entry : privateMobBooster.entrySet()) {
                        int time = (entry.getValue())-1;
                        if (time > 0){
                            entry.setValue(time);
                        } else {
                            privateMobBooster.remove(entry.getKey());
                            privateMobMultiplier.remove(entry.getKey());
                            getDatabase().clearLocalMobBoosterInfo(entry.getKey());
                            if (Bukkit.getPlayer(entry.getKey()).isOnline()) {
                                Msg.send(Bukkit.getPlayer(entry.getKey()), ChatColor.RED + "Ваш локальный бустер мобов закончился!");
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Prison.getInstance(),0,1200);
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
    public DatabaseCommand getDatabase(){ return this.db; }
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
        if (!privateBoosterMultiplier.isEmpty() && !privateBooster.isEmpty()){
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).privateBooster.entrySet()) {
                this.getDatabase().sendLocalMoneyBoosterInfo(entry.getKey());
            }
        }
        if (!privateBlockBoosterMultiplier.isEmpty() && !privateBlockBooster.isEmpty()){
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).privateBlockBooster.entrySet()) {
                this.getDatabase().sendLocalBlockBoosterInfo(entry.getKey());
            }
        }
        if (!privateMobMultiplier.isEmpty() && !privateMobBooster.isEmpty()){
            for (Map.Entry<UUID, Integer> entry : Prison.getPlugin(Prison.class).privateMobBooster.entrySet()) {
                this.getDatabase().sendLocalMobBoosterInfo(entry.getKey());
            }
        }
        if (!authorityStat.isEmpty()){
            for (Map.Entry<Player, Double> entry : Prison.getPlugin(Prison.class).authorityStat.entrySet()) {
                int amount = (int) Math.round(entry.getValue());
                getDatabase().setAuthorityStat(entry.getKey(), amount);
            }
        }
        if (!religion.isEmpty()){
            for (Map.Entry<Player, Integer> entry : Prison.getInstance().religion.entrySet()) {
                getDatabase().setReligionStat(entry.getKey(), entry.getValue());
            }
        }
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
    public FileConfiguration getAchievementsConfig() {return this.achievementsConfig;}

    public File getAchievementsConfigFile() {return this.achievementsConfigFile;}

    private void createAchievementConfig() {
        achievementsConfigFile = new File(getDataFolder(), "achievements.yml");
        if (!achievementsConfigFile.exists()){
            achievementsConfigFile.getParentFile().mkdirs();
            saveResource("achievements.yml", false);
        }
        achievementsConfig = new YamlConfiguration();
        try {
            achievementsConfig.load(achievementsConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    private void loadBlocks() {
        int size = getBlocksConfig().getConfigurationSection("blocks").getKeys(false).size();
        for(int i = 1; i <= size; i++) {
            String blockName = getBlocksConfig().getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(0);
            String priceString = getBlocksConfig().getConfigurationSection("blocks").getStringList(String.valueOf(i)).get(1);
            double price = Double.parseDouble(priceString);
            blocks.put(blockName, price);
        }
    }
    public int getCommission(Player player){
        if (getDatabase().getCommission(player) == 0){
            return 20;
        } else if (getDatabase().getCommission(player) == 1){
            return 15;
        } else if (getDatabase().getCommission(player) == 2){
            return 10;
        } else if (getDatabase().getCommission(player) > 2){
            return 5;
        }
        return 20;
    }
    public int getMinedBlocks(Player player){
        String st = PlaceholderAPI.setPlaceholders(player, "%statistic_mine_block:OAK_LOG,DARK_OAK_LOG,OAK_LEAVES,DIRT,SAND,GRAVEL,COBBLESTONE,STONE,COAL_ORE,IRON_ORE,GOLD_ORE,COAL_BLOCK,VINE,RED_SAND,STRING,MELON,PUMPKIN,HAY_BLOCK,CLAY,WHITE_CONCRETE_POWDER,SNOW_BLOCK,CYAN_TERRACOTTA,MOSSY_COBBLESTONE,SANDSTONE,RED_SANDSTONE,GOLD_BLOCK,DIAMOND_ORE,ACACIA_LOG,RED_TERRACOTTA,NETHERRACK,NETHER_QUARTZ_ORE,SOUL_SAND,EMERALD_ORE,CRACKED_STONE_BRICKS,STONE_BRICKS,MOSSY_STONE_BRICKS,CHISELED_STONE_BRICKS,PURPUR_BLOCK,END_STONE_BRICKS,GLOWSTONE%");
        return Integer.parseInt(st);
    }
}
