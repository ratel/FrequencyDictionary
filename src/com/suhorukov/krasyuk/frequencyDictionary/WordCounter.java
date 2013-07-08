package com.suhorukov.krasyuk.frequencyDictionary;

/**
 * Created with IntelliJ IDEA.
 * User: Паша
 * Date: 06.07.13
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
public class WordCounter implements Comparable{
    private int count;
    private double frequency;
    private String sWord;

    WordCounter(String bSWord, int bCount) {
        sWord= bSWord;
        count= bCount;
    }

    public void incCount() {
        count++;
    }

    public void calcFrequency(int countWords) {
        frequency= ((double)count) / ((double)countWords);
    }

    public int getCount() {
        return count;
    }

    public String getSWord() {
        return sWord;
    }

    public String getWordCounterData() {
        return (sWord + " ;\t " + count + " ;\t " + frequency + "\r\n");
    }

    @Override
    public int compareTo(Object o) {
        WordCounter compareObj;

        if (o instanceof WordCounter)
            compareObj= (WordCounter) o;
        else
            return 1;

        int result= Integer.compare(compareObj.getCount(), count);

        if (result == 0) {
            result= sWord.compareTo(compareObj.getSWord());
        }

        return result;
    }
}
