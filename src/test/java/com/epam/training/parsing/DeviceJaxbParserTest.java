package com.epam.training.parsing;

import com.epam.training.data.CustomParserException;
import com.epam.training.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DeviceJaxbParserTest {

    private static final String FILE_PATH = "src/main/resources/devices.xml";

    @Test
    public void testJaxbParseShouldParseXmlFileWhenFileIsValid() throws CustomParserException {
        //given
        DeviceType firstType = new DeviceType(DeviceFunction.INFORMATION_STORAGE, false);
        DeviceType secondType = new DeviceType(DeviceFunction.INFORMATION_STORAGE, true);
        DeviceType thirdType = new DeviceType(DeviceFunction.INPUT_OUTPUT, true);
        DeviceType fourthType = new DeviceType(DeviceFunction.INPUT_OUTPUT, true);
        List<Device> deviceList = Arrays.asList(
                new StorageDevice("id111", "hardDisk", 99.9, Origin.USA, firstType, 500),
                new StorageDevice("id222", "flashUSB", 15.5, Origin.CHINA, secondType, 64),
                new InputDevice("id333", "keyboard", 53.0, Origin.GERMANY, thirdType, 104),
                new InputDevice("id444", "mouse", 25.6, Origin.CHINA, fourthType, 3));
        DeviceJaxbParser deviceJaxbParser = new DeviceJaxbParser();
        //when
        List<Device> resultList = deviceJaxbParser.parse(FILE_PATH);
        //then
        Assert.assertEquals(deviceList, resultList);
    }
}
