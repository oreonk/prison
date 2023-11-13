package com.oreonk;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class Placeholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "Prison";
    }

    @Override
    public @NotNull String getAuthor() {
        return "oreonk";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "";
        }
        if (params.equalsIgnoreCase("lvl")) {
            return String.valueOf(Prison.getPlugin(Prison.class).lvl.get(player));
        }
        if (params.equalsIgnoreCase("color")) {
            if (Prison.getInstance().faction.get(player).equals("0")){
                return "";
            }
            if (Prison.getInstance().faction.get(player).equals("white")){
                return "&f";
            } else if (Prison.getInstance().faction.get(player).equals("black")){
                return "&8";
            } else if (Prison.getInstance().faction.get(player).equals("asian")){
                return "&e";
            }
        }
        //Процент от денег, нужных на ап уровня
        if (params.toLowerCase().contains("lvlpercent_")){
            String numberOnly= params.replaceAll("[^0-9]", "");
            double amount = Double.parseDouble(numberOnly);
            //Выборка следующего уровня игрока
            int lvl = (Prison.getInstance().lvl.get(player)) + 1;
            ArrayList<String> config = (ArrayList<String>) Prison.getInstance().getLvlConfig().getConfigurationSection("Lvl").getStringList(String.valueOf(lvl));
            String stringAmount = config.get(1);
            Double finalAmount = Integer.parseInt(stringAmount) * amount / 100;
            return String.valueOf(finalAmount);
        }

        //Открытые кейсы до рестарта
        if (params.equalsIgnoreCase("cases")){
            return String.valueOf(Prison.getInstance().cases_counter.get(player.getUniqueId()));
        }

        //Сделал ли игрок пожертвование в церковь
        if (params.equalsIgnoreCase("church")){
            return String.valueOf(Prison.getInstance().church_counter.get(player.getUniqueId()));
        }

        //Количество найденых в блоках коллекционных предметов
        if (params.equalsIgnoreCase("collection")){
            return String.valueOf(Prison.getInstance().collection_counter.get(player.getUniqueId()));
        }

        //НУЖНО АПДЕЙТИТЬ
        if (params.equalsIgnoreCase("authority")){
            return String.valueOf(Prison.getInstance().authorityStat.get(player));
        }

        if (params.equalsIgnoreCase("balance")){

            double balance = Prison.getEconomy().getBalance(player);
            DecimalFormat decimalFormat = new DecimalFormat("##################0.###");
            return decimalFormat.format(balance).replace("," , ".");
        }

        //Топы
        if (params.equalsIgnoreCase("block_top_1")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(0));
        }
        if (params.equalsIgnoreCase("block_topName_1")) {
            if (!Prison.getInstance().blockTopUUIDS.get(0).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(0))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(0))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(0))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_2")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(1));
        }
        if (params.equalsIgnoreCase("block_topName_2")) {
            if (!Prison.getInstance().blockTopUUIDS.get(1).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(1))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(1))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(1))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_3")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(2));
        }
        if (params.equalsIgnoreCase("block_topName_3")) {
            if (!Prison.getInstance().blockTopUUIDS.get(2).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(2))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(2))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(2))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_4")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(3));
        }
        if (params.equalsIgnoreCase("block_topName_4")) {
            if (!Prison.getInstance().blockTopUUIDS.get(3).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(3))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(3))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(3))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_5")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(4));
        }
        if (params.equalsIgnoreCase("block_topName_5")) {
            if (!Prison.getInstance().blockTopUUIDS.get(4).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(4))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(4))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(4))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_6")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(5));
        }
        if (params.equalsIgnoreCase("block_topName_6")) {
            if (!Prison.getInstance().blockTopUUIDS.get(5).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(5))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(5))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(5))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_7")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(6));
        }
        if (params.equalsIgnoreCase("block_topName_7")) {
            if (!Prison.getInstance().blockTopUUIDS.get(6).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(6))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(6))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(6))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_8")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(7));
        }
        if (params.equalsIgnoreCase("block_topName_8")) {
            if (!Prison.getInstance().blockTopUUIDS.get(7).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(7))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(7))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(7))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_9")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(8));
        }
        if (params.equalsIgnoreCase("block_topName_9")) {
            if (!Prison.getInstance().blockTopUUIDS.get(8).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(8))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(8))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(8))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_10")){
            return String.valueOf(Prison.getInstance().blockTopValues.get(9));
        }
        if (params.equalsIgnoreCase("block_topName_10")) {
            if (!Prison.getInstance().blockTopUUIDS.get(9).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(9))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(9))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().blockTopUUIDS.get(9))).getName();
                }
            } else return "Место свободно..";
        }

        if (params.equalsIgnoreCase("lvl_top_1")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(0));
        }
        if (params.equalsIgnoreCase("lvl_topName_1")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(0).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(0))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(0))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(0))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_2")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(1));
        }
        if (params.equalsIgnoreCase("lvl_topName_2")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(1).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(1))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(1))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(1))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_3")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(2));
        }
        if (params.equalsIgnoreCase("lvl_topName_3")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(2).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(2))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(2))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(2))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_4")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(3));
        }
        if (params.equalsIgnoreCase("lvl_topName_4")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(3).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(3))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(3))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(3))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_5")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(4));
        }
        if (params.equalsIgnoreCase("lvl_topName_5")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(4).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(4))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(4))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(4))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_6")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(5));
        }
        if (params.equalsIgnoreCase("lvl_topName_6")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(5).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(5))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(5))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(5))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_7")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(6));
        }
        if (params.equalsIgnoreCase("lvl_topName_7")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(6).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(6))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(6))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(6))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_8")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(7));
        }
        if (params.equalsIgnoreCase("lvl_topName_8")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(7).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(7))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(7))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(7))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_9")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(8));
        }
        if (params.equalsIgnoreCase("lvl_topName_9")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(8).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(8))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(8))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(8))).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_10")){
            return String.valueOf(Prison.getInstance().lvlTopValues.get(9));
        }
        if (params.equalsIgnoreCase("lvl_topName_10")) {
            if (!Prison.getInstance().lvlTopUUIDS.get(9).equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(9))) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(9))).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().lvlTopUUIDS.get(9))).getName();
                }
            } else return "Место свободно..";
        }
        return null;
    }
}
