package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.RidePositionConnection;
import bsu.edu.cs.Parsers.*;
import bsu.edu.cs.Utils.AlertConstants;
import bsu.edu.cs.Utils.TextConstants;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;

import java.io.IOException;
import java.util.Map;


public class MapManager {

    private final WebEngine webEngine;
    private final RidePositionConnection ridePositionConnection = new RidePositionConnection();
    private final Alert noRideFound = new Alert(Alert.AlertType.ERROR);
    private final Map<String,Park> parksMap;
    private final GUIModel controller;
    private String currentPark;

    public MapManager(WebEngine webEngine, Map<String,Park> parkMap, GUIModel controller) {
        this.webEngine = webEngine;
        this.parksMap = parkMap;
        this.controller = controller;

        noRideFound.setTitle(AlertConstants.ERROR_TITLE);
        noRideFound.getDialogPane().getStyleClass().add(AlertConstants.ALERT_CLASS);
    }

    public void createMap(Park park) throws noItemFoundException, networkErrorException, openInputStreamException {
        currentPark = park.getName();
        webEngine.executeScript(String.format("setMapView(%s, %s, 18)", park.getLatitude(), park.getLongitude()));
    }

    public void addMarker(Ride ride) throws noItemFoundException, networkErrorException, openInputStreamException, IOException {
        RidePositionParser ridePositionParser = new RidePositionParser(new ApiInputStream(ridePositionConnection.search(new RideSearch(ride.getName(),parksMap.get(currentPark)))));
        Coordinates coordinates = ridePositionParser.parse();

        if (coordinates != null) {
            String convertedImageUrl = controller.getPhotoURl(coordinates.getPhotoReference()).replace("\\", "\\\\").replace("'", "\\'");
            String convertedRideName = ride.getName().replace("'", "\\'");
            webEngine.executeScript(String.format("addMarker(%s, %s,%d,'%s', '%s', %b, '%s')", coordinates.getLatitude(), coordinates.getLongitude(),ride.getId(), convertedRideName, ride.getWaitTime(), ride.getIsOpen(), convertedImageUrl));
            webEngine.executeScript(String.format("setMapView(%s, %s, 17)", coordinates.getLatitude(), coordinates.getLongitude()));
        }else{
            alert();
        }
    }
    public void addRestaurantMarker(Restaurant restaurant) throws IOException {
        Coordinates coordinates = restaurant.getCoordinates();
        if (coordinates != null) {
            String convertedImageUrl = controller.getPhotoURl(restaurant.getPhotoReferenceID()).replace("'", "\\'");
            String convertedRideName = restaurant.getName().replace("\\", "\\\\").replace("'", "\\'");
            webEngine.executeScript(String.format(
                    "addRestaurantMarker(%s, %s, '%s', '%s')",
                    coordinates.getLatitude(),
                    coordinates.getLongitude(),
                    convertedRideName,
                    convertedImageUrl
            ));
            webEngine.executeScript(String.format("setMapView(%s, %s, 17)", coordinates.getLatitude(), coordinates.getLongitude()));
        } else {
            alert();
        }
    }
    public void alert(){
        noRideFound.setContentText(TextConstants.NO_RIDE_FOUND);
        noRideFound.showAndWait();
    }
}
