package me.arthed.walljump.nms;

import net.minecraft.server.v1_16_R3.SoundCategory;
import net.minecraft.server.v1_16_R3.SoundEffectType;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.block.data.CraftBlockData;

public class NmsUtils_v1_16 implements NmsUtilsVersion {

    @Override
    public void playStepSound(Block block) {
        SoundEffectType soundEffectType = ((CraftBlockData)block.getBlockData()).getState().getStepSound();
        ((CraftWorld)block.getWorld()).getHandle().playSound(
                null,
                block.getX(), block.getY(), block.getZ(),
                soundEffectType.getStepSound(),
                SoundCategory.PLAYERS,
                soundEffectType.getVolume() * 0.15F,
                soundEffectType.getPitch()
        );
    }
}
