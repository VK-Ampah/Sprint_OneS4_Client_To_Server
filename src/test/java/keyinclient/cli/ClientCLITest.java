package keyinclient.cli;

import keyinclient.client.RESTClient;
import keyinclient.model.Airport;
import keyinclient.model.Aircraft;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClientCLITest {

    private RESTClient restClientMock;
    private ClientCLI clientCLI;

    @BeforeEach
    public void setUp() {
        restClientMock = Mockito.mock(RESTClient.class);
        clientCLI = new ClientCLI();
        clientCLI.setRestClient(restClientMock);
    }

    @Test
    public void testGenerateAirportReport() {
        Airport airport = new Airport();
        airport.setName("John F. Kennedy International Airport");
        airport.setCode("JFK");

        when(restClientMock.getAllAirports()).thenReturn(List.of(airport));

        String report = clientCLI.generateAirportReport(1);
        verify(restClientMock).setServerURL(anyString());
        verify(restClientMock).getAllAirports();
        System.out.println(report);
    }

    @Test
    public void testListAircraftForPassenger() {
        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 737");

        when(restClientMock.getAllAircrafts()).thenReturn(List.of(aircraft));

        clientCLI.listAircraftForPassenger(1);
        verify(restClientMock).setServerURL(anyString());
        verify(restClientMock).getAllAircrafts();
    }
}
