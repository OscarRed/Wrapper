package red.oscar.wrapper.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import red.oscar.library.configuration.Configuration;
import red.oscar.library.configuration.ConfigurationInterface;

@AllArgsConstructor
@Configuration(filePath = "plugins/Wrapper/key.yml")
public enum Config implements ConfigurationInterface {

    KEY("XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX");

    @Getter @Setter private Object configurationValue;

    /**
     * Returns the configuration object as a string.
     * @return Stored object cast to a string.
     */
    public String getString() {
        return (String) configurationValue;
    }
}