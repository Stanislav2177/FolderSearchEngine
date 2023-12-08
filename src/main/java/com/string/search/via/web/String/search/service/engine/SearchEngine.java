package com.string.search.via.web.String.search.service.engine;

import com.string.search.via.web.String.search.service.documentReader.BinaryOpener;
import com.string.search.via.web.String.search.service.documentReader.FileOpener;
import com.string.search.via.web.String.search.service.documentReader.HtmlOpener;
import com.string.search.via.web.String.search.service.documentReader.ZipOpener;
import com.string.search.via.web.String.search.service.fileTraverse.FileSystemTraverse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchEngine {
    private final FileOpener fileOpener;
    private final BinaryOpener binaryOpener;
    private final FileSystemTraverse traverse;
    private final ZipOpener zipOpener;

    private final HtmlOpener htmlOpener;

    public SearchEngine(){
        htmlOpener = new HtmlOpener();
        binaryOpener = new BinaryOpener();
        fileOpener = new FileOpener();
        traverse = new FileSystemTraverse();
        zipOpener = new ZipOpener();
    }

    public Map<String, List<Integer>> traverseAndSearchByLink(String path, String search) throws IOException {
        List<String> allFolderPathsWhichAreDirectories =
                traverse.getAllFolderPathsWhichAreDirectories(path);

        Map<String, List<Integer>> linkToListOfIndexes = new HashMap<>();

        //Search for archives and if appear, its unzip it

        for (String directory : allFolderPathsWhichAreDirectories) {
            searchForArchives(directory);
        }

        for (String link : allFolderPathsWhichAreDirectories) {
            List<Integer> integers = fileOpener.openFile(link, search);
            if(!integers.isEmpty()){
                linkToListOfIndexes.put(link, integers);
            }
        }


//        printMapWithLists(linkToListOfIndexes);

        return linkToListOfIndexes;
    }

    public Map<String, List<Integer>> searchInFile(String path, String search){
        List<Integer> integers = fileOpener.openFile(path, search);

        Map<String, List<Integer>> linkToListOfIndexes = new HashMap<>();
        linkToListOfIndexes.put(path, integers);
//        printMapWithLists(linkToListOfIndexes);

        return linkToListOfIndexes;
    }

    public Map<String, List<Integer>> searchInBinaryFile(String path, String search) throws IOException {
        List<String> allFolderPathsWhichAreDirectories =
                traverse.getAllFolderPathsWhichAreDirectories(path);

        for (String directory : allFolderPathsWhichAreDirectories) {
            searchForArchives(directory);
        }

        List<Integer> list = new ArrayList<>();

        Map<String, List<Integer>> map = new HashMap<>();

        for (String folderPath : allFolderPathsWhichAreDirectories) {
            try {
                list= binaryOpener.openBinaryFolder(folderPath, search);

                if(list.get(0) != 0){
                    map.put(folderPath,list);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        printMapWithIntegers(map);

//        printMapWithLists(map);
        return map;
    }

    public Map<String, List<Integer>> searchInHtmlFile(String path, String search) throws IOException {
        List<String> allFolderPathsWhichAreDirectories =
                traverse.getAllFolderPathsWhichAreDirectories(path);
        List<Integer> list = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        for (String directory : allFolderPathsWhichAreDirectories) {
            searchForArchives(directory);
        }

        for (String link : allFolderPathsWhichAreDirectories) {
            list = htmlOpener.openHtmlFileLocally(path, search);
            map.put(link, list);
        }

        return map;
    }



    private void searchForArchives(String path) throws IOException {
        //Method for searching in folder .zip files
        //and if appeared, to unzip it in the selected folder

        List<String> list = traverse.searchForArchivesInFolder(path);

        for (String link : list) {
            zipOpener.openZipFilesInFolder(link);
        }

    }


    private void printMapWithLists(Map<String, List<Integer>> map) {
        for (String s : map.keySet()) {
            System.out.println("Link:" + s);
            List<Integer> integers = map.get(s);
            if (integers.isEmpty()) {
                System.out.println("Empty folder");
            }
            for (Integer integer : integers) {
                System.out.println(integer);
            }
        }
    }

    private void printMapWithIntegers(Map<String, Integer> map) {
        for (String s : map.keySet()) {
            System.out.println("Link:" + s);
            System.out.println(map.get(s));
        }
    }


}
