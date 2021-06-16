package me.arthed.walljump.utils;

import me.arthed.walljump.WallJump;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.api.API;

public class AntiCheatUtils {

    public static void stopPotentialAntiCheatChecks(Player player) {
        if(WallJump.getInstance().isSpartanEnabled())
            if(API.isEnabled(Enums.HackType.IrregularMovements))
                API.stopCheck(player, Enums.HackType.IrregularMovements);
    }

    public static void restartPotentialAntiCheatChecks(Player player) {
        if(WallJump.getInstance().isSpartanEnabled())
            if(API.isEnabled(Enums.HackType.IrregularMovements))
                API.startCheck(player, Enums.HackType.IrregularMovements);
    }
}
