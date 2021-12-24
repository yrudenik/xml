package com.epam.training.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Device", propOrder = {"id", "name", "price", "origin", "deviceType"})
@XmlSeeAlso({StorageDevice.class, InputDevice.class})
public abstract class Device {

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String id;
    @XmlElement(name = "name", namespace = "http://www.training.epam.com/devices", required = true)
    private String name;
    @XmlElement(name = "price", namespace = "http://www.training.epam.com/devices", required = true)
    private double price;
    @XmlElement(name = "origin", namespace = "http://www.training.epam.com/devices", required = true)
    private Origin origin;
    @XmlElement(name = "DeviceType", namespace = "http://www.training.epam.com/devices", required = true)
    private DeviceType deviceType = new DeviceType();

    public Device(String id, String name, double price, Origin origin, DeviceType deviceType){
        this.id = id;
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.deviceType = deviceType;
    }

    public Device() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Origin getOrigin() {
        return origin;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Double.compare(price, device.price) == 0
                && id.equals(device.id)
                && name.equals(device.name)
                && origin.equals(device.origin)
                && deviceType.equals(device.deviceType);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + id.hashCode();
        long bits = Double.doubleToLongBits(price);
        result = prime * result + (int) (bits ^ (bits >>> 32));
        result = prime * result + name.hashCode();
        result = prime * result + deviceType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("\nID = %s\nName = %s\nPrice = %s\nOrigin = %s\nDeviceType = %s\n",
                id, name, price, origin, deviceType);
    }

}
