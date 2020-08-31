package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class workbench implements CommandExecutor {
    Main plugin;
    public workbench(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("console")));
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("astride.craft")) {
            player.openWorkbench(null, true);
        } else {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
        }
        return true;
    }
}
