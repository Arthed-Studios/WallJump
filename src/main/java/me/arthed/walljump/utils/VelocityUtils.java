package me.arthed.walljump.utils;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityUtils {

    public static void pushPlayerInFront(Player player, double horizontalPower, double verticalPower) {
        if(BukkitUtils.isVersionBefore(BukkitUtils.Version.V1_8))
            verticalPower += 0.1;
        Vector velocity = player.getLocation().getDirection().normalize().multiply(horizontalPower);
        velocity.setY(verticalPower);
        player.setVelocity(velocity);
    }

}
