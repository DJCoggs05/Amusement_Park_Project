package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.GUIModel;
import bsu.edu.cs.GUI.MapManager;
import bsu.edu.cs.GUI.SharedState;
import bsu.edu.cs.InternetConnections.RestaurantWebsiteConnection;
import bsu.edu.cs.InternetConnections.ReviewConnection;
import bsu.edu.cs.Parsers.*;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.TextConstants;
import bsu.edu.cs.Utils.UIConstants;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class  RidesAndRestaurantComponent {

    private final GUIModel controller;
    private final MapManager mapManager;
    private final Alert errorPopUp;
    private final ReviewsComponent reviewsComponent;
    private Park selectedPark;
    private ListView<Ride> ridesList;
    private VBox container;
    private ListView<Restaurant> restaurantListView;


    public RidesAndRestaurantComponent(GUIModel controller, MapManager mapManager, Alert errorPopUp, SharedState sharedState) {
        this.controller = controller;
        this.mapManager = mapManager;
        this.errorPopUp = errorPopUp;
        this.reviewsComponent = new ReviewsComponent(sharedState);
    }

    public Node createRidesListComponent() {
        container = new VBox(UIConstants.PADDING);
        container.getStyleClass().add(CSSConstants.CLASS_RIDES_LIST_COMPONENT);

        ridesList = new ListView<>();
        ridesList.getStyleClass().add(CSSConstants.CLASS_RIDES_CONTAINER);
        VBox.setVgrow(ridesList, Priority.ALWAYS);
        restaurantListView = new ListView<>();
        VBox.setVgrow(restaurantListView, Priority.ALWAYS);

        Button ridesButton = new Button(TextConstants.RIDE_SUFFIX);
        Button restaurantsButton = new Button(TextConstants.RESTAURANTS_TEXT);
        ridesButton.getStyleClass().add(CSSConstants.CLASS_SIDEBAR_BUTTON);
        restaurantsButton.getStyleClass().add(CSSConstants.CLASS_SIDEBAR_BUTTON);

        HBox buttonContainer = new HBox(ridesButton, restaurantsButton);
        buttonContainer.getStyleClass().add(CSSConstants.CLASS_TOGGLE_BUTTON_CONTAINER);


        ridesButton.setOnAction(event -> {
            container.getChildren().removeAll(ridesList, restaurantListView);
            container.getChildren().add(ridesList);
            ridesButton.getStyleClass().add(CSSConstants.CLASS_TOGGLE_BUTTON_ACTIVE);
            restaurantsButton.getStyleClass().remove(CSSConstants.CLASS_TOGGLE_BUTTON_ACTIVE);

            if (selectedPark != null) {
                loadRides(selectedPark);
            }
        });

        restaurantsButton.setOnAction(event -> {
            container.getChildren().removeAll(ridesList, restaurantListView);
            container.getChildren().add(restaurantListView);
            restaurantsButton.getStyleClass().add(CSSConstants.CLASS_TOGGLE_BUTTON_ACTIVE);
            ridesButton.getStyleClass().remove(CSSConstants.CLASS_TOGGLE_BUTTON_ACTIVE);

            if (selectedPark != null) {
                loadRestaurants(selectedPark);
            }
        });
        ridesButton.getStyleClass().add(CSSConstants.CLASS_TOGGLE_BUTTON_ACTIVE);
        container.getChildren().addAll(buttonContainer, ridesList);

        return container;
    }

    public void loadSelectedParkData(Park park) {
        this.selectedPark = park;
        if (container.getChildren().contains(ridesList)) {
            loadRides(park);
        } else if (container.getChildren().contains(restaurantListView)) {
            loadRestaurants(park);
        }
    }

    private void loadRides(Park park) {
        try {
            List<Ride> rideList = controller.fetchRides(park);

            if (rideList.isEmpty()) {
                rideList.add(new Ride(0, TextConstants.NO_RIDE_INFO, false, 0));
            }

            ridesList.setItems(FXCollections.observableArrayList(rideList));
            applyRidesCellFactory();

        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
            ridesList.setItems(FXCollections.observableArrayList(new Ride(0, TextConstants.ERROR_RETRIEVING_RIDES, false, 0)));
        }
    }

    private void applyRidesCellFactory() {
        ridesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    mapManager.addMarker(newValue);
                } catch (noItemFoundException | networkErrorException | openInputStreamException e) {
                    mapManager.alert();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ridesList.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Ride ride, boolean empty) {
                super.updateItem(ride, empty);
                setStyle("");
                if (empty || ride == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    getStyleClass().add(CSSConstants.CLASS_BLACK);
                    VBox rideInfoBox = new VBox(10);
                    rideInfoBox.getStyleClass().add(CSSConstants.CLASS_SIDEBAR_CONTAINER);

                    Label nameLabel = new Label(ride.getName());
                    nameLabel.setMaxWidth(200);
                    nameLabel.setWrapText(true);
                    nameLabel.getStyleClass().addAll(CSSConstants.CLASS_RIDE_NAME, CSSConstants.CLASS_CLICKABLE_LABEL);

                    VBox detailsBox = new VBox(5);
                    Label waitTimeLabel = new Label();

                    if (ride.getIsOpen()) {
                        waitTimeLabel.setText(controller.convertMinToHours(ride.getWaitTime()));
                        waitTimeLabel.getStyleClass().addAll(
                                controller.getWaitTimeColor(ride.getWaitTime()),
                                CSSConstants.CLASS_WAIT_TIME
                        );
                    }

                    Label statusLabel = new Label(ride.getIsOpen() ? TextConstants.OPEN_STATUS : TextConstants.CLOSED_STATUS);
                    statusLabel.getStyleClass().add(
                            ride.getIsOpen() ? CSSConstants.CLASS_STATUS_OPEN : CSSConstants.CLASS_STATUS_CLOSED
                    );

                    Button viewReviewsButton = new Button(TextConstants.RIDE_REVIEWS);
                    viewReviewsButton.getStyleClass().add(CSSConstants.CLASS_REVIEWS_BUTTON);

                    viewReviewsButton.setOnAction(event -> {
                        try {
                            ReviewInformation reviews = getReviewsForRide(ride, selectedPark);
                            reviewsComponent.showReviewsPopup(ride.getName(), reviews);
                        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                            errorPopUp.setContentText(TextConstants.NO_REVIEW_FOUND);
                            errorPopUp.showAndWait();
                        }
                    });

                    detailsBox.getChildren().addAll(waitTimeLabel, statusLabel);
                    rideInfoBox.getChildren().addAll(nameLabel, detailsBox, viewReviewsButton);
                    setGraphic(rideInfoBox);
                }
            }
        });
    }
    private void loadRestaurants(Park park) {
        try {
            List<Restaurant> restaurantList = controller.fetchRestaurants(park);

            if (restaurantList.isEmpty()) {
                restaurantList.add(new Restaurant(TextConstants.NO_RESTAURANT_INFO, new Coordinates(TextConstants.ZERO_TEXT, TextConstants.ZERO_TEXT), UIConstants.ZERO_DECIMAL_VALUE,UIConstants.ZERO_DECIMAL_VALUE, TextConstants.EMPTY_STRING, TextConstants.EMPTY_STRING));
            }
            restaurantListView.setItems(FXCollections.observableArrayList(restaurantList));
            applyRestaurantCellFactory();
        }catch (networkErrorException | openInputStreamException | noItemFoundException e) {
            restaurantListView.setItems(FXCollections.observableArrayList(new Restaurant(TextConstants.ERROR_RETRIEVING_RESTAURANT, new Coordinates(TextConstants.ZERO_TEXT, TextConstants.ZERO_TEXT), UIConstants.ZERO_DECIMAL_VALUE,UIConstants.ZERO_DECIMAL_VALUE, TextConstants.EMPTY_STRING, TextConstants.EMPTY_STRING)));
        }
    }
    private void applyRestaurantCellFactory() {
        restaurantListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    mapManager.addRestaurantMarker(newValue);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        restaurantListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Restaurant restaurant, boolean empty) {
                super.updateItem(restaurant, empty);
                setStyle(TextConstants.EMPTY_STRING);

                if (empty || restaurant == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    getStyleClass().add(CSSConstants.CLASS_BLACK);
                    VBox restaurantBox = new VBox(UIConstants.PADDING);
                    restaurantBox.getStyleClass().add(CSSConstants.CLASS_SIDEBAR_CONTAINER);

                    Label nameLabel = new Label(restaurant.getName());
                    nameLabel.setMaxWidth(UIConstants.NAME_LABEL_WIDTH);
                    nameLabel.setWrapText(true);
                    nameLabel.getStyleClass().addAll(CSSConstants.CLASS_RESTAURANT_NAME, CSSConstants.CLASS_CLICKABLE_LABEL);

                    Label ratingLabel = new Label(TextConstants.AVERAGE_RATING_TEXT + String.format(TextConstants.ONE_DECIMAL_PLACE, restaurant.getRating()) + TextConstants.RATING_SUFFIX);
                    ratingLabel.getStyleClass().add(CSSConstants.CLASS_RESTAURANT_RATING);

                    Label priceLabel = new Label(TextConstants.PRICE_TEXT + (restaurant.getPriceLevel() != null ? TextConstants.DOLLAR_Sign.repeat(restaurant.getPriceLevel().intValue()) : TextConstants.NO_PRICE_LEVEL_TEXT));
                    priceLabel.getStyleClass().add(CSSConstants.CLASS_RESTAURANT_PRICE);

                    Button viewReviewsButton = new Button(TextConstants.RIDE_REVIEWS);
                    viewReviewsButton.getStyleClass().add(CSSConstants.CLASS_REVIEWS_BUTTON);

                    viewReviewsButton.setOnAction(event -> {
                        try {
                            ReviewInformation reviews = getReviewsForRestaurant(restaurant.getPlaceID());
                            reviewsComponent.showReviewsPopup(restaurant.getName(), reviews);
                        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
                            errorPopUp.setContentText(TextConstants.NO_REVIEW_FOUND);
                            errorPopUp.showAndWait();
                        }
                    });

                    Button viewWebsiteButton = new Button(TextConstants.RIDE_WEBSITE);
                    viewWebsiteButton.getStyleClass().add(CSSConstants.CLASS_REVIEWS_BUTTON);

                    viewWebsiteButton.setOnAction(event -> {
                        try {
                            String url = fetchWebsite(restaurant.getPlaceID());
                            Desktop desktop = Desktop.getDesktop();
                            desktop.browse(new URI(url));
                        } catch (networkErrorException | openInputStreamException | noItemFoundException| IOException | URISyntaxException e) {
                            errorPopUp.setContentText(TextConstants.WEBSITE_ERROR);
                            errorPopUp.showAndWait();
                        }
                    });

                    HBox buttonBox = new HBox(UIConstants.MEDIUM_SPACING);
                    buttonBox.setAlignment(Pos.CENTER);
                    buttonBox.getChildren().addAll(viewReviewsButton, viewWebsiteButton);

                    HBox.setHgrow(viewReviewsButton, Priority.ALWAYS);
                    HBox.setHgrow(viewWebsiteButton, Priority.ALWAYS);
                    viewReviewsButton.setMaxWidth(Double.MAX_VALUE);
                    viewWebsiteButton.setMaxWidth(Double.MAX_VALUE);

                    restaurantBox.getChildren().addAll(nameLabel, ratingLabel, priceLabel, buttonBox);
                    setGraphic(restaurantBox);
                }
            }
        });
    }

    private ReviewInformation getReviewsForRide(Ride ride, Park park) throws noItemFoundException, networkErrorException, openInputStreamException {
        ReviewRetriever reviewRetriever = new ReviewRetriever();
        return reviewRetriever.getReviewInformation(new RideSearch(ride.getName(), park));
    }

    private ReviewInformation getReviewsForRestaurant(String placeID) throws networkErrorException, openInputStreamException, noItemFoundException {
        ReviewConnection reviewConnection = new ReviewConnection();
        ReviewParser reviewParser = new ReviewParser(new ApiInputStream(reviewConnection.search(placeID)));
        return reviewParser.parse();
    }

    private String fetchWebsite(String placeID) throws networkErrorException, openInputStreamException, noItemFoundException {
        RestaurantWebsiteConnection restaurantWebsiteConnection = new RestaurantWebsiteConnection();
        RestaurantWebsiteParser restaurantWebsiteParser = new RestaurantWebsiteParser(new ApiInputStream(restaurantWebsiteConnection.search(placeID)));
        return restaurantWebsiteParser.parse();
    }
}
