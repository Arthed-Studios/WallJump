package me.arthed.walljump.nms;

import net.minecraft.server.v1_13_R2.SoundCategory;
import net.minecraft.server.v1_13_R2.SoundEffectType;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.block.data.CraftBlockData;

public class NmsUtils_v1_13 implements NmsUtilsVersion {

    @Override
    public void playStepSound(Block block) {
        SoundEffectType soundEffectType = ((CraftBlockData)block.getBlockData()).getState().getBlock().getStepSound();
        ((CraftWorld)block.getWorld()).getHandle().a(
                null,
                block.getX(), block.getY(), block.getZ(),
                soundEffectType.d(),
                SoundCategory.PLAYERS,
                soundEffectType.a() * 0.15F,
                soundEffectType.b()
        );
    }
}
