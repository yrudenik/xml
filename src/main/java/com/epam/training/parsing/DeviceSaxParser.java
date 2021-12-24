package com.epam.training.parsing;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class DeviceSaxParser implements DeviceParser{

    private static final Logger LOGGER = LogManager.getLogger();
    private final DeviceHandler deviceHandler = new DeviceHandler();

    public List<Device> parse(String xmlFilePath) throws CustomParserException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
            XMLReader reader = saxParser.getXMLReader();
            reader.setErrorHandler(new DeviceErrorHandler());
            reader.setContentHandler((ContentHandler) deviceHandler);
            reader.parse(xmlFilePath);
            LOGGER.info("xmlFile is parsed");

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new CustomParserException("SAX parsing exception: ", e);
        }
        return null;
    }
}
