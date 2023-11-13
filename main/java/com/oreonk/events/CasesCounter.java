package com.oreonk.events;

import com.oreonk.Prison;
import me.davidml16.acubelets.api.CubeletOpenEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CasesCounter implements Listener {
    @EventHandler
    public void caseOpen(CubeletOpenEvent event){
        if (!Prison.getInstance().cases_counter.containsKey(event.getPlayer().getUuid())) {
            Prison.getInstance().cases_counter.put(event.getPlayer().getUuid(), 1);
        } else {
            int amount = Prison.getInstance().cases_counter.get(event.getPlayer().getUuid());
            Prison.getInstance().cases_counter.replace(event.getPlayer().getUuid(), amount+1);
        }
    }
    @EventHandler
    public void regularCaseOpen(PlayerInteractEvent event){
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.TRIPWIRE_HOOK) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Обычный ключ")){
                if (!Prison.getInstance().cases_counter.containsKey(event.getPlayer().getUniqueId())) {
                    Prison.getInstance().cases_counter.put(event.getPlayer().getUniqueId(), 1);
                } else {
                    int amount = Prison.getInstance().cases_counter.get(event.getPlayer().getUniqueId());
                    Prison.getInstance().cases_counter.replace(event.getPlayer().getUniqueId(), amount+1);
                }
            }
        }
    }
}
