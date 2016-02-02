package me.quasindro.deathdrops;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        World world = e.getEntity().getWorld();
        boolean keepInventory = world.getMetadata("keepInventory").get(0).asBoolean();
        boolean keepLevel = world.getMetadata("keepLevels").get(0).asBoolean();
        e.setKeepInventory(keepInventory);
        e.setKeepLevel(keepLevel);
        e.setDroppedExp(keepLevel ? 0 : e.getDroppedExp());

        System.out.println(e.getKeepInventory());
        System.out.println(e.getKeepLevel());
        System.out.println(e.getDroppedExp());
    }
}
