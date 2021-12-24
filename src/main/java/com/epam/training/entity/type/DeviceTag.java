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
    IS_PERIPHERAL("isPeripheral"),
    MEMORY_SIZE("memorySize"),
    NUMBER_OF_BUTTONS("numberOfButtons");

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

