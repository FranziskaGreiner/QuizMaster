import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuizMaster {

    private static final String API_URL = "https://quizapi.io/api/v1/questions";
    private static final String API_KEY = "78Kd7bqZLyRKpUbVoxSSnSzmzq2lM6IrGTTaXACW";


        public static void main(String[] args) throws Exception {
            HttpClient client = HttpClient.newHttpClient();
            String url = API_URL + "?apiKey=" + API_KEY + "&limit=10";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        }
}
