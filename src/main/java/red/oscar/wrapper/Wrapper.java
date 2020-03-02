package red.oscar.wrapper;

import org.bukkit.plugin.java.JavaPlugin;
import red.oscar.wrapper.configuration.Config;
import red.oscar.wrapper.utility.NetworkUtility;
import red.oscar.wrapper.utility.PluginUtility;

import java.io.File;
import java.util.Objects;

public class Wrapper extends JavaPlugin {

    /**
     * Plugin loading utility.
     */
    private PluginUtility pluginUtility = new PluginUtility();

    /**
     * Network utility for loading the plugin from the server.
     */
    private NetworkUtility networkUtility = new NetworkUtility();

    /**
     * Server target URL.
     */
    private final String TARGET_URL = "https://oscar.red/verify";

    /**
     * Called when the plugin is enabled.
     */
    @Override
    public void onEnable() {

        File loadedPluginFile = this.networkUtility.downloadPlugin(this.TARGET_URL, Config.KEY.getString());

        /**
         * Checks that the plugin has been downloaded before attempting to enable.
         */
        if (!Objects.isNull(loadedPluginFile)) {
            this.pluginUtility.loadPlugin(loadedPluginFile);
        }
    }
}