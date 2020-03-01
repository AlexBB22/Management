package nl.tudelft.oopp.communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Check login credentials with server
     * @param userName
     * @param password
     * @return
     * @throws URISyntaxException
     */
    public static boolean identifyUser(String userName, String password) throws URISyntaxException {
        String requestUrl = "";
        requestUrl = requestUrl + "/" + userName + ":" + password;

        return (boolean) request(requestUrl);
    }

    /**
     * Create a new user in the database
     * TODO: check if email and username are not already in use
     * @param username
     * @param email
     * @param password
     * @return
     * @throws URISyntaxException
     */
    public static boolean createUser(String username, String email, String password)
                                        throws URISyntaxException {
        String url = String.format("http://localhost:8080/createUser/%s/%s/%s",
                username, email, password);

        return (boolean) request(url);
    }

    /**
     * Request the rooms available (with a certain query)
     * @param date
     * @param building
     * @param timeFrom
     * @param timeTo
     * @param roomType
     * @return
     * @throws URISyntaxException
     */
    public static ArrayList<Room> getRooms(LocalDate date, String building,
                                            String timeFrom, String timeTo, String roomType)
            throws URISyntaxException, IOException {

        String url = String.format("http://localhost:8080/getAvailableRooms/%s/%s/%s:00/%s:00",
                building, date.toString(), timeFrom, timeTo);

        //String res = (String)request(url);

        InputStream res = ServerCommunication.class.getResourceAsStream("/rooms_test.json");

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Room> rooms = mapper.readValue(res, new TypeReference<ArrayList<Room>>(){});

        return rooms;
    }

    /**
     * Get all buildings
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static ArrayList<Building> getBuildings() throws URISyntaxException, IOException {
        String url = "http://localhost:8080/buildings/All";

        //String res = (String)request(url);
        InputStream res = ServerCommunication.class.getResourceAsStream("/buildings_test.json");
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Building> buildings = mapper.readValue(res,
                new TypeReference<ArrayList<Building>>(){});

        return buildings;
    }

    /**
     * Request function template
     * TODO: Test that this function works for all cases
     * @param urlStr
     * @return
     * @throws URISyntaxException
     */
    public static Object request(String urlStr) throws URISyntaxException {
        URI url = new URI(urlStr);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(url).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (response.statusCode() != 200) {
            return false;
        }
        return response;
    }

}
