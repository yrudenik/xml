package com.epam.training.parsing;

import javax.xml.validation.Schema;

public class DeviceParcerFactory {

    public static DeviceParser create(ParserType parserType){

        switch (parserType){
            case DOM:
                return new DeviceDomParser();
            case SAX:
                return new DeviceSaxParser();
            case JAXB:
                return new DeviceJaxbParser();
            default:
                throw new EnumConstantNotPresentException(parserType.getDeclaringClass(), parserType.name());
        }
    }
}
