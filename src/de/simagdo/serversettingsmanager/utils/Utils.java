package de.simagdo.serversettingsmanager.utils;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_13_R2.ChatMessageType;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import net.minecraft.server.v1_13_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author Simagdo
 * @version 1.0
 */
public class Utils {

    /**
     * Play a hover message, when the player hovers over a command
     *
     * @param player  where the message will be displayed
     * @param message which message will be displayed
     * @param event   which event will be used
     * @param lore    which sub message will be displayed
     */
    public static void sendHoverMessage(Player player, String message, HoverEvent.Action event, String lore) {
        TextComponent component = new TextComponent(message);
        component.setHoverEvent(new HoverEvent(event, new ComponentBuilder(lore).create()));
        player.spigot().sendMessage(component);
    }

    /**
     * Sends a message to the Player using the ActionBar
     *
     * @param player  which receives the Message
     * @param message which will be send.
     */
    public static void sendActionBarMessage(Player player, String message) {
        PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(chat, ChatMessageType.GAME_INFO);
        con.sendPacket(packet);
    }

    /**
     * Check if the currentValue is between the minValue and the maxValue
     *
     * @param currentValue which will be checked if between
     * @param minValue     of the check
     * @param maxValue     of the check
     * @return if the position is between the points
     */
    public static boolean between(int currentValue, int minValue, int maxValue) {
        return (currentValue >= minValue && currentValue <= maxValue);
    }

}
