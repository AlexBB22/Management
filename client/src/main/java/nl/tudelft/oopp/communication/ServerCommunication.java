package nl.tudelft.oopp.communication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.controllers.Hasher;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Check login credentials with server
     * @param userName
     * @param password
     * @return
     * @throws URISyntaxException
     */
    public static User identifyUser(String userName, String password) throws URISyntaxException {
        String hashedPassword = Hasher.hashPassword(password);

        String requestUrl = String.format("http://localhost:8080/identifyMe/%s/%s", userName, hashedPassword);
        URI url = new URI(requestUrl);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(url).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            return null;
        }
        //This code converts the JSON string into a Java Object
        String jsonUser = response.body();

        //We check if the server sent back no user. In this case the verification failed. Thus, we return null.
        if (jsonUser.equals("")) {
            return null;
        }
        //If we get till this point in code, the user was authenticated, all we need to do now is to get the values
        //of the user that the server sent back and save them
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(jsonUser);
        User authenticatedUser = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonUser);

            int userId = jsonNode.get("user_id").asInt();
            String email = jsonNode.get("email").asText();
            int roleid = jsonNode.get("role").get("role_id").asInt();
            String roleName = jsonNode.get("role").get("role_name").asText();

            Role userRole = new Role(roleid, roleName);
            authenticatedUser = new User(userId, email, userName, password, userRole);
        } catch (IOException e) {
            System.out.println(e);
        }
        return authenticatedUser;
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
    public static int createUser(String username, String email, String password, int roleId)
            throws URISyntaxException, IOException {
        //hashing password
        String hashedPassword = Hasher.hashPassword(password);
        //Setting up requestBody (JSON strings)
        HashMap<String, String> jsonValues = new HashMap<String, String>() {{
            put("email", email);
            put("user_name", username);
            put("user_password", hashedPassword);
        }};
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(jsonValues);

        //Setting up URL
        String urlStr = String.format("http://localhost:8080/addUser/%s", roleId);
        URI url = new URI(urlStr);
        //Setting up HTTP request
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(url).header("Content-type", "application/json").POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        if (response.statusCode() != 200) {
            return -1;
        }
        //server sends back the id of the user in the response body
        int userId = Integer.parseInt(response.body());

        //1st check if the userId that was returned is -1, if it is, we need to tell the client to retry
        if (userId == -1) {
            return -1;
        }
        return userId;
    }

    public static User getUserInformation() {
        return null;
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

}
