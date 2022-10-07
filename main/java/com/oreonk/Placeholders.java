package com.oreonk;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        if (params.equalsIgnoreCase("block_top_1")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopOne());
        }
        if (params.equalsIgnoreCase("block_topName_1")) {
            if (!Prison.getInstance().getDatabase().getBlockTopOneName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopOneName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopOneName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopOneName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_2")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopTwo());
        }
        if (params.equalsIgnoreCase("block_topName_2")) {
            if (!Prison.getInstance().getDatabase().getBlockTopTwoName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopTwoName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopTwoName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopTwoName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_3")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopThree());
        }
        if (params.equalsIgnoreCase("block_topName_3")) {
            if (!Prison.getInstance().getDatabase().getBlockTopThreeName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopThreeName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopThreeName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopThreeName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_4")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopFour());
        }
        if (params.equalsIgnoreCase("block_topName_4")) {
            if (!Prison.getInstance().getDatabase().getBlockTopFourName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopFourName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopFourName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopFourName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_5")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopFive());
        }
        if (params.equalsIgnoreCase("block_topName_5")) {
            if (!Prison.getInstance().getDatabase().getBlockTopFiveName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopFiveName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopFiveName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopFiveName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_6")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopSix());
        }
        if (params.equalsIgnoreCase("block_topName_6")) {
            if (!Prison.getInstance().getDatabase().getBlockTopSixName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopSixName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopSixName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopSixName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_7")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopSeven());
        }
        if (params.equalsIgnoreCase("block_topName_7")) {
            if (!Prison.getInstance().getDatabase().getBlockTopSevenName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopSevenName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopSevenName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopSevenName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_8")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopEight());
        }
        if (params.equalsIgnoreCase("block_topName_8")) {
            if (!Prison.getInstance().getDatabase().getBlockTopEightName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopEightName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopEightName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopEightName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_9")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopNine());
        }
        if (params.equalsIgnoreCase("block_topName_9")) {
            if (!Prison.getInstance().getDatabase().getBlockTopNineName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopNineName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopNineName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopNineName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("block_top_10")) {
            return String.valueOf(Prison.getInstance().getDatabase().getBlockTopTen());
        }
        if (params.equalsIgnoreCase("block_topName_10")) {
            if (!Prison.getInstance().getDatabase().getBlockTopTenName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopTenName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopTenName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getBlockTopTenName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_1")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopOne());
        }
        if (params.equalsIgnoreCase("lvl_topName_1")) {
            if (!Prison.getInstance().getDatabase().getLvlTopOneName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopOneName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopOneName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopOneName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_2")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopTwo());
        }
        if (params.equalsIgnoreCase("lvl_topName_2")) {
            if (!Prison.getInstance().getDatabase().getLvlTopTwoName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopTwoName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopTwoName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopTwoName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_3")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopThree());
        }
        if (params.equalsIgnoreCase("lvl_topName_3")) {
            if (!Prison.getInstance().getDatabase().getLvlTopThreeName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopThreeName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopThreeName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopThreeName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_4")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopFour());
        }
        if (params.equalsIgnoreCase("lvl_topName_4")) {
            if (!Prison.getInstance().getDatabase().getLvlTopFourName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopFourName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopFourName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopFourName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_5")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopFive());
        }
        if (params.equalsIgnoreCase("lvl_topName_5")) {
            if (!Prison.getInstance().getDatabase().getLvlTopFiveName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopFiveName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopFiveName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopFiveName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_6")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopSix());
        }
        if (params.equalsIgnoreCase("lvl_topName_6")) {
            if (!Prison.getInstance().getDatabase().getLvlTopSixName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopSixName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopSixName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopSixName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_7")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopSeven());
        }
        if (params.equalsIgnoreCase("lvl_topName_7")) {
            if (!Prison.getInstance().getDatabase().getLvlTopSevenName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopSevenName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopSevenName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopSevenName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_8")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopEight());
        }
        if (params.equalsIgnoreCase("lvl_topName_8")) {
            if (!Prison.getInstance().getDatabase().getLvlTopEightName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopEightName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopEightName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopEightName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_9")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopNine());
        }
        if (params.equalsIgnoreCase("lvl_topName_9")) {
            if (!Prison.getInstance().getDatabase().getLvlTopNineName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopNineName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopNineName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopNineName())).getName();
                }
            } else return "Место свободно..";
        }
        if (params.equalsIgnoreCase("lvl_top_10")) {
            return String.valueOf(Prison.getInstance().getDatabase().getLvlTopTen());
        }
        if (params.equalsIgnoreCase("lvl_topName_10")) {
            if (!Prison.getInstance().getDatabase().getLvlTopTenName().equals("")) {
                if (Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopTenName())) != null) {
                    return Bukkit.getPlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopTenName())).getName();
                } else {
                    return Bukkit.getOfflinePlayer(UUID.fromString(Prison.getInstance().getDatabase().getLvlTopTenName())).getName();
                }
            } else return "Место свободно..";
        }
        return null;
    }
}
