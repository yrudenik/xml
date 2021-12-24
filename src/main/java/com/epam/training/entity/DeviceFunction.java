package com.epam.training.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "device-function")
@XmlEnum
public enum  DeviceFunction {

    @XmlEnumValue("INPUT_OUTPUT")
    INPUT_OUTPUT,
    @XmlEnumValue("INFORMATION_STORAGE")
    INFORMATION_STORAGE,
    @XmlEnumValue("AUDIO_VIDEO")
    AUDIO_VIDEO,
    @XmlEnumValue("NETWORK_HARDWARE")
    NETWORK_HARDWARE,
    @XmlEnumValue("OTHER_DEVICES")
    OTHER_DEVICES
}
