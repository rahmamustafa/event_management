package gov.iti.evento.services.util.converters;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageConverter {
    public static byte[] recoverImageFromUrl(String urlText) throws Exception {
        System.out.println(urlText);

        URL url;
        if (urlText.startsWith("file:")) {
            url = new URL(urlText);
        } else {
            File file = new File(urlText);
            url = file.toURI().toURL();
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
        Path imagePath = Paths.get(UPLOAD_DIRECTORY, urlText);
        boolean fileExists = Files.exists(imagePath);
        if (fileExists) {
            byte[] imageBytes;

            imageBytes = Files.readAllBytes(imagePath);

            return imageBytes;
        }
        return null;
    }
}