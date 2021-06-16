package me.arthed.walljump.handlers;

import me.arthed.walljump.api.events.WallJumpEndEvent;
import me.arthed.walljump.api.events.WallJumpStartEvent;
import me.arthed.walljump.utils.BukkitUtils;
import me.treyruffy.treysdoublejump.api.DoubleJumpAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OtherPluginsHandler implements Listener {

    private boolean treysDoubleJump;
    private boolean canGroundPound;

    public OtherPluginsHandler() {
        treysDoubleJump = BukkitUtils.isPluginInstalled("TreysDoubleJump");
    }

    @EventHandler
    public void onWallJumpStart(WallJumpStartEvent event) {
        if(treysDoubleJump) {
            canGroundPound = DoubleJumpAPI.isGroundPoundEnabled(event.getPlayer());
            DoubleJumpAPI.setGroundPound(event.getPlayer(), false);
        }
    }

    @EventHandler
    public void onWallJumpEnd(WallJumpEndEvent event) {
        if(treysDoubleJump) {
            DoubleJumpAPI.setGroundPound(event.getPlayer(), canGroundPound);
        }
    }

}
