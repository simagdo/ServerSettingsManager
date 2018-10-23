package de.simagdo.serversettingsmanager.system;

import de.simagdo.serversettingsmanager.commands.ServerSettingsManagerCommand;
import de.simagdo.serversettingsmanager.listener.ServerSettingsManagerListener;
import de.simagdo.serversettingsmanager.metrics.Metrics;
import de.simagdo.serversettingsmanager.utils.Utils;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Simagdo
 * @version 1.0
 */
public class ServerSettingsManager extends JavaPlugin {

    public final static String prefix = "§5[§7SSM§5] §f";

    public static void main(String[] args) {
        System.out.println(Utils.between(11, 1, 10));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        //Register
        registerCommands();
        registerEvents(getServer().getPluginManager());

        //Enable Metrics
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));

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
