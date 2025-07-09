package bsu.edu.cs.InternetConnections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewConnectionTest {

    @Test
    public void createURLRequestTest() {
        ReviewConnection reviewConnectionInstance = new ReviewConnection();

        assertTrue(reviewConnectionInstance.createRequestUrl("ChIJgUulalN-3YgRGoTaWM2LawY")
                .matches("https://maps\\.googleapis\\.com/maps/api/place/details/json\\?place_id=ChIJgUulalN-3YgRGoTaWM2LawY&fields=name,rating,reviews&key=.+"));
    }

}
