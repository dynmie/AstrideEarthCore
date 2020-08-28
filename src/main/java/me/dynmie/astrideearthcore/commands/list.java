package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.listeners.vanishManager;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class list implements CommandExecutor {
    public list() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(Utils.chat("&aThere are &l" + vanishManager.vanishedPlayers() + " players&a online right now."));
        return true;
    }
}
