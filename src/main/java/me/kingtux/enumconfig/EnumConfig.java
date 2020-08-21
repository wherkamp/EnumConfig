package me.kingtux.enumconfig;

import me.kingtux.enumconfig.annotations.ConfigEntry;
import me.kingtux.enumconfig.annotations.ConfigValue;
import me.kingtux.simpleannotation.FieldFinder;

import java.io.IOException;
import java.lang.reflect.Field;

public class EnumConfig {
    public static void loadLang(ValueHandler file, Class<? extends Enum> enu, boolean writeUnsetValues) {
        Field[] fields = FieldFinder.getAllFieldsWithAnnotation(enu, ConfigEntry.class, true);
        //There should only be one of them.
        Field editableThing = FieldFinder.getFirstFieldWithAnnotation(enu, ConfigValue.class, true);
        editableThing.setAccessible(true);

        for (Field field : fields) {
            field.setAccessible(true);
            ConfigEntry configEntry = field.getAnnotationsByType(ConfigEntry.class)[0];
            //Basically if it is not found in the needed language it will revert back to the default in English
            if (file.get(configEntry.path()) != null) {
                try {
                    editableThing.set(Enum.valueOf(enu, field.getName()), file.get(configEntry.path()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        if (writeUnsetValues) {
            for (Field field : fields) {
                ConfigEntry configEntry = field.getAnnotation(ConfigEntry.class);
                if (file.get(configEntry.path()) == null ) {
                    try {
                        file.set(configEntry.path(), (String) editableThing.get(Enum.valueOf(enu, field.getName())));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                file.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadLang(ValueHandler file, Class<? extends Enum> enu) {
        loadLang(file, enu, true);
    }
}
