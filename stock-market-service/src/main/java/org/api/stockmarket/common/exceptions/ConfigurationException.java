package org.api.stockmarket.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationException{
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationException.class);

    protected ConfigurationException(){}

    public static void exit(String message, String source){
        logger.error("From {}: {}", source, message);
        System.exit(1);
    }
}
