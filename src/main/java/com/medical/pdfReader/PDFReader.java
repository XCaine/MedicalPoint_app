package com.medical.pdfReader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.Vector;

import static com.medical.pdfReader.States.*;

public class PDFReader {

    static int skipFirstLines = 8;
    static ParsedMedicalPoint medicalPoint = new ParsedMedicalPoint();
    static Vector<ParsedMedicalPoint> medicalPoints = new Vector<ParsedMedicalPoint>();
    static String line = "";
    static String ParsedData = "";
    static States state = NONE;
    static String profilesRegex = ".*[^.1-9]2\\.[1-9]*\\.";

    static void printMedicalPoints() {
        for(int i=0; i<medicalPoints.size(); i++)
        {
            medicalPoints.get(i).printData();
        }
    }

    static void saveMedicalPoint()
    {
        medicalPoints.add(medicalPoint);
        medicalPoint = new ParsedMedicalPoint();
    }

    static void endStep() {
        switch(state) {
            /*case READ_ADRES:
                String adres = ParsedData.replace("1.1. Adres siedziby świadczeniodawcy: ", "");
                medicalPoint.adres = adres;
                //System.out.println(medicalPoint.adres);
                ParsedData = "";
                //medicalPoint = new ParsedMedicalPoint();
                break;*/
            case READ_NAZWA_ZAKLADU:
                String nazwaZakladu = ParsedData.replace("1.3. Nazwa zakładu leczniczego: ", "");
                medicalPoint.nazwaZakladu = nazwaZakladu;
                //System.out.println(medicalPoint.nazwaZakladu);
                ParsedData = "";
                break;
            case READ_ADRES_ZAKLADU:
                String adresZakladu = ParsedData.replace("1.4. Adres zakładu leczniczego: ", "");
                medicalPoint.adresZakladu = adresZakladu;
                //System.out.println(medicalPoint.adresZakladu);
                ParsedData = "";
                break;
            case READ_PROFILES:
                ParsedData = "";
                break;

        }
    }

    static void parseLine()
    {
        if(skipFirstLines > 0)
            --skipFirstLines;
        else if(line.contains("Strona"));
        /*else if(line.contains("1.1. ")) {
            if(state != READ_ADRES)
                endStep();
            ParsedData += line;
            state = READ_ADRES;
        }*/
        else if(line.contains("1.3. ")) {
            if(state != READ_NAZWA_ZAKLADU) {
                endStep();
                saveMedicalPoint();
            }
            ParsedData += line;
            state = READ_NAZWA_ZAKLADU;
        }
        else if(line.contains("1.4. ")) {
            if(state != READ_ADRES_ZAKLADU)
                endStep();
            ParsedData += line;
            state = READ_ADRES_ZAKLADU;
        }
        //else if(state != NONE)
        //    ParsedData += line;
        /*else if(line.contains("2.1.")) {
            if(state != READ_PROFILES)
                endStep();
            ParsedData += line;
            state = READ_PROFILES;
        }*/
        else if(line.matches(profilesRegex))
        {
            if(state != READ_PROFILES)
                endStep();

            // Remove the numbers from string, leave only profile name
            int index = line.indexOf("2");
            ParsedData = line.substring(0, Math.min(line.length(), index));

            medicalPoint.profile.add(ParsedData);
            state = READ_PROFILES;
        }

        /*else if(line == line.toUpperCase() && !line.contains("1.") && !line.contains("2.") && !line.contains("3."))
        {
            System.out.println(line);
        }*/
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
        PDDocument document = PDDocument.load(new File("pdf/mazowieckie.pdf"));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            parseText(text);

            printMedicalPoints();

            //PrintWriter writer = new PrintWriter("txt/strippedtext.txt", "UTF-8");
            //System.out.println("Text:" + text);
        }
        document.close();

    }
}
