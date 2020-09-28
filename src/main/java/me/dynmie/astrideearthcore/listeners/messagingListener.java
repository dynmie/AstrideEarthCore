package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.commands.chatCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class messagingListener implements Listener {

    Main plugin;

    HashMap<Player, Player> conversations = new HashMap<>();

    public messagingListener(Main textWarp) {
        plugin = textWarp;
    }
    public void setReplyTarget(Player messager, Player reciever) {
        conversations.put(messager, reciever);
        conversations.put(reciever, messager);
    }

    public Player getReplyTarget(Player messager) {
        return conversations.get(messager);
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!(player.hasPermission("astride.chat.spy"))) {
            chatCommand.chatSpy.remove(player);
        }
    }

}