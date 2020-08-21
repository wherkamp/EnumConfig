package me.kingtux.enumconfig.tests;

import me.kingtux.enumconfig.EnumConfig;
import me.kingtux.enumconfig.PropertiesValueHandler;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        EnumConfig.loadLang(new PropertiesValueHandler(new File("test.properties")), TestEnum.class, true);
        System.out.println(TestEnum.HELLO.getValue());
    }
}
