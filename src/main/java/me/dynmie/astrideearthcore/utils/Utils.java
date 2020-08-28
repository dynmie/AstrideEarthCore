package me.dynmie.astrideearthcore.utils;

import net.md_5.bungee.api.ChatColor;

public class Utils {
    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String prefix(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String displayName(String prefix, String playername) {
        return ChatColor.translateAlternateColorCodes('&', prefix(prefix)) + playername;
    }
}
