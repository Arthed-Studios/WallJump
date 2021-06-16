package me.arthed.walljump.api.events;

import me.arthed.walljump.player.WPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public abstract class WallJumpEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();;
    private final WPlayer wplayer;

    private boolean cancelled;

    public WallJumpEvent(@NotNull WPlayer who) {
        super(who.getPlayer());
        this.wplayer = who;
    }

    public WPlayer getWPlayer() {
        return wplayer;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
