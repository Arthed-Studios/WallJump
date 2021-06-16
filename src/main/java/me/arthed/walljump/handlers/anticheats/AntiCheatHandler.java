package me.arthed.walljump.handlers.anticheats;

import org.bukkit.entity.Player;

public interface AntiCheatHandler {

    void stopPotentialWallJumpingChecks(Player player);
    void restartPotentialWallJumpingChecks(Player player);

}
