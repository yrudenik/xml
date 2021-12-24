package com.epam.training.entity;

import java.util.Objects;

public class InputDevice extends Device {

    private int numberOfButtons;

    public InputDevice(String id, String name, double price, Origin origin, DeviceType deviceType, int numberOfButtons) {
        super(id, name, price, origin, deviceType);
        this.numberOfButtons = numberOfButtons;
    }

    public InputDevice() {

    }

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(int numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
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
        InputDevice inputDevice = (InputDevice) o;
        return Double.compare(inputDevice.numberOfButtons, numberOfButtons) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfButtons);
    }

    @Override
    public String toString() {
        return "StorageDevice{" + super.toString() +
                "numberOfButtons=" + numberOfButtons +
                '}';
    }

}
