package com.oreonk.events;

import com.oreonk.Msg;
import com.oreonk.Prison;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;

public class AuthorityMobCounter implements Listener {
    //Цифры в комментах - количество мобов, которых нужно убить для капа авторитета с них
    //10
    public HashMap<Player, Integer> SECURITY_ONE = new HashMap<>() {};
    public HashMap<Player, Integer> SECURITY_ONE_COUNTER = new HashMap<>() {};
    //14
    public HashMap<Player, Integer> SECURITY_TWO = new HashMap<>() {};
    public HashMap<Player, Integer> SECURITY_TWO_COUNTER = new HashMap<>() {};
    //20
    public HashMap<Player, Integer> SECURITY_THREE = new HashMap<>() {};
    public HashMap<Player, Integer> SECURITY_THREE_COUNTER = new HashMap<>() {};
    //40
    public HashMap<Player, Integer> SECURITY_FOUR = new HashMap<>() {};
    public HashMap<Player, Integer> SECURITY_FOUR_COUNTER = new HashMap<>() {};
    //100
    public HashMap<Player, Integer> SECURITY_FIVE = new HashMap<>() {};
    public HashMap<Player, Integer> SECURITY_FIVE_COUNTER = new HashMap<>() {};
    //10
    public HashMap<Player, Integer> SLIME = new HashMap<>() {};
    public HashMap<Player, Integer> SLIME_COUNTER = new HashMap<>() {};
    //14
    public HashMap<Player, Integer> SPIDER = new HashMap<>() {};
    public HashMap<Player, Integer> SPIDER_COUNTER = new HashMap<>() {};
    //20
    public HashMap<Player, Integer> SILVERFISH = new HashMap<>() {};
    public HashMap<Player, Integer> SILVERFISH_COUNTER = new HashMap<>() {};
    //40
    public HashMap<Player, Integer> ZOMBIE = new HashMap<>() {};
    public HashMap<Player, Integer> ZOMBIE_COUNTER = new HashMap<>() {};
    //100
    public HashMap<Player, Integer> VAMPIRE = new HashMap<>() {};
    public HashMap<Player, Integer> VAMPIRE_COUNTER = new HashMap<>() {};
    @EventHandler
    public void onKill(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof LivingEntity && event.getEntity().getCustomName() != null) {
            if (((LivingEntity) event.getEntity()).getHealth() <= event.getDamage() && event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (event.getEntity().getCustomName().equalsIgnoreCase("Слабый охранник")) {
                    if (!SECURITY_ONE.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_ONE")) + 1;
                        SECURITY_ONE.put(player, amount);
                    } else {
                        if (SECURITY_ONE.get(player) <= 10) {
                            int amount = (SECURITY_ONE.get(player)) + 1;
                            SECURITY_ONE.replace(player, amount);
                        }
                    }
                    if (SECURITY_ONE.get(player) <= 10) {
                        if (!SECURITY_ONE_COUNTER.containsKey(player)) {
                            SECURITY_ONE_COUNTER.put(player, 1);
                        } else {
                            int amount = (SECURITY_ONE_COUNTER.get(player) + 1);
                            SECURITY_ONE_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SECURITY_ONE_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_ONE") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SECURITY_ONE", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 2.0);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Ожившая сопля")){
                    if (!SLIME.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SLIME")) + 1;
                        SLIME.put(player, amount);
                    } else {
                        if (SLIME.get(player) <= 10) {
                            int amount = (SLIME.get(player)) + 1;
                            SLIME.replace(player, amount);
                        }
                    }
                    if (SLIME.get(player) <= 10) {
                        if (!SLIME_COUNTER.containsKey(player)) {
                            SLIME_COUNTER.put(player, 1);
                        } else {
                            int amount = (SLIME_COUNTER.get(player) + 1);
                            SLIME_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SLIME_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SLIME") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SLIME", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 2.0);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Охранник")){
                    if (!SECURITY_TWO.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_TWO")) + 1;
                        SECURITY_TWO.put(player, amount);
                    } else {
                        if (SECURITY_TWO.get(player) <= 14) {
                            int amount = (SECURITY_TWO.get(player)) + 1;
                            SECURITY_TWO.replace(player, amount);
                        }
                    }
                    if (SECURITY_TWO.get(player) <= 14) {
                        if (!SECURITY_TWO_COUNTER.containsKey(player)) {
                            SECURITY_TWO_COUNTER.put(player, 1);
                        } else {
                            int amount = (SECURITY_TWO_COUNTER.get(player) + 1);
                            SECURITY_TWO_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SECURITY_TWO_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_TWO") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SECURITY_TWO", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 1.5);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Тарантул")){
                    if (!SPIDER.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SPIDER")) + 1;
                        SPIDER.put(player, amount);
                    } else {
                        if (SPIDER.get(player) <= 14) {
                            int amount = (SPIDER.get(player)) + 1;
                            SPIDER.replace(player, amount);
                        }
                    }
                    if (SPIDER.get(player) <= 14) {
                        if (!SPIDER_COUNTER.containsKey(player)) {
                            SPIDER_COUNTER.put(player, 1);
                        } else {
                            int amount = (SPIDER_COUNTER.get(player) + 1);
                            SPIDER_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SPIDER_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SPIDER") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SPIDER", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 1.5);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Опытный охранник")){
                    if (!SECURITY_THREE.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_THREE")) + 1;
                        SECURITY_THREE.put(player, amount);
                    } else {
                        if (SECURITY_THREE.get(player) <= 20) {
                            int amount = (SECURITY_THREE.get(player)) + 1;
                            SECURITY_THREE.replace(player, amount);
                        }
                    }
                    if (SECURITY_THREE.get(player) <= 20) {
                        if (!SECURITY_THREE_COUNTER.containsKey(player)) {
                            SECURITY_THREE_COUNTER.put(player, 1);
                        } else {
                            int amount = (SECURITY_THREE_COUNTER.get(player) + 1);
                            SECURITY_THREE_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SECURITY_THREE_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_THREE") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SECURITY_THREE", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 1.0);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Опоссум")){
                    if (!SILVERFISH.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SILVERFISH")) + 1;
                        SILVERFISH.put(player, amount);
                    } else {
                        if (SILVERFISH.get(player) <= 20) {
                            int amount = (SILVERFISH.get(player)) + 1;
                            SILVERFISH.replace(player, amount);
                        }
                    }
                    if (SILVERFISH.get(player) <= 20) {
                        if (!SILVERFISH_COUNTER.containsKey(player)) {
                            SILVERFISH_COUNTER.put(player, 1);
                        } else {
                            int amount = (SILVERFISH_COUNTER.get(player) + 1);
                            SILVERFISH_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SILVERFISH_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SILVERFISH") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SILVERFISH", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 1.0);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Сильный охранник")){
                    if (!SECURITY_FOUR.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_FOUR")) + 1;
                        SECURITY_FOUR.put(player, amount);
                    } else {
                        if (SECURITY_FOUR.get(player) <= 40) {
                            int amount = (SECURITY_FOUR.get(player)) + 1;
                            SECURITY_FOUR.replace(player, amount);
                        }
                    }
                    if (SECURITY_FOUR.get(player) <= 40) {
                        if (!SECURITY_FOUR_COUNTER.containsKey(player)) {
                            SECURITY_FOUR_COUNTER.put(player, 1);
                        } else {
                            int amount = (SECURITY_FOUR_COUNTER.get(player) + 1);
                            SECURITY_FOUR_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SECURITY_FOUR_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_FOUR") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SECURITY_FOUR", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 0.5);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Зомби")){
                    if (!ZOMBIE.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "ZOMBIE")) + 1;
                        ZOMBIE.put(player, amount);
                    } else {
                        if (ZOMBIE.get(player) <= 40) {
                            int amount = (ZOMBIE.get(player)) + 1;
                            ZOMBIE.replace(player, amount);
                        }
                    }
                    if (ZOMBIE.get(player) <= 40) {
                        if (!ZOMBIE_COUNTER.containsKey(player)) {
                            ZOMBIE_COUNTER.put(player, 1);
                        } else {
                            int amount = (ZOMBIE_COUNTER.get(player) + 1);
                            ZOMBIE_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                ZOMBIE_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "ZOMBIE") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "ZOMBIE", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 0.5);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Легендарный охранник")){
                    if (!SECURITY_FIVE.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_FIVE")) + 1;
                        SECURITY_FIVE.put(player, amount);
                    } else {
                        if (SECURITY_FIVE.get(player) <= 100) {
                            int amount = (SECURITY_FIVE.get(player)) + 1;
                            SECURITY_FIVE.replace(player, amount);
                        }
                    }
                    if (SECURITY_FIVE.get(player) <= 100) {
                        if (!SECURITY_FIVE_COUNTER.containsKey(player)) {
                            SECURITY_FIVE_COUNTER.put(player, 1);
                        } else {
                            int amount = (SECURITY_FIVE_COUNTER.get(player) + 1);
                            SECURITY_FIVE_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                SECURITY_FIVE_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "SECURITY_FIVE") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "SECURITY_FIVE", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 0.2);
                            }
                        }
                    }
                } else if (event.getEntity().getCustomName().equalsIgnoreCase("Вампир")){
                    if (!VAMPIRE.containsKey(player)) {
                        int amount = (Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "VAMPIRE")) + 1;
                        VAMPIRE.put(player, amount);
                    } else {
                        if (VAMPIRE.get(player) <= 100) {
                            int amount = (VAMPIRE.get(player)) + 1;
                            VAMPIRE.replace(player, amount);
                        }
                    }
                    if (VAMPIRE.get(player) <= 100) {
                        if (!VAMPIRE_COUNTER.containsKey(player)) {
                            VAMPIRE_COUNTER.put(player, 1);
                        } else {
                            int amount = (VAMPIRE_COUNTER.get(player) + 1);
                            VAMPIRE_COUNTER.replace(player, amount);
                            if (amount == 2) {
                                VAMPIRE_COUNTER.remove(player);
                                int finalAmount = Prison.getInstance().getDatabase().getAuthorityMobsAmount(player, "VAMPIRE") + amount;
                                Prison.getInstance().getDatabase().setAuthorityMobsAmount(player, "VAMPIRE", finalAmount);
                                double authority = Prison.getInstance().authorityStat.get(player) ;
                                Prison.getInstance().authorityStat.replace(player, authority + 0.2);
                            }
                        }
                    }
                }
            }
        }
    }
}
