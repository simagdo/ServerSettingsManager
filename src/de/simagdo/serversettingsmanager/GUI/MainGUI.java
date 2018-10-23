package de.simagdo.serversettingsmanager.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author Simagdo
 * @version 1.0
 */
public class MainGUI extends InventoryHandler {

    @Override
    public Inventory openInventory(Player player, int size, String inventoryName) {
        return null;
    }

    /**
     * Open the Main GUI
     *
     * @param size          of of the GUI
     * @param inventoryName which will be displayed at the top
     * @return the main GUI
     */
    @Override
    public Inventory openInventory(int size, String inventoryName) {
        Inventory inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', inventoryName));

        inventory.setItem(12, createItemStack(Material.REDSTONE_BLOCK, 1, "§7Server Properties"));
        inventory.setItem(14, createItemStack(Material.COMPARATOR, 1, "§7Spigot Settings"));

        return inventory;
    }
}
