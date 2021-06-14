package me.arthed.walljump.listeners;

import me.arthed.walljump.WallJump;
import me.arthed.walljump.player.PlayerManager;
import me.arthed.walljump.player.WPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    private final PlayerManager playerManager = WallJump.getInstance().getPlayerManager();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL) && event.getEntityType().equals(EntityType.PLAYER)) {
            WPlayer wplayer = playerManager.getWPlayer((Player)event.getEntity());
            if(wplayer != null && wplayer.isSliding()) {
                event.setCancelled(true);
                wplayer.onWallJumpEnd(false);
            }
        }
    }

}
