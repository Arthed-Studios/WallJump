package me.arthed.walljump.utils;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class VelocityUtils {

    public static void pushPlayerInFront(Player player, float horizontalPower, float verticalPower) {
        Vector velocity = player.getLocation().getDirection().normalize().multiply(horizontalPower);
        velocity.setY(verticalPower);
        player.setVelocity(velocity);
    }

}
