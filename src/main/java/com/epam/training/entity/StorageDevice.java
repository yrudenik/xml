package com.epam.training.entity;

import java.util.Objects;

public class StorageDevice extends Device {

    private int memorySize;

    public StorageDevice(int id, String name, double price, String origin, DeviceType deviceType, int memorySize) {
        super(id, name, price, origin, deviceType);
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
