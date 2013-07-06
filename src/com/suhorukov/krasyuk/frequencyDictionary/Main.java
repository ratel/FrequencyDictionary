package com.suhorukov.krasyuk.frequencyDictionary;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Паша
 * Date: 06.07.13
 * Time: 0:18
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String args[]) {
        HashMap<String, WordCounter> frequencyDictionary= new HashMap<String, WordCounter>();
        Reader readerData= null;

        try {
            readerData= new InputStreamReader(new BufferedInputStream(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось открыть файл \"" + args[0] + "\"");
        } catch (Exception e) {
            System.out.println("Для работы программы нужно указать имя файла по которому будет формироваться частотный словарь.");
        }

        if (readerData != null) {
            FrequencyDictionary fDictionary= new FrequencyDictionary();
            fDictionary.formFrequencyDictionary(readerData, frequencyDictionary);

            File fOut= new File("res\\Out.txt");
            try {
                FileWriter fwt= new FileWriter(fOut);
                fDictionary.sortAndWriteFrequencyDictionary(fwt, frequencyDictionary);
            } catch (IOException e) {
                System.err.println("Не удалось открыть файл для записи результатов подсчета частотного словаря!!!");
            }
        }
    }
}
