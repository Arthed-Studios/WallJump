package me.arthed.walljump.handlers.anticheats;

import me.vagdedes.spartan.api.API;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;

public class SpartanHandler implements AntiCheatHandler {

    @Override
    public void stopPotentialWallJumpingChecks(Player player) {
        if(API.isEnabled(Enums.HackType.IrregularMovements))
            API.stopCheck(player, Enums.HackType.IrregularMovements);
    }

    @Override
    public void restartPotentialWallJumpingChecks(Player player) {
        if(API.isEnabled(Enums.HackType.IrregularMovements))
            API.startCheck(player, Enums.HackType.IrregularMovements);
    }
}
