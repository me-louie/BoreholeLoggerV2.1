package network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeolocationParser {
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return this.longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }


    public GeolocationParser(String response) throws JSONException, InvalidQueryException {
        parse(response);
    }

    private void parse(String response) throws JSONException, InvalidQueryException {
        try{
            JSONObject json = new JSONObject(response);
            System.out.println(json);
            JSONArray coordArr = json.getJSONArray("features").getJSONObject(0).getJSONArray("center");
            System.out.println(coordArr);
            this.longitude = coordArr.get(1).toString();
            this.latitude = coordArr.get(0).toString();

        } catch(JSONException e){
            e.printStackTrace();
            System.out.println("Invalid response string");
            throw new InvalidQueryException("Exception thrown hereeee");
        }

    }


}
