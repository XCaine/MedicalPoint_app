package com.medical.pdfReader;

import java.util.Vector;

public class ParsedMedicalPoint {
    String nazwaZakladu, adresZakladu;
    Vector<String> profile = new Vector<String>();
    boolean sor = false;
    boolean nocnaSwiatecznaPomoc = false;
    boolean izbaPrzyjec = false;

    String getFullName() {
        return nazwaZakladu + ", " + adresZakladu;
    }

    void printData()
    {
        System.out.println("NazwaZakladu: " + nazwaZakladu);
        System.out.println("AdresZakladu: " + adresZakladu);
        System.out.println("Profile: ");
        for(int j=0; j<profile.size(); j++) {
            System.out.println("    " + profile.get(j));
        }
        System.out.println("SOR:                        " + sor);
        System.out.println("IzbaPrzyjec:                " + izbaPrzyjec);
        System.out.println("NocnaiSwiatecznaPomoc:      " + nocnaSwiatecznaPomoc);
        System.out.println("\n");
    }

}
