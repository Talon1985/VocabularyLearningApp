import java.io.*;
import java.util.Scanner;

public class VocabularyLearningApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VocabularyList vocabularyList = new VocabularyList();
        String dateiname = "C:\\Users\\Milan\\IdeaProjects\\VocabularyLearningApp\\Liste\\vokabeln.txt"; // Absoluter Pfad zur Datei

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
            scanner.nextLine(); // Eingabepuffer leeren

            switch (choice) {
                case 1:
                    System.out.print("Geben Sie das englische Wort ein: ");
                    String english = scanner.nextLine();
                    System.out.print("Geben Sie das deutsche Wort ein: ");
                    String german = scanner.nextLine();
                    Vocabulary vocabulary = new Vocabulary(english, german);
                    vocabularyList.addVocabulary(vocabulary);
                    System.out.println("Vokabel hinzugefügt!");
                    break;

                case 2:
                    System.out.println("Alle Vokabeln:");
                    vocabularyList.showAllVocabularies();
                    break;

                case 3:
                    vocabularyList.learnVocabulary(); // Multiple-Choice-Methode wird aufgerufen
                    break;

                case 4:
                    System.out.println("Programm beendet. Tschüss!");
                    return;

                case 5:
                    StringBuilder text = new StringBuilder();
                    for (Vocabulary vocab : vocabularyList.getVocabularyList()) {
                        text.append(vocab.getEnglishWord())
                                .append(";")
                                .append(vocab.getGermanWord())
                                .append("\n");
                    }
                    saveToFile(dateiname, text.toString());
                    break;

                case 6:
                    readFromFile(dateiname, vocabularyList);
                    break;

                default:
                    System.out.println("Ungültige Auswahl, bitte wählen Sie eine Zahl von 1 bis 6.");
            }
        }
    }

    private static void saveToFile(String dateiname, String text) {
        try {
            FileWriter writer = new FileWriter(dateiname, false); // false = Überschreibmodus
            writer.write(text);
            writer.close();
            System.out.println("Vokabeln wurden erfolgreich gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben in die Datei: " + e.getMessage());
        }
    }

    private static void readFromFile(String dateiname, VocabularyList vocabularyList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dateiname));
            String zeile;

            while ((zeile = reader.readLine()) != null) {
                String[] teile = zeile.split(";");
                if (teile.length == 2) {
                    Vocabulary vocab = new Vocabulary(teile[0], teile[1]);
                    vocabularyList.addVocabulary(vocab);
                }
            }

            reader.close();
            System.out.println("Vokabeln wurden erfolgreich geladen.");
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }
}
