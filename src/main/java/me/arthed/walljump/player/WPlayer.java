package me.arthed.walljump.player;

import me.arthed.walljump.WallJump;
import me.arthed.walljump.config.WallJumpConfiguration;
import me.arthed.walljump.enums.WallFace;
import me.arthed.walljump.handlers.WorldGuardHandler;
import me.arthed.walljump.utils.LocationUtils;
import me.arthed.walljump.utils.EffectUtils;
import me.arthed.walljump.utils.VelocityUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class WPlayer {

    private final Player player;

    private boolean onWall;
    private boolean sliding;

    private BlockFace lastFacing;
    private Location lastJumpLocation;
    private int remainingJumps = -1;

    private BukkitTask velocityTask;
    private BukkitTask fallTask;
    private float velocityY;
    private BukkitTask stopWallJumpingTask;

    private final WallJumpConfiguration config;
    private final WorldGuardHandler worldGuard;

    public boolean enabled;

    protected WPlayer(Player player) {
        this.player = player;

        config = WallJump.getInstance().getWallJumpConfig();
        worldGuard = WallJump.getInstance().getWorldGuardHandler();
    }

    public void onWallJumpStart() {
        if(!canWallJump())
            return;

        onWall = true;
        lastFacing = player.getFacing();
        lastJumpLocation = player.getLocation();
        if(remainingJumps > 0)
            remainingJumps--;

        //play sound and spawn particles
        EffectUtils.playWallJumpSound(player, lastFacing, 0.3f, 1.2f);
        EffectUtils.spawnSlidingParticles(player, 5, lastFacing);

        //stop the player from falling and moving while on the wall
        //or make them slide down
        velocityY = 0;
        velocityTask = Bukkit.getScheduler().runTaskTimerAsynchronously(WallJump.getInstance(), () -> {
            player.setVelocity(new Vector(0, velocityY, 0));
            if(velocityY != 0) {
                EffectUtils.spawnSlidingParticles(player, 2, lastFacing);
                if(sliding) {
                    if (player.isOnGround())
                        onWallJumpEnd(false);
                    if (lastJumpLocation.getY() - player.getLocation().getY() >= 1.2) {
                        lastJumpLocation = player.getLocation();
                        EffectUtils.playWallJumpSound(player, lastFacing, 0.2f, 0.6f);
                    }
                }
            }
        }, 0, 1);

        //make the player fall | slide when the time runs out
        if(fallTask != null)
            fallTask.cancel();
        fallTask = Bukkit.getScheduler().runTaskLaterAsynchronously(WallJump.getInstance(), () -> {
            if(config.getBoolean("slide")) {
                velocityY = (float) -config.getDouble("slidingSpeed");
                sliding = true;
            }
            else
                onWallJumpEnd();
        }, (long)(config.getDouble("timeOnWall")*20));

        //cancel the task for resetting wall jumping if the player wall jumps
        if(stopWallJumpingTask != null)
            stopWallJumpingTask.cancel();


    }

    public void onWallJumpEnd() {
        onWallJumpEnd(true);
    }

    public void onWallJumpEnd(boolean jump) {
        onWall = false;
        sliding = false;

        //allow the player to move again
        player.setFallDistance(0);
        velocityTask.cancel();

        //if the player is not sliding or can jump while sliding and is not looking down
        if(jump &&
                ((velocityY == 0 && player.getLocation().getPitch() < 85) ||
                (config.getBoolean("canJumpWhileSliding") && player.getLocation().getPitch() < 60)))
            //push the player in the direction that they are looking
            VelocityUtils.pushPlayerInFront(player,
                    (float) config.getDouble("horizontalJumpPower"),
                    (float) config.getDouble("verticalJumpPower"));

        //after 1.5 seconds, if the player hasn't wall jumped again, reset everything
        Bukkit.getScheduler().runTaskLaterAsynchronously(WallJump.getInstance(), () -> {
            if(LocationUtils.isOnGround(player)) {
                reset();
            }
        }, 12);

        stopWallJumpingTask = Bukkit.getScheduler().runTaskLaterAsynchronously(WallJump.getInstance(), this::reset, 24);

    }

    private void reset() {
        lastFacing = null;
        lastJumpLocation = null;
        remainingJumps = config.getInt("maxJumps");
        if(remainingJumps == 0)
            remainingJumps = -1;
        stopWallJumpingTask.cancel();
        stopWallJumpingTask = null;
    }

    private boolean canWallJump() {
        if(lastJumpLocation != null)
            //used so height doesn't matter when calculating distance between the players location and the last jump location
            lastJumpLocation.setY(player.getLocation().getY());
        if(
                        !enabled ||
                        onWall || //player is already stuck to an wall
                        remainingJumps == 0 || //player reached jump limit
                        (lastFacing != null && lastFacing.equals(player.getFacing())) || //player is facing the same direction as the last jump
                        (lastJumpLocation != null && player.getLocation().distance(lastJumpLocation) <= config.getDouble("minimumDistance")) ||  //player is too close to the last jump location
                        player.getVelocity().getY() < config.getDouble("maximumVelocity") || //player is falling too fast
                        (config.getBoolean("needPermission") && !player.hasPermission("walljump.use")) || //player does not have the permission to wall-jump
                        !worldGuard.canWallJump(player) //wall-jumping is not allowed in the region the player is in
        )

            return false;
        //check if the block the player is wall jumping on is blacklisted
        WallFace wallFacing = WallFace.fromBlockFace(player.getFacing());
        boolean onBlacklistedBlock = config.getMaterialList("blacklistedBlocks").contains(
                player.getLocation().clone().add(wallFacing.xOffset,
                        wallFacing.yOffset,
                        wallFacing.zOffset)
                        .getBlock()
                        .getType());
        boolean reverseBlockBlacklist = config.getBoolean("reversedBlockBlacklist");
        if((!reverseBlockBlacklist && onBlacklistedBlock) ||
                (reverseBlockBlacklist && !onBlacklistedBlock))
            return false;

        //check if the world the player is in is blacklisted
        boolean inBlacklistedWorld = config.getWorldList("blacklistedWorlds").contains(
                player.getWorld());
        boolean reverseWorldBlacklist = config.getBoolean("reversedWorldBlacklist");
        if((!reverseWorldBlacklist && inBlacklistedWorld) ||
                (reverseWorldBlacklist && !inBlacklistedWorld))
            return false;

        return true;
    }

    public boolean isOnWall() {
        return onWall;
    }

    public boolean isSliding() {
        return sliding;
    }

}
