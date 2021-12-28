package com.epam.training.parsing;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DeviceErrorHandler implements ErrorHandler {

    private boolean error = false;

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        error = true;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        error = true;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        error = true;
    }

    public boolean isErrorExists() {
        return error;
    }
}
