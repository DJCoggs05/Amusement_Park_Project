package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.GUI.SharedState;
import bsu.edu.cs.Parsers.ReviewInformation;
import bsu.edu.cs.Parsers.Review;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.ResourcePathsConstants;
import bsu.edu.cs.Utils.TextConstants;
import bsu.edu.cs.Utils.UIConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class ReviewsComponent {
    private Scene reviewScene;
    private Stage reviewStage;
    private final SharedState sharedState;

    public ReviewsComponent(SharedState sharedState) {
        this.reviewScene = null;
        this.reviewStage = null;
        this.sharedState = sharedState;
    }
    public void showReviewsPopup(String parkName, ReviewInformation reviewInformation) {
        reviewStage = new Stage();
        reviewStage.setTitle(TextConstants.REVIEWS_FOR_TEXT + parkName);

        VBox mainLayout = new VBox(UIConstants.REVIEW_SPACING);
        mainLayout.setPadding(new Insets(UIConstants.PADDING_LARGE));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getStyleClass().add(CSSConstants.CLASS_REVIEW_POPUP);

        Label parkTitleLabel = new Label(parkName + TextConstants.REVIEWS_TEXT);
        parkTitleLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_TITLE);
        mainLayout.getChildren().add(parkTitleLabel);

        if (reviewInformation != null && reviewInformation.getListOfReviews() != null && !reviewInformation.getListOfReviews().isEmpty()) {
            Label ratingLabel = new Label(TextConstants.AVERAGE_RATING_TEXT + String.format(TextConstants.TWO_DECIMAL_PLACE, reviewInformation.getRating()) + TextConstants.RATING_SUFFIX);
            ratingLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_RATING);
            mainLayout.getChildren().add(ratingLabel);

            VBox reviewsContainer = new VBox(UIConstants.PADDING);
            reviewsContainer.setPadding(new Insets(UIConstants.PADDING));

            for (Review review : reviewInformation.getListOfReviews()) {
                HBox reviewBox = new HBox(UIConstants.REVIEW_SPACING);
                reviewBox.setPadding(new Insets(UIConstants.PADDING));
                reviewBox.getStyleClass().add(CSSConstants.CLASS_REVIEW_BOX);
                reviewBox.setAlignment(Pos.TOP_LEFT);

                ImageView profileImageView = new ImageView(new Image(review.profilePhotoURL(), UIConstants.PROFILE_IMAGE_SIZE, UIConstants.PROFILE_IMAGE_SIZE, true, true));
                profileImageView.getStyleClass().add(CSSConstants.CLASS_PROFILE_IMAGE);

                VBox reviewDetails = new VBox(UIConstants.PADDING);
                reviewDetails.setAlignment(Pos.TOP_LEFT);

                Label authorLabel = new Label(review.authorName());
                authorLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_AUTHOR);

                Label timeLabel = new Label(review.relativeTimeDescription());
                timeLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_TIME);

                Label reviewTextLabel = new Label(review.text());
                reviewTextLabel.setWrapText(true);
                reviewTextLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_REVIEW_TEXT);

                Label reviewRatingLabel = new Label(TextConstants.RATING_TEXT + review.rating() + TextConstants.RATING_SUFFIX);
                reviewRatingLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_REVIEW_RATING);

                reviewDetails.getChildren().addAll(authorLabel, timeLabel, reviewTextLabel, reviewRatingLabel);
                reviewBox.getChildren().addAll(profileImageView, reviewDetails);

                reviewsContainer.getChildren().add(reviewBox);
                reviewsContainer.getStyleClass().add(CSSConstants.CLASS_REVIEW_CONTAINER);
            }
            ScrollPane scrollPane = new ScrollPane(reviewsContainer);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add(CSSConstants.CLASS_SCROLL_PANE);
            mainLayout.getChildren().add(scrollPane);
        } else {
            Label noReviewsLabel = new Label(TextConstants.NO_REVIEWS_AVAILABLE);
            noReviewsLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_NO_REVIEWS);
            mainLayout.getChildren().add(noReviewsLabel);
        }

        reviewScene = new Scene(mainLayout, UIConstants.REVIEW_MAX_WIDTH, UIConstants.REVIEW_MAX_HEIGHT);
        reviewScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(ResourcePathsConstants.STYLE_PATH)).toExternalForm());
        reviewStage.setScene(reviewScene);
        applyTheme();
        reviewStage.show();
    }

    private void applyTheme() {
        if (reviewScene == null) {
            return;
        }
        boolean isDarkMode = sharedState.getSharedBooleanProperty().get();
        String theme = isDarkMode ? ResourcePathsConstants.DARK_STYLE_PATH : ResourcePathsConstants.STYLE_PATH;
        reviewScene.getStylesheets().setAll(Objects.requireNonNull(getClass().getResource(theme)).toExternalForm());
    }
}