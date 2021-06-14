package me.arthed.walljump.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;


public class UpdateChecker implements Listener {

    private boolean update = false;
    private final String currentVers;

    public String updateAvailableMessage = "&6[WallJump] There is an update available! Download it from: &nhttps://www.spigotmc.org/resources/88311/";

    public UpdateChecker(Plugin plugin) {
        currentVers = plugin.getDescription().getVersion();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void checkUpdates() {
        checkUpdates.start();
    }

    private final Thread checkUpdates = new Thread() {
        public void run() {
            URL url;
            try {
                url = new URL("https://api.spigotmc.org/legacy/update.php?resource=88311");
            } catch (MalformedURLException invalidUrl) {
                return;
            }
            URLConnection conn;
            try {
                conn = url.openConnection();
            } catch (IOException ioException) {
                return;
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                if (br.readLine().equals(currentVers)) {
                    update = false;
                } else {
                    update = true;
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', updateAvailableMessage));
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        if(p.isOp()) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', updateAvailableMessage));
                        }
                    }
                }
            } catch (IOException ignored) {}
        }
    };

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p.isOp() && update) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', updateAvailableMessage));
        }
    }

}