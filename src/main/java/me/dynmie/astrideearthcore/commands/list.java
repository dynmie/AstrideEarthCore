package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.listeners.vanishListener;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class list implements CommandExecutor {
    public list() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Utils.chat("&aThere are &l" + vanishListener.vanishedPlayers() + " players&a online right now."));
        return true;
    }
}
