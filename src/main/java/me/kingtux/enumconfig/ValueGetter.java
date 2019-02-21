package me.kingtux.enumconfig;

import java.io.IOException;

public interface ValueGetter {

    String get(String path);

    void save() throws IOException;

    void set(String path, String o);
}

