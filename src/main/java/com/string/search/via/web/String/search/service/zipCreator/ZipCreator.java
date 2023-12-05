package com.string.search.via.web.String.search.service.zipCreator;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {
    public void createFiles(String root) {
        try {
            for (int i = 1; i <= 5; i++) {
                File file = new File(root, "file" + i + ".txt");
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zipFiles(String sourceFolder, String outputZipFile) {
        try (FileOutputStream fos = new FileOutputStream(outputZipFile);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            File sourceFile = new File(sourceFolder);

            // Add all files in the source folder to the zip file
            addFilesToZip(sourceFile, sourceFile.getName(), zipOut);

            System.out.println("Files zipped successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFilesToZip(File sourceFile, String entryName, ZipOutputStream zipOut) throws IOException {
        if (sourceFile.isDirectory()) {
            for (File file : sourceFile.listFiles()) {
                // Recursively add files in subdirectories
                addFilesToZip(file, entryName + File.separator + file.getName(), zipOut);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(sourceFile);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {

                // Add a file entry to the zip file
                ZipEntry zipEntry = new ZipEntry(entryName);
                zipOut.putNextEntry(zipEntry);

                // Read the file content and write it to the zip file
                byte[] bytes = new byte[1024];
                int length;
                while ((length = bis.read(bytes)) > 0) {
                    zipOut.write(bytes, 0, length);
                }

                zipOut.closeEntry();
            }
        }
    }

    public void deleteAllFiles(String path){
        File file = new File(path);
        File[] files = file.listFiles();

        for (File file1 : files) {
            file1.delete();
        }
    }
}
