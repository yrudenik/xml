package com.epam.training.entity;

import org.w3c.dom.Node;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceType", propOrder = {"device-function", "isPeripheral"})
public class DeviceType {
    @XmlElement(name = "device-function", namespace = "http://www.training.epam.com/devices", required = true)
    private String deviceFunction;
    @XmlElement(name = "isPeripheral", namespace = "http://www.training.epam.com/devices", required = true)
    boolean isPeripheral;

    public DeviceType(String deviceFunction, boolean isPeripheral) {
        this.deviceFunction = deviceFunction;
        this.isPeripheral = isPeripheral;
    }

    public DeviceType() {
    }

    public String getDeviceFunction() {
        return deviceFunction;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setDeviceFunction(String deviceFunction) {
        this.deviceFunction = deviceFunction;
    }

    public void setPeripheral(boolean peripheral) {
        isPeripheral = peripheral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceType deviceType = (DeviceType) o;
        return deviceFunction.equals(deviceType.deviceFunction)
                && isPeripheral == deviceType.isPeripheral;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + deviceFunction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("\nDeviceFunction = %s\nIsPeripheral = %s\n",
                deviceFunction, isPeripheral);
    }


}
