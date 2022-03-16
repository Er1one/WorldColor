package ru.er1one.worldcolor;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.er1one.worldcolor.events.JoinListener;
import ru.er1one.worldcolor.events.WorldChangeListener;
import ru.er1one.worldcolor.placeholders.WorldColorPlaceholder;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WorldColor extends JavaPlugin {

    public static WorldColor instance;
    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    private final Map<String, String> worlds = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        loadWorlds();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new WorldChangeListener(), this);
        if(isPlaceholderAPIEnabled()) {
            new WorldColorPlaceholder().register();
        }
    }

    /*
     *  Подгружает миры из config.yml
     */
    private void loadWorlds() {
        if(getConfig().getConfigurationSection("worlds") == null) {
            return;
        }
        for(String world : getConfig().getConfigurationSection("worlds").getKeys(false)) {
            worlds.put(world.toLowerCase(), color(getConfig().getString("worlds." + world)));
        }
    }

    /**
     * Возвращает объект плагина
     *
     * @return Main-класс плагина
     */
    public static WorldColor getInstance() {
        return instance;
    }

    /**
     * Возвращает цветовые настройки мира
     *
     * @param worldName - Название мира
     * @return строку из config.yml, которая соответствует указанному миру
     */
    public String getWorldColor(String worldName) {
        return worlds.get(worldName.toLowerCase());
    }

    /**
     * Проверяет, был ли подгружен указанный мир, при запуске сервера
     *
     * @param worldName - Название мира
     * @return существует ли цвет указанного мира
     */
    public boolean isWorldColorSet(String worldName) {
        return worlds.containsKey(worldName);
    }

    /**
     * Проверяет состояние настройки auto-replace из config.yml
     *
     * @return состояние настройки auto-replace из config.yml
     */
    public boolean isAutoReplaceEnabled() {
        return getConfig().getBoolean("settings.auto-replace");
    }

    /**
     * Проверяет состояние настройки placeholder из config.yml
     *
     * @return состояние настройки placeholder из config.yml
     */
    public boolean isPlaceholderAPIEnabled() {
        return getConfig().getBoolean("settings.placeholder");
    }

    /**
     * Устанавливает HEX цвета в строку
     *
     * @param str - Нецветная строка
     * @return Строка с цветами
     */
    private String color(String str) {
        Matcher match = pattern.matcher(str);
        while (match.find()) {
            String color = str.substring(match.start(), match.end());
            str = str.replace(color, ChatColor.of(color) + "");
            match = pattern.matcher(str);
        }
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
