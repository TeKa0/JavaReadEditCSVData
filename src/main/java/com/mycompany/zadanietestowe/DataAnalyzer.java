/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanietestowe;

/**
 *
 * @author TeKa
 */
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataAnalyzer {
    public List<Zadanie> sortZadaniaPriorytet(List<Zadanie> zadania) {
        zadania.sort(Comparator.comparingInt(Zadanie::getPriorytet).reversed());
        return zadania;
    }
    
    public List<Zadanie> sortZadaniaData(List<Zadanie> zadania) {
        zadania.sort(Comparator.comparing(Zadanie::getData_utworzenia));
        return zadania;
    }
    
    public void zmienInformacjeNaWykonane(List<Zadanie> zadania, int id) {
        for (Zadanie zadanie : zadania) {
            if (zadanie.getId() == id) {
                zadanie.setInformacja("Wykonane");
                break;
            }
        }
    }
    
    public List<Zadanie> pobierzNiewykonaneZadania(List<Zadanie> zadania) {
        return zadania.stream()
                .filter(zadanie -> "niewykonane".equals(zadanie.getInformacja()))
                .collect(Collectors.toList());
    }
    
    public Map<String, Long> policzNiewykonaneZadaniaWedlugTypu(List<Zadanie> zadania) {
        return zadania.stream()
                .filter(zadanie -> "niewykonane".equals(zadanie.getInformacja()))
                .collect(Collectors.groupingBy(Zadanie::getTyp, Collectors.counting()));
    }
    
    public Map<String, List<Zadanie>> grupujNiewykonaneZadaniaWedlugTypu(List<Zadanie> zadania) {
        return zadania.stream()
                .filter(zadanie -> "niewykonane".equals(zadanie.getInformacja()))
                .collect(Collectors.groupingBy(Zadanie::getTyp));
    }
    
    public long policzWykonaneZadania(List<Zadanie> zadania) {
        return zadania.stream()
                .filter(zadanie -> "Wykonane".equals(zadanie.getInformacja()))
                .count();
    }
    
    public Optional<Integer> znajdzMaksymalnyPriorytetNiewykonanychZadan(List<Zadanie> zadania) {
        return zadania.stream()
                .filter(zadanie -> "niewykonane".equals(zadanie.getInformacja()))
                .map(Zadanie::getPriorytet)
                .max(Integer::compareTo);
    }
}