package com.medical.pdfReader;

import java.util.Vector;

public class ParsedMedicalPoint {
    String nazwa, adres, nazwaZakladu, adresZakladu;
    Vector<String> profile = new Vector<String>();

    void saveToDatabase()
    {

        //TO DO

    }

    void printData()
    {
        System.out.println("NazwaZakladu: " + nazwaZakladu);
        System.out.println("AdresZakladu: " + adresZakladu);
        System.out.println("Profile: ");
        for(int j=0; j<profile.size(); j++) {
            System.out.println("    " + profile.get(j));
        }
        System.out.println("\n");
    }

}
