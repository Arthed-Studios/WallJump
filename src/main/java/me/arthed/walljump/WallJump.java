package me.arthed.walljump;

import me.arthed.walljump.config.WallJumpConfiguration;
import me.arthed.walljump.listeners.PlayerDamageListener;
import me.arthed.walljump.listeners.PlayerJoinListener;
import me.arthed.walljump.listeners.PlayerQuitListener;
import me.arthed.walljump.listeners.PlayerToggleSneakListener;
import me.arthed.walljump.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WallJump extends JavaPlugin {

    private static WallJump plugin;

    public static WallJump getInstance() {
        return plugin;
    }

    private PlayerManager playerManager;

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    private WallJumpConfiguration config;

    public WallJumpConfiguration getWallJumpConfig() {
        return config;
    }


    @Override
    public void onEnable() {
        plugin = this;

        config = new WallJumpConfiguration("config.yml");

        playerManager = new PlayerManager();

        registerEvents(
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new PlayerToggleSneakListener(),
                new PlayerDamageListener()
        );

        //in case the plugin has been loaded while the server is running using plugman or any other similar methods, register all the online players
        for(Player player : Bukkit.getOnlinePlayers()) {
            playerManager.registerPlayer(player);
        }
    }

    @Override
    public void onDisable() {
    }

    private void registerEvents(Listener... listeners) {
        for(Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
