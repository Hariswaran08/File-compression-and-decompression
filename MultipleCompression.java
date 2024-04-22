import java.io.*;
import java.util.*;
import java.util.zip.*;

public class MultiFileCompression {
    
    public static void main(String[] args) {
        String[] fileNames = {"file1.txt", "file2.txt", "file3.txt"};
        String zipFileName = "compressed_files.zip";
        
        try {
            // Compression
            compressFiles(fileNames, zipFileName);
            System.out.println("Compression successful. Output file: " + zipFileName);
            
            // Decompression
            String outputFolder = "decompressed_files";
            decompressFiles(zipFileName, outputFolder);
            System.out.println("Decompression successful. Output folder: " + outputFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void compressFiles(String[] fileNames, String zipFileName) throws IOException {
        // Create a FileOutputStream to write data to the zip file
        FileOutputStream fos = new FileOutputStream(zipFileName);
        // Create a ZipOutputStream to write zip entries to the zip file
        ZipOutputStream zos = new ZipOutputStream(fos);
        
        // Iterate through each file and add it to the zip file
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (file.exists()) {
                addFileToZip(file, zos);
            } else {
                System.out.println("File " + fileName + " not found.");
            }
        }
        
        // Close the ZipOutputStream
        zos.close();
    }
    
    private static void addFileToZip(File file, ZipOutputStream zos) throws IOException {
        // Create a FileInputStream to read data from the file
        FileInputStream fis = new FileInputStream(file);
        // Create a new ZipEntry with the file name
        ZipEntry zipEntry = new ZipEntry(file.getName());
        // Add the ZipEntry to the ZipOutputStream
        zos.putNextEntry(zipEntry);
        
        // Write data from the FileInputStream to the ZipOutputStream
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            zos.write(buffer, 0, bytesRead);
        }
        
        // Close the ZipEntry and FileInputStream
        zos.closeEntry();
        fis.close();
    }
    
    private static void decompressFiles(String zipFileName, String outputFolder) throws IOException {
        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        
        // Create a FileInputStream to read data from the zip file
        FileInputStream fis = new FileInputStream(zipFileName);
        // Create a ZipInputStream to read zip entries from the zip file
        ZipInputStream zis = new ZipInputStream(fis);
        
        // Iterate through each zip entry and write it to the output folder
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            String entryName = zipEntry.getName();
            File entryFile = new File(outputFolder + File.separator + entryName);
            FileOutputStream fos = new FileOutputStream(entryFile);
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = zis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
            fos.close();
            zis.closeEntry();
        }
        
        // Close the ZipInputStream
        zis.close();
    }
}
