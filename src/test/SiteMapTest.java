package test;

import network.SiteMap;
import org.junit.jupiter.api.Test;

public class SiteMapTest {

    @Test
    void testConstructor() {
        SiteMap siteMap = new SiteMap("currMap", "-123.136337", "49.134889");
    }
}
