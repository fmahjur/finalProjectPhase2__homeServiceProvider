package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.validation.PictureValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GetImage {
    public byte[] getImage() {
        String imageFilePath = "C:\\Users\\paage\\OneDrive\\Documents\\reihaneh\\1111.jpg";
        PictureValidator.isValidImageFile(imageFilePath);
        File file = new File(imageFilePath);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bFile;
    }
}
