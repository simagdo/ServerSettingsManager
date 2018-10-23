package de.simagdo.serversettingsmanager.commands;

import de.simagdo.serversettingsmanager.GUI.ServerPropertiesGUI;
import de.simagdo.serversettingsmanager.system.ServerSettingsManager;
import de.simagdo.serversettingsmanager.utils.Utils;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Simagdo
 * @version 1.0
 */
public class ServerSettingsManagerCommand implements CommandExecutor {

    private ServerSettingsManager plugin;

    public ServerSettingsManagerCommand(ServerSettingsManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Check if Command was executed by a Player
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou can use this command only as a Player!");
        } else {

            Player player = (Player) sender;

            //Check if Player is Operator
            if (player.isOp()) {
                if (args.length == 0) {
                    Utils.sendHoverMessage(player, ServerSettingsManager.prefix + ChatColor.GRAY + "You need help? " + ChatColor.DARK_GREEN + "» " + ChatColor.GREEN +
                                    "Hover over the Text to see the commands ", HoverEvent.Action.SHOW_TEXT,
                            "§6Avaible commands: \n\n" +
                                    "§7• settings: §bChange the Settings Properties");

                    return true;
                }
                switch (args[0]) {
                    //Open the Server Properties GUI
                    case "settings":
                        player.openInventory(new ServerPropertiesGUI().openInventory(54, "§eServer Properties"));
                        break;
                }
            } else {
                Utils.sendActionBarMessage(player, "§cYou do not have Permission to use that command!");
            }

        }

        return false;
    }
}
