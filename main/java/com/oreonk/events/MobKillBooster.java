package com.oreonk.events;

import com.oreonk.Prison;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

import java.util.HashMap;

public class MobKillBooster implements Listener {
    //Опоссум
    public HashMap<Player, Integer> SILVERFISH = new HashMap<>() {};
    //Ожившая сопля
    public HashMap<Player, Integer> SLIME = new HashMap<>() {};
    //Тарантул
    public HashMap<Player, Integer> SPIDER = new HashMap<>() {};
    //Зомби
    public HashMap<Player, Integer> ZOMBIE = new HashMap<>() {};
    //Вампир
    public HashMap<Player, Integer> ZOMBIFIED_PIGLIN = new HashMap<>() {};
    //Боссы
    //Ведьма
    public HashMap<Player, Integer> WITCH = new HashMap<>() {};
    //Шахтёр
    public HashMap<Player, Integer> IRON_GOLEM = new HashMap<>() {};
    //Злой фермер
    public HashMap<Player, Integer> ZOMBIE_VILLAGER = new HashMap<>() {};
    //Воришка
    public HashMap<Player, Integer> VINDICATOR = new HashMap<>() {};
    //Страж сатаны
    public HashMap<Player, Integer> BLAZE = new HashMap<>() {};
    //Иссушитель
    public HashMap<Player, Integer> WITHER = new HashMap<>() {};
    //Хранитель леса
    public HashMap<Player, Integer> HUSK_HANA = new HashMap<>() {};
    //Ледяное чудовище
    public HashMap<Player, Integer> SNOWMAN_SNOWBREAKER = new HashMap<>() {};
    @EventHandler(priority = EventPriority.MONITOR)
    public void mobDamage(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof LivingEntity && event.getEntity().getCustomName() != null) {
            if (((LivingEntity) event.getEntity()).getHealth() <= event.getDamage() && event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                EntityType entityType = event.getEntityType();
                if (event.getEntity().getCustomName() != null) {
                    if (!event.getEntity().getCustomName().toLowerCase().contains("охранник") && !event.getEntity().getCustomName().toLowerCase().contains("ледяное чудовище") && !event.getEntity().getCustomName().toLowerCase().contains("иссушитель")) {
                        player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                        if (Prison.getInstance().privateMobBooster.containsKey(player.getUniqueId())) {
                            if (Prison.getInstance().privateMobMultiplier.get(player.getUniqueId()) == 1){
                                if (event.getEntity().getCustomName().toLowerCase().contains("опоссум")){
                                    if (!SILVERFISH.containsKey(player)){
                                        SILVERFISH.put(player, 1);
                                    } else {
                                        if (SILVERFISH.get(player) == 1){
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            SILVERFISH.replace(player, 0);
                                        } else {
                                            int amount = SILVERFISH.get(player) + 1;
                                            SILVERFISH.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("сопля")){
                                    if (!SLIME.containsKey(player)){
                                        SLIME.put(player, 1);
                                    } else {
                                        if (SLIME.get(player) == 1){
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            SLIME.replace(player, 0);
                                        } else {
                                            int amount = SLIME.get(player) + 1;
                                            SLIME.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("тарантул")) {
                                    if (!SPIDER.containsKey(player)) {
                                        SPIDER.put(player, 1);
                                    } else {
                                        if (SPIDER.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            SPIDER.replace(player, 0);
                                        } else {
                                            int amount = SPIDER.get(player) + 1;
                                            SPIDER.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("зомби")) {
                                    if (!ZOMBIE.containsKey(player)) {
                                        ZOMBIE.put(player, 1);
                                    } else {
                                        if (ZOMBIE.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            ZOMBIE.replace(player, 0);
                                        } else {
                                            int amount = ZOMBIE.get(player) + 1;
                                            ZOMBIE.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("вампир")) {
                                    if (!ZOMBIFIED_PIGLIN.containsKey(player)) {
                                        ZOMBIFIED_PIGLIN.put(player, 1);
                                    } else {
                                        if (ZOMBIFIED_PIGLIN.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            ZOMBIFIED_PIGLIN.replace(player, 0);
                                        } else {
                                            int amount = ZOMBIFIED_PIGLIN.get(player) + 1;
                                            ZOMBIFIED_PIGLIN.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("ведьма")) {
                                    if (!WITCH.containsKey(player)) {
                                        WITCH.put(player, 1);
                                    } else {
                                        if (WITCH.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            WITCH.replace(player, 0);
                                        } else {
                                            int amount = WITCH.get(player) + 1;
                                            WITCH.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("шахтёр")) {
                                    if (!IRON_GOLEM.containsKey(player)) {
                                        IRON_GOLEM.put(player, 1);
                                    } else {
                                        if (IRON_GOLEM.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            IRON_GOLEM.replace(player, 0);
                                        } else {
                                            int amount = IRON_GOLEM.get(player) + 1;
                                            IRON_GOLEM.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("злой фермер")) {
                                    if (!ZOMBIE_VILLAGER.containsKey(player)) {
                                        ZOMBIE_VILLAGER.put(player, 1);
                                    } else {
                                        if (ZOMBIE_VILLAGER.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            ZOMBIE_VILLAGER.replace(player, 0);
                                        } else {
                                            int amount = ZOMBIE_VILLAGER.get(player) + 1;
                                            ZOMBIE_VILLAGER.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("воришка")) {
                                    if (!VINDICATOR.containsKey(player)) {
                                        VINDICATOR.put(player, 1);
                                    } else {
                                        if (VINDICATOR.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            VINDICATOR.replace(player, 0);
                                        } else {
                                            int amount = VINDICATOR.get(player) + 1;
                                            VINDICATOR.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("страж сатаны")) {
                                    if (!BLAZE.containsKey(player)) {
                                        BLAZE.put(player, 1);
                                    } else {
                                        if (BLAZE.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            BLAZE.replace(player, 0);
                                        } else {
                                            int amount = BLAZE.get(player) + 1;
                                            BLAZE.replace(player, amount);
                                        }
                                    }

                                } else if(event.getEntity().getCustomName().toLowerCase().contains("хранитель леса")) {
                                    if (!HUSK_HANA.containsKey(player)) {
                                        HUSK_HANA.put(player, 1);
                                    } else {
                                        if (HUSK_HANA.get(player) == 1) {
                                            player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                                            HUSK_HANA.replace(player, 0);
                                        } else {
                                            int amount = HUSK_HANA.get(player) + 1;
                                            HUSK_HANA.replace(player, amount);
                                        }
                                    }
                                }
                            } else if (Prison.getInstance().privateMobMultiplier.get(player.getUniqueId()) == 2){
                                player.incrementStatistic(Statistic.KILL_ENTITY, entityType, 1);
                            }
                        }

                    } else if (event.getEntity().getCustomName().toLowerCase().contains("ледяное чудовище")){
                        player.incrementStatistic(Statistic.KILL_ENTITY, EntityType.SNOWMAN, 1);
                        if (Prison.getInstance().privateMobMultiplier.get(player.getUniqueId()) == 1){
                            if (!SNOWMAN_SNOWBREAKER.containsKey(player)) {
                                SNOWMAN_SNOWBREAKER.put(player, 1);
                            } else {
                                if (SNOWMAN_SNOWBREAKER.get(player) == 1) {
                                    player.incrementStatistic(Statistic.KILL_ENTITY, EntityType.SNOWMAN, 1);
                                    SNOWMAN_SNOWBREAKER.replace(player, 0);
                                } else {
                                    int amount = SNOWMAN_SNOWBREAKER.get(player) + 1;
                                    SNOWMAN_SNOWBREAKER.replace(player, amount);
                                }
                            }
                        } if (Prison.getInstance().privateMobMultiplier.get(player.getUniqueId()) == 2) {
                            player.incrementStatistic(Statistic.KILL_ENTITY, EntityType.SNOWMAN, 1);
                        }

                    } else if (event.getEntity().getCustomName().toLowerCase().contains("иссушитель")) {
                            player.incrementStatistic(Statistic.KILL_ENTITY, EntityType.WITHER, 1);
                            if (Prison.getInstance().privateMobMultiplier.get(player.getUniqueId()) == 1) {
                                if (!SNOWMAN_SNOWBREAKER.containsKey(player)) {
                                    SNOWMAN_SNOWBREAKER.put(player, 1);
                                } else {
                                    if (SNOWMAN_SNOWBREAKER.get(player) == 1) {
                                        player.incrementStatistic(Statistic.KILL_ENTITY, EntityType.WITHER, 1);
                                        SNOWMAN_SNOWBREAKER.replace(player, 0);
                                    } else {
                                        int amount = SNOWMAN_SNOWBREAKER.get(player) + 1;
                                        SNOWMAN_SNOWBREAKER.replace(player, amount);
                                    }
                                }
                            }
                            if (Prison.getInstance().privateMobMultiplier.get(player.getUniqueId()) == 2) {
                                player.incrementStatistic(Statistic.KILL_ENTITY, EntityType.WITHER, 1);
                            }
                    }
                }
            }
        }
    }
    @EventHandler
    public void mobStat(PlayerStatisticIncrementEvent event){
        if (event.getEntityType() != null){
            event.setCancelled(true);
        }
    }
}
