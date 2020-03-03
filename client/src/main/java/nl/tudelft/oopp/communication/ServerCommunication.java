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

    /**.
     * Check login credentials with server
     * @param userName username of user
     * @param password password of user
     * @return true if username and password match
     * @throws URISyntaxException exception if syntax is wrong
     */
    public static boolean identifyUser(String userName, String password) throws URISyntaxException {
        String requestUrl = "";
        requestUrl = requestUrl + "/" + userName + ":" + password;

        return (boolean) request(requestUrl);
    }

    /**.
     * Create a new user in the database
     * TODO: check if email and username are not already in use
     * @param username username of user
     * @param email email of the user
     * @param password password of the user
     * @return true if user is saved successful
     * @throws URISyntaxException exception is syntax is wrong
     */
    public static boolean createUser(String username, String email, String password)
                                        throws URISyntaxException {
        String url = String.format("http://localhost:8080/createUser/%s/%s/%s",
                username, email, password);

        return (boolean) request(url);
    }

    /**.
     * Request the rooms available (with a certain query)
     * @param date the date at the time of calling function
     * @param building building to get rooms from
     * @param timeFrom the starting time for the room
     * @param timeTo the end time for the room
     * @param roomType the type of room
     * @return a list of rooms matching the description
     * @throws URISyntaxException exception if syntax is incorrect
     */
    public static ArrayList<Room> getRooms(LocalDate date, String building,
                                            String timeFrom, String timeTo, String roomType)
            throws URISyntaxException, IOException {

        String url = String.format("http://localhost:8080/getAvailableRooms/%s/%s/%s:00/%s:00",
                building, date.toString(), timeFrom, timeTo);

        //String res = (String)request(url);

        InputStream res = ServerCommunication.class.getResourceAsStream("/rooms_test.json");

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(res, new TypeReference<ArrayList<Room>>(){});
    }

    /**.
     * Get all buildings
     * @return a list of all buildings stored in the database
     * @throws URISyntaxException exception if syntax is incorrect
     * @throws IOException exception if input or output is incorrect
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

    /**.
     * Request function template
     * TODO: Test that this function works for all cases
     * @param urlStr the url that has to be turned into a request
     * @return object that is retrieved through the request
     * @throws URISyntaxException exception if syntax is incorrect
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
