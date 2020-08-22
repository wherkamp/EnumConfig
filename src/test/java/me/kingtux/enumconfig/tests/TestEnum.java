package me.kingtux.enumconfig.tests;

import me.kingtux.enumconfig.EnumConfig;
import me.kingtux.enumconfig.annotations.ConfigEntry;
import me.kingtux.enumconfig.annotations.ConfigValue;

public enum TestEnum implements EnumConfig {
    @ConfigEntry
    HELLO("HEY MAN!"),
    @ConfigEntry( "bye")
    BYE("BYE MAN!");
    @ConfigValue
    private String value;

    TestEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
