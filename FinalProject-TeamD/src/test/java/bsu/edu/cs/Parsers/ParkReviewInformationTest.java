package bsu.edu.cs.Parsers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkReviewInformationTest {

    @Test
    public void getParkReviewInformationRatingTest(){
        ReviewInformation testParkReviewInformation = new ReviewInformation(3.5,
                new ArrayList<>(List.of(new Review("Ian Campbell",
                        "https://lh3.googleusercontent.com/a-/ALV-UjUiz9Jw14IvbkueKKL2i4LHn6jcTwoXH_OHGBE5Wa0qYvUkPKOBkQ=s128-c0x00000000-cc-rp-mo-ba5", 5,
                        "a month ago", "I spent my honeymoon at Disney World (with park hopper) and I would absolutely say that Magic Kingdom is the most magical and my favorite park! Yes it can get crowded. We went mid January and it was perfect walking weather. Mid 50s and 60s Fahrenheit. The rides here are so much fun from People Mover, Pirates of the Caribbean app the way to Tea Cups and Barn Stormer. You can find a handful of restaurants for a little sit down lunch. There’s gift shops galore on Main Street. The castle is breathtaking and the theme every corner of the way is so detailed and pretty. Parades and fireworks are definitely worth watching a couple times! We got lucky enough to have a character dinner in the castle and though the wait was little long to get in with a reservation of course, it was so worth it! Had an absolute great time. Spent maybe like 2 full days here in total and I still feel like I need to explore more!"))));
        assertEquals(3.5,testParkReviewInformation.getRating());
    }
}
