package com.epam.training.parsing;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class DeviceSaxParser implements DeviceParser {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Device> parse(String xmlFilePath) throws CustomParserException {
        DeviceHandler handler = new DeviceHandler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            reader.setContentHandler(handler);
            reader.parse(xmlFilePath);
            LOGGER.info("xmlFile is parsed");

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new CustomParserException("SAX parsing exception: ", e);
        }
        return handler.getDevices();
    }
}
