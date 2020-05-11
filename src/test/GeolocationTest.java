package test;

import network.GeolocationQuery;
import exceptions.InvalidQueryException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeolocationTest {

    @BeforeEach
    void setup(){

    }

    @Test
    void testConstructor() throws JSONException, InvalidQueryException {
        GeolocationQuery geolocation = new GeolocationQuery("4968 Beamish Court");
    }
}
