package me.arthed.walljump.nms;

import org.bukkit.block.Block;

public class NmsUtils {

    private static final NmsUtilsVersion utils;

    static {
        switch(BukkitVersion.getVersion()) {
            case v1_18:
                utils = new NmsUtils_v1_18();
                break;
            case v1_17:
                utils = new NmsUtils_v1_17();
                break;
            case v1_16:
                utils = new NmsUtils_v1_16();
                break;
            case v1_15:
                utils = new NmsUtils_v1_15();
                break;
            case v1_14:
                utils = new NmsUtils_v1_14();
                break;
            case v1_13:
                utils = new NmsUtils_v1_13();
                break;
            case v1_12:
                utils = new NmsUtils_v1_12();
                break;
            case v1_11:
                utils = new NmsUtils_v1_11();
                break;
            default:
                utils = new NmsUtils_v1_10();
                break;
        }
    }

    public static void playStepSound(Block block) {
        utils.playStepSound(block);
    }
}
