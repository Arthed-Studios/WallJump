package me.arthed.walljump.api.events;

import me.arthed.walljump.player.WPlayer;
import org.jetbrains.annotations.NotNull;

public class WallJumpEndEvent extends WallJumpEvent {

    private float horizontalPower;
    private float verticalPower;

    public WallJumpEndEvent(@NotNull WPlayer who, float horizontalPower, float verticalPower) {
        super(who);
    }

    public float getHorizontalPower() {
        return horizontalPower;
    }

    public float getVerticalPower() {
        return verticalPower;
    }

    public void setHorizontalPower(float power) {
        this.horizontalPower = power;
    }

    public void setVerticalPower(float power) {
        this.verticalPower = power;
    }
}
