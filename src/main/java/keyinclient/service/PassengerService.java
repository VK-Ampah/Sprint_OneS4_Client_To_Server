package keyinclient.service;


import keyinclient.model.Aircraft;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PassengerService {

    private final RestTemplate restTemplate;

    @Autowired
    public PassengerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Aircraft> getAircraftsForPassenger(int passengerId) {
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/passengers/" + passengerId + "/aircrafts", Aircraft[].class));
    }
}

