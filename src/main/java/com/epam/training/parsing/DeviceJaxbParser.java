package com.epam.training.parsing;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.Device;
import com.epam.training.entity.Devices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DeviceJaxbParser implements DeviceParser{

    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public List<Device> parse(String xmlFilePath) throws CustomParserException {
        List<Device> deviceList;

        try {
            JAXBContext context = JAXBContext.newInstance(Devices.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader reader = new FileReader(xmlFilePath);
            Devices devices = (Devices) unmarshaller.unmarshal(reader);
            deviceList = devices.getDevices();
            LOGGER.info("Devices read and added to list");
        } catch (JAXBException | FileNotFoundException e) {
            throw new CustomParserException("Jaxb parsing exception: ", e);
        }
        return deviceList;
    }
}
