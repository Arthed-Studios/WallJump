package me.arthed.walljump.utils;

import me.arthed.walljump.enums.WallFace;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class EffectUtils {

    public static void spawnSlidingParticles(Player player, int count, BlockFace facing) {
        WallFace wallFacing = WallFace.fromBlockFace(facing);
        Location location = player.getLocation();
        player.getWorld().spawnParticle(
                Particle.BLOCK_DUST,
                location.clone().add(wallFacing.xOffset*0.3, wallFacing.yOffset*0.3-0.3, wallFacing.zOffset*0.3),
                count,
                0.2f,
                0.2f,
                0.2f,
                location.clone().add(wallFacing.xOffset, wallFacing.yOffset, wallFacing.zOffset).getBlock().getBlockData());
    }

    public static void playWallJumpSound(Player player, BlockFace facing, float volume, float pitch) {
        WallFace wallFacing = WallFace.fromBlockFace(facing);
        player.getWorld().playSound(
                player.getLocation(),
                NmsUtils.getStepSoundForBlock(
                        player.getLocation().clone().add(wallFacing.xOffset, wallFacing.yOffset, wallFacing.zOffset).getBlock()),
                volume,
                pitch
        );
    }

}
