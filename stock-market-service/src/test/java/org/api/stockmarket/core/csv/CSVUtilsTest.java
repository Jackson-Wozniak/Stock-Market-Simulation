package org.api.stockmarket.core.csv;

import org.junit.jupiter.api.Test;

import javax.naming.ConfigurationException;

import static org.junit.jupiter.api.Assertions.*;

class CSVUtilsTest {
    private static final String TestFilePath = "csv_utils_test.csv";

    @Test
    void readingAbsentFileThrowsError(){
        assertDoesNotThrow(() -> CSVUtils.fileContents(TestFilePath, "CSVUtilsTest"));
    }
}