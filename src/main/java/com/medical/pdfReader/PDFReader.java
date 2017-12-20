package com.medical.pdfReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.Vector;

public class PDFReader {

    static int skipFirstLines = 8;
    static ParsedMedicalPoint medicalPoint = new ParsedMedicalPoint();
    static Vector<ParsedMedicalPoint> medicalPoints = new Vector<ParsedMedicalPoint>();
    static String line = "";
    static String profileRegex = ".*[^.1-9]2\\.[1-9]*\\.";

    static void printMedicalPoints() {
        for(int i=0; i<medicalPoints.size(); i++)
        {
            medicalPoints.get(i).printData();
        }
    }

    static void saveMedicalPoint() {
        if(medicalPoint.nazwaZakladu != null) {
            medicalPoints.add(medicalPoint);
            medicalPoint = new ParsedMedicalPoint();
        }
    }

    static void parseLine() {
        line = line.toUpperCase();
        if(skipFirstLines > 0)
            --skipFirstLines;
        else if(line.contains("1.3. ") || line.contains("1.3 ")) {
            saveMedicalPoint();

            String nazwaZakladu = line.replace("1.3. NAZWA ZAKŁADU LECZNICZEGO: ", "");
            medicalPoint.nazwaZakladu = nazwaZakladu;
        }
        else if(line.contains("1.4. ") || line.contains("1.4 ")) {
            String adresZakladu = line.replace("1.4. ADRES ZAKŁADU LECZNICZEGO: ", "");
            medicalPoint.adresZakladu = adresZakladu;
        }
        else if(line.contains("PORADA SPECJALISTYCZNA")) {
            int index = line.indexOf("3");
            if(index == -1)
                index = 0;

            String specjalnosc = line.substring(0, Math.min(line.length(), index));
            specjalnosc = specjalnosc.replace("PORADA SPECJALISTYCZNA – ", "");
            specjalnosc = specjalnosc.replace("PORADA SPECJALISTYCZNA - ", ""); // Dolnośląskie ma chyba inny myślnik albo biały znak

            if(!medicalPoint.profile.contains(specjalnosc))
                medicalPoint.profile.add(specjalnosc);
        }
        else if(line.matches(profileRegex)) {
            if(line.contains("IZBA"))
                medicalPoint.izbaPrzyjec = true;
            else if(line.contains("SOR") || line.contains("SZPITALNY ODDZIAŁ RATUNKOWY"))
                medicalPoint.sor = true;
            else {
                int index = line.indexOf("2");
                String profil = line.substring(0, Math.min(line.length(), index));

                medicalPoint.profile.add(profil);
            }
        }
        else if(line.contains("ŚWIADCZENIA NOCNEJ I ŚWIĄTECZNEJ")) {
            medicalPoint.nocnaSwiatecznaPomoc = true;
        }
    }

    static void parseText(String text) {
        try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
            while ((line = reader.readLine()) != null) {
                parseLine();
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        /*
        Kompatybilność z PDFami:
        7/16

        Działają:
            Mazowieckie
            Dolnośląskie
            Kujawsko-Pomorskie
            Podkarpackie
            Swiętokrzyskie
            Warminsko-Mazurskie
            Wielkopolskie

        Wymagają poprawek:
            Lodzkie
            Lubelskie
            Lubuskie
            Malopolskie
            Opolskie
            Podlaskie
            Pomorskie
            Sląskie
            Zachodniopomorskie

            TO DO:
                - x dla dzieci == x dziecięca, usunięcie powtórzeń jeśli gdzieś są
        */
        PDDocument document = PDDocument.load(new File("pdf/mazowieckie.pdf"));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            parseText(text);
            printMedicalPoints();
        }
        document.close();
    }
}
