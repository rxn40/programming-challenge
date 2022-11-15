package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


    /*
     * Test für die Klasse ReadFile.java
     */
    @Test
    @DisplayName("ReadFile.java - readCsvFile")
    void readCsvFile(){
        //Datei kann gefunden werden und werden richtig aufbereiet
        ReadFile reader = new ReadFile();
        List<String>  res1 = new ArrayList<>();
        res1.add("Day,MxT,MnT");
        res1.add("1,88,59");
        Assert.assertEquals(res1, reader.readCsvFile("test.csv")); 

        //name ohne .csv angegeben
        Assert.assertEquals(res1, reader.readCsvFile("test")); 

         /*
          * Fehler
          */
        //Datei kann nich gefunden werden
        assertThrows(Exception.class, () -> {reader.readCsvFile("dontExist.csv");}, "file does not exist");
    }


    /*
     * Test für die Klasse FileInformation.java
     */
    @Test
    @DisplayName("FileInformation.java - addEntries")
    void testAddEntries(){
        FileInformation fileInfo = new FileInformation();

        fileInfo.addEntries("A", "0.0"); // erster Eintrag "A"
        fileInfo.addEntries("B", "1.0"); // zweiter Eintrag "B"
        fileInfo.addEntries("A", "1.1"); // neuer Eintrag für "A"

         /*
          * Fehler
          */
        //sollten alle IllegalArgumentException werfen - key fehlerhaft
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntries("", "1.1");},"key is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntries("  ", "1.1");},"key is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntries(null, "1.1");},"key is empty or null");

        //sollten alle IllegalArgumentException werfen - entry fehlerhaft
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntries("A", "");},"entry is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntries("A", "  ");},"entry is empty or null");
        assertThrows(IllegalArgumentException.class, () -> {fileInfo.addEntries("A", null);},"entry is empty or null");
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
        fileInfo.addEntries("A", "0.0"); 
        fileInfo.addEntries("A", "1.1"); 
        fileInfo.addEntries("B", "1.0"); 
        fileInfo.addEntries("B", "1.0"); 
        assertEquals("0.1", fileInfo.getMinDelta("A", "B"));

        //A hat mehr Einträge
        fileInfo.addEntries("A", "1.0"); 
        assertEquals("0.1", fileInfo.getMinDelta("A", "B"));

        //B hat mehr Einträge
        fileInfo.addEntries("B", "1.0"); 
        fileInfo.addEntries("B", "2.0"); 
        assertEquals("0.0", fileInfo.getMinDelta("A", "B"));

         /*
          * Fehler
          */
        assertThrows(IllegalArgumentException.class, () -> {  fileInfo.getMinDelta("C", "B");},"key1 does not exist");
        assertThrows(IllegalArgumentException.class, () -> {  fileInfo.getMinDelta("A", "C");},"key2 does not exist");
    }


   
}