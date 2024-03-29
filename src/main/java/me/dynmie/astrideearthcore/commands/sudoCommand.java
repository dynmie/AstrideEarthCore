package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class sudoCommand implements CommandExecutor {
    Main plugin;
    public sudoCommand(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("astride.sudo"))) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.chat("&cUsage: /sudo <player> <command>"));
            return true;
        }

        if (getPlayer(args[0]) == null) {
            sender.sendMessage(Utils.chat("&cThat player is offline."));
            return true;
        } else {
            Player target = getPlayer(args[0]);
            if (args.length > 1) {
                if (target.hasPermission("astride.sudo.exempt")) {
                    if (sender instanceof Player) {
                        sender.sendMessage(Utils.chat("&cYou cannot force this player."));
                        return true;
                    }
                }
                args[0] = "";
                String execute = "";
                for (String arg : args) {
                    execute += " " + arg;
                }
                target.performCommand(execute.trim());
                sender.sendMessage(Utils.chat("&bYou have forced &f" + target.getDisplayName() + " &ato execute &f/" + execute.trim() + "&a."));
            } else {
                sender.sendMessage(Utils.chat("&cProvide a valid command to execute."));
            }
        }
        return true;
    }
}
