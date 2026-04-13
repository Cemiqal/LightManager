package weather;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class WeatherService {

   public LocalTime fetchSunset(){
      HttpRequest request = HttpRequest.newBuilder()
              .GET()
              .uri(URI.create("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&daily=sunset&timezone=Europe%2FBerlin&forecast_days=1")
              ).build();

      try (HttpClient client = HttpClient.newBuilder()
              .version(HttpClient.Version.HTTP_1_1)
              .build()) {
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         return parseToTime(response.body());
      } catch (IOException | InterruptedException e) {
         System.out.println("Something went wrong executing HttpRequest: " + e.getMessage());
      }
      return null;
   }

   private LocalTime parseToTime(String body){
      JSONObject root = new JSONObject(body);
      JSONObject daily = root.getJSONObject("daily");
      return LocalDateTime.parse(daily.getJSONArray("sunset").getString(0)).toLocalTime();
   }

   public long getTimeUntilSunset(TimeUnit timeUnit){
      long delay = LocalTime.now().until(fetchSunset(), timeUnit.toChronoUnit());
      if(delay < 0) delay = 0;
      return delay;
   }
}
