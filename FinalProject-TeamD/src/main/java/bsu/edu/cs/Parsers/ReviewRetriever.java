package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.ParkPlaceIDConnection;
import bsu.edu.cs.InternetConnections.ReviewConnection;
import bsu.edu.cs.InternetConnections.RidePositionConnection;

public class ReviewRetriever {

    private final ParkPlaceIDConnection placeIDConnectionInstance = new ParkPlaceIDConnection();
    private final ReviewConnection reviewConnectionInstance = new ReviewConnection();

    private final RidePositionConnection ridePositionConnectionInstance = new RidePositionConnection();

    public ReviewInformation getReviewInformation(Park park) throws networkErrorException, openInputStreamException, noItemFoundException {

        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(placeIDConnectionInstance.search(park)));
        String placeID = placeIDParserInstance.parse();

        ReviewParser reviewParserInstance = new ReviewParser(new ApiInputStream(reviewConnectionInstance.search(placeID)));

        return reviewParserInstance.parse();
    }

    public ReviewInformation getReviewInformation(RideSearch rideReviewSearch) throws networkErrorException, openInputStreamException, noItemFoundException {
        //We use the RidePositionConnection class because we can get the placeID from where we grab the RidePosition
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(ridePositionConnectionInstance.search(rideReviewSearch)));
        String placeID = placeIDParserInstance.parse();

        ReviewParser reviewParserInstance = new ReviewParser(new ApiInputStream(reviewConnectionInstance.search(placeID)));

        return reviewParserInstance.parse();
    }

}
