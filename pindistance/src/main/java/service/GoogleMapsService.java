package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class GoogleMapsService {
    
    @Value("${google.api.key}")
    private String apiKey;
    
    private final RestTemplate restTemplate = new RestTemplate();

    @Cacheable("distanceCache")
    public JsonNode getRouteData(String fromPincode, String toPincode) {
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + fromPincode 
                     + "&destination=" + toPincode + "&key=" + apiKey;
        
        return restTemplate.getForObject(url, JsonNode.class);
    }
}
