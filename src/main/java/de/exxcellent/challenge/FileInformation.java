package de.exxcellent.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Klasse, die es ermöglicht Daten aus einem File abzuspeichern und Funktionalitäten bereit stellen
 * 
 * @author Groner Yvette
 */
public class FileInformation {

//Header aus File als Key, List<String> spalte
   Map<String, List<String>> map; 

   /*
    * Konstruktor
    */
    public FileInformation(){
       map = new HashMap<>();
    }


      /*
     * Fügt entrie in List<String> ein, wenn key existiert
     * Sonst wird ein neuer Eintrag in die map erstellt
     * ERROR:
     *      Wenn key leer/null ist - IlligelArgument
     *      Wenn entrie leer/null ist? - IlligelArgument
     */
    public void addEntries(String key, String entry){
    }


    /*
     * Bestimmt das minimum Delta aus der Spalte mit Header key1 und key2
     * Ruft die Methode stringToDouble auf
     * ERROR: Wenn key1 oder key2 nicht existieren 
     */
    public String getMinDelta(String key1, String key2){
        return null;
    }

    /*
     * Konvertiert einen Eintrag vom Typ String zu double
     * ERROR: Wenn entry leer/null ist?
     *        Wenn entry nicht konvertierbar ist
     */
    public double stringToDouble(String value){
        return 0.0;
    }

}
