package keyinclient.client;

import keyinclient.model.Airport;
import keyinclient.model.Aircraft;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class RESTClientTest {

    private ClientAndServer mockServer;
    private RESTClient restClient;

    @BeforeEach
    public void setUp() {
        mockServer = startClientAndServer(1080);
        restClient = new RESTClient();
    }

    @AfterEach
    public void tearDown() {
        mockServer.stop();
    }

    @Test
    public void testGetAllAirports() throws JsonProcessingException {
        restClient.setServerURL("http://localhost:1080/airports");

        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/airports")
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody("[{\"id\":1,\"name\":\"John F. Kennedy International Airport\",\"code\":\"JFK\"}]")
        );

        List<Airport> airports = restClient.getAllAirports();
        assertEquals(1, airports.size());
        assertEquals("John F. Kennedy International Airport", airports.get(0).getName());
    }

    @Test
    public void testGetAllAircrafts() throws JsonProcessingException {
        restClient.setServerURL("http://localhost:1080/aircrafts");

        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/aircrafts")
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody("[{\"id\":1,\"type\":\"Boeing 737\",\"airlineName\":\"Delta\",\"numberOfPassengers\":160}]")
        );

        List<Aircraft> aircrafts = restClient.getAllAircrafts();
        assertEquals(1, aircrafts.size());
        assertEquals("Boeing 737", aircrafts.get(0).getType());
    }
}

