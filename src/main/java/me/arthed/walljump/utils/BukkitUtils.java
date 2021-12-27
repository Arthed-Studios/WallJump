package me.arthed.walljump.utils;

import org.bukkit.Bukkit;

public class BukkitUtils {

    public static boolean isPluginInstalled(String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName) != null;
    }

}