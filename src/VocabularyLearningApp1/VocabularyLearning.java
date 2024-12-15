package VocabularyLearningApp1;

import VocabularyLearningApp1.VocabularyList;

import java.io.*;
import java.util.*;

public class VocabularyLearning {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            VocabularyList vocabularyList = new VocabularyList();
            String dateiname = "C:\\Users\\Milan\\Documents\\GitHub\\VocabularyLearningApp\\src\\VocabularyLearningApp1\\Liste\\vokabeln.txt";

            // Vokabelliste beim Start automatisch laden
            readFromFile(dateiname, vocabularyList);

            while (true) {
                System.out.println("\n*** Vokabeltrainer ***");
                System.out.println("1. Neue Vokabel hinzufügen");
                System.out.println("2. Alle Vokabeln anzeigen");
                System.out.println("3. Vokabel lernen (Multiple Choice)");
                System.out.println("4. Vokabel löschen");
                System.out.println("5. Beenden");
                System.out.println("6. Vokabeln speichern");
                System.out.println("7. Vokabeln laden");
                System.out.println("8. Fortschritt anzeigen");
                System.out.print("Wählen Sie eine Option (1-8): ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Eingabepuffer leeren

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
                        System.out.print("Geben Sie das englische Wort der zu löschenden Vokabel ein: ");
                        String wordToDelete = scanner.nextLine();
                        if (vocabularyList.deleteVocabulary(wordToDelete)) {
                            System.out.println("Vokabel gelöscht.");
                        } else {
                            System.out.println("Vokabel nicht gefunden.");
                        }
                        break;

                    case 5:
                        System.out.println("Programm beendet. Tschüss!");
                        return;

                    case 6:
                        saveToFile(dateiname, vocabularyList);
                        break;

                    case 7:
                        readFromFile(dateiname, vocabularyList);
                        break;

                    case 8:
                        vocabularyList.showProgress();
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
                    file.getParentFile().mkdirs(); // Ordner erstellen, falls nicht vorhanden
                    file.createNewFile();         // Datei erstellen
                    System.out.println("Datei wurde neu erstellt: " + dateiname);
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
                System.out.println("Fehler beim Erstellen der Datei: " + e.getMessage());
            }
        }
    }