package me.dynmie.astrideearthcore.utils;

import me.dynmie.astrideearthcore.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData {
    public static boolean existsPlayerData(Player player) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        return file.exists();
    }

    public static void createPlayerData(Player player) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        if (!existsPlayerData(player)) {
            try {
                file.createNewFile();
            } catch (IOException error) {
                error.printStackTrace();
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("uuid", player.getUniqueId());
            try {
                config.save(file);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    public static File getFile(Player player) {
        if (existsPlayerData(player)) {
            return new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        } else {
            return null;
        }
    }

    public static void save(Player player) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        if (!existsPlayerData(player)) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set("uuid", player.getUniqueId());
            try {
                config.save(file);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }
    public static void setKey(Player player, String path, Object object) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        if (!existsPlayerData(player)) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set(path, object);
        }
    }

    public static Object getKey(Player player, String path) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        if (!existsPlayerData(player)) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            return config.get(path);
        } else {
            return null;
        }
    }

    public static String getStringKey(Player player, String path) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        if (!existsPlayerData(player)) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            return (String) config.get(path);
        } else {
            return null;
        }
    }

    public static Boolean getBooleanKey(Player player, String path) {
        File file = new File(Main.getPlugin().getDataFolder() + "/playerdata/", player.getUniqueId().toString() + ".yml");
        if (!existsPlayerData(player)) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            return (Boolean) config.get(path);
        } else {
            return null;
        }
    }

}
