package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class messagingListener {

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
}