package com.epam.training.entity.type;

public enum TypeOfDevice {

    STORAGE_DEVICE("storage-device"),
    INPUT_DEVICE("input-device");

    private final String value;
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        result = result.replace(UNDERSCORE, HYPHEN);
        return result;
    }

    TypeOfDevice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeOfDevice valueOfXmlTag(String tag) {
        tag = tag.toUpperCase().replace(HYPHEN, UNDERSCORE);
        return TypeOfDevice.valueOf(tag);
    }
}
