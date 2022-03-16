package ru.er1one.worldcolor;

import org.bukkit.plugin.java.JavaPlugin;

public final class WorldColor extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
