package me.arthed.walljump.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private Map<Player, WPlayer> players = new HashMap<>();

    public void registerPlayer(Player player) {
        players.put(player, new WPlayer(player));
    }

    public void unregisterPlayer(Player player) {
        players.remove(player);
    }

    public WPlayer getWPlayer(Player player) {
        return players.get(player);
    }

}
