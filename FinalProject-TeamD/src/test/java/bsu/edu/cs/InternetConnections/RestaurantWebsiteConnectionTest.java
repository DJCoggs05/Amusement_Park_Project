package bsu.edu.cs.InternetConnections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestaurantWebsiteConnectionTest {

    @Test
    public void createURLRequestTest(){
        String placeID = "ChIJVVVVFbNi54gRtxun5fsZ9eg";
        RestaurantWebsiteConnection restaurantWebsiteConnection = new RestaurantWebsiteConnection();
        assertTrue(restaurantWebsiteConnection.createRequestUrl(placeID).matches("https://maps\\.googleapis\\.com/maps/api/place/details/json\\?place_id=ChIJVVVVFbNi54gRtxun5fsZ9eg&fields=website&key=.+"));
    }

}
