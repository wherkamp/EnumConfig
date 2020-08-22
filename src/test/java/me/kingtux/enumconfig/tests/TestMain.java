package me.kingtux.enumconfig.tests;

import me.kingtux.enumconfig.EnumConfigLoader;
import me.kingtux.enumconfig.PropertiesValueHandler;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {


    @Test
    public void generalTest() {
        PropertiesValueHandler propertiesValueHandler = new PropertiesValueHandler(new File("test.properties"));
        EnumConfigLoader.loadLang(propertiesValueHandler, TestEnum.class, true);
        TestEnum.HELLO.setValue("TEST_1");
        EnumConfigLoader.writeValue(TestEnum.HELLO, propertiesValueHandler);
        EnumConfigLoader.loadLang(propertiesValueHandler, TestEnum.class, true);
        assertEquals(TestEnum.HELLO.getValue(), "TEST_1");

    }
}
