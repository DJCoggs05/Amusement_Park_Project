package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Parsers.Weather;
import bsu.edu.cs.Utils.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WeatherComponent {
    public HBox createWeatherDisplay(Weather weather) {
        Label temperatureLabel = new Label(weather.getTemperature() + TextConstants.TEMPERATURE_UNIT);
        Label humidityLabel = new Label(weather.getHumidity() + TextConstants.HUMIDITY_UNIT);
        Label windSpeedLabel = new Label(weather.getWindSpeed() + TextConstants.WIND_SPEED_UNIT);

        ImageView weatherIcon = new ImageView(new Image(ResourcePathsConstants.WEATHER_ICON_URL_PREFIX + weather.getIconID() + ResourcePathsConstants.WEATHER_ICON_URL_SUFFIX));
        ImageView humidityIcon = new ImageView(new Image(ResourcePathsConstants.HUMIDITY_ICON_PATH));
        ImageView windIcon = new ImageView(new Image(ResourcePathsConstants.WIND_ICON_PATH));

        humidityIcon.setFitWidth(UIConstants.WEATHER_ICON_SIZE);
        humidityIcon.setFitHeight(UIConstants.WEATHER_ICON_SIZE);
        windIcon.setFitWidth(UIConstants.WEATHER_ICON_SIZE);
        windIcon.setFitHeight(UIConstants.WEATHER_ICON_SIZE);
        weatherIcon.setFitHeight(UIConstants.WEATHER_ICON_SIZE);
        weatherIcon.setFitWidth(UIConstants.WEATHER_ICON_SIZE);

        HBox humidityDetails = new HBox(UIConstants.SMALL_SPACING);
        humidityDetails.getChildren().addAll(humidityIcon, humidityLabel);

        HBox windDetails = new HBox(UIConstants.SMALL_SPACING);
        windDetails.getChildren().addAll(windIcon, windSpeedLabel);

        HBox temperatureDetails = new HBox(UIConstants.SMALL_SPACING);
        temperatureDetails.getChildren().addAll(weatherIcon, temperatureLabel);

        temperatureLabel.getStyleClass().addAll(CSSConstants.CLASS_WHITE, CSSConstants.CLASS_TEMP);
        humidityLabel.getStyleClass().addAll(CSSConstants.CLASS_WHITE,  CSSConstants.CLASS_TEMP);
        windSpeedLabel.getStyleClass().addAll(CSSConstants.CLASS_WHITE,  CSSConstants.CLASS_TEMP);

        HBox weatherDetails = new HBox(UIConstants.PADDING_LARGE);
        weatherDetails.getChildren().addAll(temperatureDetails, humidityDetails, windDetails);
        weatherDetails.getStyleClass().add(CSSConstants.CLASS_WEATHER_CONTAINER);


        return weatherDetails;

    }
}
