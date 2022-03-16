package ru.er1one.worldcolor.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import ru.er1one.worldcolor.WorldColor;

public class WorldChangeListener implements Listener {
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName();

        if(!WorldColor.getInstance().isWorldColorSet(worldName)) {
            return;
        }

        if(WorldColor.getInstance().isAutoReplaceEnabled()) {
            String worldColor = WorldColor.instance.getWorldColor(worldName);
            player.setPlayerListName(worldColor.replace("%player_name%", player.getName()));
        }
    }
}
