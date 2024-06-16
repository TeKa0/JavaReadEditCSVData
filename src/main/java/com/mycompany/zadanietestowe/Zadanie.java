/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.zadanietestowe;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author TeKa
 */
public class Zadanie {
    Integer id;
    String nazwa;
    String typ;
    Integer priorytet;
    LocalDate data_utworzenia;
    String informacja;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getPriorytet() {
        return priorytet;
    }

    public void setPriorytet(Integer priorytet) {
        this.priorytet = priorytet;
    }

    public LocalDate getData_utworzenia() {
        return data_utworzenia;
    }

    public void setData_utworzenia(LocalDate data_utworzenia) {
        this.data_utworzenia = data_utworzenia;
    }

    public String getInformacja() {
        return informacja;
    }

    public void setInformacja(String informacja) {
        this.informacja = informacja;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        return "id: " + id + ", nazwa: " + nazwa + ", typ: " + typ + ", priorytet: " + priorytet + ", data_utworzenia: " + formatter.format(data_utworzenia) + ", informacja: " + informacja;
    }
}