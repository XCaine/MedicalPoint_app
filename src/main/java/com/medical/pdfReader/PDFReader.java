package com.medical.pdfReader;

import com.medical.config.AppConfig;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;
import com.medical.service.MedicalPointService;
import com.medical.service.MedicalUnitTypeService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class PDFReader {

    static int skipFirstLines = 8;
    static ParsedMedicalPoint medicalPoint = new ParsedMedicalPoint();
    static Vector<ParsedMedicalPoint> medicalPoints = new Vector<>();
    static String line = "";
    static String profileRegex = ".*[^.1-9]2\\.[1-9]*\\.";

    static void printMedicalPoints() {
        for(int i=0; i<medicalPoints.size(); i++)
        {
            medicalPoints.get(i).printData();
        }
    }

    static void saveMedicalPointsToDatabase() throws Exception {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MedicalPointService pointService = (MedicalPointService) context.getBean("medicalPointService");
        MedicalUnitTypeService unitTypeService = ( MedicalUnitTypeService) context.getBean("medicalUnitTypeService");

        for(int i=0; i<medicalPoints.size(); i++)
        {
            String fullName = medicalPoints.get(i).getFullName();
            MedicalPoint point;
            try {
                point = pointService.addMedicalPointWithName(fullName);

                Set<Specialty> specialties = new HashSet<>();
                Vector<Integer> indexes = new Vector<>();

                for(int j=0; j<medicalPoints.get(i).profile.size(); j++)
                {
                    if(medicalPoints.get(i).profile.get(j).contains("ANESTEZJOLOGIA"))
                    {
                        if(!indexes.contains(1))
                            indexes.add(1);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("KARDIOLOGIA"))
                    {
                        if(!indexes.contains(2))
                            indexes.add(2);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("GENETYKA"))
                    {
                        if(!indexes.contains(5))
                            indexes.add(5);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("DERMATOLOGIA"))
                    {
                        if(!indexes.contains(7))
                            indexes.add(7);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("ENDOKRYNOLOGIA"))
                    {
                        if(!indexes.contains(8))
                            indexes.add(8);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("GASTROENTEROLOGIA"))
                    {
                        if(!indexes.contains(10))
                            indexes.add(10);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("GERIATRIA"))
                    {
                        if(!indexes.contains(11))
                            indexes.add(11);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("GINEKOLOGIA"))
                    {
                        if(!indexes.contains(12))
                            indexes.add(12);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("CHOROBY ZAKAŹNE"))
                    {
                        if(!indexes.contains(13))
                            indexes.add(13);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("CHOROBY WEWNĘTRZNE"))
                    {
                        if(!indexes.contains(14))
                            indexes.add(14);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("ONKOLOGIA"))
                    {
                        if(!indexes.contains(15))
                            indexes.add(15);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("NEFROLOGIA"))
                    {
                        if(!indexes.contains(18))
                            indexes.add(18);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("NEUROLOGIA"))
                    {
                        if(!indexes.contains(19))
                            indexes.add(19);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("NEUROCHIRURGIA"))
                    {
                        if(!indexes.contains(20))
                            indexes.add(20);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("OKULISTYKA"))
                    {
                        if(!indexes.contains(23))
                            indexes.add(23);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("ORTOPEDIA"))
                    {
                        if(!indexes.contains(25))
                            indexes.add(25);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("OTORYNOLARYNGOLOGIA"))
                    {
                        if(!indexes.contains(26))
                            indexes.add(26);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("CHIRURGIA DZIECIĘCA"))
                    {
                        if(!indexes.contains(27))
                            indexes.add(27);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("PEDIATRIA"))
                    {
                        if(!indexes.contains(28))
                            indexes.add(28);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("CHIRURGIA PLASTYCZNA"))
                    {
                        if(!indexes.contains(32))
                            indexes.add(32);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("RADIOTERAPIA"))
                    {
                        if(!indexes.contains(37))
                            indexes.add(37);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("REUMATOLOGIA"))
                    {
                        if(!indexes.contains(38))
                            indexes.add(38);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("CHIRURGIA OGÓLNA"))
                    {
                        if(!indexes.contains(39))
                            indexes.add(39);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("UROLOGIA"))
                    {
                        if(!indexes.contains(41))
                            indexes.add(41);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("CHIRURGIA NACZYNIOWA"))
                    {
                        if(!indexes.contains(42))
                            indexes.add(42);
                    }
                    if(medicalPoints.get(i).profile.get(j).contains("ALERGOLOGIA"))
                    {
                        if(!indexes.contains(43))
                            indexes.add(43);
                    }
                }

                if (medicalPoints.get(i).sor == true) {
                    if(!indexes.contains(44))
                        indexes.add(44);
                }
                if (medicalPoints.get(i).nocnaSwiatecznaPomoc == true) {
                    if(!indexes.contains(45))
                        indexes.add(45);
                }
                if (medicalPoints.get(i).izbaPrzyjec == true) {
                    if(!indexes.contains(46))
                        indexes.add(46);
                }

                for(Integer j: indexes)
                {
                    Specialty specialty = new Specialty();
                    specialty.setId(j);
                    specialties.add(specialty);
                }

                MedicalUnitType unitType = unitTypeService.findByName("Oddział Szpitalny");
                pointService.addMedicalUnit("Oddział Szpitalny", unitType, point, specialties);
            } catch(NullPointerException e) {

            }
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
        */


        PDDocument document = PDDocument.load(new File("pdf/wielkopolskie.pdf"));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            parseText(text);
            printMedicalPoints();
        }
        document.close();
        saveMedicalPointsToDatabase();
    }
}
