package nl.tudelft.oopp.demo.communication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    // TODO check if email and username are not already in use
    public static boolean createUser(String username, String email, String password) throws URISyntaxException {
        String requestURL = "http://localhost:8080/createUser" +
                                "/" + username + ":" + email + ":" + password;

        return (boolean) request(requestURL);
    }

    public static boolean identifyUser(String userName, String password) throws URISyntaxException {
        String requestUrl = "";
        requestUrl = requestUrl + "/" + userName + ":" + password;

        return (boolean) request(requestUrl);
    }

    public static String[] getRooms(LocalDate date, String building, String timeslot, String roomType) throws URISyntaxException {
        String url = "http://localhost:8080/getRooms/" +
                        date.toString() + ":" + building + ":" + timeslot + ":" + roomType;

        return (String[]) request(url);
    }

    // TODO test that this function works for all cases
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
