package nl.tudelft.oopp.communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.List;

import nl.tudelft.oopp.MainApp;
import nl.tudelft.oopp.controllers.Hasher;

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
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ArrayList<Building> buildings = mapper.readValue(res, new TypeReference<ArrayList<Building>>(){});
        System.out.println("Hello is this working");

        return buildings;
    }

    /**
     * requests all types from the database.
     * @return a list of all types from the database
     * @throws URISyntaxException exception if URI syntax is wrong.
     * @throws IOException exception if input or output are wrong.
     * @author Scott.
     */
    public static ArrayList<Type> getTypes() throws URISyntaxException, IOException {
        String url = "http://localhost:8080/getListOfTypes";

        String res = request(url);
        System.out.println(res);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Type> types = mapper.readValue(res, new TypeReference<ArrayList<Type>>(){});

        return types;
    }

    /**
     * requests all rooms from the database.
     * @return a list of all rooms from the database
     * @throws URISyntaxException exception if URI syntax is wrong.
     * @throws IOException exception if input or output are wrong.
     */
    public static ArrayList<Room> getAllRooms() throws URISyntaxException, IOException {
        String url = "http://localhost:8080/getListOfRooms";

        String res = request(url);
        System.out.println(res);
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArrayList<Room> rooms = mapper.readValue(res, new TypeReference<ArrayList<Room>>(){});

        return rooms;
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

    /**
     * Creates a new bike reservation.
     * @author Sartori Kendra
     * @param buildingName - the name of the building from which a bike needs to be reserved
     * @param day - the date on which the bike will be reserved
     * @return -1 if the creating a reservation has failed and 1 otherwise
     * @throws URISyntaxException - thrown is URL is invalid
     */
    public static int createBikeReservation(String buildingName, Date day) throws URISyntaxException {
        String urlString = String.format("http://localhost:8080/addBikeReservation/%s/%s/%s",
                buildingName, day, MainApp.user.getUserId());
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

    /**
     * Function requesting a room reservation from the server.
     * @param roomId - room ID
     * @param buildingName - name of the building
     * @param day - date
     * @param startTime - starting time
     * @param endTime - ending time
     * @return int 1/-1 based on whether the request was succesful
     * @throws URISyntaxException - url exception
     */
    public static int createRoomReservation(int roomId, String buildingName, Date day, Time startTime, Time endTime) throws URISyntaxException {
        String urlString = String.format("http://localhost:8080/createNewReservation/%s/%s/%s/%s/%s/%s", roomId,
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

    /**
     *This method gets the number of bikes available.
     * @param buildingName the name of the building where the bike is.
     * @param day the day when the user wants to select a bike.
     * @return the number of bikes available near that building at that specific day.
     */
    public static int getNumberOfAvailableBikes(String buildingName, LocalDate day) throws URISyntaxException, IOException {
        String url = String.format("http://localhost:8080/availableBikesNumber/%s/%s", buildingName, day.toString());

        String res = request(url);
        System.out.println(res);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(res, new TypeReference<Integer>() {});
    }

    /**
     * This method communicates with the server and overrides a reservation.
     * @author Kanish Dwivedi
     * @param reservationID - the id of the reservation which is to be overriden
     * @param userID - the id of the user who will now be "owner" of this reservation
     * @return int. -1 if fail, 1 if success
     * @throws URISyntaxException - thrown if URL is invalid.
     */
    public static int overrideRoomReservation(int reservationID, int userID) throws URISyntaxException {
        String url = String.format("http://localhost:8080/overrideRoomReservation/%s/%s", reservationID, userID);
        URI uri = new URI(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).PUT(HttpRequest.BodyPublishers.ofString("")).build();

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



    /**
     * Request from the server whether the user has already reserved a room.
     * @author Hidde Agterberg
     * @param day - the day of the reservation
     * @param startTime - the starting time of the reservation
     * @param endTime - the end time of the reservation
     * @return shows if the user has already resereved a room at that time and day
     * @throws URISyntaxException - url exception
     */
    public static boolean hasReservation(Date day, Time startTime, Time endTime) throws URISyntaxException {
        String url = String.format("http://localhost:8080/hasReservation/%s/%s/%s/%s", MainApp.user.getUserId(),
                day, startTime, endTime);
        String res = request(url);
        return Boolean.valueOf(res);
    }

    /**
     * Request from the server whether the user already has a bike reservation on that day.
     * @author Sartori Kendra
     * @param day - the day on which we check for the reservation
     * @return true if the user already has a reservation and false otherwise
     * @throws URISyntaxException - url exception
     */
    public static boolean hasBikeReservation(Date day) throws  URISyntaxException {
        String url = String.format("http://localhost:8080/hasBikeReservation/%s/%s", day, MainApp.user.getUserId());
        String res = request(url);
        return Boolean.valueOf(res);
    }

    /**
     * This method requests the server to retrieve a list of all reservations made by a user.
     * @author Kanish Dwivedi
     * @param userID - the id of the user for which the reservations are to be retrieved from
     * @return - a list of UserReservationInfo objects that contain information on each reservation a user has made
     * @throws IOException - exception thrown if jackson mapping fails
     * @throws URISyntaxException - exception thrown if URL to interact with DB is invalid.
     */
    public static ArrayList<UserReservationInfo> getUserReservationInfo(int userID) throws IOException, URISyntaxException {
        String url = String.format("http://localhost:8080/getUserReservationInfo/%s", userID);
        String jsonRes = request(url);
        System.out.println("These are all the users reservations: " + jsonRes);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRes, new TypeReference<ArrayList<UserReservationInfo>>(){});

    }

    /**
     * This method gives a list of strings containing the bike reservations for a specific user.
     * @author Sartori Kendra
     * @return a list of reservations for a specific user
     * @throws IOException - thrown if mapping fails
     * @throws URISyntaxException - thrown if url is invalid
     */
    public static List<String> bikeReservationList() throws IOException, URISyntaxException {
        String url = String.format("http://localhost:8080/bikeReservationsForUser/%s", MainApp.user.getUserId());
        String res = request(url);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(res, new TypeReference<List<String>>() {});
    }

    /**
     * This method deletes a bike reservation from the database.
     * @author - Sartori Kendra
     * @param reservationID - the id of the reservation that needs to be deleted
     * @return -1 if fail and 1 if success
     * @throws URISyntaxException - exception thrown if a syntax error occurs
     */
    public static int deleteBikeReservation(int reservationID) throws URISyntaxException {
        String urlString = String.format("http://localhost:8080/deleteBikeReservation/%s", reservationID);
        URI url = new URI(urlString);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(url).header("Content-type", "application/json").DELETE().build();

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

    /**
     * This method adds a new todo item for the user by sending the required parameters to the server.
     * @param userID - the id that identifies the user for which the todo is to be added
     * @param title - the title of the todo
     * @param day - the day at which the todo is being added
     * @return boolean - true if successful, false otherwise
     * @throws URISyntaxException - exception thrown if URL to interact with DB is invalid.
     * @throws IOException - exception thrown if jackson mapping fails
     */

    public static boolean addNewTodo(int userID, String title, String day) throws URISyntaxException, IOException {
        String strUrl = String.format("http://localhost:8080/addNewTodo/%s/%s", userID, day);
        URI url = new URI(strUrl);

        //Setting up requestBody (JSON strings)
        HashMap<String, String> jsonValues = new HashMap<String, String>() {
            {
                put("title", title);
            }
        };
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(jsonValues);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(url).header("Content-type", "application/json").POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Boolean.parseBoolean(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method communicates with the server to retrieve a list of todos for a particular user.
     * @param userID - the user for which todos are to be returned
     * @return A list of UserTodo objects
     * @throws URISyntaxException - exception thrown if URL to interact with DB is invalid.
     * @throws IOException - exception thrown if jackson mapping fails
     */
    public static ArrayList<UserTodo> getUserTodoList(int userID) throws URISyntaxException, IOException {
        String url = String.format("http://localhost:8080/getAllTodos/%s", userID);
        String jsonRes = request(url);
        System.out.println("These are all the users todos: " + jsonRes);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRes, new TypeReference<ArrayList<UserTodo>>(){});
    }

    /**
     * A method which gets all the restaurants available in a given building.
     * @param buildingName the name of the building for which you want to find all available restaurants
     * @return A list of restaurants which are available in a building
     * @throws URISyntaxException - url exception
     * @throws IOException - input/output exception
     */
    public static ArrayList<Restaurant> getRestaurants(String buildingName) throws URISyntaxException, IOException {
        String url = String.format("http://localhost:8080/ListRestaurants/%s", buildingName);
        String jsonRes = request(url);
        System.out.println("These are all the restaurants: " + jsonRes);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRes, new TypeReference<ArrayList<Restaurant>>(){});
    }

    /**
     * A method which gets all the restaurants available in the DB.
     * @return A list of restaurants which are available in the DB
     * @throws URISyntaxException - url exception
     * @throws IOException - input/output exception
     */
    public static ArrayList<Restaurant> getAllRestaurants() throws URISyntaxException, IOException {
        String url = "http://localhost:8080/getAllRestaurants";
        String jsonRes = request(url);
        System.out.println("These are all the restaurants: " + jsonRes);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRes, new TypeReference<ArrayList<Restaurant>>(){});
    }

    /**
     * A method which gets all the food that a restaurant serves.
     * @param resId the id of the restaurant for which we want to get all available food
     * @return a list of food available in selected restaurant
     * @throws URISyntaxException - url exception
     * @throws IOException - input/ouput exception
     */
    public static ArrayList<Food> getFoods(int resId) throws URISyntaxException, IOException {
        String url = String.format("http://localhost:8080/getAllFoodForRestaurant/%s", resId);
        String jsonRes = request(url);
        System.out.println("These are all the food options for this restaurant: " + jsonRes);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRes, new TypeReference<ArrayList<Food>>(){});
    }

    /**
     * A method which adds a new food reservation in our DB.
     * @param foodId the id of the food that is ordered
     * @param restaurantId the restaurant in which that food is ordered
     * @param day the day for which that food is ordered
     * @param startTime the start time of the timeslot for which that food is ordered
     * @param endTime the end time of the timeslot for which that food is ordered
     * @return an int which is a status code that is used to check whether a food reservation has been
     *              successfully created
     * @throws URISyntaxException - url exception
     */
    public static int createFoodReservation(int foodId, int restaurantId, Date day, Time startTime, Time endTime) throws URISyntaxException {
        String urlString = String.format("http://localhost:8080//addFoodReservation/%s/%s/%s/%s/%s/%s", foodId,
                restaurantId, day, startTime, endTime, MainApp.user.getUserId());
        URI url = new URI(urlString);
        System.out.println(foodId);
        System.out.println(restaurantId);
        System.out.println(day);
        System.out.println(startTime);
        System.out.println(endTime);

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
    /**
     * creates a new building in the database.
     * @param buildingName the name of the building.
     * @param nonReservableSpace boolean indicating if building has non reservable space
     * @param carParkingSpaces the amount of car parking space
     * @param description a String giving the description of the building
     * @param opening the time the building opens
     * @param closing the time the building closes
     * @throws URISyntaxException url exception
     */
    
    public static void createBuilding(String buildingName, boolean nonReservableSpace, int carParkingSpaces, String description, Time opening, Time closing) throws URISyntaxException {
        String url = String.format("http://localhost:8080/addNewBuilding/%s/%s/%s/%s/%s/%s", buildingName, nonReservableSpace, carParkingSpaces, description, opening, closing);
        URI uri = new URI(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString("")).build();

        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Error code = " + response.statusCode());
        }
    }

    /**
     * sends message to the server to create a room in the database.
     * @param capacity the capacity of the room.
     * @param roomName the name of the room.
     * @param buildingName the building in which it is located.
     * @param type the type of the room.
     * @throws URISyntaxException exception if URI syntax is wrong.
     * @author Scott.
     */
    public static void createRoom(int capacity, String roomName, String buildingName, int type) throws URISyntaxException {
        String url = String.format("http://localhost:8080/addRoomToDB/%s/%s/%s/%s", capacity, roomName, buildingName, type);
        URI uri = new URI(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.ofString("")).build();

        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Error code = " + response.statusCode());
        }
    }

    /**
     * This method deletes a room reservation.
     * @author - Sartori Kendra
     * @param id - the id of the room that needs to be deleted
     * @return -1 if it fails, 1 if it succeeds
     * @throws URISyntaxException - exception thrown if the syntax is incorrect
     */
    public static int deleteRoomReservation(int id) throws URISyntaxException {
        String urlString = String.format("http://localhost:8080/deleteRoomReservation/%s", id);
        URI url = new URI(urlString);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(url).header("Content-type", "application/json").DELETE().build();

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

    /**
     * sends a request to the server to delete a building.
     * @param buildingName the name of the building
     * @throws URISyntaxException exception if URI syntax is wrong.
     * @author Scott.
     */
    public static void deleteBuilding(String buildingName) throws URISyntaxException {
        String url = String.format("http://localhost:8080/deleteBuilding/%s", buildingName);
        URI uri = new URI(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).DELETE().build();

        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Error code = " + response.statusCode());
        }
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Error code = " + response.statusCode());
        }
    }

    /**
     * requests to delete a room from the database.
     * @param roomId the id of the room to delete.
     * @throws URISyntaxException exception if URI syntax is wrong.
     */
    public static void deleteRoom(int roomId) throws URISyntaxException {
        String url = String.format("http://localhost:8080/deleteRoom/%s", roomId);
        URI uri = new URI(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).DELETE().build();

        //Sending HTTP Request and getting response
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Error code = " + response.statusCode());
        }
    }
}
