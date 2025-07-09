package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.GUI.SharedState;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.ResourcePathsConstants;
import bsu.edu.cs.Utils.UIConstants;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import java.util.Objects;

public class ThemeManager {
    private final Image sunIcon;
    private final Image moonIcon;
    private final SharedState sharedState;

    public ThemeManager(SharedState sharedState) {
        this.sharedState = sharedState;
        this.sunIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ResourcePathsConstants.SUN_ICON_PATH)));
        this.moonIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ResourcePathsConstants.MOON_ICON_PATH)));
    }

    public Button createThemeToggleButton(Scene scene) {
        ImageView toggleIcon = new ImageView(sunIcon);
        toggleIcon.setFitHeight(UIConstants.ICON_SIZE);
        toggleIcon.setFitWidth(UIConstants.ICON_SIZE);

        Button toggleThemeButton = new Button();
        toggleThemeButton.setGraphic(toggleIcon);
        toggleThemeButton.getStyleClass().add(CSSConstants.CLASS_TOGGLE_BUTTON);

        updateIcon(toggleIcon);

        sharedState.getSharedBooleanProperty().addListener((obs, oldVal, newVal) -> toggleTheme(scene, toggleIcon, newVal));
        toggleThemeButton.setOnAction(e -> sharedState.setAtomicBooleanValue(!sharedState.getAtomicBooleanValue()));

        return toggleThemeButton;
    }

    private void toggleTheme(Scene scene, ImageView toggleIcon, boolean isDarkMode) {
        String theme = isDarkMode ? ResourcePathsConstants.DARK_STYLE_PATH : ResourcePathsConstants.STYLE_PATH;
        scene.getStylesheets().setAll(Objects.requireNonNull(getClass().getResource(theme)).toExternalForm());
        updateIcon(toggleIcon);
    }

    private void updateIcon(ImageView toggleIcon) {
        if (sharedState.getSharedBooleanProperty().get()) {
            toggleIcon.setImage(moonIcon);
        } else {
            toggleIcon.setImage(sunIcon);
        }
    }
}