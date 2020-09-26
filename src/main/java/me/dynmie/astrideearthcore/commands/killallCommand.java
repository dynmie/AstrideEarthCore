package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class killallCommand implements CommandExecutor {
    Main plugin;
    public killallCommand(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("console")));
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission("astride.kill.all"))) {
            player.sendMessage(Utils.chat(this.plugin.getConfig().getString("noperms")));
            return true;
        }

        int entities = 0;
        for (Entity en : player.getWorld().getEntities()){
            if (!(en instanceof Player)) {
                en.remove();
                entities = entities + 1;
            }
        }
        player.sendMessage(Utils.chat(this.plugin.getConfig().getString("KillAll") + " &7(" + entities + " entities)&b."));

        return true;
    }
}
