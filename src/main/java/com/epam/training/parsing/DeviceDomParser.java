package com.epam.training.parsing;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.*;
import com.epam.training.entity.type.DeviceTag;
import com.epam.training.entity.type.TypeOfDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDomParser implements DeviceParser {

    private static final Logger LOGGER = LogManager.getLogger();
    List<Device> deviceList;

    @Override
    public List<Device> parse(String xmlFilePath) throws CustomParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        deviceList = new ArrayList<>();

        Document document;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFilePath);
            Element root = document.getDocumentElement();
            createSpecifiedDevices(root, TypeOfDevice.STORAGE_DEVICE);
            createSpecifiedDevices(root, TypeOfDevice.INPUT_DEVICE);
            LOGGER.info("XML has been parsed successfully!");
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOGGER.warn(String.format("File %s can't ber read or parsed", xmlFilePath), e);
        }
        return deviceList;
    }

    private void createSpecifiedDevices(Element root, TypeOfDevice typeOfDevice) throws CustomParserException {
        NodeList deviceNodeList = root.getElementsByTagName(typeOfDevice.toString());
        for (int i = 0; i < deviceNodeList.getLength(); i++) {
            Element deviceElement = (Element) deviceNodeList.item(i);
            Device device = buildDevice(deviceElement, typeOfDevice);
            deviceList.add(device);
        }
    }

    private Device buildDevice(Element deviceElement, TypeOfDevice typeOfDevice) throws CustomParserException {
        Device device;
        switch (typeOfDevice) {
            case STORAGE_DEVICE:
                device = new StorageDevice();
                ((StorageDevice) device).setMemorySize(Integer.parseInt(getElementTextContent(deviceElement, DeviceTag.MEMORY_SIZE.toString())));
                break;
            case INPUT_DEVICE:
                device = new InputDevice();
                ((InputDevice) device).setNumberOfButtons(Integer.parseInt(getElementTextContent(deviceElement, DeviceTag.NUMBER_OF_BUTTONS.toString())));
                break;
            default:
                throw new CustomParserException("Invalid gem type!");
        }
        device.setId(deviceElement.getAttribute(DeviceTag.ID.toString()));
        String name = getElementTextContent(deviceElement, DeviceTag.NAME.toString());
        device.setName(name);
        double price = Double.parseDouble(getElementTextContent(deviceElement, DeviceTag.PRICE.toString()));
        device.setPrice(price);
        Origin origin = Origin.valueOf(getElementTextContent(deviceElement, DeviceTag.ORIGIN.toString()));
        device.setOrigin(origin);
        String deviceTypeTagName = DeviceTag.DEVICE_TYPE.toString();
        Element deviceType = (Element) deviceElement.getElementsByTagName(deviceTypeTagName).item(0);
        String deviceFunction = getElementTextContent(deviceType, DeviceTag.DEVICE_FUNCTION.toString());
        device.getDeviceType().setDeviceFunction(deviceFunction);
        boolean isPeripheral = Boolean.parseBoolean(getElementTextContent(deviceType, DeviceTag.IS_PERIPHERAL.toString()));
        device.getDeviceType().setPeripheral(isPeripheral);
        return device;
    }

    private static String getElementTextContent(Element deviceElement, String tagName) {
        NodeList nodeList = deviceElement.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private List<Device> getDeviceList() {
        return deviceList;
    }
}






/*    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Device> parse(String xmlFilePath) throws CustomParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Device> deviceList = new ArrayList<>();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFilePath);
            Element root = document.getDocumentElement();

            NodeList storageDevices = root.getElementsByTagName("storage-device");
            for (int i = 0; i < storageDevices.getLength(); i++) {
                Element storageDeviceElement = (Element) storageDevices.item(i);
                StorageDevice storageDevice = new StorageDevice();
                storageDevice.setName(storageDeviceElement.getElementsByTagName("name").item(0).getTextContent());
                storageDevice.setPrice(Double.parseDouble(storageDeviceElement.getElementsByTagName("price").item(0).getTextContent()));
                storageDevice.setOrigin(Origin.valueOf(storageDeviceElement.getElementsByTagName("origin").item(0).getTextContent()));
                storageDevice.setMemorySize(Integer.parseInt(storageDeviceElement.getElementsByTagName("memorySize").item(0).getTextContent()));
                DeviceType deviceType = new DeviceType();
                NodeList deviceTypes = root.getElementsByTagName("device-type");
                for (int j = 0; j < deviceTypes.getLength(); j++) {
                    Element deviceTypeElement = (Element) deviceTypes.item(j);
                    deviceType.setDeviceFunction(deviceTypeElement.getElementsByTagName("device-function").item(0).getTextContent());
                    deviceType.setPeripheral(Boolean.parseBoolean(deviceTypeElement.getElementsByTagName("isPeripheral").item(0).getTextContent()));
                }
                deviceList.add(storageDevice);
            }

            NodeList inputDevices = root.getElementsByTagName("input-device");
            for (int i = 0; i < inputDevices.getLength(); i++) {
                Element inputDeviceElement = (Element) inputDevices.item(i);
                InputDevice inputDevice = new InputDevice();
                inputDevice.setName(inputDeviceElement.getElementsByTagName("name").item(0).getTextContent());
                inputDevice.setPrice(Double.parseDouble(inputDeviceElement.getElementsByTagName("price").item(0).getTextContent()));
                inputDevice.setOrigin(Origin.valueOf(inputDeviceElement.getElementsByTagName("origin").item(0).getTextContent()));
                inputDevice.setNumberOfButtons(Integer.parseInt(inputDeviceElement.getElementsByTagName("numberOfButtons").item(0).getTextContent()));
                DeviceType deviceType = new DeviceType();
                NodeList deviceTypes = root.getElementsByTagName("device-type");
                for (int j = 0; j < deviceTypes.getLength(); j++) {
                    Element deviceTypeElement = (Element) deviceTypes.item(j);
                    deviceType.setDeviceFunction(deviceTypeElement.getElementsByTagName("device-function").item(0).getTextContent());
                    deviceType.setPeripheral(Boolean.parseBoolean(deviceTypeElement.getElementsByTagName("isPeripheral").item(0).getTextContent()));
                }
                deviceList.add(inputDevice);
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new CustomParserException("Error in DOM Parser: ", e);
        }
        return deviceList;
    }
}*/
