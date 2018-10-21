package de.simagdo.serversettingsmanager.listener;

import de.simagdo.serversettingsmanager.GUI.ServerPropertiesGUI;
import de.simagdo.serversettingsmanager.config.ServerSettingsManagerConfig;
import de.simagdo.serversettingsmanager.system.ServerSettingsManager;
import de.simagdo.serversettingsmanager.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ServerSettingsManagerListener implements Listener {

    private ServerSettingsManager plugin;
    private ServerSettingsManagerConfig config = new ServerSettingsManagerConfig();

    public ServerSettingsManagerListener(ServerSettingsManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        ArrayList<String> inventoryNames = new ArrayList<>();
        inventoryNames.add("§eServer Settings");
        inventoryNames.add("§eServer Properties");

        if (inventoryNames.contains(event.getInventory().getName()))
            event.setCancelled(true);

        //player.sendMessage("§eInventory: §a" + event.getInventory().getName() + ", §eSlot: §a" + event.getSlot());

        //Check which Inventory is currently open
        switch (event.getInventory().getName()) {
            case "§eServer Settings":
                switch (event.getSlot()) {
                    case 12:
                        player.openInventory(new ServerPropertiesGUI().openInventory(54, "&eServer Properties"));
                        break;
                }
                break;
            case "§eServer Properties":
                if (event.getCurrentItem() != null) {
                    changeSetting(event.getCurrentItem(), event.isLeftClick() ? Action.LEFT_CLICK_BLOCK : Action.RIGHT_CLICK_BLOCK);
                    //Update the Players Inventory
                    player.openInventory(new ServerPropertiesGUI().openInventory(54, "&eServer Properties"));
                }
        }

    }

    private void changeSetting(ItemStack itemStack, Action action) {
        List<String> lore = itemStack.getItemMeta().getLore();

        //Get the Setting from the current Item
        String setting = lore.get(lore.size() - 1).substring(lore.get(lore.size() - 1).indexOf(':') + 4);

        //Check what Item was clicked
        if (lore.size() == 6) {
            int currentValue = Integer.valueOf(lore.get(lore.size() - 3).substring(lore.get(lore.size() - 3).indexOf(':') + 4));
            Bukkit.broadcastMessage("§eAction: §a" + action + ", §eSetting: §a" + setting + ", §eCurrent Value: §a" + currentValue);

            switch (setting) {
                case "view-distance":
                    config.setProperty(setting, changeValue(1, 16, currentValue, action, 1));
                    break;
                case "gamemode":
                    config.setProperty(setting, changeValue(0, 3, currentValue, action, 1));
                    break;
                case "difficulty":
                    config.setProperty(setting, changeValue(1, 4, currentValue, action, 1));
                    break;
                case "spawn-protection":
                    config.setProperty(setting, changeValue(0, 128, currentValue, action, 16));
                    break;
            }
        } else {
            boolean currentState = !lore.get(1).split(" ")[2].equalsIgnoreCase("enable");
            Bukkit.broadcastMessage("§eAction: §a" + action + ", §eSetting: §a" + setting + ", §eCurrent Value: §a" + currentState);

            config.setProperty(setting, !currentState);

        }

    }

    /**
     * Change the Value of the Setting
     *
     * @param minValue     which the Setting can obtain
     * @param maxValue     which the Setting can obtain
     * @param currentValue of the Setting
     * @param action       from the {@link Player}
     * @param value        which will be added or removed
     * @return the new Value
     */
    private int changeValue(int minValue, int maxValue, int currentValue, Action action, int value) {

        if (Utils.between(currentValue + 1, minValue, maxValue) && action == Action.LEFT_CLICK_BLOCK) {
            currentValue += value;
        } else if (Utils.between(currentValue - 1, minValue, maxValue) && action == Action.RIGHT_CLICK_BLOCK) {
            currentValue -= value;
        }

        return currentValue;
    }

}
