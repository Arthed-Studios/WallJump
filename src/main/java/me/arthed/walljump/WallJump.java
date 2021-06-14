package me.arthed.walljump;

import me.arthed.walljump.command.WallJumpCommand;
import me.arthed.walljump.config.WallJumpConfiguration;
import me.arthed.walljump.handlers.BStats;
import me.arthed.walljump.handlers.WorldGuardHandler;
import me.arthed.walljump.listeners.PlayerJoinListener;
import me.arthed.walljump.listeners.PlayerQuitListener;
import me.arthed.walljump.listeners.PlayerToggleSneakListener;
import me.arthed.walljump.player.PlayerManager;
import me.arthed.walljump.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
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

    private WorldGuardHandler worldGuard;
    public WorldGuardHandler getWorldGuardHandler() {
        return worldGuard;
    }


    @Override
    public void onEnable() {
        playerManager = new PlayerManager();

        registerEvents(
                new PlayerJoinListener(),
                new PlayerQuitListener(),
                new PlayerToggleSneakListener()
        );
        this.getCommand("walljump").setExecutor(new WallJumpCommand());

        //in case the plugin has been loaded while the server is running using plugman or any other similar methods, register all the online players
        for(Player player : Bukkit.getOnlinePlayers()) {
            playerManager.registerPlayer(player);
        }

        new BStats(this, 10126);

        UpdateChecker updateChecker = new UpdateChecker(this);
        if(!config.getBoolean("ignoreUpdates"))
            updateChecker.checkUpdates();
    }

    @Override
    public void onLoad() {
        plugin = this;
        config = new WallJumpConfiguration("config.yml");

        Plugin worldGuardPlugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if(worldGuardPlugin != null) {
            worldGuard = new WorldGuardHandler(worldGuardPlugin, this);
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
