package ru.er1one.worldcolor.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import ru.er1one.worldcolor.WorldColor;

public class WorldColorPlaceholder extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "worldcolor";
    }

    @Override
    public String getAuthor() {
        return "er1one";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(!player.isOnline()) {
            return "";
        }
        Player player1 = player.getPlayer();
        assert player1 != null;
        String worldName = player1.getWorld().getName().toLowerCase();
        if(!WorldColor.getInstance().isWorldColorSet(worldName)) {
            return player1.getName();
        }
        return WorldColor.instance.getWorldColor(worldName).replace("%player_name%", player1.getName());
    }
}
