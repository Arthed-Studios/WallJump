package me.arthed.walljump.nms;

import org.bukkit.Bukkit;

public enum BukkitVersion {

    v1_18("1.18", 118),
    v1_17("1.17", 117),
    v1_16("1.16", 116),
    v1_15("1.15", 115),
    v1_14("1.14", 114),
    v1_13("1.13", 113),
    v1_12("1.12", 112),
    v1_11("1.11", 111),
    v1_10("1.10", 110),
    v1_9("1.9", 19),
    v1_8("1.8", 18);

    public static BukkitVersion version;

    public static BukkitVersion getVersion() {
        return version;
    };

    private final int versionInt;

    BukkitVersion(String versionStr, int versionInt) {
        this.versionInt = versionInt;
        if(Bukkit.getVersion().contains(versionStr)) setVersion(this);
    }

    void setVersion(BukkitVersion bukkitVersion) {
        version = bukkitVersion;
    }

    public boolean isLessThan(BukkitVersion version) {
        return this.versionInt <= version.versionInt;
    }

}