package bsu.edu.cs.Parsers;

import java.util.List;

public class ReviewInformation {

    private final double rating;
    private final List<Review> listOfReviews;

    public ReviewInformation(double rating, List<Review> listOfReviews){
        this.rating = rating;
        this.listOfReviews = listOfReviews;
    }

    public double getRating(){
        return rating;
    }

    public List<Review> getListOfReviews(){
        return listOfReviews;
    }

}
