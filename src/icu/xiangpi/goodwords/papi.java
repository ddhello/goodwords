package icu.xiangpi.goodwords;

import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class papi extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "xiangpi";
    }

    @Override
    public String getIdentifier() {
        return "goodwords";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("name")) {
            return player == null ? null : player.getName(); // "name" requires the player to be valid
        }


        return Commander.getWords(params);
    }
}
