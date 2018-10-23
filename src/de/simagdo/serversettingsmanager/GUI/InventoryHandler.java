package de.simagdo.serversettingsmanager.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * Creates a custom Inventory where you can specify the player, who receive the inventory, the size and the name.
 * @author Simagdo
 * @version 1.0
 */
public abstract class InventoryHandler {

    /**
     * Open a custom GUI with some parameters
     *
     * @param player        which will see the GUI
     * @param size          of the GUI
     * @param inventoryName which will be displayed at the top
     * @return the custom GUI
     */
    public abstract Inventory openInventory(Player player, int size, String inventoryName);

    /**
     * Open a custom GUI with some parameters
     *
     * @param size          of of the GUI
     * @param inventoryName which will be displayed at the top
     * @return the custom GUI
     */
    public abstract Inventory openInventory(int size, String inventoryName);

    /**
     * Creates a custom Item with the given parameters
     *
     * @param material    type of the item
     * @param amount      how many items will be used
     * @param displayName of the item
     * @param lores       which will be displayed
     * @return the custom item
     */
    public ItemStack createItemStack(Material material, int amount, String displayName, String... lores) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        ArrayList<String> lore = new ArrayList<>();

        for (String item : lores) lore.add(ChatColor.translateAlternateColorCodes('&', item));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    /**
     * Creates a custom Potion with the given parameters
     *
     * @param effect      which effect the potion should have
     * @param amount      how many items will be used
     * @param displayName the name of the item
     * @param lores       which will be displayed
     * @return the custom potion
     */
    public ItemStack createPotion(PotionEffectType effect, int amount, String displayName, String... lores) {
        ItemStack itemStack = new ItemStack(Material.POTION, amount);
        PotionMeta itemMeta = (PotionMeta) itemStack.getItemMeta();
        itemMeta.addCustomEffect(new PotionEffect(effect, 0, 0), true);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        ArrayList<String> lore = new ArrayList<>();
        for (String item : lores) lore.add(ChatColor.translateAlternateColorCodes('&', item));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Creates a custom Skull with the given parameters
     *
     * @param amount      how many items will be used
     * @param userName    from which User the Skull will be created
     * @param displayName the name of the item
     * @param lores       which will be displayed
     * @return the custom Skull
     */
    public ItemStack createSkull(int amount, String userName, String displayName, String... lores) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, amount, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        Player player = Bukkit.getPlayer(userName);
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        itemStack.setItemMeta(skullMeta);
        for (String item : lores) lore.add(ChatColor.translateAlternateColorCodes('&', item));
        skullMeta.setLore(lore);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    /**
     * Custom item which will be used in GUIs
     *
     * @return back item
     */
    public ItemStack back() {
        return createItemStack(Material.ARROW, 1, "&bBack", "&7Click to go back");
    }

}
