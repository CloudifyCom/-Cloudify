package com.cloudify.beans;

import com.cloudify.entities.WeatherDelayPrediction;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class WeatherAndDelayServiceZrno {


    public WeatherDelayPrediction getWeatherAndDelayPrediction(String flightId, WeatherDelayPrediction originPrediction, WeatherDelayPrediction destinationPrediction, String finalDestination, String originDestination) {

        double originDelay = Double.parseDouble(originPrediction.getDelayProbability().replace("%", ""));
        double destinationDelay = Double.parseDouble(destinationPrediction.getDelayProbability().replace("%", ""));
        double finalProbabilityOfDelay = (originDelay + destinationDelay) / 2;

        WeatherDelayPrediction prediction = new WeatherDelayPrediction();
        prediction.setFlightId(flightId);
        prediction.setWeatherForecast(originPrediction.getWeatherForecast());
        prediction.setDelayProbability(originPrediction.getDelayProbability());
        prediction.setFinalDestination(finalDestination);
        prediction.setOriginDestination(originDestination);
        prediction.setFinalDestinationWeatherForecast(destinationPrediction.getWeatherForecast());
        prediction.setFinalDestinationDelayProbability(destinationPrediction.getDelayProbability());
        prediction.setWindSpeed(originPrediction.getWindSpeed());
        prediction.setVisibility(originPrediction.getVisibility());
        prediction.setHumidity(originPrediction.getHumidity());
        prediction.setTemperature(originPrediction.getTemperature());
        prediction.setFinalProbabilityOfDelay(String.format("%.0f%%", finalProbabilityOfDelay));

        return prediction;

    }
}
