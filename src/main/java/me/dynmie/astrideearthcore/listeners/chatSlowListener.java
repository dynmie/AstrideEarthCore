package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class chatSlowListener implements Listener {
    private Main plugin;
    public chatSlowListener(Main plugin) {this.plugin = plugin;}

    private final Map<String, Long> cooldownTime = new HashMap<>();

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("astride.chat")) {
            return;
        }
        long now = System.currentTimeMillis();
        String name = player.getName();
        Long lastChat = cooldownTime.get(name);
        if (lastChat != null) {
            long earliestNext = lastChat + plugin.getConfig().getInt("slowtime") * 1000;
            if (now < earliestNext) {
                int timeRemaining = (int) ((earliestNext - now) / 1000L) + 1;
                player.sendMessage(Utils.chat("&cPlease wait " + timeRemaining + " second" + (timeRemaining > 1 ? "s" : "") + " before talking again."));
                event.setCancelled(true);
                return;
            }
        }
        cooldownTime.put(name, now);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        cooldownTime.remove(player.getName());
    }

}
