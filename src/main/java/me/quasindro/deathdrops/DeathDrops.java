package me.quasindro.deathdrops;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathDrops extends JavaPlugin {

    private PluginManager pm;
    private FileConfiguration config;

    public void onEnable() {
        pm = Bukkit.getPluginManager();
        config = getConfig();

        validateWorlds();
        setupWorlds();

        pm.registerEvents(new PlayerDeathListener(), this);
    }

    private void validateWorlds() {
        for (String section : config.getKeys(false)) {
            if (Bukkit.getWorld(section) == null) {
                config.set(section, null);
                Bukkit.getLogger().warning("Removing " + section + " from config, invalid world!");
                saveConfig();
            }
        }
    }

    private void setupWorlds() {
        for (World world : Bukkit.getWorlds()) {
            String worldName = world.getName();
            if (config.getConfigurationSection(worldName) == null) {
                config.createSection(worldName);
                config.set(worldName + ".keep-inventory", false);
                config.set(worldName + ".keep-levels", false);
                Bukkit.getLogger().warning("Adding " + worldName + " to the config.");
                saveConfig();
            }
            world.setMetadata("keepInventory", new FixedMetadataValue(this, config.get(worldName + ".keep-inventory")));
            world.setMetadata("keepLevels", new FixedMetadataValue(this, config.get(worldName + ".keep-levels")));
        }
    }
}
