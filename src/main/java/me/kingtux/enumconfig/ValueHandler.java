package me.kingtux.enumconfig;

import java.io.IOException;

public interface ValueHandler {

    String get(String path);

    void save() throws IOException;

    void set(String path, String o);
}

