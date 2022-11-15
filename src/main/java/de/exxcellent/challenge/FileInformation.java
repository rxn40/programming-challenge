package de.exxcellent.challenge;

import java.util.ArrayList;
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
    public void addEntry(String key, String entry){
        //key nicht valide
        if(key == null || key.trim().length() == 0){
            throw new IllegalArgumentException("key is empty or null");
        }

        //entry nicht valide
        if(entry == null || entry.trim().length() == 0){
            throw new IllegalArgumentException("entry is empty or null");
        }

        // entry mit key einfügen
        List<String> entries = map.get(key);
        if(entries == null){ // key gibt es noch nicht
            entries = new ArrayList<>();
        }
        entries.add(entry);
        map.put(key, entries); // Eintrag aktuallisieren/anlegen
    }


    /*
     * Bestimmt das minimum Delta aus der Spalte mit Header key1 und key2
     * Ruft die Methode stringToDouble auf
     * ERROR: Wenn key1 oder key2 nicht existieren 
     */
    public String getMinDelta(String key1, String key2, String identifier){
        List<String> entries1 = map.get(key1);
        List<String> entries2 = map.get(key2);

        //Fehlerbehandlung, falls key keine Einträge hinterlegt 
        if(entries1 == null){
            throw new IllegalArgumentException("key1 does not exist");
        }else if(entries2 == null){
            throw new IllegalArgumentException("key2 does not exist");
        }else if(map.get(identifier) == null){
            throw new IllegalArgumentException("identifier does not exist");
        }


        //minimale laenge ermitteln
        int size = getMinSize(entries1, entries2);

        //index bestimmen
        int index = getIndexMInDif(size, entries1, entries2);;

        return map.get(identifier).get(index);
    }



    /*
     * Methode, um minimale Differenz zu bestimmen
     */
    public int getIndexMInDif(int size, List<String> entries1, List<String> entries2){
        double res = Double.MAX_VALUE;
        int index = -1;

        for(int i = 0; i < size; i++){
            double dif = Math.abs(stringToDouble(entries1.get(i))-stringToDouble(entries2.get(i)));
            if(dif < res){
                res = dif;
                index = i;
            }
        }

        return index;
    }

    /*
     * Methode, welche die minimale Lange der Listen bestimmt
     */
    public int getMinSize(List<String> entries1, List<String> entries2){
        int size = entries1.size();
        if(size > entries2.size()){
            size = entries2.size();
        }
        return size;
    }

    /*
     * Konvertiert einen Eintrag vom Typ String zu double
     * ERROR: Wenn entry leer/null ist?
     *        Wenn entry nicht konvertierbar ist
     */
    public double stringToDouble(String value){
        if(value == null || value.trim().length() == 0){ //value = "", " " oder null
            throw new IllegalArgumentException("key is empty or null");
        }

        if(value.contains(".")){
            String[] valueSplit = value.trim().split("\\.");
            if (valueSplit.length != 2){
                throw new IllegalArgumentException("value cant be convertert"); // "X."
            }else if(valueSplit.length == 2 && valueSplit[0].isEmpty()){ // " .X"
                throw new IllegalArgumentException("value cant be convertert");
            }
        }
        
        try{
            return Double.parseDouble(value);
        }catch(NumberFormatException e)
        {
            throw new IllegalArgumentException("value cant be convertert");
        }
    }

}
