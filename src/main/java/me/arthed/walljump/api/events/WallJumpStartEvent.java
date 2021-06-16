package me.arthed.walljump.api.events;

import me.arthed.walljump.player.WPlayer;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class WallJumpStartEvent extends WallJumpEvent implements Cancellable {

    public WallJumpStartEvent(@NotNull WPlayer who) {
        super(who);
    }
}
