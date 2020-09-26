package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class disguiseCommand implements CommandExecutor {
    Main plugin;
    public disguiseCommand(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("Only players can execute this command!"));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.nick"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
            player.sendMessage(Utils.chat("&bYou have set your disguise name to &f") + player.getName() + Utils.chat("&b."));
            return true;
        }

        args[0] = args[0].replaceAll("[^A-Za-z0-9\\\\[\\\\]]", "");

        if (args[0].length() < 4|| args[0].length() > 10) {
            player.sendMessage(Utils.chat("&cYou can only set a disguise from &f4 &c-&f 10&c characters."));
            return true;
        }

        if (getPlayer(args[0]) == null) {
            player.setDisplayName(args[0]);
            player.setPlayerListName(args[0]);
            player.sendMessage(Utils.chat("&bYou have set your disguise name to &f") + args[0] + Utils.chat("&b."));
            return true;
        } else {
            player.sendMessage(Utils.chat("&cThat player already exists."));
        }
        return true;
    }
}
