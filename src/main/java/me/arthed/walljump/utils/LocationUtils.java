package me.arthed.walljump.utils;

import me.arthed.walljump.enums.WallFace;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LocationUtils {

    public static boolean isTouchingAWall(Player player) {
        WallFace facing = WallFace.fromBlockFace(player.getFacing());

        Location location = player.getLocation();
        Block block = location.clone().add(facing.xOffset, facing.yOffset, facing.zOffset).getBlock();
        float distanceLimit = facing.distance;

        if(block.getType().isSolid()) {
            if(facing.equals(WallFace.EAST) || facing.equals(WallFace.WEST))
                return Math.abs(location.getX() - block.getX()) < distanceLimit;
            else
                return  Math.abs(location.getZ() - block.getZ()) < distanceLimit;
        }

        return false;
    }

    public static boolean isOnGround(Player player) {
        return !player.getLocation().clone().subtract(0, 0.2, 0).getBlock().isPassable();
    }


}
