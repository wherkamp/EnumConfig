package me.kingtux.enumconfig;

import me.kingtux.enumconfig.annotations.ConfigEntry;
import me.kingtux.simpleannotation.FieldFinder;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface EnumConfig {
    String getValue();

    void setValue(String value);

    String name();

    public default Field getConfigValue() {
        return FieldFinder.getFirstFieldWithAnnotation(getClass(), ConfigEntry.class, true);
    }

    public default String getPath(String divider) {
        Field field = getField();
        ConfigEntry entry = getField().getAnnotation(ConfigEntry.class);
        if (entry.value().isEmpty()) {
            return field.getName().replace("_", divider).toLowerCase();
        }
        return entry.value();
    }

    default Field getField() {
        return Arrays.stream(FieldFinder.getAllFieldsWithAnnotation(getClass(), ConfigEntry.class, true)).filter(field -> field.getName().equals(name())).findFirst().orElseThrow(() -> new RuntimeException("OH NO"));
    }

}
