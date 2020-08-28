package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.commands.freeze;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class freezeManager implements Listener {
    Main plugin;
    public freezeManager(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (freeze.frozenPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        freeze.frozenPlayers.remove(event.getPlayer());
    }
}
