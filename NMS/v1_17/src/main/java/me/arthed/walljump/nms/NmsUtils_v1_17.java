package me.arthed.walljump.nms;

import net.minecraft.sounds.SoundCategory;
import net.minecraft.world.level.block.SoundEffectType;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;

public class NmsUtils_v1_17 implements NmsUtilsVersion {

    @Override
    public void playStepSound(Block block) {
        SoundEffectType soundEffectType = ((CraftBlockData)block.getBlockData()).getState().getStepSound();
        ((CraftWorld)block.getWorld()).getHandle().playSound(
                null,
                block.getX(), block.getY(), block.getZ(),
                soundEffectType.getStepSound(),
                SoundCategory.h,
                soundEffectType.getVolume() * 0.15F,
                soundEffectType.getPitch()
        );
    }
}