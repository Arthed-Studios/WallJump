package me.arthed.walljump.handlers.anticheats;

import fr.neatmonster.nocheatplus.NCPAPIProvider;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.components.NoCheatPlusAPI;
import fr.neatmonster.nocheatplus.hooks.ExemptionContext;
import fr.neatmonster.nocheatplus.players.IPlayerData;
import org.bukkit.entity.Player;

public class NoCheatPlusHandler implements AntiCheatHandler {

    private final NoCheatPlusAPI api;
    private final ExemptionContext exemptionContext;

    public NoCheatPlusHandler() {
        api = NCPAPIProvider.getNoCheatPlusAPI();
        exemptionContext = new ExemptionContext(88311);
    }
    @Override
    public void stopPotentialWallJumpingChecks(Player player) {
        IPlayerData playerData = api.getPlayerDataManager().getPlayerData(player);
        if(playerData.isCheckActive(CheckType.MOVING, player))
            playerData.exempt(CheckType.MOVING, exemptionContext);
    }

    @Override
    public void restartPotentialWallJumpingChecks(Player player) {
        IPlayerData playerData = api.getPlayerDataManager().getPlayerData(player);
        if(playerData.isCheckActive(CheckType.MOVING, player))
            playerData.unexempt(CheckType.MOVING, exemptionContext);
    }
}
