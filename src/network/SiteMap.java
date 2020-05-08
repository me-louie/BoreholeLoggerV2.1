package network;

import javax.imageio.ImageIO;
import java.awt.*;
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
    private Image map = null;
    private String saveDir = "src/ui/resources.resources.images";
    private String targetURL;

    private static final int BUFFER_SIZE = 4096;


    public SiteMap(String mapId, String latitude, String longitude) {
        try {
            this.mapId = mapId;
            this.latitude = latitude;
            this.longitude = longitude;

            setTargetUrl();
            downloadFile(saveDir, mapId);
            loadAndSetMap();
            System.out.println(map == null);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not download file");
        }
    }

    private void setTargetUrl() {
        this.targetURL =
                "https://api.mapbox.com/styles/v1/mapbox/satellite-v9/static/" + this.latitude + "," + this.longitude + "," +
                        "16.57,0/500x500?access_token=" +
                        "pk.eyJ1IjoibWVsb3VpZSIsImEiOiJjazJvMTlmNHUwMDV0M3BtemRmeTFveGRiIn0.PnKZ_-j1wONn43DnbtzShw";
    }

    public Image getSiteMap() {
        return map;
    }

    private void loadAndSetMap() {
        try {
            map = ImageIO.read(new File("src/ui/resources.resources.images/" + mapId + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //https://www.codejava.net/java-se/networking/use-httpurlconnection-to-download-file-from-an-http-url
    private void downloadFile(String saveDir, String fileName)
            throws IOException {
        String fileURL = this.targetURL;
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
//            String fileName = "";
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();


            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName + ".jpg");

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName + ".jpg";

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
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

    public Image getMap() {
        return map;
    }

    public void setMap(Image map) {
        this.map = map;
    }
}

