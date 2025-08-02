package org.api.stockmarket.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ConfigurationException{
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationException.class);

    protected ConfigurationException(){}

    public static void failAndExit(String message, String source){
        logger.error("From {}: {}", source, message);
        System.exit(1);
    }

    public static <T> T failAndExitAs(String message, String source){
        failAndExit(message, source);
        return null;
    }
}
