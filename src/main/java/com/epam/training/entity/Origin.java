package com.epam.training.entity;

public enum Origin {

    CHINA,
    USA,
    GERMANY;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        result = result.replace(UNDERSCORE, HYPHEN);
        return result;
    }
}


