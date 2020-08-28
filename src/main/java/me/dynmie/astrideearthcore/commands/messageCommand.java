package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

public class messageCommand implements CommandExecutor {

    Main plugin;

    public messageCommand(Main textWarp) {
        plugin = textWarp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length > 0) {
            if (args.length > 2) {
                if (!(getPlayer(args[0]) == null)) {
                    Player messager = (Player) sender;
                    Player receiver = getPlayer(args[0]);
                    plugin.mM.setReplyTarget(messager, receiver);
                    args[0] = "";
                    String message = "";
                    for (String arg : args) {
                        message += " " + arg;
                    }
                    messager.sendMessage(Utils.chat("&7(To &f" + receiver.getDisplayName() + "&7) " + message.trim()));
                    receiver.sendMessage(Utils.chat("&7(From &f" + messager.getDisplayName() + "&7) " + message.trim()));
                    receiver.playSound(receiver.getLocation(), ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.9f);
                } else {
                    sender.sendMessage(Utils.chat("&cThat player is offline."));
                }
            } else {
                sender.sendMessage(Utils.chat("&cThat isn't a valid message."));
            }
            return true;
        }
        return false;
    }
}
