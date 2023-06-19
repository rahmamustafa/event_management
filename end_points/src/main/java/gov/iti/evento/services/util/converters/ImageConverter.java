package gov.iti.evento.services.util.converters;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

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

        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }

        return output.toByteArray();
    }
}
