package me.arthed.walljump.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import me.arthed.walljump.utils.BukkitUtils.Version;

import java.lang.reflect.Field;

public class NmsUtils {

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static Class<?> getNmsClass(String pre1_17ClassName, String _1_17Path) throws ClassNotFoundException {
        if(BukkitUtils.isVersionBefore(Version.V1_16))
            return getNmsClass(pre1_17ClassName);
        return Class.forName(_1_17Path);
    }


    public static Sound getBreakSoundForBlock(Block block) {
        if(BukkitUtils.isVersionBefore(Version.V1_8))
            return getSoundForBlock(block, "getBreakSound");
        return getSoundForBlock(block, getBreakSoundFieldName());
    }

    public static Sound getStepSoundForBlock(Block block) {
        if(BukkitUtils.isVersionBefore(Version.V1_8))
            return getSoundForBlock(block, "getStepSound");
        return getSoundForBlock(block, getStepSoundFieldName());
    }

    public static Sound getPlaceSoundForBlock(Block block) {
        if(BukkitUtils.isVersionBefore(Version.V1_8))
            return getSoundForBlock(block, "getBreakSound");
        return getSoundForBlock(block, getPlaceSoundFieldName());
    }

    public static Sound getHitSoundForBlock(Block block) {
        if(BukkitUtils.isVersionBefore(Version.V1_8))
            return getSoundForBlock(block, "getStepSound");
        return getSoundForBlock(block, getHitSoundFieldName());
    }

    public static Sound getFallSoundForBlock(Block block) {
        if(BukkitUtils.isVersionBefore(Version.V1_8))
            return getSoundForBlock(block, "getStepSound");
        return getSoundForBlock(block, getFallSoundFieldName());
    }

    private static Sound getSoundForBlock(Block block, String fieldName) {
        try {
            Object nmsWorld = block.getWorld().getClass().getMethod("getHandle").invoke(block.getWorld());


            Object blockPosition = getNmsClass("BlockPosition", "net.minecraft.core.BlockPosition")
                    .getConstructor(double.class, double.class, double.class)
                    .newInstance(block.getX(), block.getY(), block.getZ());
            Object nmsType = nmsWorld.getClass()
                    .getMethod("getType", blockPosition.getClass())
                    .invoke(nmsWorld, blockPosition);

            Object nmsBlock = nmsType.getClass().getMethod("getBlock").invoke(nmsType);

            if(BukkitUtils.isVersionBefore(Version.V1_8)) {
                String soundString = (String) nmsBlock.getClass().getMethod(fieldName).invoke(nmsBlock);
                return Sound.valueOf(soundString);
            }

            Object soundEffectType = nmsBlock.getClass()
                    .getMethod(
                            "getStepSound",
                            getNmsClass("IBlockData", "net.minecraft.world.level.block.state.IBlockData"))
                    .invoke(nmsBlock, (Object)null);

            Field sound = soundEffectType.getClass().getDeclaredField(fieldName);
            sound.setAccessible(true);
            Object nmsSound = sound.get(soundEffectType);

            String keyFieldName = "b";

            if(BukkitUtils.isVersionBefore(Version.V1_12))
                keyFieldName = "b";
            else if(BukkitUtils.isVersionBefore(Version.V1_15))
                keyFieldName = "a";

            Field keyField = nmsSound.getClass().getDeclaredField(keyFieldName);
            keyField.setAccessible(true);
            Object nmsKey = keyField.get(nmsSound);

            String getKeyMethodName = "getKey";
            if(BukkitUtils.isVersionBefore(Version.V1_11))
                getKeyMethodName = "a";

            String key = (String) nmsKey.getClass().getMethod(getKeyMethodName).invoke(nmsKey);

            return Sound.valueOf(key.replace(".", "_").toUpperCase());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return Sound.BLOCK_STONE_PLACE;
    }

    private static String getBreakSoundFieldName() {
        String soundFieldName = "aA"; //1.17
        if(BukkitUtils.isVersionBefore(Version.V1_12))
            soundFieldName = "o";
        else if(BukkitUtils.isVersionBefore(Version.V1_13))
            soundFieldName = "q";
        else if(BukkitUtils.isVersionBefore(Version.V1_14))
            soundFieldName = "y";
        else if(BukkitUtils.isVersionBefore(Version.V1_15))
            soundFieldName = "z";
        else if(BukkitUtils.isVersionBefore(Version.V1_16))
            soundFieldName = "X";
        return soundFieldName;
    }

    private static String getStepSoundFieldName() {
        String soundFieldName = "aB"; //1.17
        if(BukkitUtils.isVersionBefore(Version.V1_12))
            soundFieldName = "p";
        else if(BukkitUtils.isVersionBefore(Version.V1_13))
            soundFieldName = "r";
        else if(BukkitUtils.isVersionBefore(Version.V1_14))
            soundFieldName = "z";
        else if(BukkitUtils.isVersionBefore(Version.V1_15))
            soundFieldName = "A";
        else if(BukkitUtils.isVersionBefore(Version.V1_16))
            soundFieldName = "Y";
        return soundFieldName;
    }

    private static String getPlaceSoundFieldName() {
        String soundFieldName = "aC"; //1.17
        if(BukkitUtils.isVersionBefore(Version.V1_12))
            soundFieldName = "q";
        else if(BukkitUtils.isVersionBefore(Version.V1_13))
            soundFieldName = "s";
        else if(BukkitUtils.isVersionBefore(Version.V1_14))
            soundFieldName = "A";
        else if(BukkitUtils.isVersionBefore(Version.V1_15))
            soundFieldName = "B";
        else if(BukkitUtils.isVersionBefore(Version.V1_16))
            soundFieldName = "Z";
        return soundFieldName;
    }

    private static String getHitSoundFieldName() {
        String soundFieldName = "aD"; //1.17
        if(BukkitUtils.isVersionBefore(Version.V1_12))
            soundFieldName = "r";
        else if(BukkitUtils.isVersionBefore(Version.V1_13))
            soundFieldName = "t";
        else if(BukkitUtils.isVersionBefore(Version.V1_14))
            soundFieldName = "B";
        else if(BukkitUtils.isVersionBefore(Version.V1_15))
            soundFieldName = "C";
        else if(BukkitUtils.isVersionBefore(Version.V1_16))
            soundFieldName = "aa";
        return soundFieldName;
    }

    private static String getFallSoundFieldName() {
        String soundFieldName = "aE"; //1.17
        if(BukkitUtils.isVersionBefore(Version.V1_12))
            soundFieldName = "s";
        else if(BukkitUtils.isVersionBefore(Version.V1_13))
            soundFieldName = "u";
        else if(BukkitUtils.isVersionBefore(Version.V1_14))
            soundFieldName = "C";
        else if(BukkitUtils.isVersionBefore(Version.V1_15))
            soundFieldName = "D";
        else if(BukkitUtils.isVersionBefore(Version.V1_16))
            soundFieldName = "ab";
        return soundFieldName;
    }


}
