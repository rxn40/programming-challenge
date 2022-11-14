package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

    @Test
    @DisplayName("Files lesen")
    void readCsvFileAll(){
        //Datei kann gefunden werden
        List<String>  res1 = new ArrayList<>();
        res1.add("Day,MxT,MnT");
        res1.add("1,88,59");
        Assert.assertEquals(res1, ReadFile.readCsvFileAll("test.csv")); 

        //Datei kann nich gefunden werden
        List<String>  res2 = new ArrayList<>();
        Assert.assertEquals(res2, ReadFile.readCsvFileAll("dontExist.csv"));
    }
}