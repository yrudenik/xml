package com.epam.training.data;

public class CustomParserException extends Exception {

    public CustomParserException() {
        super();
    }

    public CustomParserException(String textOfException) {
    }

    public CustomParserException(String textOfException, Throwable typeOfException){
        super(textOfException, typeOfException);
    }

    public CustomParserException(Throwable cause) {
        super(cause);
    }




}
