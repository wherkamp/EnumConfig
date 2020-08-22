package me.kingtux.enumconfig;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BukkitYamlHandler implements ValueHandler {
    private YamlConfiguration configuration;
    private File file;

    public BukkitYamlHandler(File file) {
        this.file = file;
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public String get(String path) {
        return configuration.getString(path);
    }

    @Override
    public void save() throws IOException {
        configuration.save(file);
    }

    @Override
    public void set(String path, String o) {
        configuration.set(path, o);
    }

    @Override
    public String getDivider() {
        return "-";
    }

}
