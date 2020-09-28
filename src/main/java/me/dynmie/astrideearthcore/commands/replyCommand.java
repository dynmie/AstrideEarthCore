package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

public class replyCommand implements CommandExecutor {
    Main plugin;

    public replyCommand(Main textWarp) {
        plugin = textWarp;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player messager = (Player) sender;
            if (plugin.mM.getReplyTarget(messager) == null) {
                messager.sendMessage(Utils.chat("&cThere is no one to reply to."));
                return true;
            }

            if (!(args.length > 0)) {
                sender.sendMessage(Utils.chat("&cThat isn't a valid message."));
                return true;
            }

            Player receiver = plugin.mM.getReplyTarget(messager);
            String message = "";
            for (String arg : args) {
                message += " " + arg;
            }
            messager.sendMessage(Utils.chat("&7(To &f" + receiver.getDisplayName() + "&7) " + message.trim()));
            receiver.sendMessage(Utils.chat("&7(From &f" + messager.getDisplayName() + "&7) " + message.trim()));
            for (Player spy : chatCommand.chatSpy) {
                spy.sendMessage("§7(From §f" + receiver.getDisplayName() + "§7 to §f " + messager.getDisplayName() + "§7) " + message.trim());
            }
            receiver.playSound(receiver.getLocation(), ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.9f);
            return true;
        }
        return false;
    }
}