package com.string.search.via.web.String.search.service.documentReader;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
@Service
public class ZipOpener {

    public void openZipFilesInFolder(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        System.out.println("Unziping " + folderPath);

        if (files != null) {
            for (File file : files) {
                if (file.isFile() &&
                        file.getName().toLowerCase().endsWith(".zip")) {
                    System.out.println(file.getAbsolutePath());
                    openZipFile(file.getAbsolutePath(), folderPath);
                }
            }
        }
    }

    public void openZipFile(String path, String root) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path))) {
            System.out.println("Received path " + path);
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            System.out.println("ZipEntry " + zipEntry.getName());



            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    String fileName = new File(zipEntry.getName()).getName();
                    String fileExtension = fileName
                            .substring(fileName.lastIndexOf('.') + 1)
                            .toLowerCase();

                    switch (fileExtension) {
                        case "txt", "html", "png", "jpg" -> {
                            File newFile = new File(root, fileName);
                            extractFile(zipInputStream, newFile);
                            System.out.println("Extracted: " +
                                    newFile.getAbsolutePath() + " from " + zipEntry.getName());
                        }
                        default -> {
                        }
                    }
                }

                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }


    private void extractFile(ZipInputStream zipInputStream, File newFile) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = zipInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
        }
    }
}
