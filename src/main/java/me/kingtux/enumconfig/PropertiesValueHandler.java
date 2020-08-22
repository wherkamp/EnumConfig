package me.kingtux.enumconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesValueHandler implements ValueHandler {
    private File file;
    private Properties properties;

    public PropertiesValueHandler(File file) {
        this.file = file;
        properties = new Properties();
        try {
            if (!file.exists()) file.createNewFile();

            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(String path) {
        return properties.getProperty(path);
    }

    @Override
    public void save() throws IOException {
        FileOutputStream fr = new FileOutputStream(file);
        properties.store(fr, null);
        fr.close();
    }

    @Override
    public void set(String path, String o) {
        properties.setProperty(path, o);
    }

    @Override
    public String getDivider() {
        return ".";
    }
}
