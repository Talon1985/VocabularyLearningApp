public class Vocabulary {


    private String englishWord;
    private String germanWord;

    public Vocabulary(String englishWord, String germanWord) {
        this.englishWord = englishWord;
        this.germanWord = germanWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getGermanWord() {
        return germanWord;
    }

    @Override
    public String toString() {
        return englishWord + " - " + germanWord;
    }
}
