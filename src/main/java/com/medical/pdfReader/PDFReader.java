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
        if(skipFirstLines > 0)
            --skipFirstLines;
        else if(line.contains("1.3. ")) {
            saveMedicalPoint();

            String nazwaZakladu = line.replace("1.3. Nazwa zakładu leczniczego: ", "");
            medicalPoint.nazwaZakladu = nazwaZakladu;
        }
        else if(line.contains("1.4. ")) {
            String adresZakladu = line.replace("1.4. Adres zakładu leczniczego: ", "");
            medicalPoint.adresZakladu = adresZakladu;
        }
        else if(line.toUpperCase().contains("PORADA SPECJALISTYCZNA")) {
            int index = line.indexOf("3");

            String specjalnosc = line.substring(0, Math.min(line.length(), index));
            specjalnosc = specjalnosc.toUpperCase();
            specjalnosc = specjalnosc.replace("PORADA SPECJALISTYCZNA – ", "");

            if(!medicalPoint.profile.contains(specjalnosc))
                medicalPoint.profile.add(specjalnosc);
        }
        else if(line.matches(profileRegex)) {
            int index = line.indexOf("2");
            String Profil = line.substring(0, Math.min(line.length(), index));

            medicalPoint.profile.add(Profil);
        }
    }

    static void parseText(String text) {
        try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
            while ((line = reader.readLine()) != null) {
                parseLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        PDDocument document = PDDocument.load(new File("pdf/dolnoslaskie.pdf"));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            parseText(text);
            printMedicalPoints();
        }
        document.close();

    }
}
