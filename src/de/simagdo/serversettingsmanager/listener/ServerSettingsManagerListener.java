package de.simagdo.serversettingsmanager.listener;

import de.simagdo.serversettingsmanager.GUI.ServerPropertiesGUI;
import de.simagdo.serversettingsmanager.system.ServerSettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ServerSettingsManagerListener implements Listener {

    private ServerSettingsManager plugin;

    public ServerSettingsManagerListener(ServerSettingsManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        //Check which Inventory is currently open
        switch (event.getInventory().getName()) {
            case "§eServer Settings":
                switch (event.getSlot()) {
                    case 12:
                        player.openInventory(new ServerPropertiesGUI().openInventory(54, "&eServer Properties"));
                        break;
                }
                break;
        }

    }

}
