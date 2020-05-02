package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Geolocation {

    private String query;
    private SiteMap siteMap;
    private HttpURLConnection connection = null;

    private String targetURL;

    public Geolocation(String query) {
        this.query = trimQuery(query);
        createTargetUrl();
        executeGET();
    }

    private void createTargetUrl(){
        String website = "https://api.mapbox.com/geocoding/v5/mapbox.places/";
        String query = this.query;
        String bbox = ".json?bbox=-123.31828695235798,49.00233130261947,-122.57499303553395,49.43177943428003&";
        String accessToken = "access_token=";
        String token = "pk.eyJ1IjoibWVsb3VpZSIsImEiOiJjazJvMTlmNHUwMDV0M3BtemRmeTFveGRiIn0.PnKZ_-j1wONn43DnbtzShw";
        targetURL = website + query + bbox + accessToken + token;
    }

    private String trimQuery(String query){
        System.out.println(this.query);
        return query.replace(" ", "%20");
    }


    private String executeGET() {
        try {
            //Create connection
            URL url = new URL(this.targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


}
