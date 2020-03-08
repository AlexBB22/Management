package nl.tudelft.oopp.communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.controllers.Hasher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Check login credentials with server.
     * @author Kanish Dwivedi
     * @param userName - the username given from the user
     * @param password - the password given from the user
     * @return - Null if the user was not verified. The actual user entity from the DB if the user is verified
     * @throws URISyntaxException - If the request URL is invalid
     **/
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
     * Create a new user in the database.
     * TODO: check if email and username are not already in use
     * @author Kanish Dwivedi
     * @param username - the userName of the new user which is to be added
     * @param email - the email of the new user which is to be added
     * @param password - the password of the new user which is to be added
     * @return int. if int = -1, then userName already exists, if int != -1, user was created successfully
     * @throws URISyntaxException - Error thrown when request URL is invalid
     */
    public static int createUser(String username, String email, String password, int roleId)
            throws URISyntaxException, IOException {
        //hashing password
        String hashedPassword = Hasher.hashPassword(password);
        //Setting up requestBody (JSON strings)
        HashMap<String, String> jsonValues = new HashMap<String, String>() {
            {
                put("email", email);
                put("user_name", username);
                put("user_password", hashedPassword);
            }
        };
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


    /**
     * This method is responsible to communicate with the database to retrieve a list of overrideable rooms based
     * on the roleID. It then also using JackSon to map the resulting JSON string to OverridableRoom objects.
     * @author Kanish Dwivedi
     * @param buildingName - the name of the building in which the room exists
     * @param day - the day at which the rooms are reserved
     * @param startTime - the startime at which the rooms are reserved
     * @param endTime - the endtime at which the rooms are reserved upto
     * @param roleId - the roleID represents which rooms we want to get. If roleID = 1, we want to get
     *               all rooms that have already been reserved by Students.
     * @return OverridableRoom - returns a list of rooms that this user can override.
     * @throws URISyntaxException - The error returned if the URL has invalid syntax.
     * @throws IOException - The error returned if the communication fails, or Object mapping fails by JackSon.
     */
    public static ArrayList<OverridableRoom> getOnlyOverridableRooms(String buildingName, LocalDate day, String startTime, String endTime,
                                                             int roleId) throws URISyntaxException, IOException {
        String url = String.format("http://localhost:8080/getOnlyOverridableRooms/%s/%s/%s/%s/%s",
                buildingName, day.toString(), startTime, endTime, roleId);
        String res = request(url);
        System.out.println("These are overridable rooms:" + res);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(res, new TypeReference<ArrayList<OverridableRoom>>(){});
    }

    /**
     * This method is responsible to communicate with the database to retrieve a list of available rooms based
     * on the roleID. It then also using JackSon to map the resulting JSON string to OverridableRoom objects.
     * @author Kanish Dwivedi
     * @param buildingName - the name of the building in which the room exists
     * @param day - the day at which we want to find available rooms
     * @param startTime - the startime at which the rooms are available from
     * @param endTime - the endtime at which the rooms are available upto
     * @return AvailableRoom - returns a list of available rooms that this user can reserve
     * @throws URISyntaxException - The error returned if the URL has invalid syntax.
     * @throws IOException - The error returned if the communication fails, or Object mapping fails by JackSon.
     */
    public static ArrayList<AvailableRoom> getOnlyAvailableRooms(String buildingName, LocalDate day, String startTime, String endTime)
            throws URISyntaxException, IOException {
        String url = String.format("http://localhost:8080/getOnlyAvailableRooms/%s/%s/%s/%s",
                buildingName, day.toString(), startTime, endTime);
        String jsonRes = request(url);
        System.out.println("These are available rooms: " + jsonRes);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonRes, new TypeReference<ArrayList<AvailableRoom>>(){});
    }


    /**
     * Request the rooms available (with a certain query).
     * @param date - the day at which the user wants to reserve a room
     * @param building - the building at which the user wants to reserve a room
     * @param timeFrom - the startTime at which the user wants to reserve a room
     * @param timeTo - the endTime at which the user wants to reserve a room
     * @param roomType - the roomType at which the user wants to reserve a room
     * @return List of Rooms that are available for the given specifications
     * @throws URISyntaxException - Exception thrown when request URL made is invalid.
     */
    public static ArrayList<Room> getRooms(LocalDate date, String building,
                                            String timeFrom, String timeTo, String roomType)
            throws URISyntaxException, IOException {

        String url = String.format("http://localhost:8080/getAvailableRooms/%s/%s/%s:00/%s:00/%s",
                building, date.toString(), timeFrom, timeTo, MainApp.user.getUserId());

        String res = request(url);
        System.out.println(res);

        //InputStream res = ServerCommunication.class.getResourceAsStream("/rooms_test.json");

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(res, new TypeReference<ArrayList<Room>>(){});
    }

    /**
     * Get all buildings.
     * @return List of buildings that are available to be selected from.
     * @throws URISyntaxException - Exception thrown when request URL is invalid
     * @throws IOException - Exception thrown when JSON mapping is unsuccessful
     */
    public static ArrayList<Building> getBuildings() throws URISyntaxException, IOException {
        String url = "http://localhost:8080/buildings/All";

        String res = request(url);
        System.out.println(res);
        //InputStream res = ServerCommunication.class.getResourceAsStream("/buildings_test.json");
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Building> buildings = mapper.readValue(res, new TypeReference<ArrayList<Building>>(){});

        return buildings;
    }

    /**
     * Get request function template.
     * TODO: Test that this function works for all cases
     * @param urlStr the url that has to be turned into a request
     * @return object that is retrieved through the request
     * @throws URISyntaxException exception if syntax is incorrect
     */
    public static String request(String urlStr) throws URISyntaxException {
        URI url = new URI(urlStr);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(url).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.body();
    }

    public static int createRoomReservation(int roomId, String buildingName, Date day, Time startTime, Time endTime) throws URISyntaxException {
        String urlString = String.format("http://localhost:8080/createNewReservation/%s/%s/%s/%s:00/%s:00/%s", roomId,
                buildingName, day, startTime, endTime, MainApp.user.getUserId());
        URI url = new URI(urlString);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(url).header("Content-type", "application/json").POST(HttpRequest.BodyPublishers.ofString("")).build();

        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        if (response.statusCode() != 200) {
            System.out.println("Error code = " + response.statusCode());
            return -1;
        }
        return 1;
    }
}
