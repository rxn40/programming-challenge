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
    Methode, die Daten aus der name.csv Datei liest und in informations schreibt
    */
    public static List<String> readCsvFileAll(String name){
        List<String> informations = new ArrayList<>();

        String path = "C:/Users/GRY1UL/Desktop/Programmiren/programming-challenge/src/main/resources/de/exxcellent/challenge/"+name;

        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                informations.add(scanner.nextLine());
             }
        }catch(Exception e) {
            System.out.println("Something went wrong. File "+name + "can't be found!");
        }

        return  informations;
    }

}