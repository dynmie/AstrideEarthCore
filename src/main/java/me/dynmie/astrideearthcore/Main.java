package me.dynmie.astrideearthcore;

import me.dynmie.astrideearthcore.commands.*;
import me.dynmie.astrideearthcore.listeners.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public messageManager mM;
    private static Economy econ;
    private static Permission perms ;
    private static Chat chat;

    @Override
    public void onEnable() {
        /*if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }*/
        configLoader();
        startCommands();
        registerEvents();
        tabCompleter();
        setupPermissions();
        setupChat();
        setupEconomy();
    }

    @Override
    public void onDisable() {
        getLogger().info("AstrideEarth Disabled");
    }

    public void startCommands() {
        getLogger().info("AstrideEarth Enabled");
        getCommand("fly").setExecutor(new Fly());
        getCommand("speed").setExecutor(new Speed());
        getCommand("enderchest").setExecutor(new enderChest(this));
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("message").setExecutor(new messageCommand(this));
        getCommand("reply").setExecutor(new replyCommand(this));
        getCommand("flyspeed").setExecutor(new flySpeed());
        getCommand("walkspeed").setExecutor(new walkSpeed());
        getCommand("workbench").setExecutor(new workbench(this));
        getCommand("kill").setExecutor(new Kill());
        getCommand("sudo").setExecutor(new sudo());
        getCommand("vanish").setExecutor(new vanish(this));
        getCommand("disguise").setExecutor(new disguise());
        getCommand("nightvision").setExecutor(new nightVision());
        getCommand("chat").setExecutor(new chat(this, this));
        getCommand("killall").setExecutor(new killAll(this));
        getCommand("list").setExecutor(new list());
        getCommand("invsee").setExecutor(new invsee(this));
    }

    public void tabCompleter() {
        getCommand("gamemode").setTabCompleter(new Gamemode());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new joinLeaveMessages(this), this);
        getServer().getPluginManager().registerEvents(new vanishManager(this), this);
        getServer().getPluginManager().registerEvents(new ToggleChat(this), this);
        getServer().getPluginManager().registerEvents(new SlowMode(this), this);
        getServer().getPluginManager().registerEvents(new cannotCraft(this), this);
        getServer().getPluginManager().registerEvents(new tabList(this), this);
        mM = new messageManager(this);
    }

    public void configLoader() {
        saveDefaultConfig();
        reloadConfig();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}

