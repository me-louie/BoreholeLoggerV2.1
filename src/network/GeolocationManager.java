//package network;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class GeolocationManager {
//
//    private HttpURLConnection connection = null;
//    private JSONObject jsonObject = null;
//    private StringBuilder response = null;
//    private String line;
//    private String siteAddress;
//    private String jsonResponse = null;
//
////
//    //MODIFIES: this
//    //EFFECTS: creates new site map object and returns coordinates the address query
//    public GeolocationManager(String siteAddress) throws JSONException {
//        queryBuilder(siteAddress);
//        getJsonResponse();
//        createJson(jsonResponse);
//        parseCoordinates(jsonObject);
//
//
//    }
//
//
//    //method modified from source: https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
//    private void getJsonResponse() {
//        try {
//            createConnection();
//            //Get Response
//            InputStream is = connection.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//            response = new StringBuilder();
//            while ((line = rd.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
//            }
//            rd.close();
////            System.out.println(response.toString());
//            jsonResponse = response.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
////            return null;
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//    }
//
//    private void queryBuilder(String siteAddress) {
//        this.siteAddress = siteAddress.replace(" ", "%20");
////        System.out.println(output);
//    }
//
//    //EFFECTS: creates connection to the web
//    private void createConnection() throws IOException {
//        String website = "https://api.mapbox.com/geocoding/v5/mapbox.places/";
//        String query = this.siteAddress;
//        String bbox = ".json?bbox=-123.31828695235798,49.00233130261947,-122.57499303553395,49.43177943428003&";
//        String accessToken = "access_token=";
//        String token = "pk.eyJ1IjoibWVsb3VpZSIsImEiOiJjazJvMTlmNHUwMDV0M3BtemRmeTFveGRiIn0.PnKZ_-j1wONn43DnbtzShw";
//        String buildURL = website + query + bbox + accessToken + token;
//
//        URL url = new URL(buildURL);
//        connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//    }
//
//
//    private void createJson(String response) {
//        try {
//            this.jsonObject = new JSONObject(response);
//        } catch (JSONException err) {
//            System.out.println("Invalid response.");
//        }
////        return this.jsonObject;
//    }
//
//
//    private String parseCoordinates(JSONObject json) throws JSONException {
//        JSONArray myObj;
//        myObj = this.jsonObject.getJSONArray("features");
//
//        JSONObject features;
//        features = myObj.getJSONObject(0);
//
//        JSONObject geometry;
//        geometry = features.getJSONObject("geometry");
//
//        JSONArray coordinates;
//        coordinates = geometry.getJSONArray("coordinates");
//        System.out.println("The coordinates of your site are: " + coordinates.toString());
//
//        return coordinates.toString();
//    }
//}
//
//
