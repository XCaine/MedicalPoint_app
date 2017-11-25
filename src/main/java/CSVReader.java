import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public void ReadFiles() {
        String pathToDirectory = "./data/csv/";
        String filenames[] = {"sor.csv", "nisoz.csv", "izby_przyjec.csv", "stomatologiczna_pomoc_dorazna.csv"};
        String line = "";
        String separator =";";

        for(int i = 0; i < filenames.length; i++) {
            System.out.println("\n" + filenames[i] + ": \n");
            try (BufferedReader reader = new BufferedReader(new FileReader(pathToDirectory + filenames[i]))) {
                while ((line = reader.readLine()) != null) {
                    String[] separatedLine = line.split(separator);

                    switch(filenames[i]) {
                        case "sor.csv":
                            // add to database
                            break;
                        case "nisoz.csv":
                            // add to database
                            break;
                        case "izby_przyjec.csv":
                            // add to database
                            break;
                        case "stomatologiczna_pomoc_dorazna.csv":
                            // add to database
                            break;
                        default:
                            System.out.println("unsupported file");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
