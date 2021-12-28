package com.epam.training.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "devices", namespace = "http://www.training.epam.com/devices")
public class Devices {

    @XmlElements({
            @XmlElement(name = "storage-device", namespace = "http://www.training.epam.com/devices", type = StorageDevice.class),
            @XmlElement(name = "input-device", namespace = "http://www.training.epam.com/devices", type = InputDevice.class)
    })

    private List<Device> deviceList = new ArrayList<>();

    public Devices() {
    }

    public void setDevices(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public boolean add(Device device) {
        return deviceList.add(device);
    }

    public List<Device> getDevices() {
        return deviceList;
    }
}
