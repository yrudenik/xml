package com.epam.training.parsing;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.Device;

import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

public interface DeviceParser {
    List<Device> parse(String xmlFilePath) throws CustomParserException, ParserConfigurationException;
}
