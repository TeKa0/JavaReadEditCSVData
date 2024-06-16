/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.zadanietestowe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TeKa
 */
public class ZadanieTestowe {
    
    public static void main(String[] args) {
        String csvFile = "src\\main\\java\\com\\mycompany\\zadanietestowe\\zadania.csv";
        String csvDelimiter = ",";

        DataReader dataReader = new CSVReader(csvDelimiter);
        List<Zadanie> zadania = dataReader.readData(csvFile);
        
        zadania.forEach(System.out::println);
        
        System.out.println("Wczytano zadania!");
        
        DataAnalyzer analyzer = new DataAnalyzer();
        
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Jak chcesz posortować listę:");
        //String outputWybor = scanner.nextLine();
        
//        if (outputWybor == "priorytet") {
//            List<Zadanie> sortowaneZadania = analyzer.sortZadaniaPriorytet(zadania);
//            sortowaneZadania.forEach(System.out::println);
//        }
            
        boolean wyborDobry = false;
        while (wyborDobry == false) {
            System.out.println("Jak chcesz posortować listę (dodania / priorytetu):");
            String outputWybor = scanner.nextLine();

            if (null == outputWybor) {
                System.out.println("Zły rodzaj sortowania!");
            } else switch (outputWybor) {
                case "priorytetu":
                    {
                        List<Zadanie> sortowaneZadania = analyzer.sortZadaniaPriorytet(zadania);
                        sortowaneZadania.forEach(System.out::println);
                        System.out.println("Wczytano zadania posortowane według priorytetu!");
                        wyborDobry = true;
                        break;
                    }
                case "dodania":
                    {
                        List<Zadanie> sortowaneZadania = analyzer.sortZadaniaData(zadania);
                        sortowaneZadania.forEach(System.out::println);
                        System.out.println("Wczytano zadania posortowane według dodania!");
                        wyborDobry = true;
                        break;
                    }
                default:
                    System.out.println("Zły rodzaj sortowania!");
                    break;
            }
        }
        
        System.out.println("Wprowadź id zadań które oznaczyć za wykonane (Kliknięcie enter zakończy wprowadzanie):");

        Scanner scannerid = new Scanner(System.in);
        String ids;
        boolean OznaczWykonane = false;
        while (!OznaczWykonane) {
            System.out.print("Id zadania: ");
            ids = scannerid.nextLine();
            
            if (ids == "") {
                System.out.println("Zakończono edytowanie zadań!");
                System.out.println("");
                OznaczWykonane = true;
                break;
            } 
            Integer id;
            try {
                id = Integer.valueOf(ids);
            } catch (NumberFormatException e) {
                continue; // Continue to the next iteration if conversion fails
            }     
                analyzer.zmienInformacjeNaWykonane(zadania, id);
            
                zadania.forEach(System.out::println);
        }
        
        Map<String, Long> niewykonaneZadaniaWedlugTypu = analyzer.policzNiewykonaneZadaniaWedlugTypu(zadania);
        niewykonaneZadaniaWedlugTypu.forEach((typ, liczba) -> {
            System.out.println("Typ: " + typ + ", Liczba niewykonanych zadań: " + liczba);
        });
        
        System.out.println("");
        
        long liczbaWykonanychZadan = analyzer.policzWykonaneZadania(zadania);
        System.out.println("Liczba wykonanych zadań: " + liczbaWykonanychZadan);
        
        System.out.println("");
        
        Optional<Integer> maksymalnyPriorytetNiewykonanych = analyzer.znajdzMaksymalnyPriorytetNiewykonanychZadan(zadania);
        if (maksymalnyPriorytetNiewykonanych.isPresent()) {
            System.out.println("Maksymalny priorytet niewykonanych zadań: " + maksymalnyPriorytetNiewykonanych.get());
        } else {
            System.out.println("Brak niewykonanych zadań.");
        }
        
        System.out.println("Podaj nazwę do pliku wyjściowego:");
        String outputFilePath = "src\\main\\java\\com\\mycompany\\zadanietestowe\\" + scanner.nextLine();
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            niewykonaneZadaniaWedlugTypu.forEach((typ, liczba) -> {
                try {
                    writer.write("Typ: " + typ + ", Liczba niewykonanych zadań: " + liczba + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(ZadanieTestowe.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            writer.write("Liczba wykonanych zadań: " + liczbaWykonanychZadan + "\n");
            writer.write("Maksymalny priorytet niewykonanych zadań: " + maksymalnyPriorytetNiewykonanych.get() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("ścieżka do pliku: " + outputFilePath);
        
        System.out.println("Zakończono działanie programu.");
    }
}
