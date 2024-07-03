package keyinclient.cli;

import keyinclient.client.RESTClient;
import keyinclient.model.Aircraft;
import keyinclient.model.Airport;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Component
public class ClientCLI implements CommandLineRunner {
    private RESTClient restClient;

    public String generateAirportReport(int cityId) {
        getRestClient().setServerURL("http://localhost:8080/cities/" + cityId + "/airports");
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuilder report = new StringBuilder();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append(" - ");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(",");
            }
        }

        System.out.println(report.toString());

        return report.toString();
    }

    void listAircraftForPassenger(int passengerId) {
        getRestClient().setServerURL("http://localhost:8080/passengers/" + passengerId + "/aircrafts");
        List<Aircraft> aircrafts = getRestClient().getAllAircrafts();
        aircrafts.forEach(aircraft -> System.out.println(aircraft.getType()));
    }

    private void listAirportsUsedByPassenger(int passengerId) {
        getRestClient().setServerURL("http://localhost:8080/airports/used-by-passenger/" + passengerId);
        List<Airport> airports = getRestClient().getAllAirports();
        airports.forEach(airport -> System.out.println(airport.getName()));
    }

    private void listAirportsForAircraft(int aircraftId) {
        getRestClient().setServerURL("http://localhost:8080/airports/for-aircraft/" + aircraftId);
        List<Airport> airports = getRestClient().getAllAirports();
        airports.forEach(airport -> System.out.println(airport.getName()));
    }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -jar client.jar <command> <id>");
            return;
        }

        String command = args[0];
        int id = Integer.parseInt(args[1]);

        switch (command) {
            case "airports-in-city":
                generateAirportReport(id);
                break;
            case "aircrafts-for-passenger":
                listAircraftForPassenger(id);
                break;
            case "airports-used-by-passenger":
                listAirportsUsedByPassenger(id);
                break;
            case "airports-for-aircraft":
                listAirportsForAircraft(id);
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    public static void main(String[] args) {
        ClientCLI cliApp = new ClientCLI();
        try {
            cliApp.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
