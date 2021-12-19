package com.epam.training.entity;

public class DeviceType {

    private String function;
    boolean isPeripheral;

    public DeviceType(String function, boolean isPeripheral) {
        this.function = function;
        this.isPeripheral = isPeripheral;
    }

    public String getFunction() {
        return function;
    }

    public boolean isPeripheral() {
        return isPeripheral;
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
        return function.equals(deviceType.function)
                && isPeripheral == deviceType.isPeripheral;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + function.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("\nFunction = %s\nIsPeripheral = %s\n",
                function, isPeripheral);
    }

}
