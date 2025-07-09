package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.GUIModel;
import bsu.edu.cs.GUI.MapManager;
import bsu.edu.cs.GUI.SharedState;
import bsu.edu.cs.Parsers.Park;
import bsu.edu.cs.Parsers.ReviewInformation;
import bsu.edu.cs.Parsers.ReviewRetriever;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.TextConstants;
import bsu.edu.cs.Utils.UIConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

public class ParksListComponent extends VBox {
    private final ReviewRetriever reviewRetriever = new ReviewRetriever();
    private ReviewInformation reviews;
    private final ListView<String> parksList;
    private final TextField searchBar;
    public ParksListComponent(ReviewsComponent reviewsComponent, Map<String, Park> parksMap, Alert errorPopUp,
                              Label parkTitle, VBox ridesAndRestaurantControl, VBox mainContent,
                              WeatherComponent weatherComponent, MapManager mapManager,
                              Button viewReviewsButton, SharedState sharedState) {

        this.setSpacing(UIConstants.PADDING);
        this.setPadding(new Insets(UIConstants.PADDING));
        this.getStyleClass().add(CSSConstants.CLASS_SIDEBAR);

        GUIModel controller = new GUIModel();
        RidesAndRestaurantComponent ridesAndRestaurantComponent = new RidesAndRestaurantComponent(controller, mapManager, errorPopUp, sharedState);
        VBox rideToggleSection = (VBox) ridesAndRestaurantComponent.createRidesListComponent();
        VBox.setVgrow(rideToggleSection, Priority.ALWAYS);

        searchBar = new TextField();
        searchBar.setPromptText(TextConstants.SEARCH_PROMPT);

        parksList = new ListView<>();
        ObservableList<String> sortedList = FXCollections.observableArrayList(parksMap.keySet());
        Collections.sort(sortedList);
        parksList.getItems().addAll(sortedList);

        searchBar.setOnKeyTyped(event -> {
            String searchText = searchBar.getText().toLowerCase();
            ObservableList<String> filteredList = FXCollections.observableArrayList();
            for (String park : parksMap.keySet()) {
                if (park.toLowerCase().startsWith(searchText)) {
                    filteredList.add(park);
                }
            }
            parksList.getSelectionModel().clearSelection();
            Collections.sort(filteredList);
            parksList.setItems(filteredList);
        });

        Hyperlink contributionLink = new Hyperlink(TextConstants.QUEUE_TIMES_TEXT);
        contributionLink.setOnAction(actionEvent -> {
            try {
                Desktop.getDesktop().browse(new URI(TextConstants.QUEUE_TIMES_URL));
            } catch (Exception e) {
                errorPopUp.setContentText(TextConstants.LINK_ERROR);
                errorPopUp.showAndWait();
            }
        });

        parksList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String park, boolean empty) {
                super.updateItem(park, empty);
                if (empty || park == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(park);
                    getStyleClass().add(CSSConstants.CLASS_PARKS_CONTAINER);
                }
            }
        });
        ridesAndRestaurantControl.getChildren().add(rideToggleSection);
        this.getChildren().addAll(searchBar, parksList, contributionLink);

        parksList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.equals(oldValue)) {
                Park selectedPark = parksMap.get(newValue);

                viewReviewsButton.setOnAction(event -> {
                    try {
                        reviews = getReviewsForPark(selectedPark);
                        reviewsComponent.showReviewsPopup(newValue, reviews);
                    } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                        errorPopUp.setContentText(TextConstants.NO_REVIEW_FOUND);
                        errorPopUp.showAndWait();
                    }
                });
                parkTitle.setText(newValue + TextConstants.RIDE_SUFFIX);

                try {
                    mapManager.createMap(selectedPark);
                    mainContent.getChildren().remove(1);
                    HBox weatherUpdated = weatherComponent.createWeatherDisplay(controller.getWeather(selectedPark.getLatitude(), selectedPark.getLongitude()));
                    weatherUpdated.setMaxWidth(UIConstants.WEATHER_MAX_WIDTH);
                    weatherUpdated.setMaxHeight(UIConstants.WEATHER_MAX_HEIGHT);
                    weatherUpdated.getStyleClass().add(CSSConstants.CLASS_WEATHER_CONTAINER);
                    mainContent.getChildren().add(1, weatherUpdated);
                } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                    errorPopUp.setHeaderText(TextConstants.ERROR_RETRIEVING_PARKS);
                    errorPopUp.setContentText(TextConstants.NETWORK_ERROR);
                    errorPopUp.showAndWait();
                }
                ridesAndRestaurantComponent.loadSelectedParkData(selectedPark);
            }
        });
    }

    private ReviewInformation getReviewsForPark(Park park) throws noItemFoundException, networkErrorException, openInputStreamException {
        return reviewRetriever.getReviewInformation(park);
    }
}
