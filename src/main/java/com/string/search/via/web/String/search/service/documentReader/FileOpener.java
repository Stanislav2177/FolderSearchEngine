package com.string.search.via.web.String.search.service.documentReader;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class FileOpener {
    public List<Integer> openFile(String path, String search) {
        //This method, receive folder path and the specific string
        //which we are looking for and return all indexes where the
        //searched string had appeared


        //TODO: Check the usage of this arraylist
        List<String> fileContents = new ArrayList<>();

        List<Integer> integers = new ArrayList<>();

        //Added second list of integers to store the indexes
        //which appear in string searching
        List<Integer> list = new ArrayList<>();

        File files = new File(path);

        File[] filesInFolder = files.listFiles();

        if (filesInFolder != null) {
            for (File file : filesInFolder) {
                if(!file.isDirectory()){
                    String absolutePath = file.getAbsolutePath();
                    try (BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            fileContents.add(line);

                            //Receive list of integers which had appeared in the specific line
                            integers = checkLineForTheCorrectString(search, line);

                            list.addAll(integers);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return list;
    }

    private List<Integer> checkLineForTheCorrectString(String search, String line) {
        String[] split = line.split(" ");
        //This is test for test case nothing more

        List<Integer> list = new ArrayList<>();

        int index = 0;

        //adding the string which we are looking for in
        //array, so we can compare the words in each line
        String[] totalWords = search.split(" ");
        //test case

        int counter = 0;

        //TODO: idea for faster algorithm, if we compare by first char

        if(totalWords.length == 1){
            //If only one word we are searching for, this code will be executed
            for (String s : split) {
                if(search.equals(s)){
                    list.add(index);
                }
                index += s.length() + 1;

            }
        }else{
            //code for words.length > 1
            for (int i = 0; i < split.length; i++) {
                if(split[i].equals(totalWords[counter])){
                    for (int j = 0; j < totalWords.length; j++) {
                        if(split[i].equals(totalWords[j])){
                            list.add(index);
                        }
                    }
                }

                index += split[i].length() + 1;
                 // adding space after every word
            }
        }

        return list;
    }
}
