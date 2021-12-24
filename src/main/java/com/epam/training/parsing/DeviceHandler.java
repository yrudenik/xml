package com.epam.training.parsing;

import com.epam.training.entity.Device;
import com.epam.training.entity.InputDevice;
import com.epam.training.entity.StorageDevice;
import com.epam.training.entity.type.DeviceTag;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DeviceHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private final List<Device> devices = new ArrayList<>();
    private Device currentDevice;
    private DeviceTag currentTag;
    private final EnumSet<DeviceTag> textTags = EnumSet.range(DeviceTag.NAME, DeviceTag.PRICE);

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String indexName, Attributes attributes) {
        String storageDeviceTag = DeviceTag.STORAGE_DEVICE.getValue();
        String inputDeviceTag = DeviceTag.INPUT_DEVICE.getValue();

        if (storageDeviceTag.equals(indexName) || inputDeviceTag.equals(indexName)) {
            currentDevice = storageDeviceTag.equals(indexName) ? new StorageDevice() : new InputDevice();
            defineAttributes(attributes);
        } else {
            DeviceTag current = DeviceTag.valueOfXmlTag(indexName);
            if (textTags.contains(current)) {
                currentTag = current;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String indexName) {
        String storageDeviceTag = DeviceTag.STORAGE_DEVICE.getValue();
        String inputDeviceTag = DeviceTag.INPUT_DEVICE.getValue();

        if (storageDeviceTag.equals(indexName) || inputDeviceTag.equals(indexName)) {
            devices.add(currentDevice);
            LOGGER.info("Device added to list");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        StorageDevice storageDevice;
        InputDevice inputDevice;


        if (currentTag != null) {
            switch (currentTag) {
                case ID:
                    currentDevice.setId(data);
                    break;
                case NAME:
                    currentDevice.setName(data);
                    break;
                case PRICE:
                    currentDevice.setPrice(Double.parseDouble(data));
                    break;
                case DEVICE_FUNCTION:
                    currentDevice.getDeviceType().setDeviceFunction(data);
                    break;
                case IS_PERIPHERAL:
                    currentDevice.getDeviceType().setPeripheral(Boolean.parseBoolean(data));
                    break;
                case MEMORY_SIZE:
                    storageDevice = (StorageDevice) currentDevice;
                    storageDevice.setMemorySize(Integer.parseInt(data));
                    currentDevice = storageDevice;
                    break;
                case NUMBER_OF_BUTTONS:
                    inputDevice = (InputDevice) currentDevice;
                    inputDevice.setNumberOfButtons(Integer.parseInt(data));
                    currentDevice = inputDevice;
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.name());
            }
            LOGGER.info("Information parsed from tag");
        }
        currentTag = null;
    }

    private void defineAttributes(Attributes attributes) {
        String deviceId = attributes.getValue(DeviceTag.ID.getValue());
        currentDevice.setId(deviceId);
    }
}
