package network;

import org.json.JSONException;

public class GeolocationManager {

    private String longitude;
    private String latitude;

    private SiteMap siteMap;

    public GeolocationManager(String query) throws JSONException, InvalidQueryException {
        GeolocationQuery locationQuery = new GeolocationQuery(query);
        String response = locationQuery.getResponse();
        GeolocationParser geoParser = new GeolocationParser(response);

        this.latitude = geoParser.getLatitude();
        this.longitude = geoParser.getLongitude();
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }
}
