package com.epam.training.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StorageDevice", propOrder = {"memorySize"})
public class StorageDevice extends Device {

    @XmlElement(name = "memory-size", namespace = "http://www.training.epam.com/devices", required = true)
    private int memorySize;

    public StorageDevice(String id, String name, double price, Origin origin, DeviceType deviceType, int memorySize) {
        super(id, name, price, origin, deviceType);
        this.memorySize = memorySize;
    }

    public StorageDevice() {
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getMemorySize() {
        return memorySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        StorageDevice storageDevice = (StorageDevice) o;
        return Double.compare(storageDevice.memorySize, memorySize) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), memorySize);
    }

    @Override
    public String toString() {
        return "StorageDevice{" + super.toString() +
                "memorySize=" + memorySize +
                '}';
    }
}
