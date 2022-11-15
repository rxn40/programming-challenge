package de.exxcellent.challenge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasse, die es ermöglicht Daten aus einem File zu lesen
 * 
 * @author Groner Yvette
 */
public final class ReadFile {


    /*  
        Methode, die Daten aus der name.csv Datei liest und in FileInformation schreibt
        ERROR:  File existiert/kann nicht gefunden werden 
                File ist leer
        NOTIZ   name oder name.csv angabe möglich sein
    */
    public FileInformation readCsvFile(String name){
        
        if(!name.contains(".csv")){
            name = name+".csv";
        }
        String path = "C:/Users/GRY1UL/Desktop/Programmiren/programming-challenge/src/main/resources/de/exxcellent/challenge/"+name;

        FileInformation infoFile = new FileInformation(); 

        try (Scanner scanner = new Scanner(new File(path));) {
            //header enthält dann alle keys der map von infoFile
            String[] header = scanner.nextLine().split(",");
    
            for(int i = 0; i < header.length; i++){ //falls "XX, YY" und nicht "XX,YY"
                header[i] = header[i].trim();
            }

            // Einträge der map von infoFile erstellen
            while (scanner.hasNextLine()) {
                String[] entries = scanner.nextLine().split(",");

                for(int i = 0; i < entries.length; i++){
                    infoFile.addEntry(header[i], entries[i].trim());
                }
                
             }

        }catch(Exception e){ 
            throw new IllegalArgumentException("Something went wrong. File "+name + "can't be found!");
        }
         
        return infoFile;
    }
}