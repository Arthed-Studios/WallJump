package me.arthed.walljump.handlers;

import me.arthed.walljump.WallJump;
import me.arthed.walljump.player.PlayerManager;
import me.arthed.walljump.utils.LocationUtils;
import me.treyruffy.treysdoublejump.api.GroundPoundEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OtherPluginsHandler implements Listener {

    private final PlayerManager playerManager;

    public OtherPluginsHandler() {
        playerManager = WallJump.getInstance().getPlayerManager();
    }

    @EventHandler
    public void onTreysDoubleJumpGroundPound(GroundPoundEvent event) {
        if(playerManager.getWPlayer(event.getPlayer()).isWallJumping() || LocationUtils.isTouchingAWall(event.getPlayer()))
            event.setCancelled(true);
    }

}
