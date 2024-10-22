package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import entity.Route;
import repository.RouteRepository;

@Service
public class RouteService {
    
    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private RouteRepository routeRepository;

    public Route getDistanceAndDuration(String fromPincode, String toPincode) {
        // Check cache/database first
        Route route = routeRepository.findByFromPincodeAndToPincode(fromPincode, toPincode);
        if (route != null) {
            return route;
        }

        // If not found, fetch from Google Maps
        JsonNode routeData = googleMapsService.getRouteData(fromPincode, toPincode);
        if (routeData != null && routeData.has("routes")) {
            double distance = routeData.get("routes").get(0).get("legs").get(0).get("distance").get("value").asDouble();
            String duration = routeData.get("routes").get(0).get("legs").get(0).get("duration").get("text").asText();
            String routeDetails = routeData.toString();

            // Save to the database
            route = new Route();
            route.setFromPincode(fromPincode);
            route.setToPincode(toPincode);
            route.setDistance(distance);
            route.setDuration(duration);
            route.setRouteDetails(routeDetails);
            
            routeRepository.save(route);
        }

        return route;
    }
}

