package de.simagdo.serversettingsmanager.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainGUI extends InventoryHandler {

    @Override
    public Inventory openInventory(Player player, int size, String inventoryName) {
        return null;
    }

    @Override
    public Inventory openInventory(int size, String inventoryName) {
        Inventory inventory = Bukkit.createInventory(null, size, inventoryName);

        inventory.setItem(12, createItemStack(Material.REDSTONE_BLOCK, 1, 0, "§7Server Properties"));
        inventory.setItem(14, createItemStack(Material.COMPARATOR, 1, 0, "§7Spigot Settings"));

        return inventory;
    }
}
