package nl.tudelft.oopp.demo.communication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    // TODO check if email and username are not already in use
    public static boolean createUser(String username, String email, String password) throws URISyntaxException {
        String requestURL = "http://localhost:8080/createUser";
        requestURL += "/" + username + ":" + email + ":" + password;

        URI url = new URI(requestURL);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(url).build();
        HttpResponse<String> response = null;
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

    public static boolean identifyUser(String userName, String password) throws MalformedURLException, URISyntaxException {
        String requestUrl = "http://localhost:8080/identifyMe";
        requestUrl = requestUrl + "/" + userName + ":" + password;
        System.out.println(requestUrl);

        URI url = new URI(requestUrl);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(url).build();
        HttpResponse<String> response = null;
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
