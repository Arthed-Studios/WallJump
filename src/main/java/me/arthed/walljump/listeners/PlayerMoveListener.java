package me.arthed.walljump.listeners;

import me.arthed.walljump.WallJump;
import me.arthed.walljump.player.PlayerManager;
import me.arthed.walljump.player.WPlayer;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final PlayerManager playerManager = WallJump.getInstance().getPlayerManager();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getTo().getY() < event.getFrom().getY()) {
            WPlayer wplayer = playerManager.getWPlayer(event.getPlayer());
            if (wplayer.isOnWall() && !wplayer.isSliding()) {
                Location clone = event.getTo().clone();
                clone.setY(event.getFrom().getY());
                event.setTo(clone);
            }
        }
    }

}
