package com.pathz.nettychat.exception;

import org.springframework.core.io.Resource;

import java.io.IOException;

public class UnableLoadFxmlException extends RuntimeException {
    public UnableLoadFxmlException(Resource chatPage, IOException cause) {
        super("Unable to load '" + chatPage.getFilename() + "'", cause);
    }
}
