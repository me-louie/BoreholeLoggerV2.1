package network;

import exceptions.InvalidQueryException;
import model.Coordinates;
import org.json.JSONException;

public class GeolocationManager {



    private Coordinates coordinates;
    private SiteMap siteMap;

    public GeolocationManager(String query) throws JSONException, InvalidQueryException {
        GeolocationQuery locationQuery = new GeolocationQuery(query);
        String response = locationQuery.getResponse();
        GeolocationParser geoParser = new GeolocationParser(response);

        this.coordinates = new Coordinates(geoParser.getLatitude(), geoParser.getLongitude());
//        this.coordinates.setLat(geoParser.getLatitude());
//        this.coordinates.setLon(geoParser.getLongitude());
    }

    public String getLongitude() {
        return this.coordinates.getLon();
    }

    public String getLatitude() {
        return this.coordinates.getLat();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
