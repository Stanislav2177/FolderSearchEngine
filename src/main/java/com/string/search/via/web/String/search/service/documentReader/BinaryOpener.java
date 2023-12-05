package com.string.search.via.web.String.search.service.documentReader;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class BinaryOpener {


    public List<Integer> openBinaryFolder(String path, String search) throws IOException {
        File folder = new File(path);
        File[] filesInFolder = folder.listFiles();
        int counter = 0;

        if (filesInFolder != null) {
            for (File file : filesInFolder) {
                if (file.isFile() && isImageFile(file.getName())) {
                    try (InputStream is = new FileInputStream(file)) {
                        int byteRead;
                        int searchIndex = 0;

                        while ((byteRead = is.read()) != -1) {
                            // Convert to the same case for case-insensitive comparison
                            byteRead = Character.toLowerCase((char) byteRead);
                            int searchByte = Character.toLowerCase(search.charAt(searchIndex));

                            if (byteRead == searchByte) {
                                searchIndex++;

                                if (searchIndex == search.length()) {
                                    counter++;
                                    searchIndex = 0; // Reset the search
                                }
                            } else {
                                searchIndex = 0; // Reset the search if mismatch
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Total " + counter + " occurrences of \"" + search + "\"" + " in" + path );
        return Arrays.asList(counter);
    }

    private boolean isImageFile(String fileName) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp"}; // Add more extensions if needed
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return Arrays.asList(imageExtensions).contains(fileExtension);
    }
}
