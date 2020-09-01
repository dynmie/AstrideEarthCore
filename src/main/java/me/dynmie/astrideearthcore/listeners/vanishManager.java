package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.commands.vanish;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class vanishManager implements Listener {
    Main plugin;
    public vanishManager(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String prefix = Main.getChat().getPlayerPrefix(player);
        if (player.hasPermission("astride.vanish.vanish")) {
            vanish.vanishedPlayers.add(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false));
            player.setAllowFlight(true);
            player.setPlayerListName(Utils.chat("&7[VANISHED] &o") + player.getDisplayName());
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!(online.hasPermission("astride.vanish.see"))) {
                    online.hidePlayer(plugin, player);
                }
            }
            player.sendMessage(Utils.chat(plugin.getConfig().getString("enabled-vanish")));
        } else {
            player.setPlayerListName(Utils.displayName(prefix, player.getDisplayName()));
        }
        if (!(player.hasPermission("astride.vanish.see"))) {
            for (int i = 0; i < vanish.vanishedPlayers.size(); i++) {
                player.hidePlayer(plugin, vanish.vanishedPlayers.get(i));
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        vanish.vanishedPlayers.remove(player);
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getTarget() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getTarget())) {
                event.setCancelled(true);
            }
        }
    }

   @EventHandler
    public void onDamageByBlock(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getDamager())) {
                event.setCancelled(true);
            }
        } else if (event.getEntity() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (vanish.vanishedPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (vanish.vanishedPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanish.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerCropTrample(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(vanish.vanishedPlayers.contains(player))) return;
        if (event.getAction() != Action.PHYSICAL) return;
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().toString().matches("SOIL|FARMLAND"))  event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (vanish.vanishedPlayers.contains(p)) e.setDeathMessage(null);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (!(vanish.vanishedPlayers.contains(player))) return;
        if (player.hasPermission("astride.vanish.vanish")) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!(online.hasPermission("astride.vanish.see"))) {
                    online.hidePlayer(plugin, player);
                }
            }
        }
        if (!(player.hasPermission("astride.vanish.see"))) {
            for (int i = 0; i < vanish.vanishedPlayers.size(); i++) {
                player.hidePlayer(plugin, vanish.vanishedPlayers.get(i));
            }
        }
    }

    public static int vanishedPlayers() {
        return Bukkit.getOnlinePlayers().size() - vanish.vanishedPlayers.size();
    }

}
