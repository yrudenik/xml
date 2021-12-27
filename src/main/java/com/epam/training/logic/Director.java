package com.epam.training.logic;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.Device;
import com.epam.training.parsing.DeviceParserFactory;
import com.epam.training.parsing.DeviceParser;
import com.epam.training.parsing.ParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class Director {

        private static final DeviceValidator validator = new DeviceValidator();
    private static final Logger LOGGER = LogManager.getLogger();

        public List<Device> createDevices(String xmlPath, String xsdPath, ParserType parserType) {
            List<Device> devices = new ArrayList<>();

            try {
                if (validator.isValid(xmlPath, xsdPath)) {
                    DeviceParser parser = DeviceParserFactory.create(parserType);

                    devices = parser.parse(xmlPath);
                }
            } catch (CustomParserException | ParserConfigurationException e) {
                LOGGER.error("Error in creating devices:", e);
            }

            return devices;
        }

    }
