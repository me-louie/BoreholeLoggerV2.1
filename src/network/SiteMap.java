package network;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SiteMap {

    private String mapId;
    private String longitude;
    private String latitude;
    private BufferedImage map = null;
    private String saveDir = "src/ui/images";
    private String targetURL;
    private static final int BUFFER_SIZE = 4096;


    public SiteMap(String mapId, String latitude, String longitude) {
        try {
            this.mapId = mapId;
            this.latitude = latitude;
            this.longitude = longitude;

            setTargerUrl();
            System.out.println(this.targetURL);
            downloadFile(this.targetURL, saveDir, mapId);
            setSiteMap();
            System.out.println(map == null);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not download file");
        }
    }

    private void setTargerUrl() {
        this.targetURL =
                "https://api.mapbox.com/styles/v1/mapbox/satellite-v9/static/" + this.latitude + "," + this.longitude + "," +
                        "16.57,0/500x500?access_token=" +
                        "pk.eyJ1IjoibWVsb3VpZSIsImEiOiJjazJvMTlmNHUwMDV0M3BtemRmeTFveGRiIn0.PnKZ_-j1wONn43DnbtzShw";
    }

    public Image getSiteMap() {
        return map;
    }

    private void setSiteMap() {
        try {
            map = ImageIO.read(new File("src/ui/images/currMap.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //https://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
    private void downloadFile(String fileURL, String saveDir, String fileName)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
//            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

//            if (disposition != null) {
//                // extracts file name from header field
//                int index = disposition.indexOf("filename=");
//                if (index > 0) {
////                    fileName = disposition.substring(index + 10,
////                            disposition.length() - 1);
//                    fileName = "testMap.jpg";
//                }
//            } else {
//                // extracts file name from URL
////                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
////                        fileURL.length());
//                fileName = "testMap.jpg";
//            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName + ".jpg");

            // create new file
//            File file = new File(String.valueOf(Paths.get("network", "data", fileName)));
//            System.out.println("Attempting to read from file in: "+file.getCanonicalPath());
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName + ".jpg";

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
//            FileOutputStream outputStream = new FileOutputStream(file);
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}

