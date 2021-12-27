package com.epam.training.logic;

import com.epam.training.data.CustomParserException;
import org.junit.Assert;
import org.junit.Test;

public class DeviceValidatorTest {

    private static final String XML = "src/main/resources/devices.xml";
    private static final String XSD = "src/main/resources/xmlSchema.xsd";
    private static final String XML_INVALID = "src/main/resources/deviceInvalid.xml";

    @Test
    public void testValidateShouldReturnTrueWhenFileIsValid() throws CustomParserException {
        //given
        DeviceValidator validator = new DeviceValidator();
        //when
        boolean result = validator.isValid(XML, XSD);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateShouldReturnFalseWhenFileInvalid() throws CustomParserException {
        //given
        DeviceValidator validator = new DeviceValidator();
        //when
        boolean result = validator.isValid(XML_INVALID, XSD);
        //then
        Assert.assertFalse(result);
    }

    @Test(expected = CustomParserException.class)
    public void testValidateShouldThrowExceptionWhenXmlFilePathIsEmpty() throws CustomParserException {
        //given
        DeviceValidator validator = new DeviceValidator();
        //when
        boolean result = validator.isValid("", XSD);
    }

    @Test(expected = CustomParserException.class)
    public void testValidateShouldThrowExceptionWhenXsdFilePathIsEmpty() throws CustomParserException {
        //given
        DeviceValidator validator = new DeviceValidator();
        //when
        boolean result = validator.isValid(XML, "");
    }
}

