package me.quasindro.deathdrops;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class WorldLoadListener implements Listener {

    private DeathDrops plugin;
    private FileConfiguration config;

    public WorldLoadListener(DeathDrops plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @EventHandler
    public void onDeathDropsWorldLoad(WorldLoadEvent e) {
        String worldName = e.getWorld().getName();
        if (config.getConfigurationSection(worldName) == null) {
            config.createSection(worldName);
            config.set(worldName + ".keep-inventory", false);
            config.set(worldName + ".keep-levels", false);
            Bukkit.getLogger().info("Adding " + worldName + " to the config.");
            plugin.saveConfig();
        }
        e.getWorld().setMetadata("keepInventory", new FixedMetadataValue(plugin, config.get(worldName + ".keep-inventory")));
        e.getWorld().setMetadata("keepLevels", new FixedMetadataValue(plugin, config.get(worldName + ".keep-levels")));
    }
}
