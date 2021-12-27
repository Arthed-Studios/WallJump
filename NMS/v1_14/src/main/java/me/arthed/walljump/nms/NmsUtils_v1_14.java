package me.arthed.walljump.nms;

import net.minecraft.server.v1_14_R1.SoundCategory;
import net.minecraft.server.v1_14_R1.SoundEffectType;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.block.data.CraftBlockData;

public class NmsUtils_v1_14 implements NmsUtilsVersion {

    @Override
    public void playStepSound(Block block) {
        SoundEffectType soundEffectType = ((CraftBlockData)block.getBlockData()).getState().r();
        ((CraftWorld)block.getWorld()).getHandle().playSound(
                null,
                block.getX(), block.getY(), block.getZ(),
                soundEffectType.d(),
                SoundCategory.PLAYERS,
                soundEffectType.a() * 0.15F,
                soundEffectType.b()
        );
    }
}
