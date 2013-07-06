package com.suhorukov.krasyuk.frequencyDictionary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: Паша
 * Date: 06.07.13
 * Time: 1:40
 * To change this template use File | Settings | File Templates.
 */
public class FrequencyDictionary {

    private int addWordStat(StringBuilder addWord, HashMap<String, WordCounter> frequencyDictionary) {
        WordCounter wordCounter;

        if (addWord.length() > 0) {
            wordCounter= frequencyDictionary.get(addWord.toString());
            if (wordCounter == null) {
                frequencyDictionary.put(addWord.toString(), new WordCounter(addWord.toString(), 1));
            }
            else
                wordCounter.incCount();

            return 1;
        }
        return 0;
    }

    public void formFrequencyDictionary(Reader readerData, HashMap<String, WordCounter> frequencyDictionary) {
        //Scanner scnData= new Scanner(readerData);
        int countAllWords= 0;
        StringBuilder wordBuilder= new StringBuilder();
        int ch;

        try {
            while ((ch= readerData.read()) > -1) {
                if (Character.isLetterOrDigit(ch)) {
                    wordBuilder.append((char)ch);
                }
                else {
                    countAllWords+= addWordStat(wordBuilder, frequencyDictionary);
                    wordBuilder.setLength(0);
                }
            }
        }
        catch (IOException e) {
            System.err.println("Ошибка при считывании данных из файла!!!");
        }

        countAllWords+= addWordStat(wordBuilder, frequencyDictionary);

        System.out.println("Размер словаря " + frequencyDictionary.size());

        for (Map.Entry<String, WordCounter> entry: frequencyDictionary.entrySet()) {
            entry.getValue().calcFrequency(countAllWords);
        }
    }

    public void sortAndWriteFrequencyDictionary(FileWriter fwt, HashMap<String, WordCounter> frequencyDictionary) {
        TreeSet<WordCounter> sortedWordCounter= new TreeSet<WordCounter>();
        if ((fwt != null) && (frequencyDictionary != null))
        {
            for (Map.Entry<String, WordCounter> entry: frequencyDictionary.entrySet()) {
                sortedWordCounter.add(entry.getValue());
            }

            for (WordCounter wc: sortedWordCounter) {
                try {
                    fwt.append(wc.getWordCounterData());
                    //fwt.write(wc.getWordCounterData());
                    fwt.flush();
                } catch (IOException e) {
                    System.err.println("Ошибка записи в файл!!!");
                    break;
                }
            }
        }
    }
}
