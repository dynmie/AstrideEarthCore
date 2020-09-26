package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.commands.vanishCommand;
import org.bukkit.entity.Player;
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
        Player player = e.getPlayer();
        if (plugin.getConfig().getBoolean("handle-joinleave-vanish")) {
            if (plugin.getConfig().getBoolean("handle-joinleave")) {
                if (vanishCommand.vanishedPlayers.contains(player)) {
                    e.setJoinMessage("");
                    return;
                }
            }
        } else {
            if (plugin.getConfig().getBoolean("handle-joinleave")) {
                e.setJoinMessage(plugin.getConfig().getString("joinmessage"));
                return;
            }
        }
        return;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (plugin.getConfig().getBoolean("handle-joinleave-vanish")) {
            if (plugin.getConfig().getBoolean("handle-joinleave")) {
                if (vanishCommand.vanishedPlayers.contains(player)) {
                    e.setQuitMessage("");
                    return;
                }
            }
        } else {
            if (plugin.getConfig().getBoolean("handle-joinleave")) {
                e.setQuitMessage(plugin.getConfig().getString("quitmessage"));
                return;
            }
        }
        return;
    }
}
