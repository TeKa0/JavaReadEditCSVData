/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanietestowe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author TeKa
 */
public class CSVReader implements DataReader {
    private String delimiter;

    public CSVReader(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public List<Zadanie> readData(String filePath) {
        List<Zadanie> zadania = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);

                Zadanie zadanie = new Zadanie();
                zadanie.setId(Integer.parseInt(values[0].trim()));
                zadanie.setNazwa(values[1]);
                zadanie.setTyp(values[2]);
                zadanie.setPriorytet(Integer.parseInt(values[3].trim()));
                
                LocalDate date = LocalDate.parse(values[4].trim());
                
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                zadanie.setData_utworzenia(date);
                zadanie.setInformacja("niewykonane");

                zadania.add(zadanie);
                
                //System.out.println(line + ", " + zadanie.getInformacja());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zadania;
    }
}