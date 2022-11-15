package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;

import java.io.File;
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


    /*
     * Test für die Klasse ReadFile.java
     */
    @Test
    @DisplayName("ReadFile.java - readCsvFile")
    void readCsvFile(){
        //Datei kann gefunden werden und werden richtig aufbereiet
        ReadFile reader = new ReadFile();
        FileInformation fileInfo = new FileInformation();
        fileInfo.addEntry("Day", "1");
        fileInfo.addEntry("MxT", "88");
        fileInfo.addEntry("MnT", "59");

        Assert.assertEquals(reader.readCsvFile("test.csv").map.get("Day"), fileInfo.map.get("Day"));
        Assert.assertEquals(reader.readCsvFile("test.csv").map.get("MxT"), fileInfo.map.get("MxT"));
        Assert.assertEquals(reader.readCsvFile("test.csv").map.get("MnT"), fileInfo.map.get("MnT"));

        //name ohne .csv angegeben
        Assert.assertEquals(reader.readCsvFile("test").map.get("Day"), fileInfo.map.get("Day"));
        Assert.assertEquals(reader.readCsvFile("test").map.get("MxT"), fileInfo.map.get("MxT"));
        Assert.assertEquals(reader.readCsvFile("test").map.get("MnT"), fileInfo.map.get("MnT"));

         /*
          * Fehler
          */
        //Datei kann nich gefunden werden
        assertThrows(IllegalArgumentException.class, () -> {reader.readCsvFile("dontExist.csv");}, "Something went wrong. File dontExist.csv can't be found!");
    }


    /*
     * Test für die Klasse FileInformation.java
     */
    @Test
    @DisplayName("FileInformation.java - addEntry")
    void testAddEntry(){
        FileInformation fileInfo = new FileInformation();

        fileInfo.addEntry("A", "0.0"); // erster Eintrag "A"
        fileInfo.addEntry("B", "1.0"); // zweiter Eintrag "B"
        fileInfo.addEntry("A", "1.1"); // neuer Eintrag für "A"

         /*
          * Fehler
          */
        //sollten alle IllegalArgumentException werfen - key fehlerhaft
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntry("", "1.1");},"key is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntry("  ", "1.1");},"key is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntry(null, "1.1");},"key is empty or null");

        //sollten alle IllegalArgumentException werfen - entry fehlerhaft
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntry("A", "");},"entry is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntry("A", "  ");},"entry is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntry("A", null);},"entry is empty or null");
    }



    @Test
    @DisplayName("FileInformation.java - stringToDouble")
    void testStringToDouble(){
        FileInformation fileInfo = new FileInformation();
        //Test die Erfolgreich sein sollten
        //0 
        assertEquals(0.0, fileInfo.stringToDouble("0.0"));
        assertEquals(0.0, fileInfo.stringToDouble("0"));
        //1
        assertEquals(1.0, fileInfo.stringToDouble("1.0"));
        assertEquals(1.0, fileInfo.stringToDouble("1"));
         //-1
         assertEquals(-1.0, fileInfo.stringToDouble("-1.0"));
         assertEquals(-1.0, fileInfo.stringToDouble("-1"));

         /*
          * Fehler
          */
         //value ist null oder empty
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble("");},"value as string is empty or null");
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble("  ");},"value as string is empty or null");
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble(null);},"value as string is empty or null");

         //value kann nicht konvertiert werden 
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble(".");},"value cant be convertert");
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble("1.");},"value cant be convertert");
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble(".1");},"value cant be convertert");
         assertThrows(IllegalArgumentException.class, () -> { fileInfo.stringToDouble("Test");},"value cant be convertert");

         //value ist out of range
         assertThrows(Exception.class, () -> { fileInfo.stringToDouble(" -1,7 * 10^308");},"value is out of range");
         assertThrows(Exception.class, () -> { fileInfo.stringToDouble("1,7 * 10^308");},"value is out of range");
    }

    @Test
    @DisplayName("FileInformation.java - getMinDelta")
    void testGetMinDelta(){
        FileInformation fileInfo = new FileInformation();

        //Funktionalität
        fileInfo.addEntry("A", "0.0"); 
        fileInfo.addEntry("A", "1.1"); 
        fileInfo.addEntry("B", "1.0"); 
        fileInfo.addEntry("B", "1.0"); 
        assertEquals(1, fileInfo.getMinDelta("A", "B"));

        //A hat mehr Einträge
        fileInfo.addEntry("A", "1.0"); 
        assertEquals(1, fileInfo.getMinDelta("A", "B"));

        //B hat mehr Einträge
        fileInfo.addEntry("B", "1.0"); 
        fileInfo.addEntry("B", "2.0"); 
        assertEquals(2, fileInfo.getMinDelta("A", "B"));

         /*
          * Fehler
          */
        assertThrows(IllegalArgumentException.class, () -> {  fileInfo.getMinDelta("C", "B");},"key1 does not exist");
        assertThrows(IllegalArgumentException.class, () -> {  fileInfo.getMinDelta("A", "C");},"key2 does not exist");
    }


   
}