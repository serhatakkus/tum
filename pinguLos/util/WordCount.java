package pgdp.searchengine.util;

public class WordCount {
    private String word;
    private int count;
    
    public WordCount(String word) {
        this.word = word;
        this.count = 0;
    }
    
    public WordCount(String word, int count) {
        this.word = word;
        this.count = count > 0 ? count : 0;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
    
    public void increment() {
        count++;
    }   
}
