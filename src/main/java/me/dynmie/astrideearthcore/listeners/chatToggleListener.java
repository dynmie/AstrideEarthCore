package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.commands.chatCommand;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatToggleListener implements Listener {
    private Main plugin;
    public chatToggleListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (chatCommand.muted) {
            if (!(player.hasPermission("astride.chat"))) {
                event.setCancelled(true);
                player.sendMessage(Utils.chat("&cThe chat is currently muted."));
            } else if (player.hasPermission("astride.chat")) {
                event.setCancelled(false);
            }
        }
    }
}
