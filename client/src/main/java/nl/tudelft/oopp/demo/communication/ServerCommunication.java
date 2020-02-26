package nl.tudelft.oopp.demo.communication;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     *
     * @param username
     * @param email
     * @param password
     * @return
     * @throws URISyntaxException
     * TODO check if email and username are not already in use
     */
    public static boolean createUser(String username, String email, String password) throws URISyntaxException {
        String url = "http://localhost:8080/createUser";
        url += "/" + username + ":" + email + ":" + password;

        return (boolean) request(url);
    }

    /**
     *
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
     *
     * @param date
     * @param building
     * @param timeslot
     * @param roomType
     * @return
     * @throws URISyntaxException
     */
    public static String[] getRooms(LocalDate date, String building, String timeslot, String roomType) throws URISyntaxException {
        String url = "http://localhost:8080/getRooms/";
        url += date.toString() + ":" + building + ":" + timeslot + ":" + roomType;

        return (String[]) request(url);
    }

    /**
     *
     * @param urlStr
     * @return
     * @throws URISyntaxException
     * TODO test that this function works for all cases
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
        return true;
    }

}
