package keyinclient.service;

import keyinclient.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AirportService {

    private final RestTemplate restTemplate;

    @Autowired
    public AirportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Airport> getAirportsUsedByPassenger(int passengerId) {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/airports/used-by-passenger/" + passengerId, Airport[].class));
    }

    public List<Airport> getAirportsForAircraft(int aircraftId) {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/airports/for-aircraft/" + aircraftId, Airport[].class));
    }
}
