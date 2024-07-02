package keyinclient.service;

import keyinclient.model.Airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CityService {

    private final RestTemplate restTemplate;

    @Autowired
    public CityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Airport> getAirportsInCity(int cityId) {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/cities/" + cityId + "/airports", Airport[].class));
    }
}
