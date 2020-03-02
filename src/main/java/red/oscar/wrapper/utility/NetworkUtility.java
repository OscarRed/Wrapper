package red.oscar.wrapper.utility;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

public class NetworkUtility {

    /**
     * Size of the input buffer.
     */
    private final int BUFFER_SIZE = 3000000; // 3MB

    /**
     * Downloads the plugin from a specified URL and loads into temporary memory.
     * @param targetURL The target URL.
     * @param pluginKey The key for the plugin.
     * @return Location of temporary JAR file.
     */
    public File downloadPlugin(String targetURL, String pluginKey) {

        try {
            URL url = new URL(targetURL + "?key=" + pluginKey);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = httpURLConnection.getInputStream();

                File temporaryFile = File.createTempFile("plugin", ".jar");
                try (FileOutputStream fileOutputStream = new FileOutputStream(temporaryFile)) {

                    int bytesRead = -1;
                    byte[] fileBuffer = new byte[BUFFER_SIZE];

                    while ((bytesRead = inputStream.read()) != -1) {
                        fileOutputStream.write(fileBuffer, 0, bytesRead);
                    }

                    fileOutputStream.close();
                    inputStream.close();
                }

                Bukkit.getLogger().log(Level.INFO, "Successfully downloaded plugin!");
                return temporaryFile;
            }

            if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                Bukkit.getLogger().log(Level.SEVERE, "Unable to download plugin, IP address does not match with assigned IP address.");
            }

            if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                Bukkit.getLogger().log(Level.SEVERE, "Unable to download plugin, invalid key!");
            }
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to connect to server!");
            e.printStackTrace();
        }
        return null;
    }
}
