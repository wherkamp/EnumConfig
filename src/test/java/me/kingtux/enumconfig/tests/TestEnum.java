package me.kingtux.enumconfig.tests;

import me.kingtux.enumconfig.annotations.ConfigEntry;
import me.kingtux.enumconfig.annotations.ConfigValue;

public enum TestEnum {
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
}
