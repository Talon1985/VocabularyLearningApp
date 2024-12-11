import java.io.*;
import java.util.Scanner;

public class VocabularyLearningApp {

   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VocabularyList vocabularyList = new VocabularyList();
        String dateiname = "C:\\Users\\Milan\\IdeaProjects\\VocabularyLearningApp\\VocabularyLearningApp\\Liste\\vokabeln.txt";

        // Vokabelliste beim Start automatisch laden
        readFromFile(dateiname, vocabularyList);

        while (true) {
            System.out.println("\n*** Vokabeltrainer ***");
            System.out.println("1. Neue Vokabel hinzufügen");
            System.out.println("2. Alle Vokabeln anzeigen");
            System.out.println("3. Vokabel lernen (Multiple Choice)");
            System.out.println("4. Beenden");
            System.out.println("5. Vokabeln speichern");
            System.out.println("6. Vokabeln laden");
            System.out.print("Wählen Sie eine Option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Geben Sie das englische Wort ein: ");
                    String english = scanner.nextLine();
                    System.out.print("Geben Sie das deutsche Wort ein: ");
                    String german = scanner.nextLine();
                    vocabularyList.addVocabulary(new Vocabulary(english, german));
                    System.out.println("Vokabel hinzugefügt!");
                    break;

                case 2:
                    System.out.println("Alle Vokabeln:");
                    vocabularyList.showAllVocabularies();
                    break;

                case 3:
                    vocabularyList.learnVocabulary();
                    break;

                case 4:
                    System.out.println("Programm beendet. Tschüss!");
                    return;

                case 5:
                    saveToFile(dateiname, vocabularyList);
                    break;

                case 6:
                    readFromFile(dateiname, vocabularyList);
                    break;

                default:
                    System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
            }
        }
    }

    private static void saveToFile(String dateiname, VocabularyList vocabularyList) {
        try {
            FileWriter writer = new FileWriter(dateiname, false);
            for (Vocabulary vocab : vocabularyList.getVocabularyList()) {
                writer.write(vocab.getEnglishWord() + ";" + vocab.getGermanWord() + "\n");
            }
            writer.close();
            System.out.println("Vokabeln wurden gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    private static void readFromFile(String dateiname, VocabularyList vocabularyList) {
        try {
            File file = new File(dateiname);
            if (!file.exists()) {
                System.out.println("Datei nicht gefunden. Es werden keine Vokabeln geladen.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(dateiname));
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                String[] teile = zeile.split(";");
                if (teile.length == 2) {
                    vocabularyList.addVocabulary(new Vocabulary(teile[0], teile[1]));
                }
            }
            reader.close();
            System.out.println("Vokabeln wurden erfolgreich geladen.");
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }
}

