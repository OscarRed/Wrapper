package red.oscar.wrapper.utility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;

import java.io.File;
import java.util.logging.Level;

public class PluginUtility {

    /**
     * Enables the downloaded plugin on the server.
     * @param targetFile The path to the temporary plugin file.
     */
    public void loadPlugin(File targetFile) {
        try {
            Bukkit.getLogger().log(Level.INFO, "Enabling plugin...");
            Bukkit.getPluginManager().loadPlugin(targetFile);
            targetFile.delete();
            Bukkit.getLogger().log(Level.INFO, "Plugin successfully enabled!");
        } catch (InvalidDescriptionException | InvalidPluginException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to enable plugin on server!");
            e.printStackTrace();
        }
    }
}