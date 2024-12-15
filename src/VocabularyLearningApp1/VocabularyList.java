package VocabularyLearningApp1;

import java.util.*;

public class VocabularyList {

    private List<Vocabulary> vocabularyList;
    private int totalLearned = 0; // Anzahl der gelernten Vokabeln
    private int correctAnswers = 0; // Anzahl der korrekt beantworteten Fragen
    private int wrongAnswers = 0; // Anzahl der falschen Antworten

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

    // Methode zum Löschen einer Vokabel
    public boolean deleteVocabulary(String englishWord) {
        return vocabularyList.removeIf(vocabulary -> vocabulary.getEnglishWord().equalsIgnoreCase(englishWord));
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
            Vocabulary correctVocabulary = vocabularyList.get(rand.nextInt(vocabularyList.size()));

            // Antwortmöglichkeiten generieren
            List<String> options = new ArrayList<>();
            options.add(correctVocabulary.getGermanWord());

            while (options.size() < 4) {
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
                totalLearned++;
                if (options.get(answerIndex).equals(correctVocabulary.getGermanWord())) {
                    correctAnswers++;
                    System.out.println("Richtig! Gut gemacht.");
                } else {
                    wrongAnswers++;
                    System.out.println("Leider falsch. Die richtige Antwort ist: " + correctVocabulary.getGermanWord());
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und 4 ein.");
            }
        }

        // Fortschritt anzeigen
        showProgress();
    }

    // Methode zum Anzeigen des Lernfortschritts
    public void showProgress() {
        System.out.println("\n*** Lernfortschritt ***");
        System.out.println("Insgesamt beantwortet: " + totalLearned);
        System.out.println("Davon korrekt: " + correctAnswers);
        System.out.println("Davon falsch: " + wrongAnswers);
        System.out.println("Richtigkeitsquote: " + (totalLearned > 0 ? (correctAnswers * 100 / totalLearned) + "%" : "0%"));
        System.out.println("************************");
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
