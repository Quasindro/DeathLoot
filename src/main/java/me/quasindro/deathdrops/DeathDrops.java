package me.quasindro.deathdrops;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathDrops extends JavaPlugin {

    private PluginManager pm;

    public void onEnable() {
        pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new WorldLoadListener(this), this);
    }
}
