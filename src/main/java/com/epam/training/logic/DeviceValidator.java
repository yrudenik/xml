package com.epam.training.logic;

import com.epam.training.data.CustomParserException;
import com.epam.training.parsing.DeviceErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class DeviceValidator {

    private static final Logger LOGGER = LogManager.getLogger();

    public boolean isValid(String xmlFilePath, String xsdFilePath) throws CustomParserException {
        if (xmlFilePath == null || xmlFilePath.isEmpty() || xsdFilePath == null || xsdFilePath.isEmpty()) {
            throw new CustomParserException("Path of xml or xsd files is not correct");
        }

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        boolean validResult = true;

        try {
            File xsdFile = new File(xsdFilePath);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Source source = new StreamSource(xmlFilePath);
            Validator validator = schema.newValidator();
            DeviceErrorHandler deviceErrorHandler = new DeviceErrorHandler();
            validator.setErrorHandler(deviceErrorHandler);
            validator.validate(source);

            LOGGER.info(xmlFilePath + " validation process finished");
            if (deviceErrorHandler.isErrorExists()) {
                validResult = false;
                LOGGER.warn(xmlFilePath + " is invalid");
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return validResult;
    }
}

