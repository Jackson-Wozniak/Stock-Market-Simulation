package org.api.stockmarket.core.csv;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVUtilsTest {
    private static final String TestFilePath = "csv_utils_test.csv";

    @Test
    void readingAbsentFileThrowsError(){
        assertDoesNotThrow(() -> CSVUtils.fileContents(TestFilePath, "CSVUtilsTest"));

        assertDoesNotThrow(() -> CSVUtils.lineCount(TestFilePath, "CSVUtilsTest"));
    }

    @Test
    void lineCountIsCorrect(){
        assertEquals(2, CSVUtils.lineCount(TestFilePath, "CSVUtilsTest"));
    }

    @Test
    void contentsReadToArrayCorrectly(){
        List<String[]> contents = CSVUtils.fileContents(TestFilePath, "CSVUtilsTest");

        assertEquals("thislineshouldnotbeignored", String.join("", contents.get(0)));
        assertEquals("test1test2test3test4test5test6", String.join("", contents.get(1)));
    }

    @Test
    void toArrayMapsBack(){
        List<String[]> arrays = CSVUtils.toArray(List.of(
                "this,line,should,not,be,ignored", "test1,test2,test3,test4,test5,test6"
        ));
        String[] lineOne = {"this", "line", "should", "not", "be", "ignored"};
        String[] lineTwo = {"test1", "test2", "test3", "test4", "test5", "test6"};
        assertArrayEquals(lineOne, arrays.get(0));
        assertArrayEquals(lineTwo, arrays.get(1));
    }
}