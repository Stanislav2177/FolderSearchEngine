package com.string.search.via.web.String.search.service.fileTraverse;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
//Purpose of the class is to extract all possible links which are folders
public class FileSystemTraverse {

    private List<String> listFolderContents(String folderPath) {
        //Receive folder path and then list all available links which
        //can be reached
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {
                // Convert File array to a list of absolute paths
                List<String> filePaths = new ArrayList<>();
                for (File file : files) {
                    filePaths.add(file.getAbsolutePath());
                }

                // Convert the list to a comma-separated string
                String commaSeparatedPaths = String.join(",", filePaths);

                // Split the string based on commas and return the list
                return List.of(commaSeparatedPaths.split(","));
            } else {
                return Collections.singletonList("Folder is empty");
            }
        } else {
            return Collections.singletonList("Invalid folder path");
        }
    }

    public List<String> getAllFolderPathsWhichAreDirectories(String folderPath) {
        //Main method which to return all links to directories.

        //Receive as input Folder path and then return List of available
        //folder paths to be checked
        List<String> strings = listFolderContents(folderPath);
        System.out.println("root folder contains:");

        for (String string : strings) {
            System.out.println(string);
        }

        System.out.println("--------------------------------");


        //return only links to and folders
        List<String> allLinkInSubfolder = new ArrayList<>();
        allLinkInSubfolder.add(folderPath);

        List<String> links = getAllLinksInSubfolder(folderPath);
        allLinkInSubfolder.addAll(links);
        for (String s : allLinkInSubfolder) {
            getAllLinksInSubfolder(s);
        }


        System.out.println("--------------------------------");

        return allLinkInSubfolder;
    }

    public int getCountOfAllFiles(String folderPath){
        List<String> allFolderPathsWhichAreDirectories
                = getAllFolderPathsWhichAreDirectories(folderPath);
        int total = 0;

        for (String allFolderPathsWhichAreDirectory : allFolderPathsWhichAreDirectories) {
            System.out.println("Looking into " + allFolderPathsWhichAreDirectory);
        }

        for (String directory : allFolderPathsWhichAreDirectories) {
            File file = new File(directory);
            File[] files = file.listFiles();

            for (File currentFile : files) {
                if(!currentFile.isDirectory()){
                    total++;
                }
            }
        }

        return total;
    }

    private List<String> getAllLinksInSubfolder(String folderPath) {
        System.out.println("Found link: " + folderPath);
        return traverseFolders(folderPath);
    }

    private List<String> traverseFolders(String folderPath) {
        List<String> links = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        links.add(file.getAbsolutePath());
                        links.addAll(traverseFolders(file.getAbsolutePath())); // Recursive call for subfolders
                    } else if
                    (!file.getName().endsWith(".txt")
                    && !file.getName().endsWith(".html")
                    && !file.getName().endsWith(".xml")
                    && !file.getName().endsWith(".bin")
                    && !file.getName().endsWith(".jpg")
                    && !file.getName().endsWith(".png")
                    && !file.getName().endsWith(".zip")) {
                        links.add(file.getAbsolutePath());
                    }
                }
            }
        }

        return links;
    }

    public List<String> searchForArchivesInFolder(String path){
        List<String> links = getAllFolderPathsWhichAreDirectories(path);
        for (String link : links) {
            System.out.println("Link to be searched = " + link);
        }
        List<String> archiveLinks = new ArrayList<>();

        for (String link : links) {
            System.out.println(link);
            File file = new File(link);

            File[] files = file.listFiles();

            if (files != null) {
                for (File currentFile : files) {
                    if(file.exists() && file.isDirectory()){
                        if(currentFile.getName().endsWith(".zip")){
                            System.out.println("Found link which contains .zip "
                                    + link + " " + currentFile.getName());
                            archiveLinks.add(link);
                        }
                    }else {
                        System.out.println("Empty Folder");
                    }
                }
            }
        }



        return archiveLinks;
    }
}
