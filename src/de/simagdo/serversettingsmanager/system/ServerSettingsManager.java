package de.simagdo.serversettingsmanager.system;

import de.simagdo.serversettingsmanager.commands.ServerSettingsManagerCommand;
import de.simagdo.serversettingsmanager.listener.ServerSettingsManagerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ServerSettingsManager extends JavaPlugin {

    public final static File SERVER_PROPERTIES = new File("server.properties");
    public final static File SPIGOT = new File("spigot.yml");

    public final static String prefix = "§5[§7SSM§5] §f";

    @Override
    public void onEnable() {
        super.onEnable();

        //Register
        registerCommands();
        registerEvents(getServer().getPluginManager());

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerCommands() {
        getCommand("SSM").setExecutor(new ServerSettingsManagerCommand(this));
    }

    private void registerEvents(PluginManager pluginManager) {
        pluginManager.registerEvents(new ServerSettingsManagerListener(this), this);
    }

}
