package com.string.search.via.web.String.search.testService;


import com.string.search.via.web.String.search.service.documentReader.ZipOpener;
import com.string.search.via.web.String.search.service.fileTraverse.FileSystemTraverse;
import com.string.search.via.web.String.search.service.zipCreator.ZipCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemTraverseAndZipOpeningTest {
    private FileSystemTraverse traverse;
    private ZipCreator zipCreator;
    private ZipOpener zipOpener;

    @BeforeEach
    void set(){
        traverse = new FileSystemTraverse();
        zipCreator = new ZipCreator();
        zipOpener = new ZipOpener();
    }

//    @Test
//    public void testForCheckCountOfAllPaths(){
//        String root = "C:\\Users\\Stanislav\\Desktop\\folder-to-test";
//        List<String> allFolderPaths = new ArrayList<>();
//
//        allFolderPaths = traverse.getAllFolderPathsWhichAreDirectories(root);
//
//        assertEquals(6, allFolderPaths.size());
//    }

    @Test
    public void testForArchiveFolders(){
        String root = "\\home\\Github\\folder-to-test-zip";
        List<String> archivesFound = new ArrayList<>();

        archivesFound = traverse.searchForArchivesInFolder(root);

        assertEquals(3, archivesFound.size());
    }
//
//    @Test
//    public void testCountOfAllFilesWithoutZipOpener(){
//        String root = "C:\\Users\\Stanislav\\Desktop\\folder-to-test";
//
//        int countOfAllFiles = traverse.getCountOfAllFiles(root);
//
//        assertEquals(14, countOfAllFiles);
//    }
//
//    @Test
//    public void testCountOfAllFilesWithZipOpener() throws IOException {
//        String root = "C:\\Users\\Stanislav\\Desktop\\folder-to-test-specific";
//
//        zipCreator.createFiles(root);
//        zipOpener.openZipFilesInFolder(root);
//
//        assertEquals(6,traverse.getCountOfAllFiles(root));
//    }
}
