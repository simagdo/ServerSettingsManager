package de.simagdo.serversettingsmanager.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author Simagdo
 * @version 1.0
 */
public abstract class ConfigHandler {

    /**
     * Create a config
     */
    public abstract void createConfig();

    /**
     * Save the Config
     *
     * @param file          which will be saved
     * @param configuration which contains the config
     */
    public void save(File file, FileConfiguration configuration) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

}
