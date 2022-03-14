package me.arthed.walljump.nms;

import net.minecraft.sounds.SoundCategory;
import net.minecraft.world.level.block.SoundEffectType;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.block.data.CraftBlockData;

public class NmsUtils_v1_18 implements NmsUtilsVersion {

    @Override
    public void playStepSound(Block block) {
        SoundEffectType soundEffectType = ((CraftBlockData) block.getBlockData()).getState().q();
        ((CraftWorld) block.getWorld()).getHandle().a(
                null,
                block.getX(), block.getY(), block.getZ(),
                soundEffectType.d(),
                SoundCategory.h,
                soundEffectType.a() * 0.15F,
                soundEffectType.b()
        );
    }

}