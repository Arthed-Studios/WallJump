package me.arthed.walljump.utils;

import me.arthed.walljump.handlers.anticheats.AACHandler;
import me.arthed.walljump.handlers.anticheats.AntiCheatHandler;
import me.arthed.walljump.handlers.anticheats.NoCheatPlusHandler;
import me.arthed.walljump.handlers.anticheats.SpartanHandler;
import org.bukkit.entity.Player;

public class AntiCheatUtils {

    private static AntiCheatUtils instance;
    private final AntiCheatHandler antiCheatHandler;

    public AntiCheatUtils() {
        instance = this;
        if(BukkitUtils.isPluginInstalled("Spartan"))
            antiCheatHandler = new SpartanHandler();
        else if(BukkitUtils.isPluginInstalled("AAC"))
            antiCheatHandler = new AACHandler();
        else if(BukkitUtils.isPluginInstalled("NoCheatPlus"))
            antiCheatHandler = new NoCheatPlusHandler();
        else
            antiCheatHandler = null;
    }

    public static void stopPotentialAntiCheatChecks(Player player) {
        if(instance.antiCheatHandler != null)
            instance.antiCheatHandler.stopPotentialWallJumpingChecks(player);
    }

    public static void restartPotentialAntiCheatChecks(Player player) {
        if(instance.antiCheatHandler != null)
            instance.antiCheatHandler.restartPotentialWallJumpingChecks(player);
    }
}
