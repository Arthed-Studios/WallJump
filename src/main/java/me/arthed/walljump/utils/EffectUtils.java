package me.arthed.walljump.utils;

import me.arthed.walljump.enums.WallFace;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class EffectUtils {

    public static void spawnSlidingParticles(Player player, int count, WallFace facing) {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(
                Particle.BLOCK_DUST,
                location.clone().add(facing.xOffset*0.3, facing.yOffset*0.3-0.3, facing.zOffset*0.3),
                count,
                0.2f,
                0.2f,
                0.2f,
                location.clone().add(facing.xOffset, facing.yOffset, facing.zOffset).getBlock().getBlockData());
    }

    public static void playWallJumpSound(Player player, WallFace facing, float volume, float pitch) {
        player.getWorld().playSound(
                player.getLocation(),
                NmsUtils.getStepSoundForBlock(
                        LocationUtils.getBlockPlayerIsStuckOn(player, facing)),
                volume,
                pitch
        );
    }

}
