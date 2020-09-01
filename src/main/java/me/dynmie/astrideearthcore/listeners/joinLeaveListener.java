package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class joinLeaveListener implements Listener {
    Main plugin;
    public joinLeaveListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        //Player player = e.getPlayer();
        e.setJoinMessage("");

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        //Player player = e.getPlayer();
        e.setQuitMessage("");
    }

}
