package test;

import network.Geolocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;

public class GeolocationTest {

    @BeforeEach
    void setup(){

    }

    @Test
    void testConstructor(){
        Geolocation geolocation = new Geolocation("4968 Beamish Court");
    }
}
