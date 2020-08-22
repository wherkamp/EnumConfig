package me.kingtux.enumconfig;

import me.kingtux.enumconfig.annotations.ConfigEntry;
import me.kingtux.enumconfig.annotations.ConfigValue;
import me.kingtux.simpleannotation.FieldFinder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class EnumConfigLoader {
    public static String DEFAULT_DIVIDER = ".";


    public static void loadLang(ValueHandler file, Class<? extends Enum> enu, boolean writeUnsetValues) {
        validateClass(enu);
        Field[] fields = FieldFinder.getAllFieldsWithAnnotation(enu, ConfigEntry.class, true);
        //There should only be one of them.
        Field editableThing = FieldFinder.getFirstFieldWithAnnotation(enu, ConfigValue.class, true);
        editableThing.setAccessible(true);

        for (Field field : fields) {
            field.setAccessible(true);
            ConfigEntry configEntry = field.getAnnotationsByType(ConfigEntry.class)[0];
            //Basically if it is not found in the needed language it will revert back to the default in English
            if (file.get(parseEntryPath(configEntry, field, file)) != null) {
                ((EnumConfig) Enum.valueOf(enu, field.getName())).setValue(file.get(parseEntryPath(configEntry, field, file)));
            }

        }
        if (writeUnsetValues) {
            for (Field field : fields) {
                ConfigEntry configEntry = field.getAnnotation(ConfigEntry.class);
                if (file.get(parseEntryPath(configEntry, field, file)) == null) {
                    file.set(parseEntryPath(configEntry, field, file), ((EnumConfig) Enum.valueOf(enu, field.getName())).getValue());
                }
            }
            try {
                file.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeValue(Enum e, ValueHandler valueHandler) {
        validateClass(e.getDeclaringClass());
        EnumConfig enumConfig = (EnumConfig) e;
        valueHandler.set(enumConfig.getPath(valueHandler.getDivider()), enumConfig.getValue());

    }

    public static final String parseEntryPath(ConfigEntry configEntry, Field field, ValueHandler handler) {
        if (configEntry.value().isEmpty()) {
            return field.getName().replace("_", handler.getDivider()).toLowerCase();
        }
        return configEntry.value();
    }

    private static void validateClass(Class<?> clazz) {
        if (!Arrays.stream(clazz.getInterfaces()).allMatch(aClass -> aClass == EnumConfig.class)) {
            throw new IllegalArgumentException("Enum must implement EConfig");
        }
    }

    public static void loadLang(ValueHandler file, Class<? extends Enum> enu) {
        loadLang(file, enu, true);
    }
}
