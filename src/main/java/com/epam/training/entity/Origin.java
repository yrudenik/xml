package com.epam.training.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "origin")
@XmlEnum
public enum Origin {

    @XmlEnumValue("CHINA")
    CHINA,
    @XmlEnumValue("USA")
    USA,
    @XmlEnumValue("GERMANY")
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


