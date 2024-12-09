import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class VocabularyList {

    private List<Vocabulary> vocabularyList;

    public VocabularyList() {
        vocabularyList = new ArrayList<>();
    }

    // Methode zum Hinzufügen einer neuen Vokabel
    public void addVocabulary(Vocabulary vocabulary) {
        vocabularyList.add(vocabulary);
    }

    // Methode zum Abrufen der Vokabeln
    public List<Vocabulary> getVocabularyList() {
        return vocabularyList;
    }

    // Multiple-Choice-Lernen mit Beenden durch Eingabe von "0"
    public void learnVocabulary() {
        if (vocabularyList.isEmpty()) {
            System.out.println("Keine Vokabeln zum Lernen vorhanden!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        while (true) {
            // Zufälliges Vokabel-Objekt auswählen
            Vocabulary correctVocabulary = vocabularyList.get(rand.nextInt(vocabularyList.size()));

            // Antwortmöglichkeiten generieren
            List<String> options = new ArrayList<>();
            options.add(correctVocabulary.getGermanWord());

            while (options.size() < 4) { // 3 weitere zufällige, aber falsche Antworten hinzufügen
                String randomOption = vocabularyList.get(rand.nextInt(vocabularyList.size())).getGermanWord();
                if (!options.contains(randomOption)) {
                    options.add(randomOption);
                }
            }

            // Antworten mischen
            Collections.shuffle(options);

            // Frage ausgeben
            System.out.println("\nWas ist die deutsche Übersetzung von: " + correctVocabulary.getEnglishWord());
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ": " + options.get(i));
            }
            System.out.print("Ihre Antwort (oder '0' zum Beenden): ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equals("0")) {
                System.out.println("Lernen beendet. Bis zum nächsten Mal!");
                break;
            }

            // Antwort prüfen
            try {
                int answerIndex = Integer.parseInt(userAnswer) - 1;
                if (options.get(answerIndex).equals(correctVocabulary.getGermanWord())) {
                    System.out.println("Richtig! Gut gemacht.");
                } else {
                    System.out.println("Leider falsch. Die richtige Antwort ist: " + correctVocabulary.getGermanWord());
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 4 ein.");
            }
        }
    }

    // Methode zum Anzeigen aller Vokabeln
    public void showAllVocabularies() {
        if (vocabularyList.isEmpty()) {
            System.out.println("Es gibt keine Vokabeln.");
            return;
        }
        for (Vocabulary vocab : vocabularyList) {
            System.out.println(vocab);
        }
    }
}