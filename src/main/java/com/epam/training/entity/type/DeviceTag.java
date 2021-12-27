package com.epam.training.entity.type;

public enum DeviceTag {

    DEVICES("devices"),
    STORAGE_DEVICE("storage-device"),
    INPUT_DEVICE("input-device"),

    ID("id"),
    NAME("name"),
    PRICE("price"),
    ORIGIN("origin"),
    DEVICE_TYPE("device-type"),
    DEVICE_FUNCTION("device-function"),
    ISPERIPHERAL("isPeripheral"),
    MEMORYSIZE("memorySize"),
    NUMBEROFBUTTONS("numberOfButtons");


    private final String value;
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    DeviceTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeviceTag valueOfXmlTag(String tag) {
        tag = tag.toUpperCase().replace(HYPHEN, UNDERSCORE);
        return DeviceTag.valueOf(tag);
    }
}

