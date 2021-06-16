package me.arthed.walljump.handlers.anticheats;

import me.konsolas.aac.api.AACAPI;
import me.konsolas.aac.api.AACExemption;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AACHandler implements AntiCheatHandler {

    private final AACAPI api;
    private final AACExemption aacExemption;

    public AACHandler() {
        api = Bukkit.getServicesManager().load(AACAPI.class);
        aacExemption = new AACExemption("WallJumping");
    }

    @Override
    public void stopPotentialWallJumpingChecks(Player player) {
        api.addExemption(player, aacExemption);
    }

    @Override
    public void restartPotentialWallJumpingChecks(Player player) {
        api.removeExemption(player, aacExemption);
    }
}
