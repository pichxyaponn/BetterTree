package org.pichxyaponn.View;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BetterTreeView {
    public void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.GREEN + "[BetterTree] " + message);
    }

    public void playSound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
