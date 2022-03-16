package ru.er1one.worldcolor.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.er1one.worldcolor.WorldColor;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!WorldColor.getInstance().isAutoReplaceEnabled()) {
            return;
        }
        Player player = event.getPlayer();
        String worldName = player.getWorld().getName().toLowerCase();

        if(!WorldColor.getInstance().isWorldColorSet(worldName)) {
            return;
        }

        String worldColor = WorldColor.instance.getWorldColor(worldName);

        player.setPlayerListName(worldColor.replace("%player_name%", player.getName()));
    }
}
